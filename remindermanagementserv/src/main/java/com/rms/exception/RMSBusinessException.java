package com.rms.exception;

public class RMSBusinessException extends RMSException {

	private String errorCode;
	private String errorMessage;

	public RMSBusinessException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
