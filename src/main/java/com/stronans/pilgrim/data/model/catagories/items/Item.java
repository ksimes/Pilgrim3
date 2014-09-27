package com.stronans.pilgrim.data.model.catagories.items;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.io.File;
import java.util.Date;

import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;
import org.eclipse.swt.graphics.Image;

import com.stronans.pilgrim.data.model.interfaces.Files;

/**
 * Implements a class which holds those attribute which are not available from the standard Java File object.
 * At the moment uses Swing facilities to access file on disk for some of this information, but in the near future
 * will use Native routines for Windows/Linux/etc. written inhouse.
 */
public class Item implements Files {
    private static final Logger logger = Logger.getLogger(Item.class);

    private String description;
    private File file;
    private Date lastModified;
    private Image icon;

    public Item(File file) {
        this.file = file;
        description = FileSystemView.getFileSystemView().getSystemTypeDescription(file);
        if (description == null)
            description = "";

        lastModified = new Date(file.lastModified());
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public long getSize() {
        return file.length();
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public Date getModified() {
        return lastModified;
    }

    @Override
    public Date getUpdated() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public File getFile() {
        return file;
    }

    /**
     * @return the icon
     */
    public Image getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
