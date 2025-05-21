package test;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import model.Produit;

public class TestProduit {
    private Produit produit1;
    private Produit produit2;
    private Produit produit3;
    private Produit produit4;
    private Produit produit5;

    @BeforeEach
    public void setup() {
        produit1 = new Produit();
        produit2 = new Produit(null, 0);
        produit3 = new Produit(null, 0, 0);
        produit4 = new Produit(null, 0, 0, null);
        produit5 = new Produit(0, null, 0, 0, null);
    }

    @Test
    public void testConstructeurs() {
        assertNotNull(produit1);
        assertNotNull(produit2);
        assertNotNull(produit3);
        assertNotNull(produit4);
        assertNotNull(produit5);
    }
}
