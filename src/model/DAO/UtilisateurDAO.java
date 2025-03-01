package model.DAO;

import java.sql.*;

import model.Utilisateur;

public class UtilisateurDAO {
    public Utilisateur getUtilisateur(String nom, String motDePasse) {
        String query = "SELECT * FROM utilisateur WHERE nom_utilisateur = ? AND mot_de_passe_utilisateur = ?";
        
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, nom);
            statement.setString(2, motDePasse);
 
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                    rs.getInt("id_utilisateur"),
                    rs.getString("nom_utilisateur"),
                    rs.getString("mot_de_passe_utilisateur"),
                    rs.getString("role_utilisateur")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}