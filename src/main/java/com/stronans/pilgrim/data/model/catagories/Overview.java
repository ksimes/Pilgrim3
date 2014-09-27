package com.stronans.pilgrim.data.model.catagories;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.data.model.catagories.items.OverviewItem;
import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.catagories.items.LocalMachine;
import com.stronans.pilgrim.data.model.catagories.items.Network;
import com.stronans.pilgrim.data.model.catagories.items.OtherNetworks;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.DescriptionColumn;
import com.stronans.pilgrim.ui.model.columns.NameColumn;
import com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface;
import org.apache.log4j.Logger;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Overview extends ItemSpecifier implements Items
{
    private static final Logger logger = Logger.getLogger(Overview.class);

    public static final String OS_PROPERTY = "os.name";
    public static final String OS_VER_PROPERTY = "os.version";
    public static final String USERNAME = "USERNAME";
    public static final String USERDOMAIN = "USERDOMAIN";
    public static final String LOGONSERVER = "LOGONSERVER";

    public static final String COMPUTERNAME = "COMPUTERNAME";

	private static final String OVERVIEW = "Overview";
	
	private ColumnInterface columns[] = { new NameColumn(0), new DescriptionColumn(1)};
	
	private List<Items> items = new ArrayList<>();
	
	public Overview()
	{
		super(null, null, ItemType.OVERVIEW, StaticData.getIcon(StaticData.OVERVIEW));

        items.add(new OverviewItem("Operating System", StaticData.getProperty(OS_PROPERTY), StaticData.getIcon(StaticData.OVERVIEW)));
        items.add(new OverviewItem("OS Version", StaticData.getProperty(OS_VER_PROPERTY), StaticData.getIcon(StaticData.OVERVIEW)));

        items.add(new OverviewItem("User", StaticData.getEnvironment(USERNAME), StaticData.getIcon(StaticData.MACHINE)));
        items.add(new OverviewItem("Domain", StaticData.getEnvironment(USERDOMAIN), StaticData.getIcon(StaticData.MACHINE)));
        items.add(new OverviewItem("Logon Server", StaticData.getEnvironment(LOGONSERVER), StaticData.getIcon(StaticData.MACHINE)));
        items.add(new OverviewItem("Machine name", StaticData.getEnvironment(COMPUTERNAME), StaticData.getIcon(StaticData.MACHINE)));
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
		return items;
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
		List<ItemSpecific> result = new ArrayList<>();
		
		result.add(new LocalMachine(StaticData.getEnvironment(COMPUTERNAME)));
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
