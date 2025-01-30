package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Product;
import model.DAO.Connexion;

public class ProduitDAO {
    public void ajouterProduit(Product produit) {
        String query = "INSERT INTO produit (nom_produit, prix_unitaire_produit, quantite_produit, id_fournisseur) VALUES (?, ?, ?, ?)";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, produit.getName());
            statement.setDouble(2, produit.getUnitPrice());
            statement.setInt(3, produit.getQuantity());
            statement.setInt(4, produit.getProvider().getId_provider());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
    public void supprimerProduit(Product produit) {
        String query = "DELETE FROM produit WHERE id_produit=?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, produit.getId_product());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
    public void modifierProduit(Product produit) {
        String query = "UPDATE produit SET nom_produit=?, quantite_produit=?, prix_unitaire_produit=?, id_fournisseur=? WHERE id_produit = ?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, produit.getName());
            statement.setInt(2, produit.getQuantity());
            statement.setDouble(3, produit.getUnitPrice());
            statement.setInt(4, produit.getProvider().getId_provider());
            statement.setInt(5, produit.getId_product());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
}
