package com.fairdeal.exception;

/**
 * This exception is thrown in scenario when user is not having 
 * Quota to post classified. 
, * 
 * @author pbehl1
 */
public class OutOfQuotaException extends RuntimeException {

	public OutOfQuotaException(String message){
		super(message);
	}
	
}
