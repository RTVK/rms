package com.rms.service;

import java.util.List;

import com.rms.exception.RMSException;
import com.rms.model.Reminder;

/**
 * Service class to maintain transactions, call validations,
 * 
 * @author ripandya
 *
 */
public interface ReminderService {

	/**
	 * Get reminders based on filter criteria.
	 * 
	 * @param dueDate
	 * @param status
	 * @return
	 * @throws RMSException
	 */
	public List<Reminder> getRemindersByParam(final String dueDate, final String status) throws RMSException;

	/**
	 * Adds reminder.
	 * 
	 * @param reminderDetails
	 * @throws RMSException
	 */
	public void create(final Reminder reminderDetails) throws RMSException;

	/**
	 * Modifies reminder details.
	 * 
	 * @param reminderDetails
	 * @throws RMSException
	 */
	public void update(final Reminder reminderDetails) throws RMSException;

}