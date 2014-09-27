package com.stronans.pilgrim;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import com.stronans.pilgrim.data.model.Configuration;
import com.stronans.pilgrim.data.model.StaticData;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.Properties;

public final class Startup {
    private static final Logger logger = Logger.getLogger(Startup.class);

    /**
     * Creates and starts the SWT message processing loop.
     *
     * @param display SWT Display object.
     */
    public Startup(Display display) {
        // Static data for program including local binary libraries
        new StaticData(display);
        // read in the current set of folder configurations plus any display characteristics
        // such as layout position or columns in sub-windows.
        new Configuration(display);

        Shell shell = Configuration.getShell();

        shell.open();

        // SWT Shell processing loop.
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Handles the loading of the log4j configuration. properties file must be
     * on the classpath.
     *
     * @throws RuntimeException
     */
    private static void initLogging() throws RuntimeException {
        try {
            Properties properties = new Properties();
            properties.load(Startup.class.getClassLoader().getResourceAsStream("log4j.properties"));
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to load logging properties for System");
        }
    }

    /**
     * @param args command line parameters
     */
    public static void main(String[] args) {
        try {
            initLogging();
        } catch (RuntimeException ex) {
            System.out.println("Error setting up log4j logging");
            System.out.println("Application will continue but without any logging.");
        }

        Display display = new Display();
        new Startup(display);
        display.dispose();
    }
}
