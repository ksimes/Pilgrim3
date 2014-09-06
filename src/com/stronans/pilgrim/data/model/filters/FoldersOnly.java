package com.stronans.pilgrim.data.model.filters;

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
