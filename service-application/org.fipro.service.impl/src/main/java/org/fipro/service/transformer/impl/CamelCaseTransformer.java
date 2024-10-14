package org.fipro.service.transformer.impl;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.fipro.service.transformer.StringTransformer;
import org.osgi.service.component.annotations.Component;

@Path("/transform/camel")
@Component(
	property = {
		"service.exported.interfaces=*",
		"service.exported.intents=jakartars",
		"osgi.jakartars.resource=true"})
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
