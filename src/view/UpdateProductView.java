package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import controler.ProduitController;
import model.Product;
import model.Provider;
import model.DAO.ProduitDAO;

public class UpdateProductView {
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
    private int idProduct;
    private int indexRow;

    public UpdateProductView(JDialog jDialog, ProductsView parentView, Product product, ArrayList<Provider> providers, int indexRow) {
        this.idProduct = product.getId_product();
        this.indexRow = indexRow;
        frame = new JDialog(jDialog, "Modifier un produit", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelName = new JLabel("Nom du produit :");
        labelQuantity = new JLabel("Quantité en stock :");
        labelUnitPrice = new JLabel("Prix unitaire (euros) :");
        labelProvider = new JLabel("Fournisseur :");



        txtName = new JTextField(product.getName(), 15);
        txtQuantity = new JTextField(Integer.toString(product.getQuantity()), 15);
        txtUnitPrice = new JTextField(Double.toString(product.getUnitPrice()), 15);
        jBoxProvider = new JComboBox<>(providers.toArray());

        Provider providerProduct = product.getProvider();
        for (Provider provider : providers) {
            if (provider.getId_provider() == providerProduct.getId_provider()) {
                jBoxProvider.setSelectedItem(provider);
                break;
            }
        }

        btnAdd = new JButton("Modifier");
        
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

    public void addModifierProduitListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public String getNomProduit() {
        return txtName.getText();
    }
    public String getPrixProduit() {
        return txtUnitPrice.getText();
    }
    public String getQuantity() {
        return txtQuantity.getText();
    }
    public Provider getProvider() {
        Provider provider = (Provider) jBoxProvider.getSelectedItem();
        return provider;
    }
    public void close() {
        frame.dispose();
    }
    public int getIdProduct() {
        return idProduct;
    }
    public int getIndexRow() {
        return indexRow;
    }
}
