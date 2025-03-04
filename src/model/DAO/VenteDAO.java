package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DAO.Connexion;
import model.Product;
import model.Provider;
import model.Selling;

public class VenteDAO {
    public int ajouterVente(Selling vente) {
        String query = "INSERT INTO vente (id_produit, quantite_vendue_vente, date_vente) VALUES (?, ?, ?)";
        int createdId = -1;

        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, vente.getProduct().getId_product());
            statement.setInt(2, vente.getQuantitySold());
            statement.setDate(3, vente.getDateSold());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            createdId = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
        }
        return createdId;
    }
    public void supprimerVente(Selling vente) {
        String query = "DELETE FROM vente WHERE id_vente=?";
        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vente.getId_selling());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la vente : " + e.getMessage());
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
            System.out.println("Erreur lors de la modification de la vente : " + e.getMessage());
        }
    }
    public ArrayList<Selling> getVentes() {
        String query = "SELECT * FROM vente " +
                        "JOIN produit ON vente.id_produit=produit.id_produit " +
                        "JOIN fournisseur ON produit.id_fournisseur=fournisseur.id_fournisseur ";
        ArrayList<Selling> sellings = new ArrayList<>();

        try (Connection connection = Connexion.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_vente");
                int quantity = resultSet.getInt("quantite_vendue_vente");
                Date date = resultSet.getDate("date_vente");

                int idProvider = resultSet.getInt("id_fournisseur");
                String nameProvider = resultSet.getString("nom_fournisseur");
                String addressProvider = resultSet.getString("adresse_fournisseur");
                String phoneProvider = resultSet.getString("telephone_fournisseur");
                Provider provider = new Provider(idProvider, nameProvider, addressProvider, phoneProvider);

                int idProduct = resultSet.getInt("id_produit");
                String nameProduct = resultSet.getString("nom_produit");
                int quantityProduct = resultSet.getInt("quantite_produit");
                Double unitPriceProduct = resultSet.getDouble("prix_unitaire_produit");
                Product product = new Product(idProduct, nameProduct, quantityProduct, unitPriceProduct, provider);
                
                Selling selling = new Selling(id, product, quantity, date);

                sellings.add(selling);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'obtention des ventes : " + e.getMessage());
        }

        return sellings;
    }
}
