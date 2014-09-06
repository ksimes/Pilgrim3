package com.stronans.pilgrim.data.model.items;

import java.io.File;
import java.util.Date;

import javax.swing.filechooser.FileSystemView;

import org.eclipse.swt.graphics.Image;

import com.stronans.pilgrim.data.model.interfaces.Files;

public class Item implements Files
{
	private String description;
	private File file; 	
	private Date lastModified;
	private Image icon;

	public Item(File file)
	{
		this.file = file;
		description = FileSystemView.getFileSystemView().getSystemTypeDescription(file);
		if(description == null)
			description = "";
		
		lastModified = new Date(file.lastModified());
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public long getSize()
	{
		return file.length();
	}

	@Override
	public String getName()
	{
		return file.getName();
	}

	@Override
	public Date getModified()
	{
		return lastModified; 
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
		return file;
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
