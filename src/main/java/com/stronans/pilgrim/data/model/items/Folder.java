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
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.filters.ApplySpecifier;
import com.stronans.pilgrim.data.model.filters.FoldersOnly;
import com.stronans.pilgrim.data.model.interfaces.Folders;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.AttributesColumn;
import com.stronans.pilgrim.ui.model.columns.DescriptionColumn;
import com.stronans.pilgrim.ui.model.columns.ModifiedColumn;
import com.stronans.pilgrim.ui.model.columns.NameColumn;
import com.stronans.pilgrim.ui.model.columns.SizeColumn;
import com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface;

public class Folder extends ItemSpecifier implements Folders
{
	private static final ColumnInterface FolderColumns[] = { new NameColumn(0), new DescriptionColumn(1), 
		new SizeColumn(2), new ModifiedColumn(3), new AttributesColumn(4)};

	private String description;
	private String name;
	private File item;
	private long size;

	public Folder(File item, String specifier)
	{
		super(item.getAbsolutePath(), specifier, ItemType.FOLDERS, StaticData.getIcon(StaticData.FOLDERCLOSED));
		this.item = item;
		description = FileSystemView.getFileSystemView().getSystemTypeDescription(item);

		name = FileSystemView.getFileSystemView().getSystemDisplayName(item);
		size = item.length();
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public long getSize()
	{
		return size;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getTabDescription()
	{
		return "Folder " + name + " - [" + getSpecifier() + "]";
	}

	@Override
	public String getTabToolTip()
	{
		return item.getAbsolutePath() + " - [" + getSpecifier() + "]";
	}

	@Override
	public List<Items> getTableListing(FileFilter filter)
	{
		List<Items> itemList = new ArrayList<Items>();

		if(filter == null && !getSpecifier().equals("*"))
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
	public Date getModified()
	{
		return new Date(item.lastModified());
	}

	@Override
	public Date getUpdated()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile()
	{
		return item;
	}

	@Override
	public List<ColumnInterface> getColumns()
	{
		return Arrays.asList(FolderColumns);
	}
}
