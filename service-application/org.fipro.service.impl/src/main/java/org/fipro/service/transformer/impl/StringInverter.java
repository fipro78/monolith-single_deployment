package org.fipro.service.transformer.impl;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.fipro.service.transformer.StringTransformer;
import org.osgi.service.component.annotations.Component;

@Path("/transform/invert")
@Component(
	property = {
		"service.exported.interfaces=*",
		"service.exported.intents=jakartars",
		"osgi.jakartars.resource=true"})
public class StringInverter implements StringTransformer {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{value}")
    @Override
    public String transform(@PathParam("value") String input) {
        return (input != null) 
        	? new StringBuilder(input).reverse().toString()
        	: "No input given";
    }
}