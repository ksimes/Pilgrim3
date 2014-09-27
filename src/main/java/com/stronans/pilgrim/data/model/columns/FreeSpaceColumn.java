package com.stronans.pilgrim.data.model.columns;

import java.text.NumberFormat;

import com.stronans.pilgrim.data.model.Alignment;
import com.stronans.pilgrim.data.model.Configuration;
import com.stronans.pilgrim.data.model.interfaces.Drives;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.columns.abstracts.Column;

public class FreeSpaceColumn extends Column
{
	private static final String COLUMN_NAME = "Free space";

	public FreeSpaceColumn(int index, Alignment alignment)
	{
		super(COLUMN_NAME, index, alignment);
	}

	public FreeSpaceColumn(int index)
	{
		this(index, Alignment.RIGHT);
	}

	@Override
	public String getDisplay(Items item)
	{
		long size = 0;

		if(item instanceof Drives)
		{
			Drives drive = (Drives)item;
			size = drive.getFreeSpace();

			switch (Configuration.getScale())
			{
			case SHOWSCALED:
				break;
			}
		}
		
		NumberFormat display = NumberFormat.getInstance();
		
		return display.format(size);
	}
}
