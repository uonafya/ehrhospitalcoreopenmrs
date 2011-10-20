package org.openmrs.module.hospitalcore.web.controller.form.validation;

public interface Validator {
	
	boolean validate(String value);
	String getErrorMessage();
}
