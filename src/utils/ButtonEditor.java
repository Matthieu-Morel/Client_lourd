package utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import model.Provider;
import view.ProvidersView;
import view.UpdateProviderView;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private String label;
    private int row;
    private JTable table;
    private JDialog jDialog;
    private ProvidersView providersView;

    public ButtonEditor(String label, JDialog jDialog, ProvidersView parentView) {
        this.label = label;
        this.jDialog = jDialog;
        this.providersView = parentView;
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        this.table = table;
        button.setText(label);
        return button;
    }   

    public void addButtonActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }

    public JTable getTable() {
        return table;
    }

    public int getRow() {
        return row;
    }
}