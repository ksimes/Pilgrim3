package com.stronans.pilgrim.ui;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.stronans.pilgrim.data.model.Configuration;
import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.items.Overview;
import com.stronans.pilgrim.ui.interfaces.NodeChange;

public class MainPanel implements NodeChange
{
	private CTabFolder tabFolder;
	private StatusBar statusBar;
	private Shell shell;
	private ItemSpecifier currentSpecifier;

	public MainPanel(Shell shell)
	{
		this.shell = shell;

		shell.addShellListener(new ShellHandler());
		GridData tabData = new GridData();
		tabData.horizontalAlignment = SWT.FILL;	/* grow to fill available width */
		tabData.grabExcessHorizontalSpace = true;
		tabData.verticalAlignment = SWT.FILL;	/* grow to fill available width */
		tabData.grabExcessVerticalSpace = true;

		tabFolder = new CTabFolder(shell, SWT.CLOSE);
		Display display = Configuration.getGlobalDisplay();
		tabFolder.setSelectionBackground(new Color[]{display.getSystemColor(SWT.COLOR_DARK_GRAY), display.getSystemColor(SWT.COLOR_GRAY)}, new int[]{100}, true);
		tabFolder.setLayoutData(tabData);
		tabFolder.setLayout(new FillLayout());

		// Change of tab
		tabFolder.addSelectionListener(new TabSelection());

		// Setup all previous loaded windows
		Window window = new Window(tabFolder);
		window.addListener(this);
		window.setTo(new Overview());
		
		// Setup the status bar
		statusBar = new StatusBar(shell, SWT.NONE);
		GridData statusData = new GridData();
		statusData.horizontalAlignment = SWT.FILL;	/* grow to fill available width */
		statusData.grabExcessHorizontalSpace = true;
		statusBar.setLayoutData(statusData);
		statusBar.setLayout(new FillLayout());
	}

	private class ShellHandler implements ShellListener
	{
		@Override
		public void shellActivated(ShellEvent arg0)
		{
		}

		@Override
		public void shellClosed(ShellEvent arg0)
		{
		}

		@Override
		public void shellDeactivated(ShellEvent arg0)
		{
		}

		@Override
		public void shellDeiconified(ShellEvent arg0)
		{
			shell.setText(getTitleBanner(currentSpecifier));
		}

		@Override
		public void shellIconified(ShellEvent arg0)
		{
			shell.setText(getTitleIconised());
		}
	}

	private class TabSelection implements SelectionListener
	{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent arg0)
		{
			// CTabItem tab = (CTabItem)arg0.item;

		}
	}

	private String getTitleIconised()
	{
		StringBuffer result = new StringBuffer(20);

		// result.append("Pilgrim III - ");
		result.append(tabFolder.getItemCount());
		result.append(" Windows(s) Open");

		return result.toString();
	}

	private String getTitleBanner(ItemSpecifier specifier)
	{
		StringBuffer result = new StringBuffer(20);

		result.append("Pilgrim III - User ");
		result.append(StaticData.getEnvironment(StaticData.USERDOMAIN));
		result.append("\\");
		result.append(StaticData.getEnvironment(StaticData.USERNAME));
		result.append(", Current view - ");
		result.append(specifier.getTabToolTip());
		result.append("");

		return result.toString();
	}

	@Override
	public void change(ItemSpecifier specifier)
	{
		currentSpecifier = specifier;

		if (shell.getMinimized())
			shell.setText(getTitleIconised());
		else
			shell.setText(getTitleBanner(specifier));
	}
}
