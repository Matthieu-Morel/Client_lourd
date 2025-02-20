package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.DAO.FournisseurDAO;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;
import utils.StringChecker;
import model.Product;
import model.Provider;
import view.AddProductView;
import view.DeleteProductView;
import view.ProductsView;
import view.UpdateProductView;

public class ProduitController {
    private AddProductView vue;
    private ProduitDAO produitDAO;

    public ProduitController(AddProductView vue, ProduitDAO produitDAO, ProductsView parentView) {
        this.vue = vue;
        this.produitDAO = produitDAO;
        this.vue.setAjouterProduitListener(e -> {
            String name = vue.getNomProduit();
            String quantity = vue.getQuantity();
            String unitPrice = vue.getPrixProduit().replace(",", ".");
            Provider provider = vue.getProvider();

            if (name.isBlank() || quantity.isBlank() || unitPrice.isBlank()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            }
            else if (StringChecker.isNumeric(name)) {
                JOptionPane.showMessageDialog(null, "Le nom du produit ne doit pas être une valeur numérique.");
            }
            else if (!StringChecker.isInteger(quantity)) {
                JOptionPane.showMessageDialog(null, "La quantité en stock du produit doit être un nombre entier.");
            }
            else if (Integer.parseInt(quantity) < 0) {
                JOptionPane.showMessageDialog(null, "La quantité en stock du produit doit être supérieur ou égal à 0.");
            }
            else if (!StringChecker.isNumeric(unitPrice)) {
                JOptionPane.showMessageDialog(null, "Le prix unitaire du produit doit être une valeur numérique.");
            }
            else if (Double.parseDouble(unitPrice) <= 0) {
                JOptionPane.showMessageDialog(null, "Le prix unitaire du produit doit être supérieur à 0.");
            }
            else{
                Product product = new Product(name, Integer.parseInt(quantity), Double.parseDouble(unitPrice), provider);
    
                int createdId = produitDAO.ajouterProduit(product);
                product.setId_product(createdId);
                if (parentView != null) {
                    parentView.addProductToTable(product);
                }
    
                JOptionPane.showMessageDialog(null, "Produit ajouté avec succès!");
                vue.close();
            }
        });
    }
    public ProduitController(ProductsView vue, ProduitDAO produitDAO, FournisseurDAO fournisseurDAO) {
        ArrayList<Product> products = produitDAO.getProduits();
        ArrayList<Provider> providers = fournisseurDAO.getFournisseurs();
        vue.setProducts(products);
        vue.setProviders(providers);
    }
    public ProduitController(UpdateProductView vue, ProduitDAO produitDAO, ProductsView parentView) {
        vue.addModifierProduitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = vue.getNomProduit();
                String quantity = vue.getQuantity();
                String unitPrice = vue.getPrixProduit().replace(",", ".");
                Provider provider = vue.getProvider();

                if (name.isBlank() || quantity.isBlank() || unitPrice.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                }
                else if (StringChecker.isNumeric(name)) {
                    JOptionPane.showMessageDialog(null, "Le nom du produit ne doit pas être une valeur numérique.");
                }
                else if (!StringChecker.isInteger(quantity)) {
                    JOptionPane.showMessageDialog(null, "La quantité en stock du produit doit être un nombre entier.");
                }
                else if (Integer.parseInt(quantity) < 0) {
                    JOptionPane.showMessageDialog(null, "La quantité en stock du produit doit être supérieur ou égal à 0.");
                }
                else if (!StringChecker.isNumeric(unitPrice)) {
                    JOptionPane.showMessageDialog(null, "Le prix unitaire du produit doit être une valeur numérique.");
                }
                else if (Double.parseDouble(unitPrice) <= 0) {
                    JOptionPane.showMessageDialog(null, "Le prix unitaire du produit doit être supérieur à 0.");
                }
                else{
                    Product product = new Product(vue.getIdProduct(), name, Integer.parseInt(quantity), Double.parseDouble(unitPrice), provider);
    
                    produitDAO.modifierProduit(product);
                    
                    if (parentView != null) {
                        parentView.updateProduct(vue.getIndexRow(), product);
                    }
                    JOptionPane.showMessageDialog(null, "Produit Modifié avec succès!");
                    vue.close();
                }
            }

        });
    }
    public ProduitController(DeleteProductView vue, ProduitDAO produitDAO, ProductsView parentView, Product product, int row) {
        vue.addButtonCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vue.close();
            }
        });
        vue.addButtonValidateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produitDAO.supprimerProduit(product);
                
                if (parentView != null) {
                    parentView.deleteProduct(row);
                }
                JOptionPane.showMessageDialog(null, "Produit supprimé avec succès!");
                vue.close();
            }

        });
    }
}
