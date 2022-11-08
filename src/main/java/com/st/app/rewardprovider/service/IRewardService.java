package com.st.app.rewardprovider.service;

import com.st.app.rewardprovider.entity.Customer;
import com.st.app.rewardprovider.entity.OrderDetail;
import com.st.app.rewardprovider.exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Interface for Reward Services operations
 */
public interface IRewardService {

    /**
     * calculate rewards point for customerId
     *
     * @param customerId
     * @return
     */
    Collection<OrderDetail> getRewardPointById(Long customerId, int fromMonth) throws ResourceNotFoundException;

    /**
     * Create order detail for customer
     *
     * @param customerId
     * @param orderDetail
     * @return
     */
    OrderDetail createOrderDetail(long customerId,OrderDetail orderDetail) throws ResourceNotFoundException;

    /**
     * Get all customer
     *
     * @return
     */
    List<Customer> getAllCustomer();

    /**
     * Get customer by customerId
     *
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    Customer getCustomerById(Long customerId) throws ResourceNotFoundException;

    /**
     * Create customer details
     *
     * @param customer
     * @return
     */
    Customer createCustomer(Customer customer);

    /**
     * Update Customer Details for customerId
     *
     * @param customerId
     * @param customerDetails
     * @return
     * @throws ResourceNotFoundException
     */
    Customer updateCustomer(Long customerId, Customer customerDetails) throws ResourceNotFoundException;

    /**
     * Delete customer details for customerId
     *
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    Map<String, Boolean> deleteCustomer(Long customerId) throws ResourceNotFoundException;
}
