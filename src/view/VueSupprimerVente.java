package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.VenteControleur;
import model.Vente;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;

public class VueSupprimerVente {
    private JDialog frame;
    private JLabel labelDelete;
    private JButton btnCancel;
    private JButton btnValidate;

    public VueSupprimerVente(JDialog jDialog, VueVentes parentView, Vente selling, int row) {
        frame = new JDialog(jDialog, "Supprimer une vente", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        labelDelete = new JLabel("Souhaitez-vous supprimer la vente effectuée le " + format.format(selling.getDateSold()) +
                                 " et contenant " + Integer.toString(selling.getQuantitySold()) + 
                                 " fois le produit \"" + selling.getProduct().getName() + "\" ?");
        labelDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnCancel = new JButton("Annuler");
        btnValidate = new JButton("Supprimer");

        VenteDAO venteDAO = new VenteDAO();
        ProduitDAO produitDAO = new ProduitDAO();
        VenteControleur controller = new VenteControleur(this, venteDAO, produitDAO, parentView, selling, row);

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

    public void addButtonCancelListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }

    public void addButtonValidateListener(ActionListener listener) {
        btnValidate.addActionListener(listener);
    }

    public void close() {
        frame.dispose();
    }

}