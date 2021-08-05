package com.codecool.shop.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public void sendMail(String sendTo) {
        final String username = "username@gmail.com";
        final String password = "password";

        Properties properties = new Properties();
//        properties.setProperty("mail.debug", "true");
//        properties.put("mail.from", "myemail@gmail.com");
//        properties.put("mail.smtp.starttls.enable", "true"); //TLS
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(
                properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, sendTo);
            message.setSubject("CodecoolShopDDLN test mail");
//            message.setSentDate(new Date());
            message.setText("CodecoolShopDDLN test mail text");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException messagingException) {
            System.out.println("Send failed, exception: " + messagingException);
        }


//***********************************************************************************************************
//
//        final String username = "username@gmail.com";
//        final String password = "password";
//
//        Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true"); //TLS
//
//        Session session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("from@gmail.com"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse("to_username_a@gmail.com, to_username_b@yahoo.com")
//            );
//            message.setSubject("Testing Gmail TLS");
//            message.setText("Dear Mail Crawler,"
//                    + "\n\n Please do not spam my email!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//***********************************************************************************************************
//
//        Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.mailtrap.io");
//        prop.put("mail.smtp.port", "25");
//        prop.put("mail.smtp.auth", true);
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
//
//        Session session = Session.getInstance(prop, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("from@gmail.com"));
//        message.setRecipients(
//                Message.RecipientType.TO, InternetAddress.parse("to@gmail.com"));
//        message.setSubject("Mail Subject");
//
//        String msg = "This is my first email using JavaMailer";
//
//        MimeBodyPart mimeBodyPart = new MimeBodyPart();
//        mimeBodyPart.setContent(msg, "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(mimeBodyPart);
//
//        message.setContent(multipart);
//
//        Transport.send(message);
    }
}
