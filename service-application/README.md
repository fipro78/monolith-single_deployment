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

## Jakarta-RS Whiteboard Application

The project **org.fipro.service.app.jakartars** contains the definition of the Jakarta Rest service application and the configuration for the `JakartarsWhiteboardComponent`.
It provides the services as REST services using the [Whiteboard Specification for Jakarta™ RESTful Web Services](https://docs.osgi.org/specification/osgi.cmpn/8.1.0/service.jakartars.html).
It uses the [OSGi Technology Whiteboard Implementation for Jakarta RESTful Web Services](https://github.com/osgi/jakartarest-osgi) reference implementation.

## Remote Service Application

The project **org.fipro.service.app.rs** contains the definition of the OSGi Remote Service Application.
It provides the services as _Remote Services_ using [Remote Services](https://docs.osgi.org/specification/osgi.cmpn/8.1.0/service.remoteservices.html) and the [Remote Service Admin Service Specification](https://docs.osgi.org/specification/osgi.cmpn/8.1.0/service.remoteserviceadmin.html) specification.
It uses the [Eclipse Communication Framework](https://www.eclipse.org/ecf/) and the [ECF Jakarta-RS Distribution Provider](https://github.com/fipro78/JakartaRSProviders), which is an adoption of the ECF JaxRS Distribution, that is updated to the `jakarta` namespace and the usage of the [Whiteboard Specification for Jakarta™ RESTful Web Services](https://docs.osgi.org/specification/osgi.cmpn/8.1.0/service.jakartars.html).

**Note:**  
The _ECF Jakarta-RS Distribution Provider_ bundles are not published on Maven Central. To make the Maven build work the necessary JARs need to be published to the local Maven repository. The folder _jakartars-provider-bundles_ contains the required JARs for this example. The script files in that folder collect the necessary statements to perform this step, so executing the _install_ script will automatically install all the required JARs into the local Maven repository.
