package com.stronans.pilgrim.data.model.interfaces;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.io.File;
import java.util.Date;

public interface Files extends Items
{
	long getSize();
	Date getModified();
	Date getUpdated();
	File getFile();
}
