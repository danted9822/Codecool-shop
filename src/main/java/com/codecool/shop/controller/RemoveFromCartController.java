package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.CartService;

import javax.management.AttributeNotFoundException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/remove-from-cart"})
public class RemoveFromCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();

        CartDao cartDataStore = CartDaoMem.getInstance();
        CartService cartService = new CartService(cartDataStore);

        String removeCart = req.getParameter("remove-cart");

        if (cartService.getAll().size() == 0) cartService.add();

        if (removeCart != null) {
            try {
                cartService.removeProductFromCart(1, productDataStore.find(Integer.parseInt(removeCart)));
            } catch (AttributeNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        resp.sendRedirect("/cart");
    }

}


