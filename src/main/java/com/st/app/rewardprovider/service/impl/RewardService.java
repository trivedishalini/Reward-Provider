package com.st.app.rewardprovider.service.impl;

import com.st.app.rewardprovider.entity.Customer;
import com.st.app.rewardprovider.entity.OrderDetail;
import com.st.app.rewardprovider.exception.ResourceNotFoundException;
import com.st.app.rewardprovider.repository.CustomerRepository;
import com.st.app.rewardprovider.repository.OrderDetailRepository;
import com.st.app.rewardprovider.service.IRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static com.st.app.rewardprovider.util.Constants.*;

@Service
public class RewardService implements IRewardService {

    private CustomerRepository customerRepository;

    private OrderDetailRepository orderDetailRepository;

    /**
     * Constructs a new RewardService instance
     *
     * @param customerRepository
     */
    @Autowired
    public RewardService(CustomerRepository customerRepository, OrderDetailRepository orderDetailRepository) {
        this.customerRepository = customerRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    /**
     * calculate rewards point for customerId
     *
     * @param customerId
     * @param fromMonth
     * @return
     */
    @Override
    public Collection<OrderDetail> getRewardPointById(Long customerId, int fromMonth) throws ResourceNotFoundException {
        Date currentDate = new Date();
        int currentMonth = currentDate.getMonth(); //TODO : replace use of getMonth()
        int rewardStartMonth = (currentMonth - fromMonth) + 1; //+1 as current month included

        Collection<OrderDetail> orderDetails = orderDetailRepository.findTotalPurchaseForMonth(customerId, rewardStartMonth);
        long totalPoints = 0;
        long totalPurchase = 0;
        totalPurchase = totalPurchase + orderDetails.stream().mapToInt(od -> od.getTotalPurchase().intValue()).sum();
        long amountRemain = totalPurchase - REWARD_PURCHASE_CUTOFF_SECOND;
        totalPoints = totalPoints + ((amountRemain >= 0 && totalPurchase > REWARD_PURCHASE_CUTOFF_FIRST) ? (REWARD_POINT_FIRST * REWARD_PURCHASE_CUTOFF_FIRST) : 0);
        totalPoints = totalPoints + ((amountRemain > 0) ? (REWARD_POINT_SECOND * amountRemain) : 0);
        System.out.println("totalPoints=" + totalPoints);
        return orderDetails;
    }

    /**
     * Create order details for customer
     *
     * @param customerId
     * @param orderDetail
     * @return
     */
    @Override
    public OrderDetail createOrderDetail(long customerId, OrderDetail orderDetail) throws ResourceNotFoundException {
        Customer customer = findCustomerById(customerId);
        orderDetail.setOrderStatus("COMPLETED");
        orderDetail.setCustomer(customer);
        Date created = orderDetail.getCreated() != null ? orderDetail.getCreated() : new Date(); // TODO: to insert data on back date. we can remove this when full system in place
        orderDetail.setCreated(created);
        final OrderDetail createdOrderDetail = orderDetailRepository.save(orderDetail);
        return createdOrderDetail;
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
