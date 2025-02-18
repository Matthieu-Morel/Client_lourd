package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.Provider;
import model.DAO.FournisseurDAO;
import utils.StringChecker;
import view.AddProviderView;
import view.DeleteProviderView;
import view.ProvidersView;
import view.UpdateProviderView;

public class FournisseurController {
    private AddProviderView vue;
    private FournisseurDAO fournisseurDAO;
    public FournisseurController(AddProviderView vue, FournisseurDAO fournisseurDAO) {
        this.vue = vue;
        this.fournisseurDAO = fournisseurDAO;
        this.vue.setAjouterFournisseurListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = vue.getName();
                String adresse = vue.getAddress();
                String telephone = vue.getPhone();

                if (nom.isBlank() || adresse.isBlank() || telephone.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                }
                else if (StringChecker.isNumeric(nom)) {
                    JOptionPane.showMessageDialog(null, "Le nom du fournisseur ne doit pas être une valeur numérique.");
                }
                else if (StringChecker.isNumeric(adresse)) {
                    JOptionPane.showMessageDialog(null, "L'adresse du fournisseur ne doit pas être une valeur numérique.");
                }
                else if ((!StringChecker.containsOnlyDigits(telephone)) || telephone.length() != 10) {
                    JOptionPane.showMessageDialog(null, "Le numéro de téléphone du fournisseur doit être composé uniquement de 10 chiffres.");
                }
                else{
                    Provider fournisseur = new Provider(nom, adresse, telephone);
    
                    int createdId = fournisseurDAO.ajouterFournisseur(fournisseur);
                    fournisseur.setId_provider(createdId);
                    ProvidersView parentView = vue.getParentView();
                    if (parentView != null) {
                        parentView.addProviderToTable(fournisseur);
                    }
    
                    JOptionPane.showMessageDialog(null, "Fournisseur ajouté avec succès!");
                    vue.close();
                }
            }
        });
    }
    public FournisseurController(ProvidersView vue, FournisseurDAO fournisseurDAO) {
        ArrayList<Provider> providers = fournisseurDAO.getFournisseurs();
        vue.setProviders(providers);
    }

    public FournisseurController(UpdateProviderView vue, FournisseurDAO fournisseurDAO) {
        this.fournisseurDAO = fournisseurDAO;
        vue.setAjouterFournisseurListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = vue.getName();
                String adresse = vue.getAddress();
                String telephone = vue.getPhone();

                if (nom.isBlank() || adresse.isBlank() || telephone.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                }
                else if (StringChecker.isNumeric(nom)) {
                    JOptionPane.showMessageDialog(null, "Le nom du fournisseur ne doit pas être une valeur numérique.");
                }
                else if (StringChecker.isNumeric(adresse)) {
                    JOptionPane.showMessageDialog(null, "L'adresse du fournisseur ne doit pas être une valeur numérique.");
                }
                else if ((!StringChecker.containsOnlyDigits(telephone)) || telephone.length() != 10) {
                    JOptionPane.showMessageDialog(null, "Le numéro de téléphone du fournisseur doit être composé uniquement de 10 chiffres.");
                }
                else{
                    int id = vue.getProvider().getId_provider();
                    Provider fournisseur = new Provider(id, nom, adresse, telephone);
    
                    fournisseurDAO.modifierFournisseur(fournisseur);
                    ProvidersView parentView = vue.getParentView();
                    if (parentView != null) {
                        parentView.updateProvider(vue.getIndexRow(), fournisseur);
                    }
    
                    JOptionPane.showMessageDialog(null, "Fournisseur modifié avec succès!");
                    vue.close();
                }
            }
        });
    }

    public FournisseurController(DeleteProviderView vue, ProvidersView parentView, FournisseurDAO fournisseurDAO, Provider provider, int row) {
        this.fournisseurDAO = fournisseurDAO;
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