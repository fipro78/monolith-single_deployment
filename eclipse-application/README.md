# Eclipse RCP Application

This folder contains the projects for the Eclipse RCP application that is consuming the OSGi services from the _service-application_ folder.

The project setup is based on Eclipse PDE to help in migrating a PDE based Eclipse application project to an architecture that splits out the business functionality.

## Prerequisites

To build the examples you need at least:
- Java  >= 17
- Maven >= 3.9.6

For editing the example a current Eclipse IDE with PDE is recommended.

For opening the sources in Eclipse or building via Maven, the build in _<REPOSITORY_ROOT>/service-application_ needs to be build via

    mvn clean install
	
This way the OSGi Service API and Implementation bundles are installed in the local Maven repository, which makes them available for the Target Definition.

## Build

The build is based on Maven Tycho using the pom-less extension. This means that the build informations are derived from the PDE based projects and there is no need for 
additional pom.xml files per project as long as there is no need to extend or to deviate from the defaults.

### Build Artefacts

- **.mvn** - Folder that contains the configuration to enable the Tycho extensions
- **org.fipro.client.target** - The Target Definition that is used to configure the Target Platform to develop against.  
The Target Definition needs to be activated in order to make the sources compile in Eclipse.
- **pom.xml** - The parent pom.xml file for the pom-less Maven Tycho build.


## Plug-in Projects

The following list shows the plug-in projects of this example:
* __org.fipro.client.ui__ - The implementation of the user interface and the services for switching between online and offline mode.
* __org.fipro.client.service.impl__ - Contains service implementations that are included in the application to be able to switch between an offline and an online mode.

## Feature Projects

The following list shows the feature projects of this example:
* __org.fipro.client.ecf.feature__ - Collects all bundles that are needed for ECF.
* __org.fipro.client.jakartars.feature__ - Collects all bundles that are needed for the ECF Jakarta-RS Distribution Provider.
* __org.fipro.client.ui.feature__ - Collects all bundles that are needed for the user interface.

## Product Projects

The following list shows the product projects of this example:
* __org.fipro.client.ui.product__ - The product definition of the Eclipse RCP Application.
* __org.fipro.service.rs.product__ - The product definition for the Remote Service Application (just for verification that everything also works with PDE, can be used for initial migration steps towards plain Java/Maven project layouts). 
