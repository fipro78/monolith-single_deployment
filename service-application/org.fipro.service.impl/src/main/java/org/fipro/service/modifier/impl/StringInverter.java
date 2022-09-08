package org.fipro.service.modifier.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fipro.service.modifier.StringModifier;
import org.osgi.service.component.annotations.Component;

// The JAX-RS path annotation for this service
// needed so the service is taken up by the JAX-RS Distribution Provider
//IMPORTANT: needs to be the same as in the interface to make ECF JAX-RS work
@Path("/modify")
@Component(
	immediate = true,
	property = {
		// properties to export the service via remote services and use JAX-RS as communication mechanism
		"service.exported.interfaces=*",
		"service.exported.intents=jaxrs",
		// component property to specify the remote service resource path prefix
		// needs to be set and unique in case of multiple services in the same runtime as the Path is not part of the enpoint.id
        "ecf.jaxrs.server.pathPrefix=/inverter"})
public class StringInverter implements StringModifier {

	@GET
    // The JAX-RS annotation to specify the result type
	@Produces(MediaType.TEXT_PLAIN)
    // The JAX-RS annotation to specify that the last part
    // of the URL is used as method parameter
	@Path("/{value}")
    @Override
    public String modify(@PathParam("value") String input) {
        return (input != null) 
        	? new StringBuilder(input).reverse().toString()
        	: "No input given";
    }
}