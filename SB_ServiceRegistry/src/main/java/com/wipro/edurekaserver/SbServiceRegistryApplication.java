package com.wipro.edurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SbServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServiceRegistryApplication.class, args);
	}

}
