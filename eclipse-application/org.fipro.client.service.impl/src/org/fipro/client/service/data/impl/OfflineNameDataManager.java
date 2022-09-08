package org.fipro.client.service.data.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.fipro.service.data.DataManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(
    configurationPid="org.fipro.offline",
    configurationPolicy=ConfigurationPolicy.REQUIRE)
public class OfflineNameDataManager implements DataManager {

	private static ConcurrentHashMap<UUID, String> dataMap = new ConcurrentHashMap<>();
	
	public void addData(String data) {
    	dataMap.put(UUID.randomUUID(), data);
    }
	
	public void removeData(String id) {
    	UUID uuid = UUID.fromString(id);
    	dataMap.remove(uuid);
    }
	
	public String getData(String id) {
    	UUID uuid = UUID.fromString(id);
    	return dataMap.get(uuid);
    }

	public Map<UUID, String> listData() {
    	return dataMap;
    }

}
