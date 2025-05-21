package utils;

import javax.swing.table.DefaultTableModel;

public class ModeleTablePersonnalise extends DefaultTableModel{

    public ModeleTablePersonnalise(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
