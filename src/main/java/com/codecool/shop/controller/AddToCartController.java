package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add-to-cart"})
public class AddToCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        req.getHeader("referer");
        ProductDao productDataStore = ProductDaoMem.getInstance();

        OrderDao cartDataStore = OrderDaoMem.getInstance();
        OrderService orderService = new OrderService(cartDataStore);

        String addCart = req.getParameter("add-cart");

//        System.out.println(orderService.getAll().size());
        if (orderService.getAll().size() == 0) orderService.add();

//        System.out.println(orderService.getAll().size());
        if (addCart != null)
            orderService.addProductToCart(1, productDataStore.find(Integer.parseInt(addCart)));

//        System.out.println(orderService.getOrder(1).getCart());

        resp.sendRedirect(req.getHeader("referer"));
    }


}


