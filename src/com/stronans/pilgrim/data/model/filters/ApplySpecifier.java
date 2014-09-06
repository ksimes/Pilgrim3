package com.stronans.pilgrim.data.model.filters;

import java.io.File;
import java.io.FileFilter;

public class ApplySpecifier implements FileFilter
{
	private String specifier;
	
	public ApplySpecifier(String specifier)
	{
		super();
		this.specifier = specifier;
	}

	public boolean accept(File pathname)
	{
		return pathname.getName().matches(specifier);
	}
}
