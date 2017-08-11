package com.rms.exception;

public class RMSSystemException extends RMSException {

	private String errorCode;
	private String errorMessage;

	public RMSSystemException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
