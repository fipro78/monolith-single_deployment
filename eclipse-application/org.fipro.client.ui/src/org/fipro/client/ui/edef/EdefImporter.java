package org.fipro.client.ui.edef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.remoteserviceadmin.EndpointDescription;
import org.osgi.service.remoteserviceadmin.ImportRegistration;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdmin;

@Component(service = EdefImporter.class)
public class EdefImporter {

	@Reference
	RemoteServiceAdmin admin;
	
	ArrayList<ImportRegistration> modifierRegistrations = new ArrayList<>();
	ArrayList<ImportRegistration> transformerRegistrations = new ArrayList<>();

	ImportRegistration dataManagerRegistration;
	
	public void importModifierServices() {
		// avoid multiple import executions
		if (!isModifierImportActive()) {
			Map<String, Object> properties = createModifierServiceProperties("http://localhost:8282/inverter", 1l);
			EndpointDescription desc = new EndpointDescription(properties);
			this.modifierRegistrations.add(admin.importService(desc));
			
			properties = createModifierServiceProperties("http://localhost:8282/uppercase", 2l);
			desc = new EndpointDescription(properties);
			this.modifierRegistrations.add(admin.importService(desc));
			
			properties = createModifierServiceProperties("http://localhost:8282/camelcase", 3l);
			desc = new EndpointDescription(properties);
			this.modifierRegistrations.add(admin.importService(desc));
		}
	}
	
	public void importTransformerServices() {
		// avoid multiple import executions
		if (!isTransformerImportActive()) {
			Map<String, Object> properties = createTransformerServiceProperties("http://localhost:8282/transform/invert", 10l);
			EndpointDescription desc = new EndpointDescription(properties);
			this.transformerRegistrations.add(admin.importService(desc));
			
			properties = createTransformerServiceProperties("http://localhost:8282/transform/upper", 11l);
			desc = new EndpointDescription(properties);
			this.transformerRegistrations.add(admin.importService(desc));
			
			properties = createTransformerServiceProperties("http://localhost:8282/transform/camel", 12l);
			desc = new EndpointDescription(properties);
			this.transformerRegistrations.add(admin.importService(desc));
		}
	}
	
	public void importDataManagerService() {
		// avoid multiple import executions
		if (!isDataManagerImportActive()) {
			Map<String, Object> properties = createDataManagerServiceProperties("http://localhost:8282/data", 42l);
			EndpointDescription desc = new EndpointDescription(properties);
			this.dataManagerRegistration = admin.importService(desc);
		}
	}

	/**
	 * 
	 * @param serviceURL The publishing URL of the service used as ecf.endpoint.id
	 * @param rsvcId A unique ID that is needed to distinguish multiple imported services. Does not need to match the values of an export.
	 * @return
	 */
	private Map<String, Object> createModifierServiceProperties(String serviceURL, long rsvcId) {
		Map<String, Object> properties = createServiceProperties(serviceURL, rsvcId);
		
		properties.put("endpoint.package.version.org.fipro.service.modifier", "1.0.0");
		properties.put("objectClass", new String[] { "org.fipro.service.modifier.StringModifier" });
		
		return properties;
	}
	
	/**
	 * 
	 * @param serviceURL The publishing URL of the service used as ecf.endpoint.id
	 * @param rsvcId A unique ID that is needed to distinguish multiple imported services. Does not need to match the values of an export.
	 * @return
	 */
	private Map<String, Object> createTransformerServiceProperties(String serviceURL, long rsvcId) {
		Map<String, Object> properties = createServiceProperties(serviceURL, rsvcId);
		
		properties.put("endpoint.package.version.org.fipro.service.transformer", "1.0.0");
		properties.put("objectClass", new String[] { "org.fipro.service.transformer.StringTransformer" });
		
		return properties;
	}
	
	/**
	 * 
	 * @param serviceURL The publishing URL of the service used as ecf.endpoint.id
	 * @param rsvcId A unique ID that is needed to distinguish multiple imported services. Does not need to match the values of an export.
	 * @return
	 */
	private Map<String, Object> createDataManagerServiceProperties(String serviceURL, long rsvcId) {
		Map<String, Object> properties = createServiceProperties(serviceURL, rsvcId);
		
		properties.put("endpoint.package.version.org.fipro.service.data", "1.0.0");
		properties.put("objectClass", new String[] { "org.fipro.service.data.DataManager" });
		
		return properties;
	}
	
	/**
	 * 
	 * @param serviceURL The publishing URL of the service used as ecf.endpoint.id
	 * @param rsvcId A unique ID that is needed to distinguish multiple imported services. Does not need to match the values of an export.
	 * @return
	 */
	private Map<String, Object> createServiceProperties(String serviceURL, long rsvcId) {
		Map<String, Object> properties = new HashMap<>();
		
		properties.put("ecf.endpoint.id", serviceURL);
		properties.put("ecf.endpoint.id.ns", "ecf.namespace.jakartars");
		properties.put("ecf.rsvc.id", rsvcId);
		properties.put("endpoint.id", "" + rsvcId);
		properties.put("endpoint.framework.uuid", "0");
		properties.put("remote.configs.supported", new String[] { "ecf.jakartars.jersey.server" });
		properties.put("remote.intents.supported", new String[] { "passByValue", "exactlyOnce", "ordered", "osgi.async", "osgi.private", "osgi.confidential", "jakartars" });
		properties.put("service.imported", "true");
		properties.put("service.intents", new String[] { "jakartars" });
		properties.put("service.imported.configs", new String[] { "ecf.jakartars.jersey.server" });
		properties.put("osgi.jakartars.resource", "true");
		
		return properties;
	}
	
	public boolean isModifierImportActive() {
		return !modifierRegistrations.isEmpty();
	}
	
	public boolean isTransformerImportActive() {
		return !transformerRegistrations.isEmpty();
	}
	
	public boolean isDataManagerImportActive() {
		return dataManagerRegistration != null && dataManagerRegistration.getImportReference() != null;
	}
	
	public void closeModifierImports() {
		this.modifierRegistrations.forEach(ImportRegistration::close);
		this.modifierRegistrations.clear();
	}
	
	public void closeTransformerImports() {
		this.transformerRegistrations.forEach(ImportRegistration::close);
		this.transformerRegistrations.clear();
	}
	
	public void closeDataManagerImport() {
		if (this.dataManagerRegistration != null) {
			this.dataManagerRegistration.close();
			this.dataManagerRegistration = null;
		}
	}
}
