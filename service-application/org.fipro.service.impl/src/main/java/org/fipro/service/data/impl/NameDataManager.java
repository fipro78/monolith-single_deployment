package org.fipro.service.data.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fipro.service.data.DataManager;
import org.osgi.service.component.annotations.Component;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
@Component(
	immediate = true,
	property = {
		"service.exported.interfaces=*",
		"service.exported.intents=jaxrs",
        "ecf.jaxrs.server.pathPrefix=/name"})
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
