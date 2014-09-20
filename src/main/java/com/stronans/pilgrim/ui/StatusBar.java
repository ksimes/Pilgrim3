package com.stronans.pilgrim.ui;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

public class StatusBar extends Composite
{

	public StatusBar(Composite parent, int flags)
	{
		super(parent, flags);
	    RowLayout layout = new RowLayout();
	    layout.type = SWT.HORIZONTAL;
		this.setLayout(layout);
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Composite#computeSize(int, int, boolean)
	 */
	@Override
	public Point computeSize(int arg0, int arg1, boolean arg2)
	{
		return super.computeSize(arg0, 20, arg2);
	}

}
