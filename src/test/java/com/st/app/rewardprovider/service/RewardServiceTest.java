package com.st.app.rewardprovider.service;

import com.st.app.rewardprovider.dto.RewardResponseDTO;
import com.st.app.rewardprovider.entity.Customer;
import com.st.app.rewardprovider.entity.OrderDetail;
import com.st.app.rewardprovider.exception.ResourceNotFoundException;
import com.st.app.rewardprovider.repository.CustomerRepository;
import com.st.app.rewardprovider.repository.OrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RewardServiceTest {
    @Autowired
    private IRewardService rewardService;

    @MockBean
    private OrderDetailRepository orderDetailRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void testRewardPointForSuccess() throws ResourceNotFoundException {
        long customerId = 1;
        int rewardStartMonth = 3;
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        getOrderDetails(new BigDecimal(50), new Date(), orderDetails);
        Date date1 = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(50), date1, orderDetails);
        Date date2 = Date.from(LocalDate.now().minusMonths(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(20), date2, orderDetails);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(customerId, "test", "test", "test@gmail.com")));
        when(orderDetailRepository.findTotalPurchaseForMonth(anyLong(), anyInt())).thenReturn(orderDetails);
        int expected = 90;
        RewardResponseDTO actual = rewardService.getRewardPointById(customerId, rewardStartMonth);
        assertEquals(expected, actual.getTotalPoint());
    }

    @Test
    public void testRewardPointForOKStatusWhenPurchaseLessThan50InLast3Month() throws ResourceNotFoundException {
        long customerId = 1;
        int rewardStartMonth = 3;
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        getOrderDetails(new BigDecimal(10), new Date(), orderDetails);
        Date date1 = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(10), date1, orderDetails);
        Date date2 = Date.from(LocalDate.now().minusMonths(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(20), date2, orderDetails);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(customerId, "test", "test", "test@gmail.com")));
        when(orderDetailRepository.findTotalPurchaseForMonth(anyLong(), anyInt())).thenReturn(orderDetails);
        int expected = 0;
        RewardResponseDTO actual = rewardService.getRewardPointById(customerId, rewardStartMonth);
        assertEquals(expected, actual.getTotalPoint());
    }

    @Test
    public void testRewardPointForOKStatusWhenPurchaseEquals50InLast3Month() throws ResourceNotFoundException {
        long customerId = 1;
        int rewardStartMonth = 3;
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        getOrderDetails(new BigDecimal(10), new Date(), orderDetails);
        Date date1 = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(20), date1, orderDetails);
        Date date2 = Date.from(LocalDate.now().minusMonths(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(20), date2, orderDetails);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(customerId, "test", "test", "test@gmail.com")));
        when(orderDetailRepository.findTotalPurchaseForMonth(anyLong(), anyInt())).thenReturn(orderDetails);
        int expected = 0;
        RewardResponseDTO actual = rewardService.getRewardPointById(customerId, rewardStartMonth);
        assertEquals(expected, actual.getTotalPoint());
    }

    @Test
    public void testRewardPointForOKStatusWhenPurchaseGreaterThan50InLast3Month() throws ResourceNotFoundException {
        long customerId = 1;
        int rewardStartMonth = 3;
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        getOrderDetails(new BigDecimal(50), new Date(), orderDetails);
        Date date1 = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(10), date1, orderDetails);
        Date date2 = Date.from(LocalDate.now().minusMonths(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(20), date2, orderDetails);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(customerId, "test", "test", "test@gmail.com")));
        when(orderDetailRepository.findTotalPurchaseForMonth(anyLong(), anyInt())).thenReturn(orderDetails);
        int expected = 30;
        RewardResponseDTO actual = rewardService.getRewardPointById(customerId, rewardStartMonth);
        assertEquals(expected, actual.getTotalPoint());
    }

    @Test
    public void testRewardPointForOKStatusWhenPurchaseEquals100InLast3Month() throws ResourceNotFoundException {
        long customerId = 1;
        int rewardStartMonth = 3;
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        getOrderDetails(new BigDecimal(50), new Date(), orderDetails);
        Date date1 = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(30), date1, orderDetails);
        Date date2 = Date.from(LocalDate.now().minusMonths(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal(20), date2, orderDetails);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(customerId, "test", "test", "test@gmail.com")));
        when(orderDetailRepository.findTotalPurchaseForMonth(anyLong(), anyInt())).thenReturn(orderDetails);
        int expected = 50;
        RewardResponseDTO actual = rewardService.getRewardPointById(customerId, rewardStartMonth);
        assertEquals(expected, actual.getTotalPoint());
    }

    @Test
    public void testRewardPointForOKStatusWhenPurchaseInFractionsInLast3Month() throws ResourceNotFoundException {
        long customerId = 1;
        int rewardStartMonth = 3;
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        getOrderDetails(new BigDecimal("8.12"), new Date(), orderDetails);
        Date date1 = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal("8.12"), date1, orderDetails);
        Date date2 = Date.from(LocalDate.now().minusMonths(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal("90.90"), date2, orderDetails);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(customerId, "test", "test", "test@gmail.com")));
        when(orderDetailRepository.findTotalPurchaseForMonth(anyLong(), anyInt())).thenReturn(orderDetails);
        int expected = 64;
        RewardResponseDTO actual = rewardService.getRewardPointById(customerId, rewardStartMonth);
        assertEquals(expected, actual.getTotalPoint());
    }

    @Test
    public void testRewardPointForOKStatusWhenPurchaseInFractionsNotMadeDoller() throws ResourceNotFoundException {
        long customerId = 1;
        int rewardStartMonth = 3;
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        getOrderDetails(new BigDecimal("8.12"), new Date(), orderDetails);
        Date date1 = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal("8.12"), date1, orderDetails);
        Date date2 = Date.from(LocalDate.now().minusMonths(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        getOrderDetails(new BigDecimal("90.70"), date2, orderDetails);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(customerId, "test", "test", "test@gmail.com")));
        when(orderDetailRepository.findTotalPurchaseForMonth(anyLong(), anyInt())).thenReturn(orderDetails);
        int expected = 62;
        RewardResponseDTO actual = rewardService.getRewardPointById(customerId, rewardStartMonth);
        assertEquals(expected, actual.getTotalPoint());
    }

    @Test
    public void testRewardPointForOKStatusWhenPurchaseBeforeSixMonth() throws ResourceNotFoundException {
        long customerId = 1;
        int rewardStartMonth = 3;
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        getOrderDetails(new BigDecimal("8.12"), new Date(), orderDetails);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(customerId, "test", "test", "test@gmail.com")));
        when(orderDetailRepository.findTotalPurchaseForMonth(anyLong(), anyInt())).thenReturn(orderDetails);
        int expected = 0;
        RewardResponseDTO actual = rewardService.getRewardPointById(customerId, rewardStartMonth);
        assertEquals(expected, actual.getTotalPoint());
    }

    private Collection<OrderDetail> getOrderDetails(BigDecimal purchase, Date created, Collection<OrderDetail> orderDetails) {
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setTotalPurchase(purchase);
        orderDetail1.setCreated(created);
        orderDetails.add(orderDetail1);
        return orderDetails;
    }
}
