package com.ellison.model;

public class Order {
    private String orderId;
    private int prodId;
    private String username;
    private int quantity;
    private double amount;
    private boolean shipped;
    private String pname; // Joined from product
    private String image; // Joined from product
    private String paymentMethod;

    public Order() {}

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public int getProdId() { return prodId; }
    public void setProdId(int prodId) { this.prodId = prodId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public boolean isShipped() { return shipped; }
    public void setShipped(boolean shipped) { this.shipped = shipped; }
    public String getPname() { return pname; }
    public void setPname(String pname) { this.pname = pname; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
