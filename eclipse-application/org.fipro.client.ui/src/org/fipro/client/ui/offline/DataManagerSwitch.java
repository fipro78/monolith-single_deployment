package org.fipro.client.ui.offline;

import java.io.IOException;
import java.util.Hashtable;

import org.fipro.client.ui.edef.EdefImporter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = DataManagerSwitch.class)
public class DataManagerSwitch {

	@Reference
	ConfigurationAdmin configAdmin;
	
	@Reference
	EdefImporter importer;
	
	public void initializeOffline() {
		try {
			Configuration configuration = configAdmin.getConfiguration("org.fipro.offline", "?");
			configuration.update(new Hashtable<>());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void toggle() {
		try {
			// check if there is a offline configuration set
			Configuration[] offlineConfigs = configAdmin.listConfigurations("(service.pid=org.fipro.offline)");
	
			if (offlineConfigs == null) {
				// if there is no offline configuration set, create one to go offline
				Configuration configuration = configAdmin.getConfiguration("org.fipro.offline", "?");
				configuration.update(new Hashtable<>());
	
				// disable remote service
				this.importer.closeDataManagerImport();
			} else {
				// if there are offline configurations we need to delete it to go online
				for (Configuration offline : offlineConfigs) {
					offline.delete();
				}
				
				// enable remote services
				this.importer.importDataManagerService();
			}
		} catch (IOException | InvalidSyntaxException ex) {
			System.err.println("Failed to update the online/offline service consumption configuration.");
			ex.printStackTrace();
		}
	}
	
	public boolean isOnline() {
		Configuration[] offlineConfigs;
		try {
			offlineConfigs = configAdmin.listConfigurations("(service.pid=org.fipro.offline)");
			return offlineConfigs == null;
		} catch (IOException | InvalidSyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}
}
