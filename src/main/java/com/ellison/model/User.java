package com.ellison.model;

public class User {
    private String email;
    private String name;
    private String mobile;
    private String address;
    private String pincode;
    private String password;

    public User() {}

    public User(String email, String name, String mobile, String address, String pincode, String password) {
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.pincode = pincode;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
