package com.stronans.pilgrim.data.model.interfaces;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.io.FileFilter;
import java.util.List;

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface;

public interface ItemSpecific extends Items
{
	// Fully specified path to this location 
	public String getPath();
	public void setPath(String path);
	// Regular expression specifying the listing filter
	public String getSpecifier();
	public void setSpecifier(String specifier);
	public String getDescription();
	// What text goes into the tab
	public String getTabDescription();
	// What text goes into the tooltip for the tab
	public String getTabToolTip();
	
	// Once the path & specifier have been applied 
	// results of that processing
	public List<Items> getTableListing(FileFilter filter);
	
	// Once the path & specifier have been applied 
	// results of that processing
	public List<ItemSpecific> getTreeListing();

	public boolean hasTreeChildren();
	
	// What columns should be displayed in a 
	// listing of this type 
	public List<ColumnInterface> getColumns();
	
	public ItemType getType();

	public boolean equals(ItemSpecific item);
}
