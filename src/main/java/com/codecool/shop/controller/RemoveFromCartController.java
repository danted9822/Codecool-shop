package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.OrderService;

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

        OrderDao cartDataStore = OrderDaoMem.getInstance();
        OrderService orderService = new OrderService(cartDataStore);

        String removeProduct = req.getParameter("product-id");

        if (removeProduct != null) {
            try {
                orderService.removeProductFromCart(1, productDataStore.find(Integer.parseInt(removeProduct)));
            } catch (AttributeNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        resp.sendRedirect("/cart");
    }

}


