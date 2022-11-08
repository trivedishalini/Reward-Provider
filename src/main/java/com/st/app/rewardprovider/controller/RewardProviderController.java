package com.st.app.rewardprovider.controller;

import javax.validation.Valid;

import com.st.app.rewardprovider.entity.Customer;
import com.st.app.rewardprovider.entity.OrderDetail;
import com.st.app.rewardprovider.exception.ResourceNotFoundException;
import com.st.app.rewardprovider.service.IRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest Controller
 */
@RestController
@RequestMapping("/api/v1")
public class RewardProviderController {

    private IRewardService rewardService;

    /**
     * Constructs a new RewardProviderController instance
     *
     * @param rewardService
     */
    @Autowired
    public RewardProviderController(IRewardService rewardService) {
        this.rewardService = rewardService;
    }

    /**
     * Get the reward points for customerId
     *
     * @param customerId
     * @param fromMonth
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/rewards/{id}")
    public ResponseEntity<Collection<OrderDetail>> getRewardPointById(@PathVariable(value = "id") long customerId,@RequestParam int fromMonth) throws ResourceNotFoundException {
        Collection<OrderDetail> orderDetails = rewardService.getRewardPointById(customerId,fromMonth);
        return ResponseEntity.ok().body(orderDetails);
    }

    /**
     * create order from OrderDetail for customer
     *
     * @param orderDetail
     * @return
     */
    @PostMapping("/customer/{customerId}/order")
    public OrderDetail createOrderDetails(@PathVariable long customerId ,@RequestBody OrderDetail orderDetail) throws ResourceNotFoundException {
        return rewardService.createOrderDetail(customerId,orderDetail);
    }

    /**
     * Fetch All customers
     *
     * @return
     */
    @GetMapping("/customer")
    public List<Customer> getAllCustomer() {
        return rewardService.getAllCustomer();
    }

    /**
     * Get customer by customerId
     *
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer customer = rewardService.getCustomerById(customerId);
        return ResponseEntity.ok().body(customer);
    }

    /**
     * create customer from customerDetails
     *
     * @param customer
     * @return
     */
    @PostMapping("/customer")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return rewardService.createCustomer(customer);
    }

    /**
     * Update customer by customerId
     *
     * @param customerId
     * @param customerDetails
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                   @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        final Customer updatedCustomer = rewardService.updateCustomer(customerId, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

    /**
     * Delete customer by customerId
     *
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/customer/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        rewardService.deleteCustomer(customerId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
