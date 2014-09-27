package com.stronans.pilgrim.data.model.columns.interfaces;

import com.stronans.pilgrim.data.model.Alignment;
import com.stronans.pilgrim.data.model.interfaces.Items;

public interface ColumnInterface
{
	public void setTitle(String title);
	public String getTitle();
	public String getDisplay(Items item);
	public int getIndex();
	public Alignment getAlignment();
}
