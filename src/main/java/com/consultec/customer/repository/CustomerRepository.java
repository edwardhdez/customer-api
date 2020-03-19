/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consultec.customer.repository;

import com.consultec.customer.constants.Status;
import com.consultec.customer.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author acap1609
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
 
    Optional<Customer> findByUsernameAndStatus(String username,Status status);
    
}
