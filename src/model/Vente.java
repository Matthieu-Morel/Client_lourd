package model;

import java.sql.Date;

public class Vente {
    private int id_selling;
    private Produit product;
    private int quantitySold;
    private Date dateSold;

    public Vente(int id, Produit product, int quantitySold, Date dateSold) {
        this.id_selling = id;
        this.product = product;
        this.quantitySold = quantitySold;
        this.dateSold = dateSold;
    }

    public Vente(Produit product, int quantitySold, Date dateSold) {
        this.product = product;
        this.quantitySold = quantitySold;
        this.dateSold = dateSold;
    }
    public Vente() {
    }

    public Produit getProduct() {
        return product;
    }
    public void setProduct(Produit product) {
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

}
