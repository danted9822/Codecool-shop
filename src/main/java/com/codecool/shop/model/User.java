package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String shippingAddress;
    private List<Order> orders;

    public User(){
        this.orders = new ArrayList();
        orders.add(new Order());
    };


    public User(String firstName, String lastName, String email, String phoneNumber, String billingAddress, String shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.orders = new ArrayList<>();
        this.orders.add(new Order());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String country, String city, String zipcode, String address) {
        this.billingAddress = addressToString(country, city, zipcode, address);
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String country, String city, String zipcode, String address) {
        this.shippingAddress = addressToString(country, city, zipcode, address);
    }

    public String addressToString
            (String country, String city, String zipcode, String address) {
        String stringAddress = country + "," + city + ","+ zipcode + "," + address;
        return stringAddress;
    }

    public HashMap<String, String> addressToDictionary
            (String country, String city, String zipcode, String address) {
        HashMap<String, String> DictionaryAddress = new HashMap<String, String>();
        DictionaryAddress.put("Country", country);
        DictionaryAddress.put("City", city);
        DictionaryAddress.put("Zipcode", zipcode);
        DictionaryAddress.put("Address", address);
        return DictionaryAddress;
    }

    public Order getOrder(Order order) {
        return this.orders.stream().filter(t -> t.equals(order)).findFirst().orElse(null);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("\n").append(firstName).append("\n").append(lastName).append("\n").append(email)
                .append("\n").append(phoneNumber).append("\n").append(billingAddress).append("\n").append(shippingAddress)
                .append("\n").append(orders);
        return sb.toString();
    }
}
