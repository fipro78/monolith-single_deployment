package org.fipro.service.transformer.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fipro.service.transformer.StringTransformer;
import org.osgi.service.component.annotations.Component;

@Path("/transform")
@Component(
		immediate = true,
		property = {
				"service.exported.interfaces=*",
				"service.exported.intents=jaxrs",
		        "ecf.jaxrs.server.pathPrefix=/invert"})
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