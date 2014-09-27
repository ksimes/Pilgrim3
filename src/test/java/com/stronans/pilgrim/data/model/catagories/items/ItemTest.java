package com.stronans.pilgrim.data.model.catagories.items;

import com.stronans.pilgrim.data.model.StaticData;
import external.DisplayHelper;
import junit.framework.TestCase;
import org.eclipse.swt.widgets.Display;
import org.junit.Rule;

import java.io.File;
import java.net.URL;
import java.util.Date;

public class ItemTest extends TestCase {

    @Rule
    public final DisplayHelper displayHelper = new DisplayHelper();

    Item newItem;

    public void setUp() throws Exception {
        super.setUp();
        Display display = displayHelper.getDisplay();
        new StaticData(display);

        ClassLoader here = this.getClass().getClassLoader();
        URL testFile = here.getResource("com/stronans/pilgrim/data/model/items/test.txt");

        newItem = new Item(new File(testFile.toURI()));
    }

    public void tearDown() throws Exception {
        newItem = null;
    }

    public void testGetDescription() throws Exception {
        String s = newItem.getDescription();

        assertNotNull(s);
        assertEquals("TXT File", s);
    }

    public void testGetSize() throws Exception {
        long l = newItem.getSize();

        assertTrue(l != 0);
    }

    public void testGetName() throws Exception {
        String s = newItem.getName();

        assertNotNull(s);
        assertEquals("test.txt", s);
    }

    public void testGetModified() throws Exception {
        Date d = newItem.getModified();

        assertNotNull(d);
        assertTrue(d.before(new Date()));
    }

    public void testGetUpdated() throws Exception {

    }

    public void testGetFile() throws Exception {
        File f= newItem.getFile();

        assertNotNull(f);
    }

    public void testGetIcon() throws Exception {

    }
}