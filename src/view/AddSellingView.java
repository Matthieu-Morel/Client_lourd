package view;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import controler.ProduitController;
import controler.VenteController;
import model.Product;
import model.Selling;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;

public class AddSellingView {
    private JDialog frame;
    private JLabel labelProduct;
    private JLabel labelQuantity;
    private JLabel labelDate;
    private JComboBox jBoxProduct;
    private JTextField txtQuantity;
    private JSpinner dateSpinner;
    private JButton btnAdd;
    private JButton btnCancel;

    public AddSellingView(JFrame jFrame, SellingsView parentView, ArrayList<Product> products) {
        frame = new JDialog(jFrame, "Ajouter un produit", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelProduct = new JLabel("Produit vendu :", SwingConstants.CENTER);
        labelQuantity = new JLabel("Quantité vendue :", SwingConstants.CENTER);
        labelDate = new JLabel("Date de vente :", SwingConstants.CENTER);

        jBoxProduct = new JComboBox<>(products.toArray());
        txtQuantity = new JTextField(15);

        SpinnerDateModel model = new SpinnerDateModel();
        dateSpinner = new JSpinner(model);
        DateEditor editor = new DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);

        btnAdd = new JButton("Ajouter");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCancel = new JButton("Annuler");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        
        VenteDAO venteDAO = new VenteDAO();
        VenteController controller = new VenteController(this, venteDAO, parentView);

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new GridLayout(3, 2, 10, 10));
        formContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
        formContainer.add(labelProduct);
        formContainer.add(jBoxProduct);
        formContainer.add(labelQuantity);
        formContainer.add(txtQuantity);
        formContainer.add(labelDate);
        formContainer.add(dateSpinner);
        
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    public void addButtonCreateListener(ActionListener actionListener) {
        btnAdd.addActionListener(actionListener);
    }

    public Product getProduct() {
        Product product = (Product) jBoxProduct.getSelectedItem();
        return product;
    }

    public String getQuantity() {
        return txtQuantity.getText();
    }

    public java.sql.Date getDate() {
        java.util.Date date = (java.util.Date) dateSpinner.getValue();
        return new java.sql.Date(date.getTime());
    }
}
