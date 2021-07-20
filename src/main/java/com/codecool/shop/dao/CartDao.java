package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.management.AttributeNotFoundException;
import java.util.List;

public interface CartDao {

    void addProductToCart(int id);
    void removeProductFromCart(int id) throws AttributeNotFoundException;
    void resetCart();
    float totalPrice();
    Product getProduct(int id);

    List<Product> getAll();

}
