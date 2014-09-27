package com.stronans.pilgrim.data.model;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.stronans.pilgrim.ui.MainPanel;

public class Configuration {

    public Configuration() {
    }

    public static Scaling getScale() {
        return Scaling.SHOWINBYTES;
    }
}
