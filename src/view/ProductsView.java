package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

import controler.ProduitController;
import model.Product;
import model.Provider;
import model.Utilisateur;
import model.DAO.FournisseurDAO;
import model.DAO.ProduitDAO;
import utils.CustomTableModel;

public class ProductsView {
    private JDialog frame;
    private JLabel titleLabel;
    private JTable productsTable;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private ArrayList<Product> products;
    private ArrayList<Provider> providers;

    public ProductsView(JFrame jFrame, Utilisateur utilisateur) {
        frame = new JDialog(jFrame, "Gestion des Produits", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 1, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        titleLabel = new JLabel("Liste des Produits", SwingConstants.CENTER);

        String[] columnNames = {
            "Nom du produit",
            "Quantité en stock",
            "Prix unitaire",
            "Fournisseur",
        };

        ProduitDAO produitDAO = new ProduitDAO();
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        ProduitController produitController = new ProduitController(this, produitDAO, fournisseurDAO);

        List<Object[]> data = new ArrayList<>();
        for (Product product : products) {
            String[] providerData = {
                product.getName(),
                Integer.toString(product.getQuantity()),
                Double.toString(product.getUnitPrice()),
                product.getProvider().getName(),
            };
            data.add(providerData);
        }

        CustomTableModel model = new CustomTableModel(data.toArray(new Object[0][]), columnNames);
        productsTable = new JTable(model);
        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(productsTable);

        ProductsView parentView = this;
        btnAdd = new JButton("Ajouter un produit");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductView view = new AddProductView(jFrame, parentView, providers);
            }
        });

        btnUpdate = new JButton("Modifier un produit");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = productsTable.getSelectedRow();
                if (row != -1) {
                    Product product = products.get(row);
                    UpdateProductView view = new UpdateProductView(frame, parentView, product, providers, row);
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
                    Product product = products.get(row);
                    DeleteProductView view = new DeleteProductView(frame, parentView, product, row);
                }
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

        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS));
        btnContainer.add(btnAdd);
        btnContainer.add(btnUpdate);
        btnContainer.add(btnDelete);

        contentPanel.add(titleLabel);
        contentPanel.add(jScrollPane);
        contentPanel.add(btnContainer);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    public void addProductToTable(Product product) {
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

    public void updateProduct(int index, Product product) {
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
