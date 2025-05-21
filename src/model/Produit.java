package model;

import java.util.ArrayList;

public class Produit {
    private int id_product;
    private String name;
    private int quantity;
    private double unitPrice;
    private Fournisseur provider;
    private ArrayList<Vente> sellings;

    public Produit(int id, String name, int quantity, double unitPrice, Fournisseur provider) {
        this.id_product = id;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.provider = provider;
        sellings = new ArrayList<>();
    }

    public Produit(String name, int quantity, double unitPrice, Fournisseur provider) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.provider = provider;
        sellings = new ArrayList<>();
    }

    public Produit(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        sellings = new ArrayList<>();
    }

    public Produit(String name, double unitPrice, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        sellings = new ArrayList<>();
    }

    public Produit() {
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
    public Fournisseur getProvider() {
        return provider;
    }
    public void setProvider(Fournisseur provider) {
        this.provider = provider;
    }
    public int getId_product() {
        return id_product;
    }
    public void setId_product(int id) {
        this.id_product = id;
    }
    public ArrayList<Vente> getSellings() {
        return sellings;
    }
    public void addSellings(Vente selling) {
        this.sellings.add(selling);
    }

    @Override
    public String toString() {
        return name;
    }

}