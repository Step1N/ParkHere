package com.parkhere.exceptions;

public class ParkHereException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParkHereException() {
	}

	public ParkHereException(String message) {
		super(message);
	}

	public ParkHereException(Throwable cause) {
		super(cause);
	}

	public ParkHereException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParkHereException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
