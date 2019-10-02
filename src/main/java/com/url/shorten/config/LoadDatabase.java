/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.config;

import com.url.shorten.model.UrlModel;
import com.url.shorten.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Load temporary in memory database for purpose of illustration.
 */
@Configuration
@Slf4j
class LoadDatabase {

	private final UrlRepository urlRepository;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoadDatabase.class);

	public LoadDatabase(UrlRepository urlRepository ) {
		this.urlRepository  = urlRepository ;
	}

	@Bean
	CommandLineRunner initDatabase(UrlRepository repository) {
		return args -> {
			LOGGER.info("added : " + urlRepository.save(new UrlModel("https://aws.amazon.com/getting-started/projects/build-serverless-web-app-lambda-apigateway-s3-dynamodb-cognito/", "http://this.co.1")));
			LOGGER.info("added : " + urlRepository.save(new UrlModel("https://stackoverflow.com/questions/35217542/error-injectorunpr-unknown-provider-cordovafileprovider-cordovafile", "http://this.co.2")));
			LOGGER.info("added : " + urlRepository.save(new UrlModel("https://www.google.com/maps/place/Father+Ted's+House/@52.9530401,-8.8996783,10.81z/data=!4m5!3m4!1s0x485b752eb6ef7d31:0x214ac79e9db0c7fd!8m2!3d53.0104839!4d-9.0304972", "http://this.co.3")));
			LOGGER.info("URLS added to repository :" + urlRepository.count());
		};
	}
}