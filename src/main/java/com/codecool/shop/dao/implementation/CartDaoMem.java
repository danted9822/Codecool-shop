package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<Cart> data = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Cart cart) {
        cart.setId(data.size() + 1);
        data.add(cart);
    }

    @Override
    public Cart find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Cart> getAll() {
        return data;
    }



//    public void addProductToCart(int id) {
//        data.addToCart(ProductDaoMem.getInstance().find(id));
//    }
//
//    public void removeProductFromCart(int id) throws AttributeNotFoundException {
//        data.removeFromCart(ProductDaoMem.getInstance().find(id));
//    }
//
//    public Product getProduct(int id) {
//        return  data.getCart().get(id);
//    }
//
//    public List<Product> getAll() {
//        return data.getCart();
//    }
//
//    public float totalPrice() {
//        return data.sumPrice();
//    }
//
//    public void resetCart() {
//        data.resetCart();
//    }

}
