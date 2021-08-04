package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Map;

public class OrderService {

    OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order getOrder(int id) {
        return orderDao.find(id);
    }

    public List<Order> getAll() {
        return orderDao.getAll();
    }

    public void add() {
        orderDao.add(new Order());
    }

    public Map<Product, Integer> getProductFromCart(int id){
        Order order = orderDao.find(id);
        return order.getCart();
    }

    public void addProductToCart(int orderId, Product product){
        Order order = orderDao.find(orderId);
        order.addToCart(product);
    }

    public void removeProductFromCart(int orderId, Product product) throws AttributeNotFoundException {
        Order order = orderDao.find(orderId);
        order.removeFromCart(product);
    }

    public void decreaseProductFromCart(int orderId, Product product) throws AttributeNotFoundException {
        Order order = orderDao.find(orderId);
        order.decreaseProductInCart(product);
    }

    public void resetCart(int orderId){
        Order order = orderDao.find(orderId);
        order.resetCart();
    }

    public int getCartSize(int orderId) {
        Order order = orderDao.find(orderId);
        return order.getCartSize();
    }

    public float getCartPrice(int orderId) {
        Order order = orderDao.find(orderId);
        return order.sumPrice();
    }


}
