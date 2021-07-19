package com.codecool.shop.model;

import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Product> cart;

    public Cart() {
        this.cart = new ArrayList<>();
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
        if(!isEmpty()) {
            for(Product item:cart) {
                if (item.equals(product)) {
                    cart.remove(product);
                }
                else {
                    throw new AttributeNotFoundException("This product is not in the cart!");
                }
            }
        }
        else {
            throw new ArrayIndexOutOfBoundsException("The cart is empty!");
        }
    }

    public void resetCart() {
        cart = new ArrayList<>();
    }


}
