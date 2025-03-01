package view;

import javax.swing.*;

import model.Utilisateur;
import model.DAO.Connexion;
import model.DAO.UtilisateurDAO;

import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LoginView extends JFrame{
    private UtilisateurDAO utilisateurDAO; 
 
    public LoginView() { 
        utilisateurDAO = new UtilisateurDAO();
 
        setTitle("Login"); 
        setLayout(new FlowLayout()); 
        setSize(300, 150); 
 
        JLabel userNameLabel = new JLabel("Nom d'utilisateur:"); 
        JTextField userNameField = new JTextField(15); 
 
        JLabel passwordLabel = new JLabel("Mot de passe:"); 
        JPasswordField passwordField = new JPasswordField(15); 
 
        JButton loginButton = new JButton("Se connecter"); 
 
        loginButton.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) { 
                String name = userNameField.getText(); 
                String password = new String(passwordField.getPassword()); 

                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-512");
                    
                    byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

                    BigInteger integer = new BigInteger(1, hashedPassword);
                    password = integer.toString(16);
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                }
 
                Utilisateur user = utilisateurDAO.getUtilisateur(name, password); 
 
                if (user != null) { 
                    new MenuPrincipal(user); 
                    dispose();
                } else { 
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect !"); 
                } 
            } 
        }); 
 
        add(userNameLabel); 
        add(userNameField); 
        add(passwordLabel); 
        add(passwordField); 
        add(loginButton); 
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setVisible(true); 
    }
}