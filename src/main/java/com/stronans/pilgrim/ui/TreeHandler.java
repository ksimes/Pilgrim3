package com.stronans.pilgrim.ui;
/*
 * Pilgrim Explorer III
 *
 * Copyright  1998-2014  Cathcart Software Limited.  All Rights Reserved.
 */

import java.util.List;

import com.stronans.pilgrim.data.model.catagories.OverviewCategory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.interfaces.ItemSpecific;

public class TreeHandler {
    // private static final Logger logger = Logger.getLogger(TreeHandler.class);
    private static final String DUMMY = "DUMMY";
    private Window window;
    private Tree tree;

    public TreeHandler(Window window) {
        this.window = window;
        tree = new Tree(window.getFrame(), SWT.BORDER);
        GridData gridData = new GridData(GridData.FILL, SWT.FILL, true, true);
        tree.setLayoutData(gridData);

        buildRoots();
        setupSelectors();
    }

    public void locateFolder(ItemSpecific specifier) {

    }

    private void buildRoots() {
        OverviewCategory overView = new OverviewCategory();
        TreeItem iItem = new TreeItem(tree, SWT.NONE);
        iItem.setData(overView);
        iItem.setText(overView.getName());
        iItem.setImage(overView.getIcon());

        buildBranches(iItem, overView);
    }

    private boolean hasDummyNode(TreeItem branch) {
        boolean result = false;

        if (branch.getItemCount() == 1) {
            TreeItem node = branch.getItem(0);
            if (node.getText().equals(DUMMY))
                result = true;
        }

        return result;
    }

    private void createBranchNode(TreeItem branch, ItemSpecific item) {
        TreeItem iItem = new TreeItem(branch, SWT.NONE);
        iItem.setData(item);
        iItem.setText(item.getName());

        if (item.getIcon() != null)
            iItem.setImage(item.getIcon());

        if (item.hasTreeChildren()) {
            addDummyNode(iItem);
        }
    }

    private boolean notPresent(TreeItem branch, ItemSpecific specifier) {
        boolean result = true;

        TreeItem nodes[] = branch.getItems();

        for (TreeItem node : nodes) {
            ItemSpecifier data = (ItemSpecifier) node.getData();
            if (data.equals(specifier))
                result = false;
        }

        return result;
    }

    private void buildBranches(TreeItem branch, ItemSpecific specifier) {
        if (hasDummyNode(branch)) {
            // First remove the dummy node
            TreeItem node = branch.getItem(0);
            if (node.getText().equals(DUMMY)) {
                node.dispose();
            }
        }

        List<ItemSpecific> itemList = specifier.getTreeListing();

        if (itemList != null) {
            for (ItemSpecific item : itemList) {
                if (notPresent(branch, item)) {
                    createBranchNode(branch, item);
                }
            }
        }
    }

    private void addDummyNode(TreeItem branch) {
        TreeItem newItem = new TreeItem(branch, SWT.NONE);
        newItem.setText(DUMMY);
    }

    private void processNode(TreeItem branch) {
        ItemSpecifier specifier = ((ItemSpecifier) branch.getData());

        if (!branch.getExpanded()) {
            buildBranches(branch, specifier);
        }

        window.setTo(specifier);
    }

    private void setupSelectors() {
        tree.addTreeListener(new TreeListener() {

            @Override
            public void treeCollapsed(TreeEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void treeExpanded(TreeEvent arg0) {
                TreeItem branch = (TreeItem) arg0.item;
                processNode(branch);
            }

        });

        tree.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
                widgetSelected(arg0);
            }

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                TreeItem branch = (TreeItem) arg0.item;
                processNode(branch);
            }
        });
    }
}
