package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controler.ProduitController;
import controler.VenteController;
import model.Product;
import model.Provider;
import model.Selling;
import model.Utilisateur;
import model.DAO.FournisseurDAO;
import model.DAO.ProduitDAO;
import model.DAO.VenteDAO;
import utils.CustomTableModel;

public class SellingsView {
    private JDialog frame;
    private JLabel titleLabel;
    private JTable sellingsTable;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnBackToMenu;
    private ArrayList<Selling> sellings;
    private ArrayList<Product> products;

    public SellingsView(JFrame jFrame, Utilisateur utilisateur) {
        frame = new JDialog(jFrame, "Gestion des ventes", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        titleLabel = new JLabel("Liste des ventes", SwingConstants.CENTER);

        String[] columnNames = {
            "Nom du produit",
            "Quantité vendue",
            "Date de vente",
        };

        VenteDAO venteDAO = new VenteDAO();
        ProduitDAO produitDAO = new ProduitDAO();
        VenteController venteController = new VenteController(this, venteDAO, produitDAO);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<Object[]> data = new ArrayList<>();
        for (Selling selling : sellings) {
            String[] sellingData = {
                selling.getProduct().getName(),
                Integer.toString(selling.getQuantitySold()),
                simpleDateFormat.format(selling.getDateSold()),
            };
            data.add(sellingData);
        }

        CustomTableModel model = new CustomTableModel(data.toArray(new Object[0][]), columnNames);
        sellingsTable = new JTable(model);
        sellingsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(sellingsTable);

        SellingsView parentView = this;
        btnAdd = new JButton("Ajouter une vente");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSellingView view = new AddSellingView(jFrame, parentView, products);
            }
        });

        btnUpdate = new JButton("Modifier une vente");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int row = productsTable.getSelectedRow();
                // if (row != -1) {
                //     Product product = products.get(row);
                //     UpdateProductView view = new UpdateProductView(frame, parentView, product, providers, row);
                // }
            }
        });

        btnDelete = new JButton("Supprimer une vente");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int row = productsTable.getSelectedRow();
                // if (row != -1) {
                //     Product product = products.get(row);
                //     DeleteProductView view = new DeleteProductView(frame, parentView, product, row);
                // }
            }
        });

        btnBackToMenu = new JButton("Retour");
        btnBackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        sellingsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = sellingsTable.getSelectedRow();
                if (row != -1) {
                    btnUpdate.setEnabled(true);
                    if (!utilisateur.getRole().equals("manager")) {
                        btnDelete.setEnabled(true);
                    }
                }
                else{
                    btnUpdate.setEnabled(false);
                    btnDelete.setEnabled(false);
                }
            }
            
        });

        JPanel titleAndBackContainer = new JPanel();
        titleAndBackContainer.setLayout(new BorderLayout());
        titleAndBackContainer.add(btnBackToMenu, BorderLayout.WEST);
        titleAndBackContainer.add(titleLabel, BorderLayout.CENTER);
        titleAndBackContainer.add(Box.createRigidArea(new Dimension((int) btnBackToMenu.getPreferredSize().getWidth(), 0)), BorderLayout.EAST);

        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS));
        btnContainer.add(btnAdd);
        btnContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        btnContainer.add(btnUpdate);
        btnContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        btnContainer.add(btnDelete);

        contentPanel.add(titleAndBackContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(jScrollPane);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(btnContainer);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setSellings(ArrayList<Selling> sellings) {
        this.sellings = sellings;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addSellingToTable(Selling selling) {
        DefaultTableModel tableModel = (DefaultTableModel) sellingsTable.getModel();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String[] sellingData = {
            selling.getProduct().getName(),
            Integer.toString(selling.getQuantitySold()),
            simpleDateFormat.format(selling.getDateSold()),
        };
        tableModel.addRow(sellingData);
        sellings.add(selling);
    }
}