package com.br.edu.ifba.email_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient // Permite que este serviço seja registrado no Eureka
@SpringBootApplication
public class EmailMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailMsApplication.class, args);
	}

}
