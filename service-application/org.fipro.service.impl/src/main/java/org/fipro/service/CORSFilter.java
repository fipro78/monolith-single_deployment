package org.fipro.service;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.osgi.service.component.annotations.Component;

/**
 * Simple {@link ContainerResponseFilter} for adding CORS (cross origin resource sharing) headers to enable cross origin requests in the browser based examples.
 * This is a very simple and probably unsafe way to handle CORS. For productive use the CORS handling needs to be done in a more secure and reliable way.
 * Further information about CORS can be found here:
 * <ul>
 * <li>https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS</li>
 * <li>https://stackoverflow.com/questions/28065963/how-to-handle-cors-using-jax-rs-with-jersey</li>
 * </ul>
 */
@Component(
	immediate = true, 
	property = { 
		// property needed by the ECF JAX-RS Distribution Provider
		"jaxrs-service-exported-config-target=ecf.jaxrs.jersey.server",
		// property needed by the JAX-RS Whiteboard
		"osgi.jaxrs.extension=true"
	})
public class CORSFilter implements ContainerResponseFilter {

    @Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		response.getHeaders().add("Access-Control-Allow-Origin", "*");
		response.getHeaders().add("Access-Control-Allow-Headers", "CSRF-Token, X-Requested-By, Authorization, Content-Type");
		response.getHeaders().add("Access-Control-Allow-Credentials", "true");
		response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}
}