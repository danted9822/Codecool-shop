package com.codecool.shop.model;

public class Payment {

    private String cardNumber1;
    private String cardNumber2;
    private String cardNumber3;
    private String cardHolder;
    private String expiryMonth;
    private String expiryYear;
    private String code;


    private String password;
    private String username;


    private PaymentType paymentType;

    public Payment(String cardNumber1,String cardNumber2, String cardNumber3, String cardHolder,
                   String expiryMonth, String expiryYear, String code) {
        this.cardNumber1 = cardNumber1;
        this.cardNumber2 = cardNumber2;
        this.cardNumber3 = cardNumber3;
        this.cardHolder = cardHolder;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.code = code;
        paymentType = PaymentType.CREDIT_CARD;
    }

    public Payment(String username, String password) {
        this.username = username;
        this.password = password;
        paymentType = PaymentType.PAYPAL;
    }

    enum PaymentType {
        CREDIT_CARD,
        PAYPAL;
    }
}
