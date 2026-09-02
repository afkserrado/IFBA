package br.edu.ifba.emprestimos_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class EmprestimosMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmprestimosMsApplication.class, args);
	}

}
