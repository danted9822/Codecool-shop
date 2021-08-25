package com.codecool.shop.controller;

import com.codecool.shop.Logger;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderType;
import com.codecool.shop.email.SendEmail;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/confirmation"},name = "confirmation")
public class ConfirmationController extends HttpServlet {
    OrderDao orderDataStore = OrderDaoMem.getInstance();
    OrderService orderService = new OrderService(orderDataStore);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = UserDaoMem.getInstance();
        UserService userService = new UserService(userDao);
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
        for (String kvPair : keysValues) {
            dict.put(kvPair.split(":")[0], kvPair.split(":")[1]);
        }
        String paymentSource = dict.containsKey("card") ? dict.get("card") : dict.get("username");

        System.out.println("Ez a dict: " + dict);
        saveJSONFile(dict);
        User user = getUser(dict);
        userDao.add(user);
        System.out.println("Ez a user: " + user.toString());

        // sending email
        SendEmail sm = new SendEmail();
        String email = sm.emailText(user, orderService.getOrder(1));
        System.out.println(user.getEmail()+ "LEGYÉL JÓ TE PÖCS");
        sm.sendMail(user.getEmail(), email);

        resp.setStatus(200);
    }

    private void saveJSONFile(HashMap<String, String> dict) throws IOException {

        Order order = orderService.getOrder(1);
        JSONObject transaction = new JSONObject();
        try {
            transaction.put("First name", dict.get("first_name"));
            transaction.put("Last name", dict.get("last_name"));
            transaction.put("Email address", dict.get("email_address"));
            transaction.put("Phone number", dict.get("phone_number"));
            transaction.put("State", dict.get("state"));
            transaction.put("City", dict.get("city"));
            transaction.put("Address", dict.get("address"));
            transaction.put("Post code", dict.get("postcode"));
            transaction.put("Card number", dict.get("card"));
            transaction.put("Username", dict.get("username"));
            transaction.put("Password",dict.get("password"));
            transaction.put("Cart",order.getCart());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray transaxion = new JSONArray();
        transaxion.add(transaction);
        System.out.println(transaxion);


        order.setOrderType(OrderType.CHECKED);
        Logger.saveAdminLog(order,transaxion);
    }

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



    private User getUser(HashMap<String, String> dict) throws IOException {
        return new User(dict.get("username"),dict.get("password"),dict.get("email_address"),dict.get("first_name"), dict.get("last_name"),dict.get("phone_number"),
                dict.get("billingaddress"), dict.get("shippingAddress"));
    }

}
