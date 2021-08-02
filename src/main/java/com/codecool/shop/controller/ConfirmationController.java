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

@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDao cartDataStore = OrderDaoMem.getInstance();
        OrderService orderService = new OrderService(cartDataStore);

        // total price
        float totalPrice = 0;
        if (orderService.getAll().size() != 0) totalPrice = orderService.getCartPrice(1);
        // counter of the products in the cart
        int cartCounter = 0;
        if (cartDataStore.getAll().size() != 0) cartCounter = orderService.getCartSize(1);


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        if (cartDataStore.getAll() != null) {
            context.setVariable("products",  orderService.getProductFromCart(1));
            context.setVariable("total", totalPrice);
            orderService.getOrder(1).resetCart();
            cartCounter = 0;
            context.setVariable("cartCounter", cartCounter);
        }

        engine.process("product/order.html", context, resp.getWriter());
    }

}
