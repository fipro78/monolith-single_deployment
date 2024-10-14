package org.fipro.service.data.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.fipro.service.data.DataManager;
import org.osgi.service.component.annotations.Component;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
@Component(
	property = {
		"service.exported.interfaces=*",
		"service.exported.intents=jakartars",
		"osgi.jakartars.resource=true"})
public class NameDataManager implements DataManager {

	private ConcurrentHashMap<UUID, String> dataMap = new ConcurrentHashMap<>();
	
	public NameDataManager() {
		addData("Dirk");
	}
	
    @POST
    @Path("/add/{value}")
	public void addData(@PathParam("value") String data) {
    	this.dataMap.put(UUID.randomUUID(), data);
    }
	
    @DELETE
    @Path("/{id}")
	public void removeData(@PathParam("id") String id) {
    	UUID uuid = UUID.fromString(id);
    	this.dataMap.remove(uuid);
    }
	
    @GET
    @Path("/{id}")
	public String getData(@PathParam("id") String id) {
    	UUID uuid = UUID.fromString(id);
    	return this.dataMap.get(uuid);
    }

    @GET
    @Path("/list")
	public Map<UUID, String> listData() {
    	return this.dataMap;
    }

}
