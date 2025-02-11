package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.border.EmptyBorder;

import model.Selling;

public class SellingView {
    private JDialog frame;
    private JLabel labelProduct;
    private JLabel labelQuantity;
    private JLabel labelDate;
    private JTextField txtProduct;
    private JTextField txtQuantity;
    private JTextField txtDate;
    private JButton btnAdd;
    public static void main(String[] args) {
        new SellingView(null);
    }

    public SellingView(JFrame jFrame) {
        frame = new JDialog(jFrame, "Ajouter une vente", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelProduct = new JLabel("Produit vendu :");
        labelQuantity = new JLabel("Quantité vendue :");
        labelDate = new JLabel("Date de vente :");

        txtProduct = new JTextField(15);
        txtQuantity = new JTextField(15);
        txtDate = new JTextField(15);

        btnAdd = new JButton("Ajouter");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                Selling.addSelling(Integer.parseInt(txtQuantity.getText()), txtDate.getText(), Integer.parseInt(txtProduct.getText()));
            }
        });

        contentPanel.add(labelProduct);
        contentPanel.add(txtProduct);
        contentPanel.add(labelQuantity);
        contentPanel.add(txtQuantity);
        contentPanel.add(labelDate);
        contentPanel.add(txtDate);
        contentPanel.add(btnAdd);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true); 
    }
}
