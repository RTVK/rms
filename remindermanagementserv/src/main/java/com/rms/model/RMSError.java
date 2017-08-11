package com.rms.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "message", "field_path" })
public class RMSError {

	@JsonProperty("event_code")
	private String errorCode;

	@JsonProperty("message")
	private String message;

	@JsonProperty("path")
	private String path;

	public RMSError() {
	}

	public RMSError(String errorCode, String message, String path) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.path = path;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.errorCode).append(this.message).append(this.path).toHashCode();
	}

	@Override
	public boolean equals(final Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof RMSError) == false) {
			return false;
		}
		RMSError rhs = ((RMSError) other);
		return new EqualsBuilder().append(this.errorCode, rhs.errorCode).append(this.message, rhs.message)
				.append(this.path, rhs.path).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
