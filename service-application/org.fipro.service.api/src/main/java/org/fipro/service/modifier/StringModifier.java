package org.fipro.service.modifier;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public interface StringModifier {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{value}")
    String modify(@PathParam("value") String input);
}