/**
 * 
 */
package com.stronans.pilgrim.ui.model.columns.abstracts;

import com.stronans.pilgrim.data.model.Alignment;
import com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface;

/**
 * @author SimonKing
 *
 */
public abstract class Column implements ColumnInterface
{
	private String title;
	private int index;
	private Alignment alignment;

	public Column(String name, int index, Alignment alignment)
	{
		this.title = name;
		this.index = index;
		this.alignment = alignment;
	}

	public Column(String name, int index)
	{
		this(name, index, Alignment.LEFT);
	}

	public Column(String name)
	{
		this(name, 0, Alignment.LEFT);
	}

	@Override
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the index
	 */
	public int getIndex()
	{
		return index;
	}

	/* (non-Javadoc)
	 * @see com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface#getAlignment()
	 */
	@Override
	public Alignment getAlignment()
	{
		return alignment;
	}
}
