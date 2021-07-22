package com.codecool.shop.model;

import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int id;
    private List<Product> cart;


    public Cart() {
        this.cart = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        if (product == null) throw new NullPointerException("This product is not available!");
        cart.add(product);
    }

    public boolean isEmpty() {
        return cart.size() == 0;
    }

    public void removeFromCart(Product product) throws AttributeNotFoundException {
        List<Product> cartTemp = new ArrayList<>();
        cartTemp.addAll(cart);
        if(!isEmpty()) {
            for(Product item:cart) {
                if (item.equals(product)) {
                    cartTemp.remove(product);
                }
                else {
                    System.out.println("This product is not in the cart!");
                }
            }
            cart.clear();
            cart.addAll(cartTemp);
        }
        else {
            System.out.println("The cart is empty!");
        }
    }

    public float sumPrice() {
        float totalPrize = 0;
        if (!isEmpty()) {
            for (Product product:cart) {
                totalPrize += product.getDefaultPrice();
            }
        }
        return totalPrize;
    }

    public void resetCart() {
        cart = new ArrayList<>();
    }

    public int getSize() {
        return cart.size();
    }


}
