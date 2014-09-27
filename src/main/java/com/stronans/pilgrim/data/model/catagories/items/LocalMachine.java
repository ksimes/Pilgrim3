package com.stronans.pilgrim.data.model.catagories.items;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.io.FileFilter;
import java.util.List;

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.catagories.DriveCategory;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.columns.interfaces.ColumnInterface;

/**
 * @author SimonKing
 *
 */
public final class LocalMachine extends ItemSpecifier implements Items
{
	public static final String MY_COMPUTER = "My Computer";
	public static final String DEFAULT_SPECIFER = "*";
	private final String description;
	
	public LocalMachine(String Name)
	{
		super(null, null, ItemType.MACHINE, StaticData.getIcon(StaticData.MACHINE));
		if(Name == null)
			this.description = MY_COMPUTER;
		else
			this.description = MY_COMPUTER + " (" + Name + ")";
	}

	public LocalMachine()
	{
		this(null);
	}

	/* (non-Javadoc)
	 * @see com.stronans.pilgrim.data.model.interfaces.ItemSpecific#getTypeDescription()
	 */
	@Override
	public String getTabToolTip()
	{
		return description;
	}

	/* (non-Javadoc)
	 * @see com.stronans.pilgrim.data.model.interfaces.ItemSpecific#getListing()
	 */
	@Override
	public List<Items> getTableListing(FileFilter filter)
	{
		return DriveCategory.getDriveListAsItems();
	}

	@Override
	public List<ItemSpecific> getTreeListing()
	{
		return DriveCategory.getDriveList();
	}

	@Override
	public boolean hasTreeChildren()
	{
		// Assume that there is always at least one drive in the machine.
		return true;
	}

	/* (non-Javadoc)
	 * @see com.stronans.pilgrim.data.model.interfaces.ItemSpecific#getColumns()
	 */
	@Override
	public List<ColumnInterface> getColumns()
	{
		return DriveCategory.getColumns();
	}

	/* (non-Javadoc)
	 * @see com.stronans.pilgrim.data.model.interfaces.Items#getName()
	 */
	@Override
	public String getName()
	{
		return description;
	}

	/* (non-Javadoc)
	 * @see com.stronans.pilgrim.data.model.interfaces.Items#getDescription()
	 */
	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public String getTabDescription()
	{
		return description;
	}
}
