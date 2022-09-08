# OSGi based applications

This folder contains the projects for multiple OSGi based applications.
All applications provide the same functionality by including the same services.

## Services

The following projects contain the services:
* **org.fipro.service.api** - The service interfaces
* **org.fipro.service.impl** - The service implementations

These projects are the _**SINGLE SOURCE**_ that is included in the different applications (see below).

## Commandline Application

The project **org.fipro.service.app.commandline** contains the definition of the commandline application.
It contains an additional _immediate component_ that is used as starter.
The resulting executable jar can be called with a commandline parameter, which will be processed by the application.

## JAX-RS Whiteboard Application

The project **org.fipro.service.app.jaxrs** contains the definition of the JAX-RS service application.
It provides the services as REST services using the [JAX-RS Whiteboard Specification](https://docs.osgi.org/specification/osgi.cmpn/8.0.0/service.jaxrs.html).
It uses the [Aries JAX-RS Whiteboard](https://github.com/apache/aries-jax-rs-whiteboard) implementation.

## Remote Service Application

The project **org.fipro.service.app.rs** contains the definition of the OSGi Remote Service Application.
It provides the services as _Remote Services_ using [Remote Services](https://docs.osgi.org/specification/osgi.cmpn/8.0.0/service.remoteservices.html) and the [Remote Service Admin Service Specification](https://docs.osgi.org/specification/osgi.cmpn/8.0.0/service.remoteserviceadmin.html) specification.
It uses the [Eclipse Communication Framework](https://www.eclipse.org/ecf/) and the [ECF JAX-RS Distribution Provider](https://github.com/ECF/JaxRSProviders).

**Note:**  
The _ECF JAX-RS Distribution Provider_ are not published on Maven Central. To make the Maven build work the necessary JARs need to be published to the local Maven repository. The folder _jaxrs-provider-bundles_ contains the required JARs for this example. The script files in that folder collect the necessary statements to perform this step, so executing the _install_ script will automatically install all the required JARs into the local Maven repository.