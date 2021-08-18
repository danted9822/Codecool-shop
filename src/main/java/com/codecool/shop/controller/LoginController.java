package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.UserDaoDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns={"/login"})
public class LoginController extends HttpServlet {
    UserDaoDatabase userDaoDatabase = new UserDaoDatabase();

    public LoginController() throws SQLException {
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String password = req.getParameter("password");

        System.out.println(username.length());

        if(username != null && password !=null) {
            if(userDaoDatabase.find_by_name(username) !=null){

                HttpSession session = req.getSession();
                session.setAttribute("name", username);
                }
        }

        resp.sendRedirect(req.getHeader("referer"));
    }
}