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
        if(!isEmpty()) {
            for(Product item:cart) {
                System.out.println(item);
                if (item.equals(product)) {
                    cart.remove(product);
                }
                else {
                    System.out.println("This product is not in the cart!");
                }
            }
        }
        else {
            throw new ArrayIndexOutOfBoundsException("The cart is empty!");
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
