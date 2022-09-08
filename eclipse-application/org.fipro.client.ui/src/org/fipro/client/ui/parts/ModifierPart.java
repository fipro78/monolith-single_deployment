package org.fipro.client.ui.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.extensions.Service;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.fipro.client.ui.offline.OnlineModeSwitch;
import org.fipro.service.modifier.StringModifier;
 
public class ModifierPart {

	private List<StringModifier> modifierList;

	private Button button;
	private Text input;
	private Text output;
	
	@Service
	@Inject
	OnlineModeSwitch onlineSwitch;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(3, true));
		
		Label descriptionLabel = new Label(parent, SWT.WRAP);
		descriptionLabel.setText("This part gets the OSGi services injected directly. "
				+ "Switching the online/offline state uses a low-level OSGi API to enable/disable the local service instances. "
				+ "The remote services are imported via static Endpoint Description Extender Format (EDEF) configuration.\n\r");
		GridDataFactory.fillDefaults().span(3, 1).grab(true, false).applyTo(descriptionLabel);
 
		Label inputLabel = new Label(parent, SWT.NONE);
		inputLabel.setText("String to modify:");
		GridDataFactory.fillDefaults().applyTo(inputLabel);
 
		input = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(input);
 
		button = new Button(parent, SWT.PUSH);
		button.setText("Modify (" + (modifierList != null ? modifierList.size() : 0) + ")");
		GridDataFactory.defaultsFor(button).applyTo(button);
 
		Label outputLabel = new Label(parent, SWT.NONE);
		outputLabel.setText("Modified String:");
		GridDataFactory.fillDefaults().applyTo(outputLabel);
 
		output = new Text(parent, SWT.READ_ONLY | SWT.WRAP);
		GridDataFactory.fillDefaults().grab(true, true).span(2, 1).applyTo(output);
 
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				processModification();
			}
		});
 
		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR
						|| e.keyCode == SWT.KEYPAD_CR) {
					processModification();
				}
			}
		});
		 
		Button onlineButton = new Button(parent, SWT.PUSH);
		onlineButton.setText("Go online");
		GridDataFactory.defaultsFor(onlineButton).applyTo(onlineButton);
		onlineButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onlineSwitch.toogleOnlineState();
				if (onlineSwitch.isOnline()) {
					onlineButton.setText("Go offline");
				} else {
					onlineButton.setText("Go online");
				}
			}
		});
	}

	@Inject
	void setStringModifier(@Service List<StringModifier> modifierList) {
		this.modifierList = modifierList;
		
		if (button != null) {
			Display.getDefault().asyncExec(() -> {
				button.setText("Modify (" + (modifierList != null ? modifierList.size() : 0) + ")");
			});
		}
	}
	
	private void processModification() {
		StringBuilder builder = new StringBuilder();
		for (StringModifier modifier : modifierList) {
			builder.append(((StringModifier) modifier).modify(input.getText())).append(System.getProperty("line.separator"));
		}
		output.setText(builder.toString());
	}
}