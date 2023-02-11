package com.example.productorderservice.payment;

interface PaymentGateWay {


    void execute(int totalPrice, String cardNumber);
}
