/**
 * 
 */
package com.stronans.pilgrim.data.model.items;

import java.io.FileFilter;
import java.util.List;

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface;

/**
 * @author SimonKing
 *
 */
public class Network extends ItemSpecifier implements Items
{
	public static final String LOCAL_NETWORK = "Local Network";
	private String description;
	
	public Network(String name)
	{
		super(null, null, ItemType.NETWORK, StaticData.getIcon(StaticData.NETWORK));
		if(name == null)
		{
			this.description = LOCAL_NETWORK;
		}
		else
		{
			this.description = LOCAL_NETWORK + " (" + name + ")";
		}
	}

	public Network()
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
		return null;
	}

	/* (non-Javadoc)
	 * @see com.stronans.pilgrim.data.model.interfaces.ItemSpecific#getColumns()
	 */
	@Override
	public List<ColumnInterface> getColumns()
	{
		// TODO Auto-generated method stub
		return null;
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
		// TODO Need to decide what should be returned here
		return description;
	}

	@Override
	public List<ItemSpecific> getTreeListing()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasTreeChildren()
	{
		return false;
	}
}
