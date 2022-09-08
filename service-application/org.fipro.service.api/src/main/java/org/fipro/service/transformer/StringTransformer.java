package org.fipro.service.transformer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transform")
public interface StringTransformer {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{value}")
    String transform(@PathParam("value") String input);
}