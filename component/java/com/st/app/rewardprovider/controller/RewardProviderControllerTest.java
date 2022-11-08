package com.st.app.rewardprovider.controller;

import com.st.app.rewardprovider.entity.OrderDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardProviderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("/reward rest api test ")
    void testRewardPoint() {
//TODO: mock the DB layer
        long customerId = 1;
        URI targetUrl = UriComponentsBuilder.fromUriString("/api/v1/rewards/" + customerId)
                .queryParam("fromMonth", 3)
                .build()
                .encode()
                .toUri();

        ResponseEntity<Collection<OrderDetail>> actual = this.restTemplate.getForObject(targetUrl, ResponseEntity.class);
        assertEquals(actual.getStatusCode().is2xxSuccessful(), HttpStatus.OK.is2xxSuccessful());
    }
}
