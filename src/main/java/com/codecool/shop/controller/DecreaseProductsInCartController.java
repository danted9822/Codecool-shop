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

@WebServlet(urlPatterns = {"/decrease-product"})
public class DecreaseProductsInCartController extends HttpServlet{

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            ProductDao productDataStore = ProductDaoMem.getInstance();

            OrderDao orderDataStore = OrderDaoMem.getInstance();
            OrderService orderService = new OrderService(orderDataStore);

            String decreaseProduct = req.getParameter("decrease-cart");

            if (decreaseProduct != null) {
                try {
                    orderService.decreaseProductFromCart(1, productDataStore.find(Integer.parseInt(decreaseProduct)));
                } catch (AttributeNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            resp.sendRedirect("/cart");
        }

    }
