package com.consultec.customer.controller;

import com.consultec.customer.constants.Status;
import com.consultec.customer.entity.Customer;
import com.consultec.customer.model.CustomerDTO;
import com.consultec.customer.service.CustomerService;
import java.net.URI;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Servicios Restful para la administración de los clientes
 *
 * @author Edward Hernández
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Consulta de clientes
     *
     * @return El listado completo de clientes registrados
     */
    @GetMapping()
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    /**
     * Obtener los datos de un cliente en específico
     *
     * @param id Número del Cliente
     * @return El cliente que se dese Buscar
     */
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    /**
     * Buscar por atributos específicos del cliente
     *
     * @param username Nombre de usuario que utiliza
     * @param status Estatus en el sistema (ENABLED o DISABLED)
     * @return El cliente que cumple con el criterio de busqueda
     */
    @GetMapping("/search")
    public Customer getCustomerByUsernameAndStatus(@RequestParam String username, @RequestParam Status status) {
        return customerService.getCustomerByUsernameAndStatus(username, status);
    }

    /**
     * Actualizar informaciones de los clientes
     *
     * @param id Número del cliente
     * @param customerDTO Informaciones del cliente que se deben actualizar
     * @return HTTP 204 como una confirmación de que el request fue procesado
     */
    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * Registrar clientes en el sistema
     *
     * @param customerDTO Informaciones del cliente que se deben guardar
     * @return Las informaciones guardadas del cliente
     */
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.addCustomer(customerDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    /**
     * Borrar clientes del sistema
     *
     * @param id Número de cliente
     * @return Mensaje HTTP 204 como una confirmación de que fue ejecutada la
     * operación
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
