package com.negocio.msvcnegocio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableFeignClients
@SpringBootApplication
public class MsvcNegocioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcNegocioApplication.class, args);
	}



}
