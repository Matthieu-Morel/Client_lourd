package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Product {
    private int id_product;
    private String name;
    private int quantity;
    private double unitPrice;
    private Provider provider;
    private ArrayList<Selling> sellings;

    public Product(String name, int quantity, double unitPrice, Provider provider) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.provider = provider;
        sellings = new ArrayList<>();
    }

    public Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        sellings = new ArrayList<>();
    }

    public Product(String name, double unitPrice, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        sellings = new ArrayList<>();
    }

    public Product() {
        sellings = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    public int getId_product() {
        return id_product;
    }
    public void setId_product(int id) {
        this.id_product = id;
    }
    public ArrayList<Selling> getSellings() {
        return sellings;
    }
    public void addSellings(Selling selling) {
        this.sellings.add(selling);
    }

    @Override
    public String toString() {
        return "Provider{"+
                "id_product=" + id_product +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", provider=" + provider +
                ". sellings=" + sellings +
                "}";
    }

    // public static void addProduct(String name, int quantity, double price, int provider) {
    //     try {
    //         // Connexion à la base de données 
    //         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_stock", "user1", "1234");

    //         // Préparation de la requête SQL 
    //         String query = "INSERT INTO produit (nom_produit, quantite_produit, prix_unitaire_produit, id_fournisseur) VALUES (?,?,?,?)"; 
    //         PreparedStatement pstmt = con.prepareStatement(query);

    //         // Récupération des données saisies par l'utilisateur 
    //         String nameProduct = name; 
    //         int quantityProduct = quantity; 
    //         double priceProduct = price; 
    //         int providerProduct = provider; 

    //         // Remplissage des paramètres de la requête SQL 
    //         pstmt.setString(1, nameProduct);
    //         pstmt.setInt(2, quantityProduct);
    //         pstmt.setDouble(3, priceProduct);
    //         pstmt.setInt(4, providerProduct);

    //         // Exécution de la requête SQL 
    //         int rowsAffected = pstmt.executeUpdate();

    //         // Fermeture de la connexion et du PreparedStatement 
    //         pstmt.close(); 
    //         con.close(); 

    //         // Affichage d'un message de succès 
    //         JOptionPane.showMessageDialog(null, "Produit ajouté avec succès!");
    //     } catch (SQLException ex) { 
    //         ex.printStackTrace(); 
    //         JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du produit: " + ex.getMessage());
    //     }
    // }
}