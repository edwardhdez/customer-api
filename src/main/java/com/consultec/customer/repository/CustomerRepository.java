package com.consultec.customer.repository;

import com.consultec.customer.constants.Status;
import com.consultec.customer.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Componente utilizada para la interacción con la base de datos de clientes
 *
 * @author Edward Hernandez
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Buscar clientes por los criterios usuarios y estatus
     *
     * @param username Usuario registrado
     * @param status Estatus que se desea buscar
     * @return Entidad con la información del cliente
     */
    Optional<Customer> findByUsernameAndStatus(String username, Status status);

}
