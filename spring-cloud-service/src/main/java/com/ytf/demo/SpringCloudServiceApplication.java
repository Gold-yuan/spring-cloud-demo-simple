package com.ytf.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringCloudApplication
@EnableEurekaClient
public class SpringCloudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceApplication.class, args);
	}

}
