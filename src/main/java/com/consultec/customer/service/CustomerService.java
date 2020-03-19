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
 * Lógica de negocio para la administración de los clientes
 *
 * @author Edward Hernandez
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Buscar un cliente
     *
     * @param id Número del cliente
     * @return Datos del cliente
     */
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    /**
     * Busqueda de clientes por los criterios usuario y estatus
     *
     * @param username Usuario registrado
     * @param status estatus resgistarado (ENABLE / DISABLED)
     * @return Datos del Cliente
     */
    public Customer getCustomerByUsernameAndStatus(String username, Status status) {
        return customerRepository.findByUsernameAndStatus(username, status).orElseThrow(CustomerNotFoundException::new);

    }

    /**
     * Obtener el listado de clientes
     *
     * @return Datos del cliente
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Eliminar un cliente de la base de datos de clientes
     *
     * @param id Número del cliente
     */
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    /**
     * Registrar nuevos clientes
     *
     * @param customerDTO Informaciones de registro para un nuevo cliente
     * @return Datos del cliente
     */
    public Customer addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        copyCustomerDataFromDTO(customer, customerDTO);
        customer.setDateCreated(ZonedDateTime.now());
        return customerRepository.save(customer);
    }

    /**
     * Actualizar los datos del cliente
     *
     * @param id Número del cliente
     * @param customerDTO Informaciones que se deben actualizar del cliente
     */
    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        copyCustomerDataFromDTO(customer, customerDTO);
        customerRepository.save(customer);

    }

    /**
     * Transferir datos hacia el objeto del dominio del cliente
     *
     * @param customer Objeto del dominio o entidad del cliente
     * @param customerDTO Objeto para la recepción de datos a través del
     * servicio Restful
     */
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
