# modify-extension README

This is a simple VS Code Extension to verify the creation and implementation of VS Code Extensions.
The goal is to include this extension into an Eclipse Theia based application after it was packaged as .vsix file.

This project is a modified version of the [Your First Extension](https://code.visualstudio.com/api/get-started/your-first-extension) tutorial of the Visual Studio Code Documentation.

## Features

This extension contributes a simple command that modifies a selected text in the editor. It uses the cross-fetch
library to call a REST service. The REST service is published as part of the evolution strategy POC located in the "eclipse-application" folder of this repository.

## How to use the extension

In the running application, open a file, select a word and then trigger the command "Modify selection" via the command palette (F1). The selected text will be modified.

This requires that either the __JAX-RS Service App__ or the __Remote Service App__ is running and accessible. The service URL that should be used can be configured via the Settings.

## Build

To create a .vsix file that can be used to integrate the plugin in a Theia application, the "vsce" command-line tool can be used.

First ensure that the vsce tool is installed:

    npm install -g @vscode/vsce
	
Execute the following command to create the .vsix file:

    vsce package

Look at [Publishing Extensions](https://code.visualstudio.com/api/working-with-extensions/publishing-extension) for further details.

## Code Management

If the VSCode extension should be integrated in Theia, you need to ensure that you use latest a vscode version that is currently supported in the target Theia version.
You can find the currently compatible version via https://github.com/eclipse-theia/theia/blob/master/dev-packages/application-package/src/api.ts

To update to a specific version, execute the following command from the commandline in the _vscode-extension_ folder:

    npm install @types/vscode@1.81.0 --save-dev

## Tips

If you change code in a .ts file (e.g. extension.ts) or add new libraries, ensure that the Typscript compilation is executed. Otherwise the result might not contain the latest changes.

To trigger a manual compilation, open a terminal and execute the following command:

    yarn compile

To debug the extension and verify the changes, press F5.
