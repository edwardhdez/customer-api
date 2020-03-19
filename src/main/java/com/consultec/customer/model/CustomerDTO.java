package com.consultec.customer.model;

import com.consultec.customer.constants.Status;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

/**
 * Objeto para capturar los datos recibidos por el API
 *
 * @author Edward Hernández
 */
@Data
public class CustomerDTO {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String address;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
