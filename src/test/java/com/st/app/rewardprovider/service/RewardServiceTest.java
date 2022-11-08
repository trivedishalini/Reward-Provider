package com.st.app.rewardprovider.service;

import com.st.app.rewardprovider.entity.OrderDetail;
import com.st.app.rewardprovider.exception.ResourceNotFoundException;
import com.st.app.rewardprovider.service.impl.RewardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest
public class RewardServiceTest {
    @Autowired
    private RewardService rewardService;

    @Test
    @DisplayName("fetch reward point when customer not exist ")
    void testGetRewardPointById() throws ResourceNotFoundException {
//TODO: mock the DB layer
        long customer_id = 1;

        //Collection<OrderDetail> actual= rewardService.getRewardPointById(customer_id,2);
        // TODO assertEquals();
    }
}
