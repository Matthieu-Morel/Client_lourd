package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Provider;
import model.Utilisateur;
import model.DAO.FournisseurDAO;

import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList; 
 
public class MenuPrincipal extends JFrame { 
    private Utilisateur utilisateur;
    private JButton btnProvider;
    private JButton btnProduct;
    private JButton btnSelling;
    private JButton btnLogout;
    private JButton btnQuit;
     
    public MenuPrincipal(Utilisateur utilisateur) {
        this.utilisateur = utilisateur; 
        setTitle("Menu Principal"); 
        setLayout(new FlowLayout()); 
        setSize(350, 400); 
    
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 1, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JFrame frame = this;

        FournisseurDAO fournisseurDAO = new FournisseurDAO();

        btnProvider = new JButton("Gestion des fournisseurs");
        btnProvider.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        btnProvider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProvidersView providerView = new ProvidersView(frame, utilisateur);
            }
        });
        btnProduct = new JButton("Gestion des produits");
        btnProduct.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        btnProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Provider> providers = fournisseurDAO.getFournisseurs();
                if (providers.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Aucun fournisseur disponible. Veuillez ajouter un fournisseur pour ajouter un produit.");
                } else {
                    ProductsView view = new ProductsView(frame, utilisateur);
                }
            }
        });
        btnSelling = new JButton("Gestion des ventes");
        btnSelling.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        btnSelling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SellingsView view = new SellingsView(frame, utilisateur);
            }
        });
        btnLogout = new JButton("Déconnexion");
        btnLogout.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView();
            } 
        });
        btnQuit = new JButton("Quitter");
        btnQuit.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        contentPanel.add(btnProvider);
        contentPanel.add(btnProduct);
        contentPanel.add(btnSelling);
        contentPanel.add(btnLogout);
        contentPanel.add(btnQuit);

        add(contentPanel, BorderLayout.CENTER);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true); 
    } 
}