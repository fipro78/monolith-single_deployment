package org.fipro.service.modifier.impl;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.fipro.service.modifier.StringModifier;
import org.osgi.service.component.annotations.Component;

@Path("/camelcase")
@Component(
	property = {
		"service.exported.interfaces=*",
		"service.exported.intents=jakartars",
		"osgi.jakartars.resource=true"})
public class CamelCaseModifier implements StringModifier {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{value}")
	@Override
	public String modify(@PathParam("value") String input) {
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
