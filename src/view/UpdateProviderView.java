package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import controler.FournisseurController;
import model.Provider;
import model.DAO.FournisseurDAO;

public class UpdateProviderView {
    
    private JDialog frame;
    private JLabel labelName;
    private JLabel labelAddress;
    private JLabel labelPhone;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JButton btnAdd;
    private ProvidersView parentView;
    private Provider provider;
    private int indexRow;

    public UpdateProviderView(JDialog jDialog, ProvidersView providersView, Provider provider, int indexRow) {
        this.parentView = providersView;
        this.indexRow = indexRow;
        this.provider = provider;
        frame = new JDialog(jDialog, "Modifier un fournisseur", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelName = new JLabel("Nom du fournisseur :");
        labelAddress = new JLabel("Adresse du fournisseur :");
        labelPhone = new JLabel("Téléphone du fournisseur :");

        txtName = new JTextField(provider.getName(), 15);
        txtAddress = new JTextField(provider.getAddress(), 15);
        txtPhone = new JTextField(provider.getPhone(), 15);

        btnAdd = new JButton("Modifier");
        
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
    
    public void close() {
        frame.dispose();
    }
    
    public ProvidersView getParentView() {
        return parentView;
    }
    
    public void setAjouterFournisseurListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public int getIndexRow() {
        return indexRow;
    }

    public Provider getProvider() {
        return provider;
    }
}
