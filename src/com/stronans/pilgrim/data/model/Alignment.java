package com.stronans.pilgrim.data.model;

import org.eclipse.swt.SWT;

public enum Alignment
{
	LEFT(SWT.LEFT),
	RIGHT(SWT.RIGHT),
	CENTER(SWT.CENTER);
	
	private int value;
	
	Alignment(int value)
	{
		this.value = value;
	}
	
	public int underlying()
	{
		return value;
	}
}
