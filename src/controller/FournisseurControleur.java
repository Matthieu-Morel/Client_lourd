package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Fournisseur;
import model.DAO.FournisseurDAO;
import utils.ControleurDeChaine;
import view.VueAjoutFournisseur;
import view.VueSupprimerFournisseur;
import view.VueFournisseurs;
import view.VueModifierFournisseur;

public class FournisseurControleur {
    public FournisseurControleur(VueAjoutFournisseur vue, FournisseurDAO fournisseurDAO) {
        vue.setAjouterFournisseurListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = vue.getName();
                String adresse = vue.getAddress();
                String telephone = vue.getPhone();

                if (nom.isBlank() || adresse.isBlank() || telephone.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                }
                else if (ControleurDeChaine.isNumeric(nom)) {
                    JOptionPane.showMessageDialog(null, "Le nom du fournisseur ne doit pas être une valeur numérique.");
                }
                else if (ControleurDeChaine.isNumeric(adresse)) {
                    JOptionPane.showMessageDialog(null, "L'adresse du fournisseur ne doit pas être une valeur numérique.");
                }
                else if ((!ControleurDeChaine.containsOnlyDigits(telephone)) || telephone.length() != 10) {
                    JOptionPane.showMessageDialog(null, "Le numéro de téléphone du fournisseur doit être composé uniquement de 10 chiffres.");
                }
                else{
                    Fournisseur fournisseur = new Fournisseur(nom, adresse, telephone);
    
                    int createdId = fournisseurDAO.ajouterFournisseur(fournisseur);
                    fournisseur.setId_provider(createdId);
                    VueFournisseurs parentView = vue.getParentView();
                    if (parentView != null) {
                        parentView.addProviderToTable(fournisseur);
                    }
    
                    JOptionPane.showMessageDialog(null, "Fournisseur ajouté avec succès!");
                    vue.close();
                }
            }
        });
    }
    public FournisseurControleur(VueFournisseurs vue, FournisseurDAO fournisseurDAO) {
        ArrayList<Fournisseur> providers = fournisseurDAO.getFournisseurs();
        vue.setProviders(providers);
    }

    public FournisseurControleur(VueModifierFournisseur vue, FournisseurDAO fournisseurDAO) {
        vue.setAjouterFournisseurListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = vue.getName();
                String adresse = vue.getAddress();
                String telephone = vue.getPhone();

                if (nom.isBlank() || adresse.isBlank() || telephone.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                }
                else if (ControleurDeChaine.isNumeric(nom)) {
                    JOptionPane.showMessageDialog(null, "Le nom du fournisseur ne doit pas être une valeur numérique.");
                }
                else if (ControleurDeChaine.isNumeric(adresse)) {
                    JOptionPane.showMessageDialog(null, "L'adresse du fournisseur ne doit pas être une valeur numérique.");
                }
                else if ((!ControleurDeChaine.containsOnlyDigits(telephone)) || telephone.length() != 10) {
                    JOptionPane.showMessageDialog(null, "Le numéro de téléphone du fournisseur doit être composé uniquement de 10 chiffres.");
                }
                else{
                    int id = vue.getProvider().getId_provider();
                    Fournisseur fournisseur = new Fournisseur(id, nom, adresse, telephone);
    
                    fournisseurDAO.modifierFournisseur(fournisseur);
                    VueFournisseurs parentView = vue.getParentView();
                    if (parentView != null) {
                        parentView.updateProvider(vue.getIndexRow(), fournisseur);
                    }
    
                    JOptionPane.showMessageDialog(null, "Fournisseur modifié avec succès!");
                    vue.close();
                }
            }
        });
    }

    public FournisseurControleur(VueSupprimerFournisseur vue, VueFournisseurs parentView, FournisseurDAO fournisseurDAO, Fournisseur provider, int row) {
        vue.addButtonCancelActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vue.close();
            }
        });
        vue.addButtonValidateActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fournisseurDAO.supprimerFournisseur(provider);

                if (parentView != null) {
                    parentView.deleteProvider(row);
                }

                JOptionPane.showMessageDialog(null, "Fournisseur supprimé avec succès!");
                vue.close();
            }
        });
    }
}