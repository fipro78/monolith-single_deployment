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
		        "ecf.jaxrs.server.pathPrefix=/camel"})
public class CamelCaseTransformer implements StringTransformer {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{value}")
	@Override
	public String transform(@PathParam("value") String input) {
		StringBuilder builder = new StringBuilder();
		if (input != null) {
			for (int i = 0; i < input.length(); i++) {
				char currentChar = input.charAt(i);
				if (i % 2 == 0) {
					builder.append(Character.toUpperCase(currentChar));
				} else {
					builder.append(Character.toLowerCase(currentChar));
				}
			} 
		}
		else {
			builder.append("No input given");
		}
		return builder.toString();
	}
}
