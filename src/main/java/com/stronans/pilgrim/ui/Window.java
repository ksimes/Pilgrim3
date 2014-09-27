package com.stronans.pilgrim.ui;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.ui.interfaces.NodeChange;

public class Window
{
	private CTabItem tab;
	private TreeHandler tree;
	private TableHandler table;
	private Composite frame;
	private Set<NodeChange> listeners = new HashSet<NodeChange>();

	public Window(CTabFolder tabFolder, NodeChange listener)
	{
		int style = SWT.NONE;
		if(tabFolder.getItemCount() > 0)
			style = SWT.CLOSE;
		
		tab = new CTabItem(tabFolder, style);

		frame = new Composite(tabFolder, SWT.NONE);
		tab.setControl(frame);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.horizontalSpacing = 5;
		frame.setLayout(layout);

		tree = new TreeHandler(this);
		table = new TableHandler(this);

        if(listener != null) {
            listeners.add(listener);
        }
    }

	public void addListener(NodeChange listener)
	{
		listeners.add(listener);
	}

	/**
	 * @return the tab
	 */
	public CTabItem getTab()
	{
		return tab;
	}

	/**
	 * @return the frame
	 */
	public Composite getFrame()
	{
		return frame;
	}

	private void notifyListeners(ItemSpecifier specifier)
	{
		for (NodeChange listener : listeners)
		{
			listener.change(specifier);
		}
	}

	public void setTo(ItemSpecifier specifier)
	{
		tab.setText(specifier.getTabDescription());
		tab.setImage(specifier.getIcon());
		tab.setToolTipText(specifier.getTabToolTip());

		table.buildTable(specifier);
		tree.locateFolder(specifier);
		notifyListeners(specifier);
	}
}
