package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Produit;
import model.Vente;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;
import utils.ControleurDeChaine;
import view.VueAjoutVente;
import view.VueSupprimerVente;
import view.VueVentes;
import view.VueModifierVente;

public class VenteControleur {
    
    public VenteControleur(VueVentes view, VenteDAO venteDAO, ProduitDAO produitDAO) {
        ArrayList<Produit> products = produitDAO.getProduits();
        ArrayList<Vente> sellings = venteDAO.getVentes();
        view.setProducts(products);
        view.setSellings(sellings);
    }

    public VenteControleur(VueAjoutVente view, VenteDAO venteDAO, ProduitDAO produitDAO, VueVentes parentView) {
        view.addButtonCreateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produit product = view.getProduct();
                String quantitySold = view.getQuantity();
                Date date = view.getDate();

                if (quantitySold.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez renseigner la quantité du produit vendu.");
                }
                else if (!ControleurDeChaine.isInteger(quantitySold)) {
                    JOptionPane.showMessageDialog(null, "La quantité du produit vendu doit être un nombre entier.");
                }
                else if (Integer.parseInt(quantitySold) < 1) {
                    JOptionPane.showMessageDialog(null, "La quantité du produit vendu doit être supérieur ou égal à 1.");
                }
                else if (Integer.parseInt(quantitySold) > product.getQuantity()) {
                    JOptionPane.showMessageDialog(null, "La quantité de produit en stock est insuffisante. La quantité de produits vendus ne doit pas dépasser " + Integer.toString(product.getQuantity()) + " produits.");
                }
                else {
                    Vente selling = new Vente(product, Integer.parseInt(quantitySold), date);
    
                    int createdId = venteDAO.ajouterVente(selling);
                    selling.setId_selling(createdId);
                    if (parentView != null) {
                        parentView.addSellingToTable(selling);
                    }

                    product.setQuantity(product.getQuantity() - Integer.parseInt(quantitySold));
                    produitDAO.modifierProduit(product);
        
                    JOptionPane.showMessageDialog(null, "Vente ajoutée avec succès!");
                    view.close();
                }
            }
        });
    }

    public VenteControleur(VueModifierVente view, VenteDAO venteDAO, ProduitDAO produitDAO, VueVentes parentView) {
        view.addButtonUpdateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produit product = view.getProduct();
                String quantitySold = view.getQuantity();
                Date date = view.getDate();

                if (quantitySold.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez renseigner la quantité du produit vendu.");
                }
                else if (!ControleurDeChaine.isInteger(quantitySold)) {
                    JOptionPane.showMessageDialog(null, "La quantité du produit vendu doit être un nombre entier.");
                }
                else if (Integer.parseInt(quantitySold) < 1) {
                    JOptionPane.showMessageDialog(null, "La quantité du produit vendu doit être supérieur ou égal à 1.");
                }
                else if (Integer.parseInt(quantitySold) > product.getQuantity()) {
                    JOptionPane.showMessageDialog(null, "La quantité de produit en stock est insuffisante. La quantité de produits vendus ne doit pas dépasser " + Integer.toString(product.getQuantity()) + " produits.");
                }
                else {
                    Vente oldSelling = view.getOldSelling();
                    Vente selling = new Vente(oldSelling.getId_selling(), product, Integer.parseInt(quantitySold), date);
    
                    venteDAO.modifierVente(selling);
    
                    if (parentView != null) {
                        parentView.updateSelling(view.getIndexRow(), selling);
                    }

                    Produit oldProduct = oldSelling.getProduct();
                    oldProduct.setQuantity(oldProduct.getQuantity() + Integer.parseInt(quantitySold));
                    produitDAO.modifierProduit(product);

                    product.setQuantity(product.getQuantity() - Integer.parseInt(quantitySold));
                    produitDAO.modifierProduit(product);
        
                    JOptionPane.showMessageDialog(null, "Vente modifiée avec succès!");
                    view.close();
                }
            }
        });
    }

    public VenteControleur(VueSupprimerVente view, VenteDAO venteDAO, ProduitDAO produitDAO, VueVentes parentView, Vente selling, int row) {
        view.addButtonCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.close();
            }
        });
        view.addButtonValidateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Produit product = selling.getProduct();
                product.setQuantity(product.getQuantity() + selling.getQuantitySold());
                produitDAO.modifierProduit(product);

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
