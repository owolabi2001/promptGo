package com.tem.TransportApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentAuthRequestDto {


    private String grant_type;


}
