package com.grabACycle.grabACycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GrabACycleApplication {


	public static void main(String[] args) {

		SpringApplication.run(GrabACycleApplication.class, args);
	}

}
