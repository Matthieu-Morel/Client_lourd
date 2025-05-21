package test;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import model.Produit;
import model.Vente;

public class TestProduitVente {
    private Produit produit;
    private Vente vente;

    @BeforeEach
    void setUp() {
        produit = new Produit();
        vente = new Vente();
        produit.addSellings(vente);
        vente.setProduct(produit);
    }

    @Test
    public void testRelationProduitVente() {
        assertNotNull(produit.getSellings());
        assertEquals(1, produit.getSellings().size(), "Le produit devrait avoir une vente associée.");
        assertEquals(vente, produit.getSellings().get(0), "La vente associée au produit devrait être la bonne.");
        assertEquals(produit, vente.getProduct(), "Le produit de la vente devrait correspondre au produit associé.");
    }

    @Test
    public void testAjoutVenteProduit() {
        Produit nouveauProduit = new Produit();
        Vente nouvelleVente = new Vente();

        nouveauProduit.addSellings(nouvelleVente);

        assertTrue(nouveauProduit.getSellings().contains(nouvelleVente), "La nouvelle vente devrait être associée au nouveau produit.");
    }
}