package com.stronans.pilgrim.data.model.abstracts;

import org.eclipse.swt.graphics.Image;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;

public abstract class ItemSpecifier implements ItemSpecific
{
	private String path; 		// Path to a folder for display
	private String specifier; 	// Regular expression to filter Item names
	private ItemType type;		//
	private Image icon;

	public ItemSpecifier(String path, String specifier, ItemType type, Image icon)
	{
		super();
		this.path = path;
		this.specifier = specifier;
		this.type = type;
		this.icon = icon;
	}

	public ItemSpecifier(String path, String specifier, ItemType type)
	{
		this(path, specifier, type, null);
	}

	/**
	 * @return the path
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * @return the specifier
	 */
	public String getSpecifier()
	{
		return specifier;
	}

	/**
	 * @return the type
	 */
	public ItemType getType()
	{
		return type;
	}

	public boolean equals(ItemSpecific item)
	{
		boolean result = false;

		if (this.getDescription().equals(item.getDescription()))
		{
			if (this.getPath() == null && item.getPath() == null)
				result = true;
			else if (this.getPath().equals(item.getPath()))
				result = true;
		}
		return result;
	}

	/**
	 * @return the icon
	 */
	public Image getIcon()
	{
		return icon;
	}
}
