package com.codecool.shop.model;

import java.util.HashMap;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String billingAddress;
    private String shippingAddress;
    private List orders;

    User(String firstName, String lastName, String email, int phoneNumber, String billingAddress, String shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.orders.add(new Order());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

//    public Order getCart() {
//        return cart;
//    }
}
