package com.st.app.rewardprovider.repository;

import com.st.app.rewardprovider.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface Customer Repository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
