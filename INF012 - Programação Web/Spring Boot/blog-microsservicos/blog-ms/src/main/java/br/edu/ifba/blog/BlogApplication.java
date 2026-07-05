package br.edu.ifba.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableDiscoveryClient // Permite que este serviço seja registrado no Eureka
@EnableSpringDataWebSupport // Para criar o Pageble automaticamente
@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}