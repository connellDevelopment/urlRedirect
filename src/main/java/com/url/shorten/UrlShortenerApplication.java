package com.url.shorten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Spring boot application to facilitate :
 *
 * 1) Implement an API for short URL creation
 * 2) Implement forwarding of short URLs to the original ones
 * 3) There should be some form of persistent storage
 * 4) The application should be distributed as one or more Docker images
 **
 */
@SpringBootApplication
@EnableTransactionManagement
public class UrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}
}
