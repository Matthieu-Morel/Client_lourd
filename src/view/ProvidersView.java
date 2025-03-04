package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controler.FournisseurController;
import model.Provider;
import model.Utilisateur;
import model.DAO.FournisseurDAO;
import utils.CustomTableModel;

public class ProvidersView {
    private JDialog frame;
    private JLabel titleLabel;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnBackToMenu;
    private JTable providersTable;
    private ArrayList<Provider> providers;

    public ProvidersView(JFrame jFrame, Utilisateur utilisateur){
        frame = new JDialog(jFrame, "Gestion des fournisseurs", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
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

        CustomTableModel model = new CustomTableModel(data.toArray(new Object[0][]), columnNames);
        providersTable = new JTable(model);
        providersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        btnUpdate.setEnabled(false);
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

        btnDelete = new JButton("Supprimer un fournisseur");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = providersTable.getSelectedRow();
                if (row != -1) {
                    Provider provider = providers.get(row);
                    DeleteProviderView view = new DeleteProviderView(frame, parentView, provider, row);
                }
            }
            
        });

        btnBackToMenu = new JButton("Retour");
        btnBackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        providersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = providersTable.getSelectedRow();
                if (row != -1) {
                    btnUpdate.setEnabled(true);
                    if (!utilisateur.getRole().equals("manager")) {
                        btnDelete.setEnabled(true);
                    }
                }
                else{
                    btnUpdate.setEnabled(false);
                    btnDelete.setEnabled(false);
                }
            }
            
        });

        JPanel titleAndBackContainer = new JPanel();
        titleAndBackContainer.setLayout(new BorderLayout());
        titleAndBackContainer.add(btnBackToMenu, BorderLayout.WEST);
        titleAndBackContainer.add(titleLabel, BorderLayout.CENTER);
        titleAndBackContainer.add(Box.createRigidArea(new Dimension((int) btnBackToMenu.getPreferredSize().getWidth(), 0)), BorderLayout.EAST);

        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS));
        btnContainer.add(btnAdd);
        btnContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        btnContainer.add(btnUpdate);
        btnContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        btnContainer.add(btnDelete);

        contentPanel.add(titleAndBackContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(jScrollPane);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(btnContainer);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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

    public void deleteProvider(int index) {
        DefaultTableModel tableModel = (DefaultTableModel) providersTable.getModel();
        tableModel.removeRow(index);
        providers.remove(index);
    }
}