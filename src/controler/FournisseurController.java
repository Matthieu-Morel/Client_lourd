package controler;

import javax.swing.JOptionPane;

import model.Provider;
import model.DAO.FournisseurDAO;
import view.ProviderView;

public class FournisseurController {
    private ProviderView vue;
    private FournisseurDAO fournisseurDAO;
    public FournisseurController (ProviderView vue, FournisseurDAO fournisseurDAO) {
        this.vue = vue;
        this.fournisseurDAO = fournisseurDAO;
        this.vue.setAjouterFournisseurListener(e -> {
            String nom = vue.getName();
            String adresse = vue.getAddress();
            String telephone = vue.getPhone();
            Provider fournisseur = new Provider(nom, adresse, telephone); // L'id sera généré par la DB
            fournisseurDAO.ajouterFournisseur(fournisseur);
            JOptionPane.showMessageDialog(null, "Fournisseur ajouté avec succès!");
        });
    }
}