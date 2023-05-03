package com.tem.TransportApp.service;


import com.tem.TransportApp.dto.PaymentAuthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Service
@Slf4j
public class PaymentService {

    WebClient client = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build();

    String clientId = "IKIAB6AFE9F94296480CD3B5A488C870A367C1FCBD00"; // store with environment variables
    String secretKey = "kTBmZaPhlKdC3rr"; // Store with environment variables
    String combined = clientId + ":" + secretKey;
    String base64 =  Base64.getEncoder().encodeToString(combined.getBytes());

    public Mono<PaymentAuthDto> getAccessToken(){
        log.info("getToken API");
        System.out.println("API TO GET TOKEN");
        System.out.println(base64);
        return client.post()
                .uri("https://apps.qa.interswitchng.com/passport/oauth/token")
                .header("Authorization","Basic "+base64)
                .body(BodyInserters.fromFormData("grant_type","client_credentials"))
                .retrieve()
                .bodyToMono(PaymentAuthDto.class);

    }

}
