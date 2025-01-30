package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DAO.Connexion;
import model.Selling;

public class VenteDAO {
    public void ajouterVente(Selling vente) {
        String query = "INSERT INTO vente (id_produit, quantite_vendue_vente, date_vente) VALUES (?, ?, ?)";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vente.getProduct().getId_product());
            statement.setInt(2, vente.getQuantitySold());
            statement.setDate(3, vente.getDateSold());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
        }
    }
    public void supprimerVente(Selling vente) {
        String query = "DELETE FROM vente WHERE id_vente=?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vente.getId_selling());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
        }
    }
    public void modifierVente(Selling vente) {
        String query = "UPDATE vente SET id_produit=?, quantite_vendue_vente=?, date_vente=? WHERE id_vente=?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vente.getProduct().getId_product());
            statement.setInt(2, vente.getQuantitySold());
            statement.setDate(3, vente.getDateSold());
            statement.setInt(4, vente.getId_selling());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
        }
    }
}
