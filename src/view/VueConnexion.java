package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Utilisateur;
import model.DAO.UtilisateurDAO;

import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VueConnexion extends JFrame{
    private UtilisateurDAO utilisateurDAO; 
 
    public VueConnexion() { 
        utilisateurDAO = new UtilisateurDAO();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        setTitle("Connexion"); 
        setLayout(new FlowLayout()); 
        setSize(400, 200); 
 
        JLabel userNameLabel = new JLabel("Nom d'utilisateur:", SwingConstants.CENTER); 
        JTextField userNameField = new JTextField(15); 
 
        JLabel passwordLabel = new JLabel("Mot de passe:", SwingConstants.CENTER); 
        JPasswordField passwordField = new JPasswordField(15); 
 
        JButton loginButton = new JButton("Se connecter"); 
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        loginButton.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) { 
                String name = userNameField.getText(); 
                String password = new String(passwordField.getPassword()); 

                if (name.isBlank() || password.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Veuillez renseigner un nom d'utilisateur et un mot de passe.");
                } else {
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

            } 
        }); 

        JButton exitButton = new JButton("Quitter"); 
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 0, 5));
        formPanel.setBorder(new EmptyBorder(0, 0, 10, 20));
        formPanel.add(userNameLabel);
        formPanel.add(userNameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS));
        btnContainer.add(exitButton);
        btnContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        btnContainer.add(loginButton);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.setBorder(new EmptyBorder( 35, 0, 0, 0));
        jPanel.add(formPanel);
        jPanel.add(btnContainer);
 
        add(jPanel);
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true); 
    }
}