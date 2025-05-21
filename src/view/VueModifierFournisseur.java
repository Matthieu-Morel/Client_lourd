package view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.FournisseurControleur;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Fournisseur;
import model.DAO.FournisseurDAO;

public class VueModifierFournisseur {
    
    private JDialog frame;
    private JLabel labelName;
    private JLabel labelAddress;
    private JLabel labelPhone;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JButton btnUpdate;
    private JButton btnCancel;
    private VueFournisseurs parentView;
    private Fournisseur provider;
    private int indexRow;

    public VueModifierFournisseur(JDialog jDialog, VueFournisseurs providersView, Fournisseur provider, int indexRow) {
        this.parentView = providersView;
        this.indexRow = indexRow;
        this.provider = provider;
        frame = new JDialog(jDialog, "Modifier un fournisseur", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelName = new JLabel("Nom du fournisseur :");
        labelAddress = new JLabel("Adresse du fournisseur :");
        labelPhone = new JLabel("Téléphone du fournisseur :");

        txtName = new JTextField(provider.getName(), 15);
        txtAddress = new JTextField(provider.getAddress(), 15);
        txtPhone = new JTextField(provider.getPhone(), 15);

        btnUpdate = new JButton("Modifier");
        btnUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        btnContainer.add(btnUpdate);

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
    
    public void close() {
        frame.dispose();
    }
    
    public VueFournisseurs getParentView() {
        return parentView;
    }
    
    public void setAjouterFournisseurListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public int getIndexRow() {
        return indexRow;
    }

    public Fournisseur getProvider() {
        return provider;
    }
}
