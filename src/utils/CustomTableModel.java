package utils;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel{

    public CustomTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
