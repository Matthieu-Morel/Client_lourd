package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import model.Product;
import model.Provider;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;
import controler.ProduitController;

public class AddProductView {
    private JDialog frame;
    private JLabel labelName;
    private JLabel labelQuantity;
    private JLabel labelUnitPrice;
    private JLabel labelProvider;
    private JTextField txtName;
    private JTextField txtQuantity;
    private JTextField txtUnitPrice;
    private JComboBox jBoxProvider;
    private JButton btnAdd;
    private ArrayList<Provider> providers;

    public AddProductView(JFrame jFrame, ProductsView parentView, ArrayList<Provider> providers) {
        frame = new JDialog(jFrame, "Ajouter un produit", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelName = new JLabel("Nom du produit :");
        labelQuantity = new JLabel("Quantité en stock :");
        labelUnitPrice = new JLabel("Prix unitaire (euros) :");
        labelProvider = new JLabel("Fournisseur :");

        txtName = new JTextField(15);
        txtQuantity = new JTextField(15);
        txtUnitPrice = new JTextField(15);
        jBoxProvider = new JComboBox<>(providers.toArray());

        btnAdd = new JButton("Ajouter");
        
        ProduitDAO produitDAO = new ProduitDAO();
        ProduitController controller = new ProduitController(this, produitDAO, parentView);
        
        contentPanel.add(labelName);
        contentPanel.add(txtName);
        contentPanel.add(labelQuantity);
        contentPanel.add(txtQuantity);
        contentPanel.add(labelUnitPrice);
        contentPanel.add(txtUnitPrice);
        contentPanel.add(labelProvider);
        contentPanel.add(jBoxProvider);
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
    public Provider getProvider() {
        Provider provider = (Provider) jBoxProvider.getSelectedItem();
        return provider;
    }
    public void setAjouterProduitListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    public void close() {
        frame.dispose();
    }
       
}
