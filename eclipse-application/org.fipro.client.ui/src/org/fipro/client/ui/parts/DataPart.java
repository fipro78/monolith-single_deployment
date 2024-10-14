package org.fipro.client.ui.parts;

import java.util.Map.Entry;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Service;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.fipro.client.ui.edef.EdefImporter;
import org.fipro.client.ui.offline.DataManagerSwitch;
import org.fipro.service.data.DataManager;
import org.osgi.service.cm.ConfigurationAdmin;

public class DataPart {
	
	@Inject
	@Service
	ConfigurationAdmin configAdmin;
	
	@Inject
	@Service
	EdefImporter importer;
	
	@Inject
	@Service
	DataManagerSwitch managerSwitch;
	
	DataManager manager;
	
	TableViewer viewer;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, true));
		
		Label descriptionLabel = new Label(parent, SWT.WRAP);
		descriptionLabel.setText("This part gets the OSGi services injected directly. "
				+ "Switching the online/offline state uses the OSGi ConfigurationAdmin to enable/disable the services based on configurations. "
				+ "The remote services are imported via static Endpoint Description Extender Format (EDEF) configuration.\n\r");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(descriptionLabel);

		viewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		
		TableViewerColumn uuidColumn = new TableViewerColumn(viewer, SWT.NONE);
		uuidColumn.getColumn().setWidth(200);
		uuidColumn.getColumn().setText("UUID");
		uuidColumn.setLabelProvider(new ColumnLabelProvider() {
		    @SuppressWarnings("unchecked")
			@Override
		    public String getText(Object element) {
		    	Entry<UUID, String> entry = (Entry<UUID, String>) element;
		        return entry.getKey().toString();
		    }
		});

		TableViewerColumn nameColumn = new TableViewerColumn(viewer, SWT.NONE);
		nameColumn.getColumn().setWidth(200);
		nameColumn.getColumn().setText("Name");
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@SuppressWarnings("unchecked")
			@Override
			public String getText(Object element) {
				Entry<UUID, String> entry = (Entry<UUID, String>) element;
				return entry.getValue().toString();
			}
		});
		
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
		
		Text nameText = new Text(parent, SWT.BORDER);		
		
		Button addButton = new Button(parent, SWT.PUSH);
		addButton.setText("Add");
		GridDataFactory.defaultsFor(addButton).applyTo(addButton);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (manager != null && nameText.getText() != null && !nameText.getText().isBlank()) {
					manager.addData(nameText.getText());
					viewer.setInput(manager.listData().entrySet());
				}
			}
		});
		 
		Button removeButton = new Button(parent, SWT.PUSH);
		removeButton.setText("Remove Selection");
		GridDataFactory.defaultsFor(removeButton).applyTo(removeButton);
		removeButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer.getStructuredSelection().forEach(sel -> {
					manager.removeData(((Entry<UUID, String>)sel).getKey().toString());
					viewer.setInput(manager.listData().entrySet());
				});
			}
		});
		 
		Button onlineButton = new Button(parent, SWT.PUSH);
		onlineButton.setText("Go online");
		GridDataFactory.defaultsFor(onlineButton).applyTo(onlineButton);
		onlineButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				managerSwitch.toggle();
				
				if (managerSwitch.isOnline()) {
					onlineButton.setText("Go offline");
				} else {
					onlineButton.setText("Go online");
				}
			}
		});

		// if no DataManager is set, initialize the offline one
		this.managerSwitch.initializeOffline();
	}
	
	@Inject
	@Optional
	void setDataManager(@Service DataManager manager) {
		this.manager = manager;
		
		if (manager != null) {
			Display.getDefault().asyncExec(() -> {
				viewer.setInput(manager.listData().entrySet());
			});
		}
	}
	
}