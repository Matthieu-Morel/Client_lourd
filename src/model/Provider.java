package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Provider {
    private int id_provider;
    private String name;
    private String address;
    private String phone;
    private ArrayList<Product> products;

    public Provider(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        products = new ArrayList<>();
    }
    public Provider() {
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getId_provider() {
        return id_provider;
    }
    public void setId_provider(int id) {
        this.id_provider = id;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Provider{"+
                "id_provider=" + id_provider +
                ", name='" + name + '\'' +
                ", adress='" + address + '\'' +
                ", products=" + products +
                "}";
    }

    // public static void addProvider(String name, String address, String phone) {
    //     try {
    //         // Connexion à la base de données 
    //         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_stock", "user1", "1234");

    //         // Préparation de la requête SQL 
    //         String query = "INSERT INTO fournisseur (nom_fournisseur, adresse_fournisseur, telephone_fournisseur) VALUES (?,?,?)"; 
    //         PreparedStatement pstmt = con.prepareStatement(query);

    //         // Récupération des données saisies par l'utilisateur 
    //         String nameProvider = name; 
    //         String addressProvider = address; 
    //         String phoneProvider = phone; 

    //         // Remplissage des paramètres de la requête SQL 
    //         pstmt.setString(1, nameProvider); 
    //         pstmt.setString(2, addressProvider); 
    //         pstmt.setString(3, phoneProvider); 

    //         // Exécution de la requête SQL 
    //         int rowsAffected = pstmt.executeUpdate();

    //         // Fermeture de la connexion et du PreparedStatement 
    //         pstmt.close(); 
    //         con.close(); 

    //         // Affichage d'un message de succès 
    //         JOptionPane.showMessageDialog(null, "Fournisseur ajouté avec succès!");
    //     } catch (SQLException ex) { 
    //         ex.printStackTrace(); 
    //         JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du fournisseur: " + ex.getMessage());
    //     } 
    // }
}