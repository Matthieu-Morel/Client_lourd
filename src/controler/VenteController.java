package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Product;
import model.Selling;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;
import view.AddSellingView;
import view.DeleteSellingView;
import view.SellingsView;
import view.UpdateSellingView;

public class VenteController {
    
    public VenteController(SellingsView view, VenteDAO venteDAO, ProduitDAO produitDAO) {
        ArrayList<Product> products = produitDAO.getProduits();
        ArrayList<Selling> sellings = venteDAO.getVentes();
        view.setProducts(products);
        view.setSellings(sellings);
    }

    public VenteController(AddSellingView view, VenteDAO venteDAO, SellingsView parentView) {
        view.addButtonCreateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = view.getProduct();
                String quantitySold = view.getQuantity();
                Date date = view.getDate();

                Selling selling = new Selling(product, Integer.parseInt(quantitySold), date);

                int createdId = venteDAO.ajouterVente(selling);
                selling.setId_selling(createdId);
                if (parentView != null) {
                    parentView.addSellingToTable(selling);
                }
    
                JOptionPane.showMessageDialog(null, "Vente ajoutée avec succès!");
                view.close();
            }
        });
    }

    public VenteController(UpdateSellingView view, VenteDAO venteDAO, SellingsView parentView) {
        view.addButtonUpdateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = view.getProduct();
                String quantitySold = view.getQuantity();
                Date date = view.getDate();

                Selling selling = new Selling(view.getIdSelling(), product, Integer.parseInt(quantitySold), date);

                venteDAO.modifierVente(selling);

                if (parentView != null) {
                    parentView.updateSelling(view.getIndexRow(), selling);
                }
    
                JOptionPane.showMessageDialog(null, "Vente modifiée avec succès!");
                view.close();
            }
        });
    }

    public VenteController(DeleteSellingView view, VenteDAO venteDAO, SellingsView parentView, Selling selling, int row) {
        view.addButtonCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.close();
            }
        });
        view.addButtonValidateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                venteDAO.supprimerVente(selling);
                
                if (parentView != null) {
                    parentView.deleteSelling(row);
                }
                JOptionPane.showMessageDialog(null, "Vente supprimée avec succès!");
                view.close();
            }

        });
    }
}
