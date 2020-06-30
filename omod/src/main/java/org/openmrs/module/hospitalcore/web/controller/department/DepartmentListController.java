package org.openmrs.module.hospitalcore.web.controller.department;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.openmrs.module.hospitalcore.model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("DepartmentListController")
@RequestMapping({"/module/hospitalcore/departmentList.form"})
public class DepartmentListController {
	@RequestMapping(method = {RequestMethod.GET})
	public String list(@RequestParam(value = "searchName", required = false) String searchName, @RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "currentPage", required = false) Integer currentPage, Map<String, Object> model, HttpServletRequest request) {
		PatientDashboardService patientDashboardService = (PatientDashboardService)Context.getService(PatientDashboardService.class);
		List<Department> departments = patientDashboardService.listDepartment(null);
		model.put("departments", departments);
		return "/module/hospitalcore/department/departmentList";
	}
}
