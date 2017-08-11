package com.rms.util;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.rms.h2.model.Event;
import com.rms.model.DateOfEvent;
import com.rms.model.Reminder;

/**
 * Standard BeanUtils / Mapstruct can be used to copy values. The Bean Util
 * created, just to explain functionality.
 * 
 * @author ripandya
 *
 */
public class RMSBeanUtils {

	public static void copyReminderRequestToDBModel(final com.rms.h2.model.Reminder reminderDetails,
			final Reminder reminder) {
		reminderDetails.setDescription(reminder.getDescription());
		// Event details are must for any reminder. This been validated [is exist
		// check], so we are not expecting NPE here.

		reminderDetails.setEvents(copyDateOfEventToEvent(reminder.getEventDate(), reminderDetails));
		reminderDetails.setName(reminder.getName());
		if (reminder.getStatus() != null) {
			reminderDetails.setStatus(reminder.getStatus().name());
		} else {
			// Default status
			reminderDetails.setStatus(Reminder.Status.NOT_DONE.name());
		}
		if (StringUtils.isNotBlank(reminder.getId())) {
			reminderDetails.setId(Long.parseLong(reminder.getId()));
		}
		reminderDetails.setCategory(reminder.getCategory().name());
	}

	private static Set<Event> copyDateOfEventToEvent(final Set<DateOfEvent> dateOfEvents,
			final com.rms.h2.model.Reminder reminderDetails) {
		Set<Event> eventDetailSet = new HashSet<>();
		for (DateOfEvent dateOfEvent : dateOfEvents) {
			Event event = new Event();
			event.setEventDate(new Timestamp(dateOfEvent.getEventDate().getMillis()));
			if (StringUtils.isNotBlank(dateOfEvent.getId())) {
				event.setId(Long.parseLong(dateOfEvent.getId()));
			}
			event.setTimeZone(dateOfEvent.getTimeZone());
			event.setReminder(reminderDetails);
			eventDetailSet.add(event);
		}
		return eventDetailSet;
	}

	private static Set<DateOfEvent> copyEventsToDateOfEvent(final Set<Event> eventSet) {
		Set<DateOfEvent> dateOfEvents = new HashSet<>();
		for (Event eventDetails : eventSet) {
			DateOfEvent dateOfEvent = new DateOfEvent();
			dateOfEvent.setEventDate(DateUtils.getJodaDateFromTimestamp(eventDetails.getEventDate()));
			if (eventDetails.getId() != null) {
				dateOfEvent.setId(eventDetails.getId().toString());
			}
			dateOfEvent.setTimeZone(eventDetails.getTimeZone());
			dateOfEvents.add(dateOfEvent);
		}
		return dateOfEvents;
	}

	public static void copyDBModelToResponse(final Reminder reminder, final com.rms.h2.model.Reminder reminderDetails) {

		reminder.setDescription(reminderDetails.getDescription());
		reminder.setEventDate(copyEventsToDateOfEvent(reminderDetails.getEvents()));
		reminder.setName(reminderDetails.getName());
		reminder.setStatus(Reminder.Status.valueOf(reminderDetails.getStatus()));
		if (reminderDetails.getStatus() != null) {
			reminder.setStatus(Reminder.Status.fromValue(reminderDetails.getStatus()));
		} else {
			// Default status
			reminder.setStatus(Reminder.Status.NOT_DONE);
		}
		if (reminderDetails.getId() != null) {
			reminder.setId(reminderDetails.getId().toString());
		}
		reminder.setCategory(Reminder.Category.fromValue(reminderDetails.getCategory()));
	}
}
