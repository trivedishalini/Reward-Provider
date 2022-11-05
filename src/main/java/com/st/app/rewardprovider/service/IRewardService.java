package com.st.app.rewardprovider.service;

import com.st.app.rewardprovider.entity.Customer;
import com.st.app.rewardprovider.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Interface for Reward Services
 */
public interface IRewardService {

    /**
     * calculate rewards point for customerId
     *
     * @param customerId
     * @return
     */
    Customer getRewardPointById(Long customerId) throws ResourceNotFoundException;

    /**
     * @return
     */
    List<Customer> getAllCustomer();

    /**
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    Customer getCustomerById(Long customerId) throws ResourceNotFoundException;

    /**
     * @param customer
     * @return
     */
    Customer createCustomer(Customer customer);

    /**
     * @param customerId
     * @param customerDetails
     * @return
     * @throws ResourceNotFoundException
     */
    Customer updateCustomer(Long customerId, Customer customerDetails) throws ResourceNotFoundException;

    /**
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    Map<String, Boolean> deleteCustomer(Long customerId) throws ResourceNotFoundException;
}
