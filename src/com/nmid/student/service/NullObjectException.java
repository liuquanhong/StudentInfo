package com.nmid.student.service;

public class NullObjectException extends RuntimeException {

	public NullObjectException() {
		super();
		
	}

	public NullObjectException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NullObjectException(String message) {
		super(message);
		
	}

	public NullObjectException(Throwable cause) {
		super(cause);
		
	}
	
}
