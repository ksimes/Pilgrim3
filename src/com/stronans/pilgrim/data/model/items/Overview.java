package com.stronans.pilgrim.data.model.items;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.data.model.OverviewItem;
import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.DescriptionColumn;
import com.stronans.pilgrim.ui.model.columns.NameColumn;
import com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface;

public class Overview extends ItemSpecifier implements Items
{
	private static final String OVERVIEW = "Overview";
	
	private ColumnInterface columns[] = { new NameColumn(0), new DescriptionColumn(1)};
	
	private Items items[] = {	new OverviewItem("Operating System", ""), 
					 	new OverviewItem("OS Version", "") }; 
	
	public Overview()
	{
		super(null, null, ItemType.OVERVIEW, StaticData.getIcon(StaticData.OVERVIEW));
	}

	@Override
	public String getName()
	{
		return OVERVIEW;
	}

	@Override
	public String getDescription()
	{
		return OVERVIEW;
	}

	@Override
	public String getTabToolTip()
	{
		return OVERVIEW;
	}

	@Override
	public List<Items> getTableListing(FileFilter filter)
	{
		return Arrays.asList(items);
	}

	@Override
	public List<ColumnInterface> getColumns()
	{
		return Arrays.asList(columns);
	}

	@Override
	public String getTabDescription()
	{
		return OVERVIEW;
	}

	@Override
	public List<ItemSpecific> getTreeListing()
	{
		List<ItemSpecific> result = new ArrayList<ItemSpecific>();
		
		result.add(new LocalMachine(StaticData.getEnvironment(StaticData.COMPUTERNAME)));
		result.add(new Network());
		result.add(new OtherNetworks());
		
		return result;
	}

	@Override
	public boolean hasTreeChildren()
	{
		return true;
	}
}
