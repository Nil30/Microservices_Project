package com.userservice.Exceptions;

public class ResourceNotFoundExceptions extends RuntimeException {

	// Resource Not Found Exception
	public ResourceNotFoundExceptions() {
		super("Resource Not Found");
	}

	public ResourceNotFoundExceptions(String msg) {
		super(msg);
	}
}
