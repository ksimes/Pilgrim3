package com.stronans.pilgrim.data.model.columns;

import com.stronans.pilgrim.data.model.interfaces.Drives;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.columns.abstracts.Column;

public class PercentageFullColumn extends Column
{
	private static final String COLUMN_NAME = "Full";

	public PercentageFullColumn(int index)
	{
		super(COLUMN_NAME, index);
	}

	@Override
	public String getDisplay(Items item)
	{
		String str = "";
		if(item instanceof Drives)
		{
			Drives drive = (Drives)item;
			str = "" + drive.percentageFull() + "%";
		}
		return str;
	}
}
