package com.grabACycle.grabACycle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GrabACycleApplication {
	private static final Logger logger = LogManager.getLogger(GrabACycleApplication.class);


	public static void main(String[] args) {
		logger.info("This is a info log message");
		logger.debug("This is a debug message");
		logger.warn("This is Warning message");
		SpringApplication.run(GrabACycleApplication.class, args);
	}

}
