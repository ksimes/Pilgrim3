package com.stronans.pilgrim.data.model.interfaces;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

public interface Drives extends Items
{
	long getTotalSpace();
	long getFreeSpace();
	int percentageFull();
	String type();
}
