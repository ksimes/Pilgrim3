package com.stronans.pilgrim;

import com.stronans.pilgrim.data.model.Configuration;
import com.stronans.pilgrim.data.model.StaticData;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public final class Startup {
    public Startup(Display display) {
        new StaticData(display);
        new Configuration(display);

        Shell shell = Configuration.getShell();

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    private static void initLogging() throws RuntimeException {
        try {
            PropertyConfigurator.configure("./log4j.properties");
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
