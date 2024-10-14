package org.fipro.service.transformer;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public interface StringTransformer {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{value}")
    String transform(@PathParam("value") String input);
}