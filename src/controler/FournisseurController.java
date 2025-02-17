package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.Provider;
import model.DAO.FournisseurDAO;
import view.AddProviderView;
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
        });
    }

}