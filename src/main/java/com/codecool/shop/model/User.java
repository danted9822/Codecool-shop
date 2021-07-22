package com.codecool.shop.model;

import java.util.HashMap;

public class User {
    private String name;
    private String email;
    private int phoneNumber;
    private String billingAddress;
    private String shippingAddress;
    private Cart cart;

    User(String name, String email, int phoneNumber, String billingAddress, String shippingAddress, Cart cart) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String country, String city, String zipcode, String address) {
        this.billingAddress = AddressToString(country, city, zipcode, address);
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String country, String city, String zipcode, String address) {
        this.shippingAddress = AddressToString(country, city, zipcode, address);
    }

    public String AddressToString
            (String country, String city, String zipcode, String address) {
        String stringAddress = country + ", " + city + ", "+ zipcode + ", " + address;
        return stringAddress;
    }

    public HashMap<String, String> AddressToDictionary
            (String country, String city, String zipcode, String address) {
        HashMap<String, String> DictionaryAddress = new HashMap<String, String>();
        DictionaryAddress.put("Country", country);
        DictionaryAddress.put("City", city);
        DictionaryAddress.put("Zipcode", zipcode);
        DictionaryAddress.put("Address", address);
        return DictionaryAddress;
    }

    public Cart getCart() {
        return cart;
    }
}