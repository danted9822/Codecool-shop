package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.service.OrderService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDao cartDataStore = OrderDaoMem.getInstance();
        OrderService orderService = new OrderService(cartDataStore);
        int cartCounter = 0;
        float totalPrice = 0;


        if (orderService.getCartSize(1)==0) {
            System.out.println("bazd meg ");
            resp.sendRedirect("http://localhost:8888/");
        }


        if (orderService.getAll().size() != 0) totalPrice = orderService.getCartPrice(1);

        if (cartDataStore.getAll().size() != 0) cartCounter = orderService.getCartSize(1);



        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (cartDataStore.getAll() != null) {
            context.setVariable("products", orderService.getProductFromCart(1));
            context.setVariable("total", totalPrice);
            context.setVariable("cartCounter", cartCounter);
        }

        engine.process("product/checkout.html", context, resp.getWriter());



    }

    }


