package test;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import model.Fournisseur;
import model.Produit;

public class TestFournisseurProduit {
    private Produit produit;
    private Fournisseur fournisseur;

    @BeforeEach
    void setUp() {
        fournisseur = new Fournisseur();
        produit = new Produit();
        fournisseur.addProduct(produit);
        produit.setProvider(fournisseur);
    }

    @Test
    public void testRelationFournisseurProduit() {
        assertNotNull(fournisseur.getProducts());
        assertEquals(1, fournisseur.getProducts().size(), "Le fournisseur devrait avoir un produit associé.");
        assertEquals(produit, fournisseur.getProducts().get(0), "Le produit associé au fournisseur devrait être le bon.");
        assertEquals(fournisseur, produit.getProvider(), "Le fournisseur du produit devrait correspondre au fournisseur associé.");
    }

    @Test
    public void testAjoutProduitFournisseur() {
        Produit nouveauProduit = new Produit();
        Fournisseur nouveauFournisseur = new Fournisseur();

        nouveauFournisseur.addProduct(nouveauProduit);

        assertTrue(nouveauFournisseur.getProducts().contains(nouveauProduit), "Le nouveau produit devrait être associé au nouveau fournisseur.");
    }
}