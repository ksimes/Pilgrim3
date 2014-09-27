package com.stronans.pilgrim.ui;

import junit.framework.TestCase;

public class WindowTest extends TestCase {
//    @Rule
//    public final DisplayHelper displayHelper = new DisplayHelper();
//
//    CTabFolder tabFolder;
//    StatusBar statusBar;
//    Shell shell;
//    ItemSpecifier currentSpecifier;
//
//    Display display = null;
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//
//        Display display = displayHelper.getDisplay();
//        new StaticData(display);
//
//        shell = new Shell(display);
//        shell.setImage(StaticData.getIcon(StaticData.SHELL));
//        GridLayout shellLayout = new GridLayout();
//        shellLayout.numColumns = 1;
//        shell.setLayout(shellLayout);
//
//        GridData tabData = new GridData();
//        tabData.horizontalAlignment = SWT.FILL;	/* grow to fill available width */
//        tabData.grabExcessHorizontalSpace = true;
//        tabData.verticalAlignment = SWT.FILL;	/* grow to fill available width */
//        tabData.grabExcessVerticalSpace = true;
//
//        tabFolder = new CTabFolder(shell, SWT.CLOSE);
//        tabFolder.setSelectionBackground(new Color[]{display.getSystemColor(SWT.COLOR_DARK_GRAY), display.getSystemColor(SWT.COLOR_GRAY)}, new int[]{100}, true);
//        tabFolder.setLayoutData(tabData);
//        tabFolder.setLayout(new FillLayout());
//
//        Shell shell = Configuration.getShell();
//
//        shell.open();
//
//        // SWT Shell processing loop.
//        while (!shell.isDisposed()) {
//            if (!display.readAndDispatch()) {
//                display.sleep();
//            }
//        }
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//        display.dispose();
//    }
//
//    @Test
//    public void testAddListener() throws Exception {
//
//    }
//
//    @Test
//    public void testGetTab() throws Exception {
//
//    }
//
//    @Test
//    public void testGetFrame() throws Exception {
//
//    }
//
//    @Test
//    public void testSetTo() throws Exception {
//
//        Window window = new Window(tabFolder, null);
//        currentSpecifier = new Folder(new File("C:\test.txt"), "*" );
//
//        window.setTo(currentSpecifier);
//    }
}