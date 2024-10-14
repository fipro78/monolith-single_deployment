package org.fipro.service.modifier.impl;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.fipro.service.modifier.StringModifier;
import org.osgi.service.component.annotations.Component;

// The Jakarta-RS path annotation for this service
// needed so the service is taken up by the Jakarta-RS Distribution Provider
@Path("/inverter")
@Component(
	property = {
		// properties to export the service via remote services and use Jakarta-RS as communication mechanism
		"service.exported.interfaces=*",
		"service.exported.intents=jakartars",
		// property to configure that the service must be processed by the Jakarta-RS Whiteboard 
		"osgi.jakartars.resource=true"})
public class StringInverter implements StringModifier {

	@GET
    // The Jakarta-RS annotation to specify the result type
	@Produces(MediaType.TEXT_PLAIN)
    // The Jakarta-RS annotation to specify that the last part
    // of the URL is used as method parameter
	@Path("/{value}")
    @Override
    public String modify(@PathParam("value") String input) {
        return (input != null) 
        	? new StringBuilder(input).reverse().toString()
        	: "No input given";
    }
}