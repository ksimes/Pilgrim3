package com.stronans.pilgrim.data.model.catagories.items;

import com.stronans.pilgrim.data.model.StaticData;
import com.stronans.pilgrim.data.model.interfaces.Items;
import external.DisplayHelper;
import junit.framework.TestCase;
import org.eclipse.swt.widgets.Display;
import org.junit.Rule;

import java.io.File;

public class FolderTest extends TestCase {

    @Rule
    public final DisplayHelper displayHelper = new DisplayHelper();

    Items newFolderItem;

    public void setUp() throws Exception {
        super.setUp();
        Display display = displayHelper.getDisplay();
        new StaticData(display);

        newFolderItem = new Folder(new File("c:\\"), "*");
    }

    public void tearDown() throws Exception {
        newFolderItem = null;
    }

    public void testGetDescription() throws Exception {
        String s = newFolderItem.getDescription();

        assertNotNull( s );
        assertEquals("Local Disk", s);
    }

    public void testGetSize() throws Exception {

    }

    public void testGetName() throws Exception {

    }

    public void testGetTabDescription() throws Exception {

    }

    public void testGetTabToolTip() throws Exception {

    }

    public void testGetTableListing() throws Exception {

    }

    public void testGetTreeListing() throws Exception {

    }

    public void testHasTreeChildren() throws Exception {

    }

    public void testGetModified() throws Exception {

    }

    public void testGetUpdated() throws Exception {

    }

    public void testGetFile() throws Exception {

    }

    public void testGetColumns() throws Exception {

    }
}