package com.st.app.rewardprovider.service.impl;

import com.st.app.rewardprovider.entity.Customer;
import com.st.app.rewardprovider.exception.ResourceNotFoundException;
import com.st.app.rewardprovider.repository.CustomerRepository;
import com.st.app.rewardprovider.service.IRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardService implements IRewardService {

    private CustomerRepository customerRepository;

    /**
     *
     * Constructs a new RewardService instance
     *
     * @param customerRepository
     */
    @Autowired
    public RewardService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * calculate rewards point for customerId
     *
     * @param customerId
     * @return
     */
    @Override
    public Customer getRewardPointById(Long customerId) throws ResourceNotFoundException {
        Customer customer = findCustomerById(customerId);
        return customer;
    }

    /**
     * Get all customer
     *
     * @return
     */
    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    /**
     * Get customer by customerId
     *
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public Customer getCustomerById(Long customerId) throws ResourceNotFoundException {
        Customer customer = findCustomerById(customerId);
        return customer;
    }

    /**
     * Create customer details
     *
     * @param customer
     * @return
     */
    @Override
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Update Customer Details for customerId
     *
     * @param customerId
     * @param customerDetails
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public Customer updateCustomer(Long customerId, Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = findCustomerById(customerId);

        customer.setEmailId(customerDetails.getEmailId());
        customer.setLastName(customerDetails.getLastName());
        customer.setFirstName(customerDetails.getFirstName());
        final Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }

    /**
     * Delete customer details for customerId
     *
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public Map<String, Boolean> deleteCustomer(Long customerId) throws ResourceNotFoundException {
        Customer customer = findCustomerById(customerId);

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    private Customer findCustomerById(Long customerId) throws ResourceNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
    }
}
