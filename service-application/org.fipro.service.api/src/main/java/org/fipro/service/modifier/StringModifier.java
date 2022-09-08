package org.fipro.service.modifier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/modify")
public interface StringModifier {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{value}")
    String modify(@PathParam("value") String input);
}