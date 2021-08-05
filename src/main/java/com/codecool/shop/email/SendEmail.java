package com.codecool.shop.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
}
