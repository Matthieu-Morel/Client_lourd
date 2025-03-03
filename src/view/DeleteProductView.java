package view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import controler.FournisseurController;
import controler.ProduitController;
import model.Product;
import model.DAO.FournisseurDAO;
import model.DAO.ProduitDAO;

public class DeleteProductView {
    private JDialog frame;
    private JLabel labelDelete;
    private JButton btnCancel;
    private JButton btnValidate;
    private ProductsView parentView;
    private int indexRow;

    public DeleteProductView(JDialog jDialog, ProductsView parentView, Product product, int row) {
        this.parentView = parentView;
        this.indexRow = row;
        frame = new JDialog(jDialog, "Supprimer un produit", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelDelete = new JLabel("Souhaitez-vous supprimer le produit \"" + product.getName() + "\" ?");
        labelDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnCancel = new JButton("Annuler");
        btnValidate = new JButton("Supprimer");

        ProduitDAO produitDAO = new ProduitDAO();
        ProduitController controller = new ProduitController(this, produitDAO, parentView, product, row);

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
