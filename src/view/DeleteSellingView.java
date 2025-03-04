package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
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

import controler.ProduitController;
import controler.VenteController;
import model.Product;
import model.Selling;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;

public class DeleteSellingView {
    private JDialog frame;
    private JLabel labelDelete;
    private JButton btnCancel;
    private JButton btnValidate;
    private SellingsView parentView;
    private int indexRow;

    public DeleteSellingView(JDialog jDialog, SellingsView parentView, Selling selling, int row) {
        this.parentView = parentView;
        this.indexRow = row;
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
        VenteController controller = new VenteController(this, venteDAO, parentView, selling, row);

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

    public int getIndexRow() {
        return indexRow;
    }
}