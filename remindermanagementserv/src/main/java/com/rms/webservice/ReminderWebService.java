package com.rms.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.rms.exception.RMSBusinessException;
import com.rms.exception.RMSException;
import com.rms.model.RMSError;
import com.rms.model.RMSErrorResponse;
import com.rms.model.Reminder;
import com.rms.service.ReminderService;
import com.rms.validation.RMSValidation;

@Component
@Scope("request")
@Path("/reminders")
/**
 * 
 * Provides interface maintain reminders.
 * 
 *
 */
public class ReminderWebService {

	@Autowired
	private ReminderService reminderServiceImpl;

	@Autowired
	private RMSValidation rmsValidation;

	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/add")
	@POST
	/**
	 * Adds reminder.
	 * 
	 * @param reminder
	 * @return
	 */
	public Response addReminder(Reminder reminder) {
		try {
			// Template pattern can be used here. But it'll add additional layer.
			RMSErrorResponse rmsErrorResponse = rmsValidation.validate(reminder);
			if (rmsErrorResponse != null && CollectionUtils.isEmpty(rmsErrorResponse.getRmsErrors())) {
				reminderServiceImpl.create(reminder);
				return Response.ok().entity(reminder).build();
			}
			return generateErrorResponse(rmsErrorResponse);
		} catch (RMSException exception) {
			return generateErrorResponse(exception);
		}
	}

	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/update")
	@POST
	/**
	 * Updates existing reminder.
	 * 
	 * @param reminder
	 * @return
	 */
	public Response updateReminder(Reminder reminder) {
		try {
			// Template pattern can be used to
			RMSErrorResponse rmsErrorResponse = rmsValidation.validate(reminder);
			if (rmsErrorResponse != null && CollectionUtils.isEmpty(rmsErrorResponse.getRmsErrors())) {
				reminderServiceImpl.update(reminder);
				return Response.ok().entity(reminder).build();
			}
			return generateErrorResponse(rmsErrorResponse);
		} catch (RMSException exception) {
			return generateErrorResponse(exception);
		}
	}

	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/search")
	@GET
	/**
	 * Get reminders based on criteria.
	 * 
	 * @param status
	 * @param dateTime
	 * @return
	 */
	public Response getRemindersByParam(@QueryParam(value = "status") final String status,
			@QueryParam(value = "date") final String dateTime) {
		try {
			return Response.ok().entity(reminderServiceImpl.getRemindersByParam(dateTime, status)).build();
		} catch (RMSException exception) {
			return generateErrorResponse(exception);
		}
	}

	/**
	 * Generates error response.
	 * 
	 * @param rmsException
	 * @return
	 */
	private Response generateErrorResponse(final RMSException rmsException) {
		RMSErrorResponse rmsErrorResponse = new RMSErrorResponse();
		Set<RMSError> rmsErrorSet = new HashSet<>();
		rmsErrorSet.add(new RMSError(rmsException.getErrorCode(), rmsException.getErrorMessage(), null));
		rmsErrorResponse.setRmsErrors(rmsErrorSet);
		if (rmsException instanceof RMSBusinessException) {
			return Response.status(Status.BAD_REQUEST).entity(rmsErrorResponse).build();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(rmsErrorResponse).build();

	}

	/**
	 * Generates error response for multiple errors.
	 * 
	 * @param rmsException
	 * @return
	 */
	private Response generateErrorResponse(final RMSErrorResponse errorResponse) {
		return Response.status(Status.BAD_REQUEST).entity(errorResponse).build();
	}

}
