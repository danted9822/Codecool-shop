package com.codecool.shop.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private int id;
    private String filename;
    private LocalDateTime date;

    private OrderType orderType;

    private Map<Product, Integer> cart;

    public Order(OrderType orderType){
        this.orderType = orderType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Order() {
        this.cart = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    /** Add product to cart & increase quantity */
    public void addToCart(Product product) {
        if (!cart.containsKey(product)) {
            cart.put(product, 1);
        } else {
            cart.put(product, cart.get(product) + 1);
        }
    }

    /** Decrease product quantity in cart */
    public void decreaseProductInCart(Product product) {
        if (!isEmpty()) {
            cart.put(product, cart.get(product) - 1);
            if (cart.get(product) == 0) cart.remove(product);
        }
    }

    /** Remove product from cart */
    public void removeFromCart(Product product) {
        if(!isEmpty()) {
            cart.remove(product);
        }
    }

    public boolean isEmpty() {
        return cart.size() == 0;
    }

    /** Summarise the prize of all products in cart */
    public float sumPrice() {
        float totalPrize = 0;
        if (!isEmpty()) {
            for (Map.Entry<Product, Integer> product : cart.entrySet()) {
                totalPrize += product.getKey().getDefaultPrice() * product.getValue();
            }
        }
        return totalPrize;
    }

    public void resetCart() {
        cart = new HashMap<>();
    }

    public int getCartSize() {
        return cart.size();
    }
}
