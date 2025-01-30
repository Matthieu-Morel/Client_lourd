package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import model.Product;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;
import controler.ProduitController;

public class ProductView {
    private JFrame frame;
    private JLabel labelName;
    private JLabel labelQuantity;
    private JLabel labelUnitPrice;
    private JLabel labelProvider;
    private JTextField txtName;
    private JTextField txtQuantity;
    private JTextField txtUnitPrice;
    private JTextField txtProvider;
    private JButton btnAdd;
    public static void main(String[] args) {
        new ProductView();
    }

    public ProductView() {
        frame = new JFrame("Ajouter un produit");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelName = new JLabel("Nom du produit :");
        labelQuantity = new JLabel("Quantité en stock :");
        labelUnitPrice = new JLabel("Prix unitaire :");
        labelProvider = new JLabel("Fournisseur :");

        txtName = new JTextField(15);
        txtQuantity = new JTextField(15);
        txtUnitPrice = new JTextField(15);
        txtProvider = new JTextField(15);

        btnAdd = new JButton("Ajouter");
        ProductView vue = this;
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Product.addProduct(txtName.getText(), Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtProvider.getText()));
                ProduitDAO produitDAO = new ProduitDAO();
                VenteDAO venteDAO = new VenteDAO();
                ProduitController controller = new ProduitController(vue, produitDAO, venteDAO);
            }
        });

        contentPanel.add(labelName);
        contentPanel.add(txtName);
        contentPanel.add(labelQuantity);
        contentPanel.add(txtQuantity);
        contentPanel.add(labelUnitPrice);
        contentPanel.add(txtUnitPrice);
        contentPanel.add(labelProvider);
        contentPanel.add(txtProvider);
        contentPanel.add(btnAdd);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true); 
    }

    public String getNomProduit() {
        return txtName.getText();
    }
    public double getPrixProduit() {
        return Double.parseDouble(txtUnitPrice.getText());
    }
    public int getQuantity() {
        return Integer.parseInt(txtQuantity.getText());
    }
    public int getProvider() {
        return Integer.parseInt(txtProvider.getText());
    }
    public void setAjouterProduitListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
       
}
