package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private Cart data = new Cart();
    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    public void addProductToCart(int id) {
        data.addToCart(ProductDaoMem.getInstance().find(id));
    }

    public void removeProductFromCart(int id) throws AttributeNotFoundException {
        data.removeFromCart(ProductDaoMem.getInstance().find(id));
    }

    public Product getProduct(int id) {
        return  data.getCart().get(id);
    }

    public List<Product> getAll() {
        return data.getCart();
    }

    public float totalPrice() {
        return data.sumPrice();
    }

    public void resetCart() {
        data.resetCart();
    }

}
