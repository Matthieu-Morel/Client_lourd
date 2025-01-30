package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import controler.FournisseurController;
import model.Provider;
import model.DAO.FournisseurDAO;

public class ProviderView {
    private JFrame frame;
    private JLabel labelName;
    private JLabel labelAddress;
    private JLabel labelPhone;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JButton btnAdd;
    public static void main(String[] args) {
        new ProviderView();
    }

    public ProviderView() {
        frame = new JFrame("Ajouter un fournisseur");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelName = new JLabel("Nom du fournisseur :");
        labelAddress = new JLabel("Adresse du fournisseur :");
        labelPhone = new JLabel("Téléphone du fournisseur :");

        txtName = new JTextField(15);
        txtAddress = new JTextField(15);
        txtPhone = new JTextField(15);

        btnAdd = new JButton("Ajouter");
        ProviderView vue = this;
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // Provider.addProvider(txtName.getText(), txtAddress.getText(), txtPhone.getText());
                FournisseurDAO fournisseurDAO = new FournisseurDAO();
                FournisseurController fournisseurController = new FournisseurController(vue, fournisseurDAO);
            }
        });

        contentPanel.add(labelName);
        contentPanel.add(txtName);
        contentPanel.add(labelAddress);
        contentPanel.add(txtAddress);
        contentPanel.add(labelPhone);
        contentPanel.add(txtPhone);
        contentPanel.add(btnAdd);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true); 
    }

    public String getName() {
        return txtName.getText();
    }

    public String getAddress() {
        return txtAddress.getText();
    }

    public String getPhone() {
        return txtPhone.getText();
    }

    public void setAjouterFournisseurListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
}
