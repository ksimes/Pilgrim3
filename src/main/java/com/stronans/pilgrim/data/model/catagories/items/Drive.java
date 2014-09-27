package com.stronans.pilgrim.data.model.catagories.items;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import com.stronans.pilgrim.data.model.ItemType;
import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.catagories.FolderCategory;
import com.stronans.pilgrim.data.model.filters.ApplySpecifier;
import com.stronans.pilgrim.data.model.filters.FoldersOnly;
import com.stronans.pilgrim.data.model.interfaces.Drives;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.columns.interfaces.ColumnInterface;

public final class Drive extends ItemSpecifier implements Drives {
    private final String description;
    private final String name;
    private final File item;
    private final long total, free;

    public Drive(File item, String specifier) {
        super(item.getAbsolutePath(), specifier, ItemType.DRIVES, StaticData.getIcon(StaticData.DRIVE));
        this.item = item;

        description = FileSystemView.getFileSystemView().getSystemTypeDescription(item);
        String tempName = FileSystemView.getFileSystemView().getSystemDisplayName(item);

        if (tempName.equals("")) {
            name = description;
        } else {
            name = tempName;
        }


        total = item.getTotalSpace();
        free = item.getUsableSpace();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getTabDescription() {
        return name;
    }

    @Override
    public String getTabToolTip() {
        return description;
    }

    @Override
    public long getTotalSpace() {
        return total;
    }

    @Override
    public long getFreeSpace() {
        return item.getUsableSpace();
    }

    @Override
    public int percentageFull() {
        long percent = 0;
        if (total != 0) {
            percent = 100 - (100 * free / total);
        }

        return (int) percent;
    }

    @Override
    public String type() {
        return "Unknown";
    }

    @Override
    public List<Items> getTableListing(FileFilter filter) {
        List<Items> itemList = new ArrayList<>();

        if (filter == null && !getSpecifier().equals("*")) {
            filter = new ApplySpecifier(getSpecifier());
        }

        File files[] = filter != null ? item.listFiles(filter) : item.listFiles();

        if (files != null) {
            for (File file : files) {
                Items newItem;
                if (file.isDirectory()) {
                    newItem = new Folder(file, getSpecifier());
                } else {
                    newItem = new Item(file);
                }

                itemList.add(newItem);
            }
        }

        return itemList;
    }

    @Override
    public List<ItemSpecific> getTreeListing() {
        List<ItemSpecific> itemList = new ArrayList<>();

        File files[] = item.listFiles(new FoldersOnly());

        if (files != null) {
            for (File file : files) {
                itemList.add(new Folder(file, getSpecifier()));
            }
        }

        return itemList;
    }

    @Override
    public boolean hasTreeChildren() {
        return StaticData.hasChildFolders(item.getAbsolutePath());
    }

    @Override
    public List<ColumnInterface> getColumns() {
        return FolderCategory.getColumns();
    }
}
