# modify-extension README

This is a simple VS Code Extension to verify the creation and implementation of VS Code Extensions.
The goal is to include this extension into an Eclipse Theia based application after it was packaged as .vsix file.

This project is a modified version of the [Your First Extension](https://code.visualstudio.com/api/get-started/your-first-extension) tutorial of the Visual Studio Code Documentation.

## Features

This extension contributes a simple command that modifies a selected text in the editor. It uses the cross-fetch
library to call a REST service. The REST service is published as part of the evolution strategy POC located in the "eclipse-application" folder of this repository.

## How to use the extension

In the running application, open a file, select a word and then trigger the command "Modify selection" via the command palette (F1). The selected text will be modified.

## Build

To create a .vsix file that can be used to integrate the plugin in a Theia application, the "vsce" command-line tool can be used.

First ensure that the vsce tool is installed:

    npm install -g vsce
	
Execute the following command to create the .vsix file:

    vsce package

Look at [Publishing Extensions](https://code.visualstudio.com/api/working-with-extensions/publishing-extension) for further details.

