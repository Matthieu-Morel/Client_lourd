package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FournisseurControleur;
import model.Fournisseur;
import model.DAO.FournisseurDAO;

public class VueSupprimerFournisseur {
    private JDialog frame;
    private JLabel labelDelete;
    private JButton btnCancel;
    private JButton btnValidate;

    public VueSupprimerFournisseur(JDialog jDialog, VueFournisseurs providersView, Fournisseur provider, int row) {
        frame = new JDialog(jDialog, "Supprimer un fournisseur", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelDelete = new JLabel("Souhaitez-vous supprimer le fournisseur \"" + provider.getName() + "\" ?");
        labelDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnCancel = new JButton("Annuler");
        btnValidate = new JButton("Supprimer");

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        FournisseurControleur fournisseurController = new FournisseurControleur(this, providersView, fournisseurDAO, provider, row);

        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS));
        btnContainer.add(btnCancel);
        btnContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        btnContainer.add(btnValidate);

        contentPanel.add(labelDelete);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(btnContainer);

        frame.add(contentPanel);

        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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
