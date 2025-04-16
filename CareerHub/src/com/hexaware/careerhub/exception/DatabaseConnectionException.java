package com.hexaware.careerhub.exception;

public class DatabaseConnectionException extends Exception {

	public DatabaseConnectionException(String mssg) {
		
		super(mssg);
	}

	public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
