package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Product {
    private int id_product;
    private String name;
    private int quantity;
    private double unitPrice;
    private Provider provider;
    private ArrayList<Selling> sellings;

    public Product(int id, String name, int quantity, double unitPrice, Provider provider) {
        this.id_product = id;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.provider = provider;
        sellings = new ArrayList<>();
    }

    public Product(String name, int quantity, double unitPrice, Provider provider) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.provider = provider;
        sellings = new ArrayList<>();
    }

    public Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        sellings = new ArrayList<>();
    }

    public Product(String name, double unitPrice, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        sellings = new ArrayList<>();
    }

    public Product() {
        sellings = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    public int getId_product() {
        return id_product;
    }
    public void setId_product(int id) {
        this.id_product = id;
    }
    public ArrayList<Selling> getSellings() {
        return sellings;
    }
    public void addSellings(Selling selling) {
        this.sellings.add(selling);
    }

    @Override
    public String toString() {
        return name;
    }

}