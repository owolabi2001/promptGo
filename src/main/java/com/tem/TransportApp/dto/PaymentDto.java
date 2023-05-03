package com.tem.TransportApp.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaymentDto {

    private String name;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;

}
