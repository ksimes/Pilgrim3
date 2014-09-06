package com.stronans.pilgrim.ui.model.columns;

import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.abstracts.Column;

public class NameColumn extends Column
{
	private static final String NAMECOLUMN = "Name";

	public NameColumn(int index)
	{
		super(NAMECOLUMN, index);
	}

	@Override
	public String getDisplay(Items item)
	{
		return item.getName();
	}
}
