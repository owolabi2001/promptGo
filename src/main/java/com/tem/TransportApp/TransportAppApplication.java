package com.tem.TransportApp;

import com.tem.TransportApp.dto.PaymentAuthDto;
import com.tem.TransportApp.service.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransportAppApplication {
	static PaymentService paymentService = new PaymentService();

	public static void main(String[] args) {
		SpringApplication.run(TransportAppApplication.class, args);

		System.out.println("THIS IS THE GET TOKEN OUTPUT");
		PaymentAuthDto pay = paymentService.getAccessToken().block();
		System.out.println(pay);
	}


}
