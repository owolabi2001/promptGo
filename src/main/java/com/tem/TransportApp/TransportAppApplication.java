package com.tem.TransportApp;

import com.tem.TransportApp.dto.PaymentAuthDto;
import com.tem.TransportApp.service.PaymentService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
@SecurityScheme(type = SecuritySchemeType.APIKEY)
public class TransportAppApplication {

	public String PORT = System.getenv("PORT");
	static PaymentService paymentService = new PaymentService();


	public static void main(String[] args) {
		SpringApplication.run(TransportAppApplication.class, args);

		System.out.println("THIS IS THE GET TOKEN OUTPUT");
		PaymentAuthDto pay = paymentService.getAccessToken().block();
		System.out.println(pay);
	}


}
