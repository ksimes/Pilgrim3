package com.stronans.pilgrim.data.model;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.stronans.pilgrim.ui.MainPanel;

public class Configuration {
    private static Display display;
    private static MainPanel panel;
    private static Shell shell;

    public Configuration(Display localDisplay) {
        display = localDisplay;
        shell = new Shell(display);
        shell.setImage(StaticData.getIcon(StaticData.SHELL));
        GridLayout shellLayout = new GridLayout();
        shellLayout.numColumns = 1;
        shell.setLayout(shellLayout);

        panel = new MainPanel(shell);
    }

    public static MainPanel getMasterPanel() {
        return panel;
    }

    public static Shell getShell() {
        return shell;
    }

    public static Scaling getScale() {
        return Scaling.SHOWINBYTES;
    }

    public static Display getGlobalDisplay() {
        return display;
    }
}
