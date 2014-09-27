package com.stronans.pilgrim.ui;

import com.stronans.pilgrim.data.model.Scaling;
import com.stronans.pilgrim.data.model.StaticData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 *
 * Created by S.King on 27/09/2014.
 */
public class UIConfiguration {
    private static Display display;
    private static MainPanel panel;
    private static Shell shell;

    public UIConfiguration(Display localDisplay) {
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

    public static Display getGlobalDisplay() {
        return display;
    }
}
