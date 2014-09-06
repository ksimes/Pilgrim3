package com.stronans.pilgrim.data.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.data.model.items.Drive;
import com.stronans.pilgrim.data.model.items.LocalMachine;

public class StaticData {
    private static final Logger logger = Logger.getLogger(StaticData.class);
    public static final String USERNAME = "USERNAME";
    public static final String USERDOMAIN = "USERDOMAIN";
    public static final String COMPUTERNAME = "COMPUTERNAME";

    public static final int SHELL = 0;
    public static final int OVERVIEW = 1;
    public static final int NETWORK = 2;
    public static final int MACHINE = 3;
    public static final int DRIVE = 4;
    public static final int FOLDERCLOSED = 5;
    public static final int FOLDEROPEN = 6;
    public static final int UPDIR = 7;

    private static List<ItemSpecific> driveListAsSpec = null;
    private static List<Items> driveListAsItems = null;
    private static Map<String, String> environment = System.getenv();

    private static Image[] Icons = null;

    public StaticData(Display display) {
        getDefaultIcons(display);
        getDrives();
        setupNativeLib();
    }

    // Native Library name
    private static final String JNI_LIB = "PGUtilities";

    private void setupNativeLib() throws RuntimeException {

        // Sun/Oracle JVM specific. May not work as expected for other OS.
        int bitSize = new Integer(System.getProperty("sun.arch.data.model"));
        String LibraryName = JNI_LIB + "_" + bitSize;

        try {
            logger.info("Loading native library: " + LibraryName + ".dll");
            System.loadLibrary(LibraryName);
            logger.info("Loaded native library: " + LibraryName + ".dll");
        } catch (Throwable e) {
            logger.error("Failed to initialise Application. Failure loading "
                    + LibraryName + ".dll. Application will exit", e);
            System.out.println("Failed to load Native Lib: " + LibraryName);
            System.exit(-1);
        }
    }

    public static native boolean hasChildFolders(String path);

    private static void getDrives() {
        driveListAsSpec = new ArrayList<>();
        driveListAsItems = new ArrayList<>();

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

    public static String getEnvironment(String key) {
        return environment.get(key);
    }

    public static Image getIcon(int key) {
        return Icons[key];
    }

    private static void getDefaultIcons(Display display) {
        Icons = new Image[10];
        Icons[SHELL] = new Image(display, "icons/smallshell.png");
        Icons[OVERVIEW] = new Image(display, "icons/overview.png");
        Icons[MACHINE] = new Image(display, "icons/machine.png");
        Icons[NETWORK] = new Image(display, "icons/network.png");
        Icons[DRIVE] = new Image(display, "icons/harddrive.png");
        Icons[FOLDERCLOSED] = new Image(display, "icons/folderclosed.png");
        Icons[FOLDEROPEN] = new Image(display, "icons/folderopen.png");
        Icons[UPDIR] = new Image(display, "icons/updir.png");
    }
}
