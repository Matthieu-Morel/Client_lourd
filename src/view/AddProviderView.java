package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import controler.FournisseurController;
import model.Provider;
import model.DAO.FournisseurDAO;

public class AddProviderView {
    private JDialog frame;
    private JLabel labelName;
    private JLabel labelAddress;
    private JLabel labelPhone;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JButton btnAdd;
    private ProvidersView parentView;
    public static void main(String[] args) {
        new AddProviderView(null, null);
    }

    public AddProviderView(JDialog jDialog, ProvidersView providersView) {
        this.parentView = providersView;
        frame = new JDialog(jDialog, "Ajouter un fournisseur", true);

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
        
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        FournisseurController fournisseurController = new FournisseurController(this, fournisseurDAO);

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
    public void close() {
        frame.dispose();
    }

    public ProvidersView getParentView() {
        return parentView;
    }
}
