/**
 * 
 */
package com.knetik.daleiden.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an invalid item is provided.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidItemException(Long itemId) {
		super("No item with ID '" + itemId + "' exists");
	}

}
