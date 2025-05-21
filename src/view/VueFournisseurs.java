package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.FournisseurControleur;
import model.Fournisseur;
import model.Utilisateur;
import model.DAO.FournisseurDAO;
import utils.ModeleTablePersonnalise;

public class VueFournisseurs {
    private JDialog frame;
    private JLabel titleLabel;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnBackToMenu;
    private JTable providersTable;
    private ArrayList<Fournisseur> providers;

    public VueFournisseurs(JFrame jFrame, Utilisateur utilisateur){
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
        FournisseurControleur fournisseurController = new FournisseurControleur(this, fournisseurDAO);

        List<Object[]> data = new ArrayList<>();
        for (Fournisseur provider : providers) {
            String[] providerData = {
                provider.getName(),
                provider.getAddress(),
                provider.getPhone(),
            };
            data.add(providerData);
        }

        ModeleTablePersonnalise model = new ModeleTablePersonnalise(data.toArray(new Object[0][]), columnNames);
        providersTable = new JTable(model);
        providersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(providersTable);

        VueFournisseurs parentView = this;
        btnAdd = new JButton("Ajouter un fournisseur");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueAjoutFournisseur view = new VueAjoutFournisseur(frame, parentView);
            }
        });

        btnUpdate = new JButton("Modifier un fournisseur");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = providersTable.getSelectedRow();
                if (row != -1) {
                    Fournisseur provider = providers.get(row);
                    VueModifierFournisseur view = new VueModifierFournisseur(frame, parentView, provider, row);
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
                    Fournisseur provider = providers.get(row);
                    VueSupprimerFournisseur view = new VueSupprimerFournisseur(frame, parentView, provider, row);
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

    public void setProviders(ArrayList<Fournisseur> providers) {
        this.providers = providers;
    }

    public int getProvidersCount() {
        return providers.size();
    }

    public void addProviderToTable(Fournisseur provider) {
        DefaultTableModel tableModel = (DefaultTableModel) providersTable.getModel();
        String[] providerData = {
            provider.getName(),
            provider.getAddress(),
            provider.getPhone(),
        };
        tableModel.addRow(providerData);
        providers.add(provider);
    }

    public void updateProvider(int index, Fournisseur provider) {
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