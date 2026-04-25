package com.ellison.model;

public class Product {
    private int pid;
    private String pname;
    private String type;
    private double price;
    private int quantity;
    private String image;

    public Product() {}

    public Product(int pid, String pname, String type, double price, int quantity, String image) {
        this.pid = pid;
        this.pname = pname;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public int getPid() { return pid; }
    public void setPid(int pid) { this.pid = pid; }
    public String getPname() { return pname; }
    public void setPname(String pname) { this.pname = pname; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
