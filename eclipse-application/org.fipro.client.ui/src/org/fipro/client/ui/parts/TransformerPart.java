package org.fipro.client.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.fipro.client.ui.offline.StringTransformerRegistry;
import org.fipro.service.transformer.StringTransformer;
 
public class TransformerPart {

	private Button button;
	private Text input;
	private Text output;
	Button onlineButton;
	
	@Inject
	StringTransformerRegistry transformerRegistry;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(3, true));

		Label descriptionLabel = new Label(parent, SWT.WRAP);
		descriptionLabel.setText("This part gets a custom service registry injected that contains references to the OSGi services that are used. "
				+ "Switching the online/offline state uses the OSGi ConfigurationAdmin to change the target reference property which leads to have either the online or the offline service injected. "
				+ "To inform the user interface that the configuration change was applied, the OSGi EventAdmin is used to trigger an event. "
				+ "The remote services are imported via static Endpoint Description Extender Format (EDEF) configuration.\n\r");
		GridDataFactory.fillDefaults().span(3, 1).grab(true, false).applyTo(descriptionLabel);

		Label inputLabel = new Label(parent, SWT.NONE);
		inputLabel.setText("String to transform:");
		GridDataFactory.fillDefaults().applyTo(inputLabel);
 
		input = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(input);
 
		button = new Button(parent, SWT.PUSH);
		button.setText("Transform (" + (transformerRegistry != null ? transformerRegistry.getStringTransformer().size() : 0) + ")");
		GridDataFactory.defaultsFor(button).applyTo(button);
 
		Label outputLabel = new Label(parent, SWT.NONE);
		outputLabel.setText("Transformed String:");
		GridDataFactory.fillDefaults().applyTo(outputLabel);
 
		output = new Text(parent, SWT.READ_ONLY | SWT.WRAP);
		GridDataFactory.fillDefaults().grab(true, true).span(2, 1).applyTo(output);
 
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				processTransformation();
			}
		});
 
		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR
						|| e.keyCode == SWT.KEYPAD_CR) {
					processTransformation();
				}
			}
		});
		 
		onlineButton = new Button(parent, SWT.PUSH);
		setOnlineButtonText();
		GridDataFactory.defaultsFor(onlineButton).applyTo(onlineButton);
		onlineButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				transformerRegistry.toogleOnlineState();
			}
		});
	}

	void setOnlineButtonText() {
		if (transformerRegistry != null && transformerRegistry.isOnline()) {
			// update the label of the button
			onlineButton.setText("Go offline");
		} else {
			// update the label of the button
			onlineButton.setText("Go online");
		}
	}
	
	@Inject
	@Optional
	void handleUpdatedEvent(@UIEventTopic(StringTransformerRegistry.UPDATE_EVENT_TOPIC) Object data) {
		if (button != null) {
			button.setText("Transform (" + (transformerRegistry != null ? transformerRegistry.getStringTransformer().size() : 0) + ")");
			setOnlineButtonText();
		}
	}
	
	private void processTransformation() {
		StringBuilder builder = new StringBuilder();
		for (StringTransformer transformer : transformerRegistry.getStringTransformer()) {
			builder.append(((StringTransformer) transformer).transform(input.getText())).append(System.getProperty("line.separator"));
		}
		output.setText(builder.toString());
	}
}