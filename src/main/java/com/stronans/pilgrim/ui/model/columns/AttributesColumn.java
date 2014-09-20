package com.stronans.pilgrim.ui.model.columns;

import com.stronans.pilgrim.data.model.Alignment;
import com.stronans.pilgrim.data.model.interfaces.Files;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.abstracts.Column;

public class AttributesColumn extends Column
{
	private static final String NAMECOLUMN = "Attributes";

	public AttributesColumn(int index, Alignment alignment)
	{
		super(NAMECOLUMN, index, alignment);
	}

	public AttributesColumn(int index)
	{
		this(index, Alignment.LEFT);
	}

	private String thisAttribute(boolean status, String display)
	{
		if (status)
			return display;
		else
			return " ";
	}

	@Override
	public String getDisplay(Items item)
	{
		StringBuffer attributes = new StringBuffer(10);

		if(item instanceof Files)
		{
			Files file = (Files)item; 
			attributes.append(thisAttribute(file.getFile().isHidden(), "H"));
		}
		
		return attributes.toString();
	}
}
