package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class CartService {

    CartDao cartDao;
    ProductDao productDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public Cart getCart(int id) {
        return cartDao.find(id);
    }

    public List<Cart> getAll() {
        return cartDao.getAll();
    }

    public void add() {
        cartDao.add(new Cart());
    }

    public List<Product> getProductFromCart(int id){
        Cart cart = cartDao.find(id);
        return cart.getCart();
    }

    public void addProductToCart(int cartId, Product product){
        Cart cart = cartDao.find(cartId);
        cart.addToCart(product);
    }

    public void removeProductFromCart(int cartId, Product product) throws AttributeNotFoundException {
        Cart cart = cartDao.find(cartId);
        cart.removeFromCart(product);
    }

    public void resetCart(int cartId){
        Cart cart = cartDao.find(cartId);
        cart.resetCart();
    }

    public int getCartSize(int cartId) {
        Cart cart = cartDao.find(cartId);
        return cart.getSize();
    }

    public float getCartPrice(int cartId) {
        Cart cart = cartDao.find(cartId);
        return cart.sumPrice();
    }


}
