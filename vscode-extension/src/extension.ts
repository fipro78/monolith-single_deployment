// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import * as vscode from 'vscode';
import fetch from 'cross-fetch';

async function modifyREST(input:string) {
	// execute a call to the REST based service
	const response = await fetch ('http://host.docker.internal:8181/service/uppercase/modify/' + input);
	const data = await response.text();
	return data;
}

// this method is called when your extension is activated
// your extension is activated the very first time the command is executed
export function activate(context: vscode.ExtensionContext) {

	// The command has been defined in the package.json file
	// Now provide the implementation of the command with registerCommand
	// The commandId parameter must match the command field in package.json
	let disposable = vscode.commands.registerCommand('modify-extension.modify', () => {
		// get the active text editor
		const editor = vscode.window.activeTextEditor;
		
		if (editor) {
			// get the current selected text
			const text = editor.document.getText(editor.selection);
			
			// call the REST service
			const result = modifyREST(text);
			
			result.then(data => {

				// TODO if the lenght of the array is > 1 open a selection dialog

				// replace the current selection
				editor.edit(editBuilder => {
//					editBuilder.replace(editor.selection, data.join("|"));
					editBuilder.replace(editor.selection, data);
				});
			});
		}
	});

	context.subscriptions.push(disposable);
}

// this method is called when your extension is deactivated
export function deactivate() {}
