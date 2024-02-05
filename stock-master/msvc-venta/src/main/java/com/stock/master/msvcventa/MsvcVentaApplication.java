package com.stock.master.msvcventa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcVentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcVentaApplication.class, args);
	}

}
