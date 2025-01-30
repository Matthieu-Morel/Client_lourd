package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

public class Selling {
    private int id_selling;
    private Product product;
    private int quantitySold;
    private Date dateSold;
    private ArrayList<Rapport> rapports;

    public Selling(Product product, int quantitySold, Date dateSold) {
        this.product = product;
        this.quantitySold = quantitySold;
        this.dateSold = dateSold;
        rapports = new ArrayList<>();
    }
    public Selling() {
        rapports = new ArrayList<>();
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantitySold() {
        return quantitySold;
    }
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
    public Date getDateSold() {
        return dateSold;
    }
    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
    }
    public int getId_selling() {
        return id_selling;
    }
    public void setId_selling(int id_selling) {
        this.id_selling = id_selling;
    }
    public ArrayList<Rapport> getRapports() {
        return rapports;
    }
    public void addRapports(Rapport rapport) {
        this.rapports.add(rapport);
    }

    public boolean isInStock() {
        if (product.getQuantity() > 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public void printQuantity() {
        System.out.println("La quantité de "+product.getName()+" est "+product.getQuantity());
    }

    public void printProvider() {
        System.out.println("La quantité de "+product.getName()+" est "+product.getProvider().getName());
    }

    @Override
    public String toString() {
        return "Provider{"+
                "id_selling=" + id_selling +
                ", product=" + product +
                ", quantitySold=" + quantitySold +
                ", dateSold=" + dateSold +
                ". rapports=" + rapports +
                "}";
    }

    public static void addSelling(int quantity, String date, int product) {
        try {
            // Connexion à la base de données 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_stock", "user1", "1234");

            // Préparation de la requête SQL 
            String query = "INSERT INTO vente (quantite_vendue_vente, date_vente, id_produit) VALUES (?,?,?)"; 
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération des données saisies par l'utilisateur 
            int quantitySelling = quantity; 
            String dateSelling = date; 
            int productSelling = product; 

            // Remplissage des paramètres de la requête SQL 
            pstmt.setInt(1, quantitySelling); 
            pstmt.setString(2, dateSelling); 
            pstmt.setInt(3, productSelling); 

            // Exécution de la requête SQL 
            int rowsAffected = pstmt.executeUpdate();

            // Fermeture de la connexion et du PreparedStatement 
            pstmt.close(); 
            con.close(); 

            // Affichage d'un message de succès 
            JOptionPane.showMessageDialog(null, "Vente ajoutée avec succès!");
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de la vente: " + ex.getMessage());
        }
    }
}
