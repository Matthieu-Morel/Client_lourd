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

import controller.ProduitControleur;
import model.Produit;
import model.Fournisseur;
import model.Utilisateur;
import model.DAO.FournisseurDAO;
import model.DAO.ProduitDAO;
import utils.ModeleTablePersonnalise;

public class VueProduits {
    private JDialog frame;
    private JLabel titleLabel;
    private JTable productsTable;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnBackToMenu;
    private ArrayList<Produit> products;
    private ArrayList<Fournisseur> providers;

    public VueProduits(JFrame jFrame, Utilisateur utilisateur) {
        frame = new JDialog(jFrame, "Gestion des produits", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        titleLabel = new JLabel("Liste des produits", SwingConstants.CENTER);

        String[] columnNames = {
            "Nom du produit",
            "Quantité en stock",
            "Prix unitaire",
            "Fournisseur",
        };

        ProduitDAO produitDAO = new ProduitDAO();
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        ProduitControleur produitController = new ProduitControleur(this, produitDAO, fournisseurDAO);

        List<Object[]> data = new ArrayList<>();
        for (Produit product : products) {
            String[] providerData = {
                product.getName(),
                Integer.toString(product.getQuantity()),
                Double.toString(product.getUnitPrice()),
                product.getProvider().getName(),
            };
            data.add(providerData);
        }

        ModeleTablePersonnalise model = new ModeleTablePersonnalise(data.toArray(new Object[0][]), columnNames);
        productsTable = new JTable(model);
        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(productsTable);

        VueProduits parentView = this;
        btnAdd = new JButton("Ajouter un produit");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueAjoutProduit view = new VueAjoutProduit(frame, parentView, providers);
            }
        });

        btnUpdate = new JButton("Modifier un produit");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = productsTable.getSelectedRow();
                if (row != -1) {
                    Produit product = products.get(row);
                    VueModifierProduit view = new VueModifierProduit(frame, parentView, product, providers, row);
                }
            }
        });

        btnDelete = new JButton("Supprimer un produit");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = productsTable.getSelectedRow();
                if (row != -1) {
                    Produit product = products.get(row);
                    VueSupprimerProduit view = new VueSupprimerProduit(frame, parentView, product, row);
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

        productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = productsTable.getSelectedRow();
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

    public void setProducts(ArrayList<Produit> products) {
        this.products = products;
    }

    public void setProviders(ArrayList<Fournisseur> providers) {
        this.providers = providers;
    }

    public void addProductToTable(Produit product) {
        DefaultTableModel tableModel = (DefaultTableModel) productsTable.getModel();
        String[] productData = {
            product.getName(),
            Integer.toString(product.getQuantity()),
            Double.toString(product.getUnitPrice()),
            product.getProvider().getName(),
        };
        tableModel.addRow(productData);
        products.add(product);
    }

    public void updateProduct(int index, Produit product) {
        DefaultTableModel tableModel = (DefaultTableModel) productsTable.getModel();
        String[] productData = {
            product.getName(),
            Integer.toString(product.getQuantity()),
            Double.toString(product.getUnitPrice()),
            product.getProvider().getName(),
        };
        tableModel.removeRow(index);
        tableModel.insertRow(index, productData);
        products.set(index, product);
    }

    public void deleteProduct(int index) {
        DefaultTableModel tableModel = (DefaultTableModel) productsTable.getModel();
        tableModel.removeRow(index);
        products.remove(index);
    }
}
