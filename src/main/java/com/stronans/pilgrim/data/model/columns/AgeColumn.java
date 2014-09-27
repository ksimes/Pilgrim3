package com.stronans.pilgrim.data.model.columns;

import com.stronans.pilgrim.data.model.Alignment;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.columns.abstracts.Column;

public class AgeColumn extends Column
{
	private static final String NAMECOLUMN = "Name";

	public AgeColumn(int index, Alignment alignment)
	{
		super(NAMECOLUMN, index, alignment);
	}

	public AgeColumn(int index)
	{
		this(index, Alignment.LEFT);
	}

	@Override
	public String getDisplay(Items item)
	{
		return item.getName();
	}
}
