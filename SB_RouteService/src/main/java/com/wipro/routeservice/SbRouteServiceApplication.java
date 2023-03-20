package com.wipro.routeservice;

import java.net.http.HttpClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableDiscoveryClient
public class SbRouteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbRouteServiceApplication.class, args);
	}
	
}
