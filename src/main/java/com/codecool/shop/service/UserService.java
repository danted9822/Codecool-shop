package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.List;

public class UserService {

    UserDao userDao;
    OrderDao orderDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(int id) {
        return userDao.find(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void addUser() {
        userDao.add(new User());
    }

    public void addUser(String firstName, String lastName, String email, String phoneNumber, String billingAddress, String shippingAddress) {
        userDao.add(new User(firstName, lastName, email, phoneNumber, billingAddress, shippingAddress));
    }

    public String getFirstName(int id) {
        User user = userDao.find(id);
        return user.getFirstName();
    }

    public void setFirstName(int id, String firstName) {
        User user = userDao.find(id);
        user.setFirstName(firstName);
    }

    public String getLastName(int id) {
        User user = userDao.find(id);
        return user.getLastName();
    }

    public void setLastName(int id, String lastName) {
        User user = userDao.find(id);
        user.setLastName(lastName);
    }

    public String getEmail(int id) {
        User user = userDao.find(id);
        return user.getEmail();
    }

    public void setEmail(int id, String email) {
        User user = userDao.find(id);
        user.setEmail(email);
    }

    public String getPhoneNumber(int id) {
        User user = userDao.find(id);
        return user.getPhoneNumber();
    }

    public void setPhoneNumber(int id, String phoneNumber) {
        User user = userDao.find(id);
        user.setPhoneNumber(phoneNumber);
    }

    public String getBillingAddress(int id) {
        User user = userDao.find(id);
        return user.getBillingAddress();
    }

    public void setBillingAddress(int id, String country, String city, String zipcode, String address) {
        User user = userDao.find(id);
        user.setBillingAddress(country, city, zipcode, address);
    }

    public String getShippingAddress(int id) {
        User user = userDao.find(id);
        return user.getShippingAddress();
    }

    public void setShippingAddress(int id, String country, String city, String zipcode, String address) {
        User user = userDao.find(id);
        user.setShippingAddress(country, city, zipcode, address);
    }

    public Order getOrder(int userId, int orderId) {
        User user = userDao.find(userId);
        Order order = orderDao.find(orderId);
        return user.getOrder(order);
    }

    public List<Order> getAllOrders(int id) {
        User user = userDao.find(id);
        return user.getAllOrders();
    }
}
