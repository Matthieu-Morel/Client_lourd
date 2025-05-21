package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Fournisseur;

public class FournisseurDAO {
    public int ajouterFournisseur(Fournisseur provider) {
        String query = "INSERT INTO fournisseur (nom_fournisseur, adresse_fournisseur, telephone_fournisseur) VALUES (?, ?, ?)";
        int createdId = -1;
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getAddress());
            statement.setString(3, provider.getPhone());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            createdId = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du fournisseur : " + e.getMessage());
        }
        return createdId;
    }
    public void supprimerFournisseur(Fournisseur provider) {
        String query = "DELETE FROM fournisseur WHERE id_fournisseur=?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, provider.getId_provider());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du fournisseur : " + e.getMessage());
        }
    }
    public void modifierFournisseur(Fournisseur provider) {
        String query = "UPDATE fournisseur SET nom_fournisseur=?, adresse_fournisseur=?, telephone_fournisseur=? WHERE id_fournisseur = ?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getAddress());
            statement.setString(3, provider.getPhone());
            statement.setInt(4, provider.getId_provider());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du fournisseur : " + e.getMessage());
        }
    }
    public ArrayList<Fournisseur> getFournisseurs() {
        String query = "SELECT * FROM fournisseur";
        ArrayList<Fournisseur> providers = new ArrayList<>();

        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_fournisseur");
                String name = resultSet.getString("nom_fournisseur");
                String address = resultSet.getString("adresse_fournisseur");
                String phone = resultSet.getString("telephone_fournisseur");

                Fournisseur provider = new Fournisseur(id, name, address, phone);

                providers.add(provider);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'obtention des fournisseurs : " + e.getMessage());
        }

        return providers;
    }
    public Fournisseur getFournisseurById(int idProvider) {
        String query = "SELECT * FROM fournisseur WHERE id_fournisseur=?";
        Fournisseur provider = new Fournisseur();

        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idProvider);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int id = resultSet.getInt("id_fournisseur");
            String name = resultSet.getString("nom_fournisseur");
            String address = resultSet.getString("adresse_fournisseur");
            String phone = resultSet.getString("telephone_fournisseur");
            provider = new Fournisseur(id, name, address, phone);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'obtention du fournisseur : " + e.getMessage());
        }

        return provider;
    }
}