package org.openmrs.module.hospitalcore.web.controller.department;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.openmrs.module.hospitalcore.model.Department;
import org.openmrs.module.hospitalcore.model.DepartmentConcept;
import org.openmrs.module.hospitalcore.util.ConceptComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("DepartmentConceptFormController")
@RequestMapping({"/module/hospitalcore/departmentConcept.form"})
public class DepartmentConceptFormController {
	@RequestMapping(method = {RequestMethod.GET})
	public String firstView(@ModelAttribute("departmentConceptCommand") DepartmentConceptCommand departmentConceptCommand, @RequestParam(value = "dId", required = false) Integer id, Model model) {
		PatientDashboardService dashboardService = (PatientDashboardService)Context.getService(PatientDashboardService.class);
		List<Department> listDepartment = dashboardService.listDepartment(Boolean.valueOf(false));
		model.addAttribute("listDepartment", listDepartment);
		if (id != null) {
			List<DepartmentConcept> listDiagnosisDepartment = dashboardService.listByDepartment(id, Integer.valueOf(DepartmentConcept.TYPES[0]));
			List<DepartmentConcept> listProcedureDepartment = dashboardService.listByDepartment(id, Integer.valueOf(DepartmentConcept.TYPES[1]));
			List<Concept> diagnosisList = dashboardService.searchDiagnosis(null);
			Collections.sort(diagnosisList, (Comparator<? super Concept>)new ConceptComparator());
			List<Concept> procedures = dashboardService.searchProcedure(null);
			Collections.sort(procedures, (Comparator<? super Concept>)new ConceptComparator());
			List<Concept> listC = new ArrayList<Concept>();
			if (CollectionUtils.isNotEmpty(listDiagnosisDepartment))
				for (DepartmentConcept c : listDiagnosisDepartment)
					listC.add(c.getConcept());
			diagnosisList.removeAll(listC);
			listC = new ArrayList<Concept>();
			if (CollectionUtils.isNotEmpty(listProcedureDepartment))
				for (DepartmentConcept c : listProcedureDepartment)
					listC.add(c.getConcept());
			procedures.removeAll(listC);
			model.addAttribute("listProcedures", procedures);
			model.addAttribute("diagnosisList", diagnosisList);
			model.addAttribute("dId", id);
			model.addAttribute("listDiagnosisDepartment", listDiagnosisDepartment);
			model.addAttribute("listProcedureDepartment", listProcedureDepartment);
		}
		return "/module/hospitalcore/department/departmentConcept";
	}

	@RequestMapping(method = {RequestMethod.POST})
	public String formSummit(DepartmentConceptCommand command) throws Exception {
		PatientDashboardService dashboardService = (PatientDashboardService)Context.getService(PatientDashboardService.class);
		List<DepartmentConcept> listDiagnosisDepartment = dashboardService.listByDepartment(command.getDepartmentId(), Integer.valueOf(DepartmentConcept.TYPES[0]));
		List<DepartmentConcept> listProcedureDepartment = dashboardService.listByDepartment(command.getDepartmentId(), Integer.valueOf(DepartmentConcept.TYPES[1]));
		Department department = dashboardService.getDepartmentById(command.getDepartmentId());
		String user = Context.getAuthenticatedUser().getGivenName();
		Date date = new Date();
		if (CollectionUtils.isEmpty(listDiagnosisDepartment)) {
			for (Integer iDiag : command.getSelectedDiagnosisList()) {
				DepartmentConcept departmentConcept = new DepartmentConcept();
				departmentConcept.setDepartment(department);
				departmentConcept.setConcept(Context.getConceptService().getConcept(iDiag));
				departmentConcept.setCreatedBy(user);
				departmentConcept.setCreatedOn(date);
				departmentConcept.setTypeConcept(Integer.valueOf(DepartmentConcept.TYPES[0]));
				dashboardService.createDepartmentConcept(departmentConcept);
			}
		} else {
			List<Integer> listIdDiagnosis = new ArrayList<Integer>();
			for (Integer i : command.getSelectedDiagnosisList())
				listIdDiagnosis.add(i);
			for (DepartmentConcept dC : listDiagnosisDepartment) {
				if (!listIdDiagnosis.contains(dC.getConcept().getId())) {
					dashboardService.removeDepartmentConcept(dC);
					continue;
				}
				listIdDiagnosis.remove(dC.getConcept().getId());
			}
			if (CollectionUtils.isNotEmpty(listIdDiagnosis))
				for (Integer diag : listIdDiagnosis) {
					DepartmentConcept departmentConcept = new DepartmentConcept();
					departmentConcept.setDepartment(department);
					departmentConcept.setConcept(Context.getConceptService().getConcept(diag));
					departmentConcept.setCreatedBy(user);
					departmentConcept.setCreatedOn(date);
					departmentConcept.setTypeConcept(Integer.valueOf(DepartmentConcept.TYPES[0]));
					dashboardService.createDepartmentConcept(departmentConcept);
				}
		}
		if (CollectionUtils.isEmpty(listProcedureDepartment)) {
			for (Integer iPro : command.getSelectedProcedureList()) {
				DepartmentConcept departmentConcept = new DepartmentConcept();
				departmentConcept.setDepartment(department);
				departmentConcept.setConcept(Context.getConceptService().getConcept(iPro));
				departmentConcept.setCreatedBy(user);
				departmentConcept.setCreatedOn(date);
				departmentConcept.setTypeConcept(Integer.valueOf(DepartmentConcept.TYPES[1]));
				dashboardService.createDepartmentConcept(departmentConcept);
			}
		} else {
			List<Integer> listIdProcedure = new ArrayList<Integer>();
			for (Integer i : command.getSelectedProcedureList())
				listIdProcedure.add(i);
			for (DepartmentConcept dC : listProcedureDepartment) {
				if (!listIdProcedure.contains(dC.getConcept().getId())) {
					dashboardService.removeDepartmentConcept(dC);
					continue;
				}
				listIdProcedure.remove(dC.getConcept().getId());
			}
			if (CollectionUtils.isNotEmpty(listIdProcedure))
				for (Integer proc : listIdProcedure) {
					DepartmentConcept departmentConcept = new DepartmentConcept();
					departmentConcept.setDepartment(department);
					departmentConcept.setConcept(Context.getConceptService().getConcept(proc));
					departmentConcept.setCreatedBy(user);
					departmentConcept.setCreatedOn(date);
					departmentConcept.setTypeConcept(Integer.valueOf(DepartmentConcept.TYPES[1]));
					dashboardService.createDepartmentConcept(departmentConcept);
				}
		}
		return "redirect:/module/hospitalcore/departmentConcept.form?dId=" + command.getDepartmentId();
	}
}
