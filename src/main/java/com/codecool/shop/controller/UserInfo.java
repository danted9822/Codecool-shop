package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Payment;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Payment;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/userinfo"})
public class UserInfo extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ASD");
        resp.setCharacterEncoding("UTF-8");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        System.out.println("ASD");

        Payment payment;
        String paymentType = req.getParameter("payment-type");
        if (paymentType.equals("credit-card")){
            payment = new Payment(req.getParameter("cardnumber1"), req.getParameter("cardnumber2"),
                    req.getParameter("cardnumber3"),req.getParameter("holder"),req.getParameter("expiry"),
                    req.getParameter("year"),req.getParameter("code"));
            System.out.println(paymentType);
            System.out.println(payment);
        }else{
            payment = new Payment(req.getParameter("username"),
                    req.getParameter("password"));
        }

        System.out.println("WWWW");

        resp.sendRedirect("/");

    }
}
