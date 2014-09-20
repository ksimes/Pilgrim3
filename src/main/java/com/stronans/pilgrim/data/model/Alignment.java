package com.stronans.pilgrim.data.model;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

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
