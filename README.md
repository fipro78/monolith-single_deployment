# From monolith to single-source to single-deployment

This repository contains projects to evaluate an evolution strategy for Eclipse RCP Desktop based applications to create web-based applications.

The folder *eclipse-application* contains the projects for an Eclipse RCP Desktop application as a client application for the single-deployed services.
These projects use the PDE project layout.

The folder *service-application* contains the projects for the OSGi Services and projects for creating a commandline application, an application that publishes the services via REST API and an application that publishes the services via REST API and as OSGi Remote Services at once.
These projects use a Java/Maven project layout.

The folder *vscode-extension* contains a simple extension for VS Code to show the integration of a REST service. It is configured to use the services published via
the Remote Service application of the *service-application*.

## Build & Run the example

The following section describes the process for building and running the example.

### Service Applications

First you need to run the build for the OSGi service applications. As a pre-requisite you need to install the ECF JAX-RS Distribution Provider bundles to the local Maven repository, as they are not yet available via Maven Central.
To make this easier the folder _service-application/jaxrs-provider-bundles_ contains an install script (_install.bat_/_install.sh_).

After the bundles are available in the local Maven repository, execute the _service-application_ build via

    mvn clean install
	
This installs the OSGI Service API and Implementation bundle also in the local Maven repository, which is required for the Eclipse RCP Application build.

After the build succeeds the remoteservice example application can be started via

    java -jar org.fipro.service.app.rs\target\rs-app.jar 

You can verify if the application is started by accessing the following URL in a browser:  
http://localhost:8181/service/camelcase/modify/osgicommunityday

__Note__  
The build also produces a commandline application and a JAX-RS Whiteboard application that can be tested.

### Client Applications

You can run the Eclipse Application via _build & run_ from commandline. For this execute the build in the folder _eclipse-application_ via

    mvn clean verify
	
After the build succeeds you will find the resulting product for your OS in _org.fipro.client.ui.product/target/products_.

Alternatively you can start the application from within an Eclipse IDE:

- Import the projects in _eclipse-application_ into an Eclipse workspace.
- Open the Target Definition in _org.fipro.client.target_ and activate it.
- Open the product definition in _org.fipro.client.ui.product_ and *Launch an Eclipse application*

Additionally in _vscode-extension_ there is an example on how to integrate the service in Visual Studio Code. To see this in action, open the folder _vscode-extension_
in VS Code and execute the extension via F5. In the then opened VS Code instance select a word in the editor, press F1 and select *Modify Selection*.
If the Remove Service Application with JAX-RS Distribution Provider is running, the selected text should be changed to uppercase.  
Note that a devcontainer is configured to reduce the installation needs in the host system.