package App;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private static JFrame frame;
    private static JButton btnProduct;
    private static JButton btnProvider;
    private static JButton btnSelling;
    private static JButton btnQuit;
    private static JButton btnReport;
    public static void main(String[] args) {
        frame = new JFrame("Gestion de stock");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 1, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        btnProduct = new JButton("Gestion des produits");
        btnProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductsView view = new ProductsView(frame);
            }
        });
        btnProvider = new JButton("Gestion des fournisseurs");
        btnProvider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProvidersView providerView = new ProvidersView(frame);
            }
        });
        btnSelling = new JButton("Ajouter une vente");
        btnSelling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SellingView sellingView = new SellingView(frame);
            }
        });
        btnReport = new JButton("Consulter les rapports");
        btnQuit = new JButton("Quitter");
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        contentPanel.add(btnProvider);
        contentPanel.add(btnProduct);
        contentPanel.add(btnSelling);
        contentPanel.add(btnReport);
        contentPanel.add(btnQuit);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); 
    }
}
