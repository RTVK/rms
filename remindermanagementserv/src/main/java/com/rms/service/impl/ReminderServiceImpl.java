package com.rms.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rms.common.Constants;
import com.rms.common.ErrorCodes;
import com.rms.exception.RMSException;
import com.rms.exception.RMSSystemException;
import com.rms.h2.dao.ReminderDao;
import com.rms.h2.model.Reminder;
import com.rms.service.ReminderService;
import com.rms.util.DateUtils;
import com.rms.util.RMSBeanUtils;

@Named
@Transactional
public class ReminderServiceImpl implements ReminderService {

	@Autowired
	private ReminderDao reminderDaoImpl;

	@Override
	public void create(final com.rms.model.Reminder reminder) throws RMSException {
		try {
			Reminder reminderDetails = new Reminder();
			// Standard BeanUtils [common] / Mapstruct etc. can be used here, instead of
			// RMSBeanUtils.
			RMSBeanUtils.copyReminderRequestToDBModel(reminderDetails, reminder);
			reminderDaoImpl.create(reminderDetails);
			RMSBeanUtils.copyDBModelToResponse(reminder, reminderDetails);
		} catch (Exception exception) {
			throw new RMSSystemException(ErrorCodes.DB_SYS_EXCEPTION, Constants.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public void update(final com.rms.model.Reminder reminder) throws RMSException {
		try {
			Reminder reminderDetails = new Reminder();
			// Standard BeanUtils [common] / Mapstruct etc. can be used here, instead of
			// RMSBeanUtils.
			RMSBeanUtils.copyReminderRequestToDBModel(reminderDetails, reminder);
			reminderDaoImpl.update(reminderDetails);
			RMSBeanUtils.copyDBModelToResponse(reminder, reminderDetails);
		} catch (Exception exception) {
			throw new RMSSystemException(ErrorCodes.DB_SYS_EXCEPTION, Constants.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<com.rms.model.Reminder> getRemindersByParam(String dueDate, String status) throws RMSException {
		try {
			List<com.rms.h2.model.Reminder> reminderDetails = null;
			if (dueDate != null && status == null) {
				reminderDetails = reminderDaoImpl.getRemindersByDueDate(DateUtils.getSqlDateFromString(dueDate));
			} else if (dueDate == null && status != null) {
				reminderDetails = reminderDaoImpl.getRemindersByStatus(com.rms.model.Reminder.Status.fromValue(status));
			} else if (dueDate != null && status != null) {
				reminderDetails = reminderDaoImpl.getRemindersByParan(DateUtils.getSqlDateFromString(dueDate),
						com.rms.model.Reminder.Status.fromValue(status));
			} else {
				return null;
			}
			List<com.rms.model.Reminder> reminders = new LinkedList<>();
			for (Reminder reminderDetail : reminderDetails) {
				com.rms.model.Reminder reminder = new com.rms.model.Reminder();
				RMSBeanUtils.copyDBModelToResponse(reminder, reminderDetail);
				reminders.add(reminder);
			}
			return reminders;
		} catch (Exception exception) {
			throw new RMSSystemException(ErrorCodes.DB_SYS_EXCEPTION, Constants.SOMETHING_WENT_WRONG);
		}
	}
}