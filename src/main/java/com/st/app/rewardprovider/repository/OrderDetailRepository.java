package com.st.app.rewardprovider.repository;

import com.st.app.rewardprovider.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * The Interface Order detail Repository
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    /**
     *  Using groupBy get the sum of purchase for each month from reward Start Month for customerId
     * @param customer_id
     * @param rewardStartMonth
     * @return
     */
    @Query(value = "select max(order_id) order_id, max(order_status)order_status, max(customer_id)customer_id, max(created) created, sum(total_purchase) total_purchase from order_detail where customer_id= ?1 and month(created)> ?2 group by year(CREATED),month(created) order by year(created),month(created)",nativeQuery = true)
     Collection<OrderDetail> findTotalPurchaseForMonth(long customer_id, int rewardStartMonth);
}
