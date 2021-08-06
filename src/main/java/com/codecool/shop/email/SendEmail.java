package com.codecool.shop.email;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

public class SendEmail {

    public void sendMail(String sendTo, String email) {
        final String username = "deadline.codecool@gmail.com";
        final String password = "ddln1234";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, sendTo);
            message.setSubject("CodecoolShopDDLN test mail");
//            message.setContent(email, "text/html");
            message.setText(email);
//            message.setText("CodecoolShopDDLN test mail text");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Done");
        } catch (MessagingException messagingException) {
            System.out.println("Send failed, exception: " + messagingException);
        }
    }

    public String emailText(User user, Order order){
        String name = user.getFirstName();
        String items = "";

        for (Map.Entry<Product, Integer> product : order.getCart().entrySet()) {
            items += "\t" + product.getKey().getName() + "\t\t\t" + product.getValue() + " Qty.\n";
        }

        return  "CodecoolShopDDLN test mail\n" +
                "\n" +
                "\n" +
                "Codecool Shop\n" +
                "\n" +
                "ORDER CONFIRMED\n" +
                "\n" +
                "Thanks for your purchase, " + name + "! Your order is confirmed.\n" +
                "\n" +
                "We’ll let you know when it’s on the way.\n" +
                "\n" +
                "Order details\n" +
                "\n" +
                "Item(s): \n" + items +
                "\n" +
                "Total: "+ order.sumPrice() +"\n" +
                "Order number: "+order.getId() +"\n" +
                "Estimated delivery: Within 20 working days.\n";


    }

//    public String emailConvertToHTML(User user) {
//        String name = user.getFirstName();
//        return  """
//<!DOCTYPE html>
//<html lang="en">
//<head>
//    <meta charset="UTF-8">
//    <meta http-equiv="X-UA-Compatible" content="IE=edge">
//    <meta name="viewport" content="width=device-width, initial-scale=1.0">
//    <title>CODECOOLSHOP ORDER CONFIRMED</title>
//
//    <!-- Bootstrap components -->
//    <!-- Latest compiled and minified Jquery -->
//    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
//            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
//
//    <!--Latest compiled and minified Popper -->
//    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
//            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
//
//    <!-- Latest compiled and minified JavaScript -->
//    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
//            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
//
//    <!-- Latest compiled and minified CSS -->
//    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"
//          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
//    <!-- End of Bootstrap components -->
//
//
//</head>
//<body>
//    <style>
//.asd {
//    text-align: center!important;
//    padding: 4rem 2rem;
//    margin-bottom: 2rem;
//    background-color: #e9ecef;
//    border-radius: .3rem;
//    box-sizing: border-box;
//    display: block;
//    margin: 0;
//    font-family: -apple-system,BlinkMacSystemFont;
//    font-size: 1rem;
//    font-weight: 400;
//    line-height: 1.5;
//    color: #212529;
//    text-align: left;
//    background-color: #fff;
//}
//    </style>
//    <div class=asd>
//
//        <h1>Codecool Shop</h1>
//    </div>
//    <h2 style="color: darkblue;">ORDER CONFIRMED</h2>
//    <h3 style="
//    margin: auto;
//    width: 50%;
//    border: 3px solid darkblue;
//    border-radius: 25px 25px;
//    padding: 20px;
//    background: #2186f338;
//">Thanks for your purchase, " + username + "! Your order is confirmed.</h3>
//<p style="
//font-size: 23px;
//color: darkblue;
//">We’ll let you know when it’s on the way. In the meantime, we have more things you’ll love at unbeatable prices!</p>
//<a href="0.0.0.0:8888" style="
//margin: auto;
//width: 50%;
//border: 3px solid;
//padding: 10px;
//border-radius: 15px 15px;
//color: #fff;
//text-decoration: none;
//background-color: #28a745;
//border-color: #28a745;
//">Check out our website</a>
//        <p>Order details</p>
//        <ul>
//            <li>Total:</li>
//            <li>Order number:</li>
//            <li>Item ID:</li>
//            <li>Estimated delivery: Within 20 working days.</li>
//            <li>Shipping order:</li>
//        </ul>
//
//
//
//</body>
//</html>
//""";
//    }
}
