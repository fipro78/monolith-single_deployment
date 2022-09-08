package org.fipro.service.modifier.jaxrs;

import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fipro.service.modifier.StringModifier;
import org.osgi.service.component.annotations.Component;

@Path("/uppercase")
@Component(
    immediate = true,
    property = { "osgi.jaxrs.resource=true"})
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