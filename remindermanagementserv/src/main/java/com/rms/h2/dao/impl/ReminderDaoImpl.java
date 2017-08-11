package com.rms.h2.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.rms.h2.dao.ReminderDao;
import com.rms.h2.model.Reminder;
import com.rms.model.Reminder.Status;

@Named
public class ReminderDaoImpl implements ReminderDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void update(final Reminder reminder) {
		entityManager.merge(reminder);
	}

	@Override
	public void delete(long id) {
		Reminder reminderToRemove = getReminderById(id);
		if (reminderToRemove != null) {
			entityManager.remove(reminderToRemove);
		}
	}

	@Override
	public Reminder getReminderById(long id) {
		return entityManager.createQuery("FROM Reminder WHERE id = :value1", Reminder.class).setParameter("value1", id)
				.getSingleResult();
	}

	@Override
	public List<Reminder> getRemindersByStatus(Status status) {
		return entityManager.createQuery("FROM Reminder WHERE status = :value1", Reminder.class)
				.setParameter("value1", status.name()).getResultList();
	}

	@Override
	public List<Reminder> getRemindersByDueDate(Date dueDate) {
		return entityManager.createNativeQuery(
				"SELECT * FROM Reminder WHERE id IN (SELECT DISTINCT (reminder_id) FROM EVENT WHERE event_date <= ?)",
				Reminder.class).setParameter(1, dueDate).getResultList();
	}

	@Override
	public void create(Reminder reminder) {
		entityManager.persist(reminder);
	}

	@Override
	public List<Reminder> getRemindersByParan(Date dueDate, Status status) {
		return entityManager.createNativeQuery(
				"SELECT * FROM Reminder WHERE status = :status AND id IN (SELECT DISTINCT (reminder_id) FROM EVENT WHERE event_date <= :event_date)",
				Reminder.class).setParameter("event_date", dueDate).setParameter("status", status).getResultList();
	}
}