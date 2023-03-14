package com.agency.Helper;

import javax.swing.table.DefaultTableModel;

public class DatabaseTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0) { // don't edit the first column (id column)
            return false;
        }
        return super.isCellEditable(row, column);
    }
}
