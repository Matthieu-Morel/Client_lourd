package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Provider;

public class FournisseurDAO {
    public void ajouterFournisseur(Provider provider) {
        String query = "INSERT INTO fournisseur (nom_fournisseur, adresse_fournisseur, telephone_fournisseur) VALUES (?, ?, ?)";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getAddress());
            statement.setString(3, provider.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
    public void supprimerFournisseur(Provider provider) {
        String query = "DELETE FROM fournisseur WHERE id_fournisseur=?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, provider.getId_provider());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
    public void modifierFournisseur(Provider provider) {
        String query = "UPDATE fournisseur SET nom_fournisseur=?, adresse_fournisseur=?, telephone_fournisseur=? WHERE id_fournisseur = ?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getAddress());
            statement.setString(3, provider.getPhone());
            statement.setInt(4, provider.getId_provider());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
    public ArrayList<Provider> getFournisseurs() {
        String query = "SELECT * FROM fournisseur";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
}
