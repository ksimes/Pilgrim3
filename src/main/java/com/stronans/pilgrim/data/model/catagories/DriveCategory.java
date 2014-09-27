package com.stronans.pilgrim.data.model.catagories;

import com.stronans.pilgrim.data.model.catagories.items.Drive;
import com.stronans.pilgrim.data.model.catagories.items.LocalMachine;
import com.stronans.pilgrim.data.model.columns.*;
import com.stronans.pilgrim.data.model.columns.interfaces.ColumnInterface;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controls the gathering of information for the drives and other devices connected to a machine.
 * <p/>
 * Created by S.King on 27/09/2014.
 */
public final class DriveCategory {
    private static List<ItemSpecific> driveListAsSpec = new ArrayList<>();
    private static List<Items> driveListAsItems = new ArrayList<>();

    private static final ColumnInterface columns[] = {new NameColumn(0), new DescriptionColumn(1),
            new FreeSpaceColumn(2), new TotalSpaceColumn(3), new PercentageFullColumn(4)};

    private DriveCategory() {
    }

    private static void getDrives() {
        File[] drives = File.listRoots();
        driveListAsItems = new ArrayList<>();

        for (File aDrive : drives) {
            Drive drive = new Drive(aDrive, LocalMachine.DEFAULT_SPECIFER);
            driveListAsSpec.add(drive);
            driveListAsItems.add(drive);
        }
    }

    public static List<ItemSpecific> getDriveList() {
        // check to see if anything has been added to drivelist
        if (File.listRoots().length != driveListAsSpec.size()) {
            // refresh the drivelist
            getDrives();
        }

        return driveListAsSpec;
    }

    public static List<Items> getDriveListAsItems() {
        return driveListAsItems;
    }

    static public List<ColumnInterface> getColumns() {
        return Arrays.asList(columns);
    }
}
