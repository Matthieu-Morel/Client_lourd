package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import controller.FournisseurControleur;
import model.DAO.FournisseurDAO;

public class VueAjoutFournisseur {
    private JDialog frame;
    private JLabel labelName;
    private JLabel labelAddress;
    private JLabel labelPhone;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JButton btnAdd;
    private JButton btnCancel;
    private VueFournisseurs parentView;

    public VueAjoutFournisseur(JDialog jDialog, VueFournisseurs providersView) {
        this.parentView = providersView;
        frame = new JDialog(jDialog, "Ajouter un fournisseur", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelName = new JLabel("Nom du fournisseur :", SwingConstants.CENTER);
        labelAddress = new JLabel("Adresse du fournisseur :", SwingConstants.CENTER);
        labelPhone = new JLabel("Téléphone du fournisseur :", SwingConstants.CENTER);

        txtName = new JTextField(15);
        txtAddress = new JTextField(15);
        txtPhone = new JTextField(15);

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
        
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        FournisseurControleur fournisseurController = new FournisseurControleur(this, fournisseurDAO);

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new GridLayout(3, 2, 10, 10));
        formContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
        formContainer.add(labelName);
        formContainer.add(txtName);
        formContainer.add(labelAddress);
        formContainer.add(txtAddress);
        formContainer.add(labelPhone);
        formContainer.add(txtPhone);

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

    public VueFournisseurs getParentView() {
        return parentView;
    }
}
