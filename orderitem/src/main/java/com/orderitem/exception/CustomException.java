package com.orderitem.exception;

public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}
}
