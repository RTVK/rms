package com.rms.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "time_zone", "event_date" })
public class DateOfEvent {

	@JsonProperty("id")
	private String id;

	/**
	 * The date portion of when the event type occurred. For accuracy, this value
	 * should be submitted in UTC timezone if the exact timezone is not known. This
	 * must follow ISO 8601 standards.
	 *
	 */
	@JsonProperty("event_date")
	private DateTime eventDate;

	@JsonProperty("time_zone")
	private String timeZone;

	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * The date portion of when the event type occurred. For accuracy, this value
	 * should be submitted in UTC timezone if the exact timezone is not known. This
	 * must follow ISO 8601 standards.
	 *
	 * @return The eventDate
	 */
	@JsonProperty("event_date")
	public DateTime getEventDate() {
		return this.eventDate;
	}

	/**
	 * The date portion of when the event type occurred. For accuracy, this value
	 * should be submitted in UTC timezone if the exact timezone is not known. This
	 * must follow ISO 8601 standards.
	 *
	 * @param eventDate
	 *            The event_date
	 */
	@JsonProperty("event_date")
	public void setEventDate(final DateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(final String name, final Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.eventDate).append(this.id).append(this.additionalProperties)
				.append(timeZone).toHashCode();
	}

	@Override
	public boolean equals(final Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof DateOfEvent) == false) {
			return false;
		}
		DateOfEvent rhs = ((DateOfEvent) other);
		return new EqualsBuilder().append(this.eventDate, rhs.eventDate).append(this.timeZone, rhs.timeZone)
				.append(this.id, rhs.id).append(this.additionalProperties, rhs.additionalProperties).isEquals();
	}

}
