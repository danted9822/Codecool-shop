package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;


@WebServlet(urlPatterns = {"/userinfo"})
public class UserController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao customerDao = UserDaoMem.getInstance();
        StringBuilder jsonString = new StringBuilder();
        try {
            String line = "";
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jsonString.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, String> dict = new HashMap<>();
        String[] keysValues =
                jsonString.toString().replace("\"", "")
                        .replace("{", "")
                        .replace("}", "")
                        .split(",");
        for (String kvPair : keysValues)
            dict.put(kvPair.split(":")[0], kvPair.split(":")[1]);

        String paymentSource = dict.containsKey("card") ? dict.get("card") : dict.get("username");

//        saveJSONFile(dict);
//        User user = getUser(dict);
//        customerDao.add(user);
//        try {
//            sendMail(resp, customer);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

        // roleplay

        resp.sendRedirect("/");




//        System.out.println("ASD");
//        resp.setCharacterEncoding("UTF-8");
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//
//        System.out.println("ASD");
//
//        Payment payment;
//        String paymentType = req.getParameter("payment-type");
//        if (paymentType.equals("credit-card")){
//            payment = new Payment(req.getParameter("cardnumber1"), req.getParameter("cardnumber2"),
//                    req.getParameter("cardnumber3"),req.getParameter("holder"),req.getParameter("expiry"),
//                    req.getParameter("year"),req.getParameter("code"));
//            System.out.println(paymentType);
//            System.out.println(payment);
//        }else{
//            payment = new Payment(req.getParameter("username"),
//                    req.getParameter("password"));
//        }
//
//        System.out.println("WWWW");
//
//        resp.sendRedirect("/");

    }

}
