package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.DAO.FournisseurDAO;
import model.DAO.ProduitDAO;
import utils.ControleurDeChaine;
import model.Produit;
import model.Fournisseur;
import view.VueAjoutProduit;
import view.VueSupprimerProduit;
import view.VueProduits;
import view.VueModifierProduit;

public class ProduitControleur {

    public ProduitControleur(VueAjoutProduit vue, ProduitDAO produitDAO, VueProduits parentView) {
        vue.setAjouterProduitListener(e -> {
            String name = vue.getNomProduit();
            String quantity = vue.getQuantity();
            String unitPrice = vue.getPrixProduit().replace(",", ".");
            Fournisseur provider = vue.getProvider();

            if (name.isBlank() || quantity.isBlank() || unitPrice.isBlank()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            }
            else if (ControleurDeChaine.isNumeric(name)) {
                JOptionPane.showMessageDialog(null, "Le nom du produit ne doit pas être une valeur numérique.");
            }
            else if (!ControleurDeChaine.isInteger(quantity)) {
                JOptionPane.showMessageDialog(null, "La quantité en stock du produit doit être un nombre entier.");
            }
            else if (Integer.parseInt(quantity) < 0) {
                JOptionPane.showMessageDialog(null, "La quantité en stock du produit doit être supérieur ou égal à 0.");
            }
            else if (!ControleurDeChaine.isNumeric(unitPrice)) {
                JOptionPane.showMessageDialog(null, "Le prix unitaire du produit doit être une valeur numérique.");
            }
            else if (Double.parseDouble(unitPrice) <= 0) {
                JOptionPane.showMessageDialog(null, "Le prix unitaire du produit doit être supérieur à 0.");
            }
            else{
                Produit product = new Produit(name, Integer.parseInt(quantity), Double.parseDouble(unitPrice), provider);
    
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
    public ProduitControleur(VueProduits vue, ProduitDAO produitDAO, FournisseurDAO fournisseurDAO) {
        ArrayList<Produit> products = produitDAO.getProduits();
        ArrayList<Fournisseur> providers = fournisseurDAO.getFournisseurs();
        vue.setProducts(products);
        vue.setProviders(providers);
    }
    public ProduitControleur(VueModifierProduit vue, ProduitDAO produitDAO, VueProduits parentView) {
        vue.addModifierProduitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = vue.getNomProduit();
                String quantity = vue.getQuantity();
                String unitPrice = vue.getPrixProduit().replace(",", ".");
                Fournisseur provider = vue.getProvider();

                if (name.isBlank() || quantity.isBlank() || unitPrice.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                }
                else if (ControleurDeChaine.isNumeric(name)) {
                    JOptionPane.showMessageDialog(null, "Le nom du produit ne doit pas être une valeur numérique.");
                }
                else if (!ControleurDeChaine.isInteger(quantity)) {
                    JOptionPane.showMessageDialog(null, "La quantité en stock du produit doit être un nombre entier.");
                }
                else if (Integer.parseInt(quantity) < 0) {
                    JOptionPane.showMessageDialog(null, "La quantité en stock du produit doit être supérieur ou égal à 0.");
                }
                else if (!ControleurDeChaine.isNumeric(unitPrice)) {
                    JOptionPane.showMessageDialog(null, "Le prix unitaire du produit doit être une valeur numérique.");
                }
                else if (Double.parseDouble(unitPrice) <= 0) {
                    JOptionPane.showMessageDialog(null, "Le prix unitaire du produit doit être supérieur à 0.");
                }
                else{
                    Produit product = new Produit(vue.getIdProduct(), name, Integer.parseInt(quantity), Double.parseDouble(unitPrice), provider);
    
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
    public ProduitControleur(VueSupprimerProduit vue, ProduitDAO produitDAO, VueProduits parentView, Produit product, int row) {
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
