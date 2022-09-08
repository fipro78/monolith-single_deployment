package org.fipro.client.ui.offline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.fipro.client.ui.edef.EdefImporter;
import org.fipro.service.modifier.StringModifier;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;

/**
 * Implementation of an online/offline switch service.
 * It uses low-level OSGi API to enable/disable the OSGi services in the local runtime.
 * It uses the static Endpoint Description Extender Format (EDEF) as discovery instead of a dynamic mechanism like JmDNS.
 * 
 * Via this approach the Eclipse Part can directly inject the StringModifier services as the available services are handled here.
 */
@Component(service = OnlineModeSwitch.class)
public class OnlineModeSwitch {

	private AtomicBoolean online = new AtomicBoolean(false);
	
	@Reference
	ServiceComponentRuntime runtime;
	
	@Reference(target = "(!(service.imported=*))")
	volatile List<ServiceReference<StringModifier>> localStringModifier;
	
	ArrayList<ComponentDescriptionDTO> disabledDTOs = new ArrayList<>();
	
	@Reference
	EdefImporter importer;
	
	// this is a programmatic way of handling an online/offline switch
	// the declarative way would be to change the target reference property via
	// ConfigAdmin and consume the services in the Eclipse 4 part via this wrapper
	// service instead consuming them directly
	
	public void toogleOnlineState() {
		if (online.compareAndSet(false, true)) {
			// first disable the local services
			this.localStringModifier.forEach(ref -> {
				String name = (String) ref.getProperty("component.name");
				ComponentDescriptionDTO dto = this.runtime.getComponentDescriptionDTO(ref.getBundle(), name);
				this.disabledDTOs.add(dto);
				this.runtime.disableComponent(dto);
			});
			
			// then start the import of the remote services
			this.importer.importModifierServices();
			
		} else {
			online.set(false);
			
			// first close the imported remote services
			this.importer.closeModifierImports();
			
			// then enable the local services
			this.disabledDTOs.forEach(dto -> {
				this.runtime.enableComponent(dto);
			});
		}
	}
	
	public boolean isOnline() {
		return this.online.get();
	}
}
