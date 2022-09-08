package org.fipro.service.data;

import java.util.Map;
import java.util.UUID;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/data")
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
