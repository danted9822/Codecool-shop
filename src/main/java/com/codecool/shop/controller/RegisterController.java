package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns={"/registration"})
public class RegisterController extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/registration.html", context, resp.getWriter());
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String firstname = req.getParameter("first");
        String lastname = req.getParameter("last");
        String phonenumber = req.getParameter("phone");
        String state = req.getParameter("state");
        String city = req.getParameter("city");
        String address = req.getParameter("address");
        String postcode = req.getParameter("postcode");

        System.out.println(username);
        System.out.println(username+" " + password+ " " + email + " " + firstname
                + " " + lastname + " " + phonenumber + " " +state +" " +city +" " +address+ " "+postcode);

        resp.sendRedirect("/");
    }





}
