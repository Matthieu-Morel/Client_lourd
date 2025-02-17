package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.FournisseurController;
import model.Provider;
import model.DAO.FournisseurDAO;

public class DeleteProviderView {
    private JDialog frame;
    private JLabel labelDelete;
    private JButton btnCancel;
    private JButton btnValidate;
    private ProvidersView parentView;

    public DeleteProviderView(JDialog jDialog, ProvidersView providersView, Provider provider, int row) {
        this.parentView = providersView;
        frame = new JDialog(jDialog, "Supprimer un fournisseur", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 1, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelDelete = new JLabel("Souhaitez-vous supprimer le fournisseur \"" + provider.getName() + "\" ?");
        
        btnCancel = new JButton("Annuler");
        btnValidate = new JButton("Supprimer");

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        FournisseurController fournisseurController = new FournisseurController(this, providersView, fournisseurDAO, provider, row);

        contentPanel.add(labelDelete);
        contentPanel.add(btnCancel);
        contentPanel.add(btnValidate);

        frame.add(contentPanel);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addButtonCancelActionListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }

    public void addButtonValidateActionListener(ActionListener listener) {
        btnValidate.addActionListener(listener);
    }

    public void close() {
        frame.dispose();
    }
}
