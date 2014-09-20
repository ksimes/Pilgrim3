package com.stronans.pilgrim.data.model.filters;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.io.File;
import java.io.FileFilter;

public class FoldersOnly implements FileFilter
{
	public boolean accept(File pathname)
	{
		boolean result = pathname.isDirectory();
		
		return result;
	}
}
