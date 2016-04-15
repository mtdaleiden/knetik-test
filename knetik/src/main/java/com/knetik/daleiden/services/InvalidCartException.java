/**
 * 
 */
package com.knetik.daleiden.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an invalid cart ID is provided.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidCartException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidCartException(Long cartId) {
		super("No shopping cart with ID '" + cartId + "' exists");
	}

}
