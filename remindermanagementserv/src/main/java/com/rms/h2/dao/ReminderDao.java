package com.rms.h2.dao;

import java.sql.Date;
import java.util.List;

import com.rms.h2.model.Reminder;
import com.rms.model.Reminder.Status;

public interface ReminderDao {

	public void create(final Reminder reminderD);

	public void update(final Reminder reminder);

	public Reminder getReminderById(final long id);

	public List<Reminder> getRemindersByStatus(final Status status);

	public List<Reminder> getRemindersByDueDate(final Date dueDate);

	public List<Reminder> getRemindersByParan(final Date dueDate, final Status status);

	public void delete(final long id);
}