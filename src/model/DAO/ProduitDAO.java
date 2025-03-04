package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Product;
import model.Provider;
import model.DAO.Connexion;

public class ProduitDAO {
    public int ajouterProduit(Product produit) {
        String query = "INSERT INTO produit (nom_produit, prix_unitaire_produit, quantite_produit, id_fournisseur) VALUES (?, ?, ?, ?)";
        int createdId = -1;
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, produit.getName());
            statement.setDouble(2, produit.getUnitPrice());
            statement.setInt(3, produit.getQuantity());
            statement.setInt(4, produit.getProvider().getId_provider());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            createdId = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
        return createdId;
    }
    public void supprimerProduit(Product produit) {
        String query = "DELETE FROM produit WHERE id_produit=?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, produit.getId_product());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du produit : " + e.getMessage());
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
            System.out.println("Erreur lors de la modification du produit : " + e.getMessage());
        }
    }
    public ArrayList<Product> getProduits() {
        String query = "SELECT * FROM produit " +
                        "JOIN fournisseur ON produit.id_fournisseur=fournisseur.id_fournisseur";
        ArrayList<Product> products = new ArrayList<>();

        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_produit");
                String name = resultSet.getString("nom_produit");
                int quantity = resultSet.getInt("quantite_produit");
                Double unitPrice = resultSet.getDouble("prix_unitaire_produit");

                int idProvider = resultSet.getInt("id_fournisseur");
                String nameProvider = resultSet.getString("nom_fournisseur");
                String addressProvider = resultSet.getString("adresse_fournisseur");
                String phoneProvider = resultSet.getString("telephone_fournisseur");
                Provider provider = new Provider(idProvider, nameProvider, addressProvider, phoneProvider);
                
                Product product = new Product(id, name, quantity, unitPrice, provider);

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'obtention des produits : " + e.getMessage());
        }

        return products;
    }
}
