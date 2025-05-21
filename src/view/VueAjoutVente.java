package view;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;
import javax.swing.border.EmptyBorder;

import controller.VenteControleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import model.Produit;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;

public class VueAjoutVente {
    private JDialog frame;
    private JLabel labelProduct;
    private JLabel labelQuantity;
    private JLabel labelDate;
    private JComboBox jBoxProduct;
    private JTextField txtQuantity;
    private JSpinner dateSpinner;
    private JButton btnAdd;
    private JButton btnCancel;

    public VueAjoutVente(JDialog jDialog, VueVentes parentView, ArrayList<Produit> products) {
        frame = new JDialog(jDialog, "Ajouter un produit", true);

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
        ProduitDAO produitDAO = new ProduitDAO();
        VenteControleur controller = new VenteControleur(this, venteDAO, produitDAO, parentView);

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

    public Produit getProduct() {
        Produit product = (Produit) jBoxProduct.getSelectedItem();
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
