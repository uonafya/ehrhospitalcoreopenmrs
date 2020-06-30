package org.openmrs.module.hospitalcore.web.controller.department;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.openmrs.module.hospitalcore.model.Department;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DepartmentValidator implements Validator {
	public boolean supports(Class clazz) {
		return Department.class.equals(clazz);
	}

	public void validate(Object command, Errors error) {
		Department department = (Department)command;
		if (StringUtils.isBlank(department.getName()))
			error.reject("hospitalcore.department.name.required");
		PatientDashboardService inventoryService = (PatientDashboardService)Context.getService(PatientDashboardService.class);
		Department department2 = inventoryService.getDepartmentByName(department.getName());
		if (department.getId() != null) {
			if (department2 != null && department2.getId().intValue() != department.getId().intValue())
				error.reject("hospitalcore.department.name.existed");
		} else if (department2 != null) {
			error.reject("hospitalcore.department.name.existed");
		}
	}
}
