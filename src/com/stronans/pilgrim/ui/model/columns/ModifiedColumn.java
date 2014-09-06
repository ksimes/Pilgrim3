package com.stronans.pilgrim.ui.model.columns;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.items.Item;
import com.stronans.pilgrim.ui.model.columns.abstracts.Column;

public class ModifiedColumn extends Column
{
	private static final String NAMECOLUMN = "Last Modified";
	private static final String pattern = "dd/MM/yyyy HH:mm:ss"; 

	public ModifiedColumn(int index)
	{
		super(NAMECOLUMN, index);
	}

	@Override
	public String getDisplay(Items item)
	{
		String result = "";

		if (item instanceof Item)
		{
			Date date = ((Item) item).getModified();

			SimpleDateFormat formatter = new SimpleDateFormat(pattern);

			result = formatter.format(date); 
		}

		return result;
	}
}
