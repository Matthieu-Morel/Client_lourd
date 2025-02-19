package controler;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.DAO.FournisseurDAO;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;
import model.Product;
import model.Provider;
import view.AddProductView;
import view.ProductsView;

public class ProduitController {
    private AddProductView vue;
    private ProduitDAO produitDAO;

    public ProduitController(AddProductView vue, ProduitDAO produitDAO, ProductsView parentView) {
        this.vue = vue;
        this.produitDAO = produitDAO;
        this.vue.setAjouterProduitListener(e -> {
            String name = vue.getNomProduit();
            int quantity = vue.getQuantity();
            double unitPrice = vue.getPrixProduit();
            Provider provider = vue.getProvider();
            Product product = new Product(name, quantity, unitPrice, provider);

            int createdId = produitDAO.ajouterProduit(product);
            product.setId_product(createdId);
            if (parentView != null) {
                parentView.addProductToTable(product);
            }

            JOptionPane.showMessageDialog(null, "Produit ajouté !");
            vue.close();
        });
    }
    public ProduitController(ProductsView vue, ProduitDAO produitDAO, FournisseurDAO fournisseurDAO) {
        ArrayList<Product> products = produitDAO.getProduits();
        ArrayList<Provider> providers = fournisseurDAO.getFournisseurs();
        vue.setProducts(products);
        vue.setProviders(providers);
    }
}
