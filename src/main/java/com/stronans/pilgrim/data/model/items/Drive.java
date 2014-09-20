package com.stronans.pilgrim.data.model.items;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.filters.ApplySpecifier;
import com.stronans.pilgrim.data.model.filters.FoldersOnly;
import com.stronans.pilgrim.data.model.interfaces.Drives;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.AttributesColumn;
import com.stronans.pilgrim.ui.model.columns.DescriptionColumn;
import com.stronans.pilgrim.ui.model.columns.ModifiedColumn;
import com.stronans.pilgrim.ui.model.columns.NameColumn;
import com.stronans.pilgrim.ui.model.columns.SizeColumn;
import com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface;

public class Drive extends ItemSpecifier implements Drives
{
	private final ColumnInterface columns[] = { new NameColumn(0), new DescriptionColumn(1),
				new SizeColumn(2), new ModifiedColumn(3), new AttributesColumn(4) };

	private String description;
	private String name;
	private File item;
	private long total, free;

	public Drive(File item, String specifier)
	{
		super(item.getAbsolutePath(), specifier, ItemType.DRIVES, StaticData.getIcon(StaticData.DRIVE));
		this.item = item;
		
		description = FileSystemView.getFileSystemView().getSystemTypeDescription(item);
		name = FileSystemView.getFileSystemView().getSystemDisplayName(item);
		
		if (name.equals(""))
		{
			name = description;
		}

		total = item.getTotalSpace();
		free = item.getUsableSpace();
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

	@Override
	public String getTabDescription()
	{
		return name;
	}

	@Override
	public String getTabToolTip()
	{
		return description;
	}

	@Override
	public long getTotalSpace()
	{
		return total;
	}

	@Override
	public long getFreeSpace()
	{
		return item.getUsableSpace();
	}

	@Override
	public int percentageFull()
	{
		long percent = 0;
		if (total != 0)
		{
			percent = 100 - (100 * free / total);
		}

		return (int) percent;
	}

	@Override
	public String type()
	{
		return "Unknown";
	}

	@Override
	public List<Items> getTableListing(FileFilter filter)
	{
		List<Items> itemList = new ArrayList<Items>();

		if (filter == null && !getSpecifier().equals("*"))
		{
			filter = new ApplySpecifier(getSpecifier());
		}

		File files[] = filter != null ? item.listFiles(filter) : item.listFiles();

		if (files != null)
		{
			for (int i = 0; i < files.length; i++)
			{
				Items newItem = null;
				if (files[i].isDirectory())
				{
					newItem = new Folder(files[i], getSpecifier());
				} else
				{
					newItem = new Item(files[i]);
				}

				itemList.add(newItem);
			}
		}

		return itemList;
	}

	@Override
	public List<ItemSpecific> getTreeListing()
	{
		List<ItemSpecific> itemList = new ArrayList<ItemSpecific>();
		
		File files[] = item.listFiles(new FoldersOnly());

		if (files != null)
		{
			for (int i = 0; i < files.length; i++)
			{
				itemList.add(new Folder(files[i], getSpecifier()));
			}
		}

		return itemList;
	}

	@Override
	public boolean hasTreeChildren()
	{
		return StaticData.hasChildFolders(item.getAbsolutePath());
	}

	@Override
	public List<ColumnInterface> getColumns()
	{
		return Arrays.asList(columns);
	}
}
