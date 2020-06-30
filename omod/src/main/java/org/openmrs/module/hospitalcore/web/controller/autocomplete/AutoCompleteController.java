package org.openmrs.module.hospitalcore.web.controller.autocomplete;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalCoreAutoCompleteController")
public class AutoCompleteController {
	@RequestMapping({"/module/hospitalcore/checkSession.htm"})
	public String checkSession(HttpServletRequest request, Model model) {
		if (Context.getAuthenticatedUser() != null && Context.getAuthenticatedUser().getId() != null) {
			model.addAttribute("session", "1");
		} else {
			model.addAttribute("session", "0");
		}
		return "module/hospitalcore/session/checkSession";
	}

	@RequestMapping(value = {"/module/hospitalcore/autoCompleteDiagnosis.htm"}, method = {RequestMethod.GET})
	public String autoCompleteDiagnosis(@RequestParam(value = "q", required = false) String name, Model model) {
		List<Concept> diagnosis = ((PatientDashboardService)Context.getService(PatientDashboardService.class)).searchDiagnosis(name);
		model.addAttribute("diagnosis", diagnosis);
		return "module/hospitalcore/autocomplete/autoCompleteDiagnosis";
	}

	@RequestMapping(value = {"/module/hospitalcore/autoCompleteProcedure.htm"}, method = {RequestMethod.GET})
	public String autoCompleteProcedure(@RequestParam(value = "q", required = false) String name, Model model) {
		List<Concept> procedures = ((PatientDashboardService)Context.getService(PatientDashboardService.class)).searchProcedure(name);
		model.addAttribute("procedures", procedures);
		return "module/hospitalcore/autocomplete/autoCompleteProcedure";
	}
}
