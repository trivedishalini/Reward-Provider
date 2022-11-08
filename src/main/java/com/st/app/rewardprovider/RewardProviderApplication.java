package com.st.app.rewardprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The Class RewardProviderApplication
 */
@SpringBootApplication
public class RewardProviderApplication {

    /**
     * restTemplate
     *
     * @param builder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * The main method to run spring boot application.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RewardProviderApplication.class, args);
    }
}
