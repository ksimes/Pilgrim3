package com.stronans.pilgrim.data.model;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.util.Map;
import java.util.Properties;

import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class StaticData {
    private static final Logger logger = Logger.getLogger(StaticData.class);

    public static final int SHELL = 0;
    public static final int OVERVIEW = 1;
    public static final int NETWORK = 2;
    public static final int MACHINE = 3;
    public static final int DRIVE = 4;
    public static final int FOLDERCLOSED = 5;
    public static final int FOLDEROPEN = 6;
    public static final int UPDIR = 7;

    private static Map<String, String> environment = System.getenv();
    private static Properties properties = System.getProperties();

    private static Image[] Icons = null;

    public StaticData(Display display) {
        getDefaultIcons(display);
        setupNativeLib();

//        for (String envName : environment.keySet()) {
//            System.out.format("%s=%s%n",
//                    envName,
//                    environment.get(envName));
//        }
//
//        System.out.println();
//        System.out.println();
//        System.getProperties().list(System.out);
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
            logger.info("Loaded: " + LibraryName + ".dll");
        } catch (Throwable e) {
            logger.error("Failed to initialise Application. Failure loading "
                    + LibraryName + ".dll. Application will exit", e);
            System.out.println("Failed to load Native Lib: " + LibraryName);
            System.exit(-1);
        }
    }

    public static native boolean hasChildFolders(String path);

    public static String getEnvironment(String key) {
        return Strings.nullToEmpty(environment.get(key));
    }

    public static String getProperty(String key) {
        Object property = properties.get(key);
        if (property instanceof String) {
            return Strings.nullToEmpty((String) property);
        } else {
            return "";
        }
    }

    public static Image getIcon(int key) {
        return Icons[key];
    }

    private static void getDefaultIcons(Display display) {
        Icons = new Image[10];
        ClassLoader here = StaticData.class.getClassLoader();
        Icons[SHELL] = new Image(display, here.getResourceAsStream("icons/smallshell.png"));
        Icons[OVERVIEW] = new Image(display, here.getResourceAsStream("icons/overview.png"));
        Icons[MACHINE] = new Image(display, here.getResourceAsStream("icons/machine.png"));
        Icons[NETWORK] = new Image(display, here.getResourceAsStream("icons/network.png"));
        Icons[DRIVE] = new Image(display, here.getResourceAsStream("icons/harddrive.png"));
        Icons[FOLDERCLOSED] = new Image(display, here.getResourceAsStream("icons/folderclosed.png"));
        Icons[FOLDEROPEN] = new Image(display, here.getResourceAsStream("icons/folderopen.png"));
        Icons[UPDIR] = new Image(display, here.getResourceAsStream("icons/updir.png"));
    }
}
