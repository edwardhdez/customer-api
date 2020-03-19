package com.consultec.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción generada cuando no existe la información de cliente buscada
 *
 * @author Edward Hernández
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Cliente no encontrado";

    public CustomerNotFoundException() {

        super(MESSAGE);
    }

}
