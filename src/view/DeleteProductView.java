package view;

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
        contentPanel.setLayout(new GridLayout(3, 1, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelDelete = new JLabel("Souhaitez-vous supprimer le produit \"" + product.getName() + "\" ?");
        
        btnCancel = new JButton("Annuler");
        btnValidate = new JButton("Supprimer");

        ProduitDAO produitDAO = new ProduitDAO();
        ProduitController controller = new ProduitController(this, produitDAO, parentView, product, row);

        contentPanel.add(labelDelete);
        contentPanel.add(btnCancel);
        contentPanel.add(btnValidate);

        frame.add(contentPanel);

        frame.setSize(400, 300);
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
