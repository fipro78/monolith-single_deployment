package org.fipro.service.data;

import java.util.Map;
import java.util.UUID;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public interface DataManager {

    @POST
    @Path("/add/{value}")
	void addData(@PathParam("value") String data);
	
    @DELETE
    @Path("/{id}")
	void removeData(@PathParam("id") String id);
	
    @GET
    @Path("/{id}")
	String getData(@PathParam("id") String id);

    @GET
    @Path("/list")
	Map<UUID, String> listData();
}
