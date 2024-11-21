package model;

import java.util.Date;

public class Selling {
    private Product product;
    private int quantitySold;
    private Date dateSold;

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

    public boolean isInStock() {
        if (product.getQuantity() > 0) {
            return true;
        }
        else{
            return false;
        }
    }
}
