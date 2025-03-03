package view;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
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
    private JButton btnCancel;
    private ArrayList<Provider> providers;
    private int idProduct;
    private int indexRow;

    public UpdateProductView(JDialog jDialog, ProductsView parentView, Product product, ArrayList<Provider> providers, int indexRow) {
        this.idProduct = product.getId_product();
        this.indexRow = indexRow;
        frame = new JDialog(jDialog, "Modifier un produit", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelName = new JLabel("Nom du produit :", SwingConstants.CENTER);
        labelQuantity = new JLabel("Quantité en stock :", SwingConstants.CENTER);
        labelUnitPrice = new JLabel("Prix unitaire (euros) :", SwingConstants.CENTER);
        labelProvider = new JLabel("Fournisseur :", SwingConstants.CENTER);

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
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCancel = new JButton("Annuler");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        
        ProduitDAO produitDAO = new ProduitDAO();
        ProduitController controller = new ProduitController(this, produitDAO, parentView);

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new GridLayout(4, 2, 10, 10));
        formContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
        formContainer.add(labelName);
        formContainer.add(txtName);
        formContainer.add(labelQuantity);
        formContainer.add(txtQuantity);
        formContainer.add(labelUnitPrice);
        formContainer.add(txtUnitPrice);
        formContainer.add(labelProvider);
        formContainer.add(jBoxProvider);

        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS));
        btnContainer.add(btnCancel);
        btnContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        btnContainer.add(btnAdd);
        
        contentPanel.add(formContainer);
        contentPanel.add(btnContainer);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setResizable(false);
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
