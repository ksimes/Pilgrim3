package com.stronans.pilgrim.data.model.interfaces;

import java.io.File;
import java.util.Date;

public interface Files extends Items
{
	long getSize();
	Date getModified();
	Date getUpdated();
	File getFile();
}
