package com.stronans.pilgrim.data.model;

/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import org.eclipse.swt.graphics.Image;

import com.stronans.pilgrim.data.model.interfaces.Items;

public class OverviewItem implements Items
{
	private String name;
	private String description;
	private Image icon;

	public OverviewItem(String name, String description)
	{
		super();
		this.name = name;
		this.description = description;
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
	 * @return the icon
	 */
	public Image getIcon()
	{
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(Image icon)
	{
		this.icon = icon;
	}
}
