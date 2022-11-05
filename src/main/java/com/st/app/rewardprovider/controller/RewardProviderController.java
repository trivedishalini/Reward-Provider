package com.st.app.rewardprovider.controller;

import javax.validation.Valid;

import com.st.app.rewardprovider.entity.Customer;
import com.st.app.rewardprovider.exception.ResourceNotFoundException;
import com.st.app.rewardprovider.service.IRewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Slf4j
@RestController
@RequestMapping("/api/v1")
public class RewardProviderController {

    private IRewardService rewardService;

    @Autowired
    public RewardProviderController(IRewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/rewards/{id}")
    public ResponseEntity<Customer> getRewardPointById(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customer customer = rewardService.getRewardPointById(customerId);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomer() {
        return rewardService.getAllCustomer();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer customer = rewardService.getCustomerById(customerId);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customer")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return rewardService.createCustomer(customer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                   @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        final Customer updatedCustomer = rewardService.updateCustomer(customerId, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        rewardService.deleteCustomer(customerId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
