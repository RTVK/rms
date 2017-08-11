package com.rms.validation;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.CollectionUtils;

import com.rms.common.ErrorCodes;
import com.rms.model.RMSError;
import com.rms.model.RMSErrorResponse;

/**
 * To validate request data.
 * 
 * @author ripandya
 *
 */
@Named
public class RMSValidation {
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator getValidator() {
		return factory.getValidator();
	}

	/**
	 * Returns list of validation errors.
	 * 
	 * @param objectToValidate
	 * @return
	 */
	public RMSErrorResponse validate(final Object objectToValidate) {
		RMSErrorResponse rmsErrorResponse = null;
		Set<ConstraintViolation<Object>> violations = getValidator().validate(objectToValidate);
		if (!CollectionUtils.isEmpty(violations)) {
			rmsErrorResponse = new RMSErrorResponse();
			Set<RMSError> rmsErrors = new HashSet();
			for (ConstraintViolation constraintViolation : violations) {
				RMSError rmsError = new RMSError();
				rmsError.setErrorCode(ErrorCodes.VALIDATION_BIZ_EXCEPTION);
				rmsError.setMessage(constraintViolation.getMessage());
				rmsError.setPath(constraintViolation.getPropertyPath().toString());
				rmsErrors.add(rmsError);
			}
			rmsErrorResponse.setRmsErrors(rmsErrors);
		}
		return rmsErrorResponse;
	}
}
