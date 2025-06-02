package br.com.AlertHaven.AlertHaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class  AlertHavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertHavenApplication.class, args);
	}

}
