package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

public class Selling {
    private int id_selling;
    private Product product;
    private int quantitySold;
    private Date dateSold;

    public Selling(int id, Product product, int quantitySold, Date dateSold) {
        this.id_selling = id;
        this.product = product;
        this.quantitySold = quantitySold;
        this.dateSold = dateSold;
    }

    public Selling(Product product, int quantitySold, Date dateSold) {
        this.product = product;
        this.quantitySold = quantitySold;
        this.dateSold = dateSold;
    }
    public Selling() {
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantitySold() {
        return quantitySold;
    }
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
    public Date getDateSold() {
        return dateSold;
    }
    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
    }
    public int getId_selling() {
        return id_selling;
    }
    public void setId_selling(int id_selling) {
        this.id_selling = id_selling;
    }

    public boolean isInStock() {
        if (product.getQuantity() > 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public void printQuantity() {
        System.out.println("La quantité du produit "+product.getName()+" est "+product.getQuantity());
    }

    public void printProvider() {
        System.out.println("Le fournisseur du produit "+product.getName()+" est "+product.getProvider().getName());
    }

    @Override
    public String toString() {
        return "Provider{"+
                "id_selling=" + id_selling +
                ", product=" + product +
                ", quantitySold=" + quantitySold +
                ", dateSold=" + dateSold +
                "}";
    }
}
