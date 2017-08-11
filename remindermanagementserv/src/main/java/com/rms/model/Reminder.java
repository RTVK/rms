package com.rms.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "description", "date_of_events", "status", "category" })
public class Reminder {
	/**
	 * The timezone of the account. ex: America/Chicago
	 *
	 */

	@JsonProperty("id")
	private String id;

	@NotNull
	@JsonProperty("name")
	private String name;

	@NotNull
	@JsonProperty("description")
	private String description;

	/**
	 * Reminder can be set for multiple events.
	 */
	@NotNull
	@JsonProperty("date_of_events")
	private Set<DateOfEvent> eventDate;

	@JsonProperty("status")
	private Status status;

	@JsonProperty("category")
	private Category category;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Set<DateOfEvent> getEventDate() {
		return eventDate;
	}

	public void setEventDate(Set<DateOfEvent> eventData) {
		this.eventDate = eventData;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.id).append(this.description).append(this.eventDate).append(this.name)
				.append(this.status).append(this.category).toHashCode();
	}

	@Override
	public boolean equals(final Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Reminder) == false) {
			return false;
		}
		Reminder rhs = ((Reminder) other);
		return new EqualsBuilder().append(this.id, rhs.id).append(this.description, rhs.description)
				.append(this.eventDate, rhs.eventDate).append(this.name, rhs.name).append(this.category, rhs.category)
				.append(this.status, rhs.status).isEquals();
	}

	@Generated("org.jsonschema2pojo")
	public static enum Status {

		DONE("DONE"), NOT_DONE("NOT_DONE");
		private final String value;
		private static Map<String, Status> constants = new HashMap<String, Status>();

		static {
			for (Status c : values()) {
				constants.put(c.value, c);
			}
		}

		private Status(final String value) {
			this.value = value;
		}

		@JsonValue
		@Override
		public String toString() {
			return this.value;
		}

		@JsonCreator
		public static Status fromValue(final String value) {
			Status statusValue = constants.get(value);
			if (statusValue == null) {
				throw new IllegalArgumentException(value);
			} else {
				return statusValue;
			}
		}

	}

	@Generated("org.jsonschema2pojo")
	public static enum Category {

		HOME("HOME"), FRIENDS("FRIENDS"), WORK("WORK"), OTHER("OTHER");
		private final String value;
		private static Map<String, Reminder.Category> constants = new HashMap<String, Reminder.Category>();

		static {
			for (Reminder.Category c : values()) {
				constants.put(c.value, c);
			}
		}

		private Category(final String value) {
			this.value = value;
		}

		@JsonValue
		@Override
		public String toString() {
			return this.value;
		}

		@JsonCreator
		public static Reminder.Category fromValue(final String value) {
			Reminder.Category constant = constants.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}
}
