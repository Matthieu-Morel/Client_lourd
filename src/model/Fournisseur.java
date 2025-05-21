package model;

import java.util.ArrayList;

public class Fournisseur {
    private int id_provider;
    private String name;
    private String address;
    private String phone;
    private ArrayList<Produit> products;

    public Fournisseur(int id_provider, String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.id_provider = id_provider;
        products = new ArrayList<>();
    }

    public Fournisseur(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        products = new ArrayList<>();
    }
    public Fournisseur() {
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getId_provider() {
        return id_provider;
    }
    public void setId_provider(int id) {
        this.id_provider = id;
    }
    public ArrayList<Produit> getProducts() {
        return products;
    }
    public void addProduct(Produit product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return name;
    }
}