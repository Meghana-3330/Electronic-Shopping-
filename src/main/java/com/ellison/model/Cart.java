package com.ellison.model;

public class Cart {
    private String username;
    private int prodid;
    private int quantity;
    private String pname; // Joined from product
    private double price; // Joined from product
    private String image; // Joined from product

    public Cart() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getProdid() { return prodid; }
    public void setProdid(int prodid) { this.prodid = prodid; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getPname() { return pname; }
    public void setPname(String pname) { this.pname = pname; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
