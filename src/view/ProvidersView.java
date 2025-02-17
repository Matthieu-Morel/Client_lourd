package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controler.FournisseurController;
import model.Provider;
import model.DAO.FournisseurDAO;
import utils.ButtonEditor;
import utils.ButtonRenderer;
import utils.CustomTableModel;

public class ProvidersView {
    private JDialog frame;
    private JLabel titleLabel;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JTable providersTable;
    private ArrayList<Provider> providers;

    public ProvidersView(JFrame jFrame){
        frame = new JDialog(jFrame, "Fournisseurs", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 1, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        titleLabel = new JLabel("Liste des fournisseurs", SwingConstants.CENTER);

        String[] columnNames = {
            "Nom du fournisseur",
            "Adresse",
            "Numéro de téléphone",
        };

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        FournisseurController fournisseurController = new FournisseurController(this, fournisseurDAO);

        List<Object[]> data = new ArrayList<>();
        for (Provider provider : providers) {
            String[] providerData = {
                provider.getName(),
                provider.getAddress(),
                provider.getPhone(),
            };
            data.add(providerData);
        }

        // ProvidersView providersView = this;
        
        // ButtonEditor updateButtonEditor = new ButtonEditor("Modifier", frame, this);

        // ActionListener updateActionListener = new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         TableModel model = updateButtonEditor.getTable().getModel();
        //         int columnsCount = model.getColumnCount() - 2;
        //         String[] dataRow = new String[columnsCount];
        //         int row = updateButtonEditor.getRow();
        //         for (int i = 0; i < columnsCount; i++) {
        //             String data = model.getValueAt(row, i).toString();
        //             dataRow[i] = data;
        //         }
        //         Provider provider = new Provider(dataRow[0], dataRow[1], dataRow[2]);
        //         UpdateProviderView view = new UpdateProviderView(frame, providersView, provider, row);
        //     }
        // };

        // updateButtonEditor.addButtonActionListener(updateActionListener);
        
        // ButtonEditor deleteButtonEditor = new ButtonEditor("Supprimer", frame, this);

        // ActionListener deleteActionListener = new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         DefaultTableModel model = (DefaultTableModel) updateButtonEditor.getTable().getModel();
        //         int row = deleteButtonEditor.getRow();
        //         model.removeRow(row);
        //     }
        // };

        // deleteButtonEditor.addButtonActionListener(deleteActionListener);

        // DefaultTableModel model = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        CustomTableModel model = new CustomTableModel(data.toArray(new Object[0][]), columnNames);
        providersTable = new JTable(model);
        // providersTable.getColumn("Modification").setCellRenderer(new ButtonRenderer("Modifier"));
        // providersTable.getColumn("Modification").setCellEditor(updateButtonEditor);
        JScrollPane jScrollPane = new JScrollPane(providersTable);

        ProvidersView parentView = this;
        btnAdd = new JButton("Ajouter un fournisseur");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProviderView view = new AddProviderView(frame, parentView);
            }
        });

        btnUpdate = new JButton("Modifier un fournisseur");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = providersTable.getSelectedRow();
                if (row != -1) {
                    Provider provider = providers.get(row);
                    UpdateProviderView view = new UpdateProviderView(frame, parentView, provider, row);
                }
            }
            
        });

        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS));
        btnContainer.add(btnAdd);
        btnContainer.add(btnUpdate);

        contentPanel.add(titleLabel);
        contentPanel.add(jScrollPane);
        contentPanel.add(btnContainer);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setSize(750, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    public int getProvidersCount() {
        return providers.size();
    }

    public void addProviderToTable(Provider provider) {
        DefaultTableModel tableModel = (DefaultTableModel) providersTable.getModel();
        String[] providerData = {
            provider.getName(),
            provider.getAddress(),
            provider.getPhone(),
        };
        tableModel.addRow(providerData);
        providers.add(provider);
    }

    public void updateProvider(int index, Provider provider) {
        DefaultTableModel tableModel = (DefaultTableModel) providersTable.getModel();
        String[] providerData = {
            provider.getName(),
            provider.getAddress(),
            provider.getPhone(),
        };
        tableModel.removeRow(index);
        tableModel.insertRow(index, providerData);
        providers.set(index, provider);
    }
}