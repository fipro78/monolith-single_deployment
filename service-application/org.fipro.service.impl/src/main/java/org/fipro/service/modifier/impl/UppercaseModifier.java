package org.fipro.service.modifier.impl;

import java.util.Locale;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.fipro.service.modifier.StringModifier;
import org.osgi.service.component.annotations.Component;

@Path("/uppercase")
@Component(
    property = { 
        "service.exported.interfaces=*",
        "service.exported.intents=jakartars",
        "osgi.jakartars.resource=true"})
public class UppercaseModifier implements StringModifier {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{value}")
    @Override
    public String modify(@PathParam("value") String input) {
        return (input != null)
        	? input.toUpperCase(Locale.getDefault())
        	: "No input given";
    }
}