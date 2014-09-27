package com.stronans.pilgrim.data.model.columns;

import java.text.NumberFormat;

import com.stronans.pilgrim.data.model.Alignment;
import com.stronans.pilgrim.data.model.Configuration;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.catagories.items.Item;
import com.stronans.pilgrim.data.model.columns.abstracts.Column;

public class SizeColumn extends Column
{
	private static final String NAMECOLUMN = "Size";
	private String columnTitle = NAMECOLUMN;

	public SizeColumn(int index, Alignment alignment)
	{
		super(NAMECOLUMN, index, alignment);
	}

	public SizeColumn(int index)
	{
		this(index, Alignment.RIGHT);
	}

	@Override
	public String getDisplay(Items item)
	{
		String result = "";

		if (item instanceof Item)
		{
			long size = ((Item) item).getSize();

			switch (Configuration.getScale())
			{
			case SHOWSCALED:
				break;
				
			default:
				columnTitle = NAMECOLUMN + " (Bytes)";
				setTitle(columnTitle);
				break;
			}
			
			NumberFormat display = NumberFormat.getInstance();

			result = display.format(size); 
		}

		return result;
	}
}
