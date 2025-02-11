package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controler.FournisseurController;
import model.Provider;
import model.DAO.FournisseurDAO;

public class ProvidersView {
    private JDialog frame;
    private JLabel titleLabel;
    private JButton btnAdd;
    private JTable providersTable;
    private ArrayList<Provider> providers;

    public ProvidersView(JFrame jFrame){
        frame = new JDialog(jFrame, "Fournisseurs", true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 1, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        titleLabel = new JLabel("Liste des fournisseurs", SwingConstants.CENTER);

        String[] columnNames = {
            "Nom du fournisseur",
            "Adresse",
            "Numéro de téléphone",
        };

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        FournisseurController fournisseurController = new FournisseurController(this, fournisseurDAO);

        List<Object[]> data = new ArrayList<>();
        for (Provider provider : providers) {
            String[] providerData = {
                provider.getName(),
                provider.getAddress(),
                provider.getPhone(),
            };
            data.add(providerData);
        }

        DefaultTableModel model = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        providersTable = new JTable(model);
        providersTable.setEnabled(false);
        JScrollPane jScrollPane = new JScrollPane(providersTable);

        btnAdd = new JButton("Ajouter un fournisseur");
        ProvidersView parentView = this;
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProviderView view = new AddProviderView(frame, parentView);
            }
        });

        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS));
        btnContainer.add(btnAdd);

        contentPanel.add(titleLabel);
        contentPanel.add(jScrollPane);
        contentPanel.add(btnContainer);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    public void addProviderToTable(Provider provider) {
        DefaultTableModel tableModel = (DefaultTableModel) providersTable.getModel();
        String[] providerData = {
            provider.getName(),
            provider.getAddress(),
            provider.getPhone(),
        };
        tableModel.addRow(providerData);
        providersTable.setModel(tableModel);
        providersTable.repaint();
    }
}