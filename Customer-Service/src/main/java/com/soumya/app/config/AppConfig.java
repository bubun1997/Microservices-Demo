package com.soumya.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
	
	@Value("${products.path}")
	private String PRODUCT_SERVICE_BASE_URL;
	
	@Bean
	public RestClient restClient() {
				
		return RestClient.
			   builder().
			   baseUrl(PRODUCT_SERVICE_BASE_URL).
			   build();
	}

}
