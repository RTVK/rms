package com.rms.model;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "errors" })
public class RMSErrorResponse {

	public RMSErrorResponse() {
	}

	public RMSErrorResponse(Set<RMSError> rmsErrors) {
		super();
		this.rmsErrors = rmsErrors;
	}

	@JsonProperty("errors")
	private Set<RMSError> rmsErrors;

	public Set<RMSError> getRmsErrors() {
		return rmsErrors;
	}

	public void setRmsErrors(Set<RMSError> rmsErrors) {
		this.rmsErrors = rmsErrors;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.rmsErrors).toHashCode();
	}

	@Override
	public boolean equals(final Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof RMSErrorResponse) == false) {
			return false;
		}
		RMSErrorResponse rhs = ((RMSErrorResponse) other);
		return new EqualsBuilder().append(this.rmsErrors, rhs.rmsErrors).isEquals();
	}

}
