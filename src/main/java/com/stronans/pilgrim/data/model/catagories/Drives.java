package com.stronans.pilgrim.data.model.catagories;

import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.catagories.items.Drive;
import com.stronans.pilgrim.data.model.catagories.items.LocalMachine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the gathering of information for the drives and other devices connected to a machine.
 *
 * Created by S.King on 27/09/2014.
 */
public class Drives {
    private static List<ItemSpecific> driveListAsSpec = new ArrayList<>();
    private static List<Items> driveListAsItems = new ArrayList<>();

    private Drives()
    {
        getDrives();
    }

    private static void getDrives() {
        File[] drives = File.listRoots();

        for (File aDrive : drives) {
            Drive drive = new Drive(aDrive, LocalMachine.DEFAULT_SPECIFER);
            driveListAsSpec.add(drive);
            driveListAsItems.add(drive);
        }
    }

    public static List<ItemSpecific> getDriveList() {
        if (driveListAsSpec == null) {
            getDrives();
        } else
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
}
