package controler;

import javax.swing.JOptionPane;

import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;
import model.Product;

import view.ProductView;

public class ProduitController {
    private ProductView vue;
    private ProduitDAO produitDAO;
    private VenteDAO venteDAO;

    public ProduitController(ProductView vue, ProduitDAO produitDAO, VenteDAO venteDAO) {
        this.vue = vue;
        this.produitDAO = produitDAO;
        this.venteDAO = venteDAO;
        this.vue.setAjouterProduitListener(e -> {
            String nom = vue.getNomProduit();
            double prix = vue.getPrixProduit();
            int quantite = vue.getQuantity();
            Product produit = new Product(nom, prix, quantite); // L'id sera généré par la DB
            produitDAO.ajouterProduit(produit);
            JOptionPane.showMessageDialog(null, "Produit ajouté !");
        });
    }
}
