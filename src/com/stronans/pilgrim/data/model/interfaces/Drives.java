package com.stronans.pilgrim.data.model.interfaces;

public interface Drives extends Items
{
	long getTotalSpace();
	long getFreeSpace();
	int percentageFull();
	String type();
}
