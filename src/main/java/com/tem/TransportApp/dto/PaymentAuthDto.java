package com.tem.TransportApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentAuthDto {

    private String access_token;
    private String token_type;
    private int expires_in;
    private String scope;
    private String merchant_code;
    private String client_name;
    private String payable_id;
    private String jti;
}
