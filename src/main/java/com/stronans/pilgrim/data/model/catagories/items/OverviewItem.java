package com.stronans.pilgrim.data.model.catagories.items;

/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import org.apache.log4j.Logger;
import org.eclipse.swt.graphics.Image;

import com.stronans.pilgrim.data.model.interfaces.Items;

public final class OverviewItem implements Items
{
    private static final Logger logger = Logger.getLogger(OverviewItem.class);

    private final String name;
	private final String description;
	private final Image icon;

	public OverviewItem(final String name, final String description, final Image icon)
	{
		super();
		this.name = name;
		this.description = description;
        this.icon = icon;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the icon associated with this item
	 */
	public Image getIcon()
	{
		return icon;
	}
}
