package com.st.app.rewardprovider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * ResourceNotFoundException
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize ResourceNotFoundException with message
	 * @param message
	 */
	public ResourceNotFoundException(String message){
    	super(message);
    }
}
