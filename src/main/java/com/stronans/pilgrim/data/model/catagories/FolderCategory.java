package com.stronans.pilgrim.data.model.catagories;

import com.stronans.pilgrim.data.model.columns.*;
import com.stronans.pilgrim.data.model.columns.interfaces.ColumnInterface;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by S.King on 27/09/2014.
 */
public final class FolderCategory {
    private final static ColumnInterface FolderColumns[] = {new NameColumn(0), new DescriptionColumn(1),
            new SizeColumn(2), new ModifiedColumn(3), new AttributesColumn(4)};

    static public List<ColumnInterface> getColumns() {
        return Arrays.asList(FolderColumns);
    }
}
