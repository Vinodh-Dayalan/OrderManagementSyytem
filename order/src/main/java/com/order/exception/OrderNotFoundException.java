package com.order.exception;

public class OrderNotFoundException extends  Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736629675933688916L;

	public OrderNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public OrderNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OrderNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OrderNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
