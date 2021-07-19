package br.com.tech4me.erureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ErurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErurekaApplication.class, args);
	}

}
