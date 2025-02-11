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

public class FournisseurController {
    private AddProviderView vue;
    private FournisseurDAO fournisseurDAO;
    public FournisseurController (AddProviderView vue, FournisseurDAO fournisseurDAO) {
        this.vue = vue;
        this.fournisseurDAO = fournisseurDAO;
        this.vue.setAjouterFournisseurListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = vue.getName();
                String adresse = vue.getAddress();
                String telephone = vue.getPhone();
                Provider fournisseur = new Provider(nom, adresse, telephone);
                fournisseurDAO.ajouterFournisseur(fournisseur);
                JOptionPane.showMessageDialog(null, "Fournisseur ajouté avec succès!");
                vue.close();
                ProvidersView parentView = vue.getParentView();
                if (parentView != null) {
                    parentView.addProviderToTable(fournisseur);
                }
            }
        });
    }
    public FournisseurController (ProvidersView vue, FournisseurDAO fournisseurDAO) {
        ArrayList<Provider> providers = fournisseurDAO.getFournisseurs();
        vue.setProviders(providers);
    }
}