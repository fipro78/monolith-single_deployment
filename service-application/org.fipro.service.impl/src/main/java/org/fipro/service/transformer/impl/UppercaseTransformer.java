package org.fipro.service.transformer.impl;

import java.util.Locale;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.fipro.service.transformer.StringTransformer;
import org.osgi.service.component.annotations.Component;

@Path("/transform/upper")
@Component(
    property = { 
        "service.exported.interfaces=*",
        "service.exported.intents=jakartars",
        "osgi.jakartars.resource=true"})
public class UppercaseTransformer implements StringTransformer {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{value}")
    @Override
    public String transform(@PathParam("value") String input) {
        return (input != null)
        	? input.toUpperCase(Locale.getDefault())
        	: "No input given";
    }
}