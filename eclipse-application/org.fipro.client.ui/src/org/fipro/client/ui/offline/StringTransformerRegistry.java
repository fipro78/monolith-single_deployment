package org.fipro.client.ui.offline;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.fipro.client.ui.edef.EdefImporter;
import org.fipro.service.transformer.StringTransformer;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 * Registry that stores the available StringModifier services based on the
 * target service property configuration. It only references the services that
 * match the target service property, either imported via jaxrs distribution
 * provider or local if the service.intents property is not jaxrs.
 * 
 * It uses the static file based Endpoint Description Extender Format (EDEF) as
 * discovery instead of a dynamic mechanism like JmDNS, but the static importer
 * approach could be easily exchanged with a dynamic discovery.
 */
@Component(service = StringTransformerRegistry.class)
public class StringTransformerRegistry {

	private static final String TARGET_PROPERTY_KEY = "stringTransformer.target";
	private static final String IMPORTED_FILTER = "(service.imported=*)";
	public static final String UPDATE_EVENT_TOPIC = "org/fipro/client/ui/UPDATE";
	
	@Reference
	ConfigurationAdmin configAdmin;
	
	@Reference
	EventAdmin eventAdmin;
	
	@Reference
	EdefImporter importer;

	// we use method injection instead of field injection as we want to update the UI whenever a StringTransformer is bound/unbound
	
	final List<StringTransformer> stringTransformer = new CopyOnWriteArrayList<>();
	
	@Reference(
			name = "stringTransformer",
			cardinality = ReferenceCardinality.MULTIPLE,
			policy = ReferencePolicy.DYNAMIC,
			target = "(!" + IMPORTED_FILTER + ")")
	void registerStringTransformer(StringTransformer modifier) {
		this.stringTransformer.add(modifier);
		
		// send an event
        Event event = new Event(UPDATE_EVENT_TOPIC, new HashMap<>());
        eventAdmin.postEvent(event);
	}

	void unregisterStringTransformer(StringTransformer modifier) {
		this.stringTransformer.remove(modifier);
		
		// send an event
        Event event = new Event(UPDATE_EVENT_TOPIC, new HashMap<>());
        eventAdmin.postEvent(event);
	}
	
	@Activate
	void activate(Map<String, Object> properties) {
		if (isOnline(properties.get(TARGET_PROPERTY_KEY)) && !importer.isModifierImportActive()) {
			// if the configuration is set to online but the EDEF importer is not active we
			// trigger the import for consistent state
			importer.importTransformerServices();
		}
	}
	
	@Modified
	void modified() {
		// we need the modified method to avoid that the component instance is deactivated/activated on configuration changes 
	}
	
	public void toogleOnlineState() {
		try {
			// get the configuration of the StringTransformerRegistry
			Configuration config = configAdmin.getConfiguration("org.fipro.client.ui.offline.StringTransformerRegistry");
			
			Dictionary<String, Object> props = null;
			Object target = null;
			if (config != null && config.getProperties() != null) {
				props = config.getProperties();
				target = props.get(TARGET_PROPERTY_KEY);
			} else {
				props = new Hashtable<>();
			}
			
			boolean online = isOnline(target);
			if (!online) {
				// import the services via EDEF
				// actually just needed the first time to load the services, if another discovery would be used, this would not even be necessary here
				importer.importTransformerServices();
				// change the filter to consume the online services
				props.put(TARGET_PROPERTY_KEY, IMPORTED_FILTER);
			} else {
				// change the filter to consume the offline services
				props.put(TARGET_PROPERTY_KEY, "(!" + IMPORTED_FILTER + ")");
			}
			
			// toggle the state
			config.update(props);
		} catch (IOException ex) {
			System.err.println("Failed to update the online/offline service consumption configuration.");
			ex.printStackTrace();
		}
	}
	
	public List<StringTransformer> getStringTransformer() {
		return this.stringTransformer;
	}
	
	public boolean isOnline() {
		try {
			// get the configuration of the StringModifierRegistry
			Configuration config = configAdmin.getConfiguration("org.fipro.client.ui.offline.StringTransformerRegistry");
			
			Dictionary<String, Object> props = null;
			Object target = null;
			if (config != null && config.getProperties() != null) {
				props = config.getProperties();
				target = props.get(TARGET_PROPERTY_KEY);
			} else {
				props = new Hashtable<>();
			}
			
			return isOnline(target);
		} catch (IOException ex) {
			System.err.println("Failed to access the online/offline service consumption configuration.");
			ex.printStackTrace();
		}
		
		return false;
	}

	private boolean isOnline(Object targetPropertyValue) {
		return (IMPORTED_FILTER.equals(targetPropertyValue));
	}
}
