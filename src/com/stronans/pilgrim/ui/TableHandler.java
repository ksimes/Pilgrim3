package com.stronans.pilgrim.ui;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.stronans.pilgrim.data.model.abstracts.ItemSpecifier;
import com.stronans.pilgrim.data.model.interfaces.Items;
import com.stronans.pilgrim.ui.model.columns.interfaces.ColumnInterface;

public class TableHandler
{
	private Table table;
	private Window window;

	public TableHandler(Window window)
	{
		this.window = window;
		table = new Table(window.getFrame(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		// table.setLinesVisible(true);
		table.setHeaderVisible(true);

		GridData gridDataTable = new GridData(GridData.FILL, SWT.FILL, true, true);
		table.setLayoutData(gridDataTable);
		setupSelectors();
	}

	public void buildTable(ItemSpecifier specifier)
	{
		List<ColumnInterface> columns = specifier.getColumns();

		if (columns != null)
		{
			table.setRedraw(false);

			table.clearAll();
			table.removeAll();

			while (table.getColumnCount() > 0)
			{
				table.getColumns()[0].dispose();
			}

			for (ColumnInterface column : columns)
			{
				TableColumn newColumn = new TableColumn(table, column.getAlignment().underlying());
				newColumn.setText(column.getTitle());
			}

			List<Items> listing = specifier.getTableListing(null);

			table.setRedraw(true);

			if (listing != null)
			{
				for (Items item : listing)
				{
					TableItem newDisplayItem = new TableItem(table, SWT.NONE);
					newDisplayItem.setImage(item.getIcon());

					for (int i = 0; i < columns.size(); i++)
					{
						newDisplayItem.setText(i, columns.get(i).getDisplay(item));
					}
				}
			}

			for (int i = 0; i < columns.size(); i++)
			{
				table.getColumn(i).pack();
			}
		}
	}

	private void setupSelectors()
	{
		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				widgetSelected(arg0);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
			}
		});
	}
}
