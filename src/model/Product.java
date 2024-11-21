package model;

public class Product {
    private String name;
    private int quantity;
    private double unitPrice;
    private Provider provider;

    public Product(String name, int quantity, double unitPrice, Provider provider) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.provider = provider;
    }
    public Product() {
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
}