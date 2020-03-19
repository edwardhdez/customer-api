/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consultec.customer.service;

import com.consultec.customer.constants.Status;
import com.consultec.customer.entity.Customer;
import com.consultec.customer.exception.CustomerNotFoundException;
import com.consultec.customer.model.CustomerDTO;
import com.consultec.customer.repository.CustomerRepository;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author acap1609
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer getCustomerByUsernameAndStatus(String username, Status status) {
        return customerRepository.findByUsernameAndStatus(username, status).orElseThrow(CustomerNotFoundException::new);

    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        copyCustomerDataFromDTO(customer, customerDTO);
        customer.setDateCreated(ZonedDateTime.now());
        return customerRepository.save(customer);
    }

    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        copyCustomerDataFromDTO(customer, customerDTO);
        customerRepository.save(customer);

    }

    private void copyCustomerDataFromDTO(Customer customer, CustomerDTO customerDTO) {
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPassword(customerDTO.getPassword());
        customer.setStatus(customerDTO.getStatus());
        customer.setUsername(customerDTO.getUsername());
    }

}
