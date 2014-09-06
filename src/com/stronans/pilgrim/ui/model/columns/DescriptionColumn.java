package com.stronans.pilgrim.ui.model.columns;

import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.abstracts.Column;

public class DescriptionColumn extends Column
{
	private static final String COLUMN_NAME = "Description";

	public DescriptionColumn(int index)
	{
		super(COLUMN_NAME, index);
	}

	@Override
	public String getDisplay(Items item)
	{
		return item.getDescription();
	}
}
