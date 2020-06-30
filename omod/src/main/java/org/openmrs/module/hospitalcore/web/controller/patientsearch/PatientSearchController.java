package org.openmrs.module.hospitalcore.web.controller.patientsearch;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.matcher.AgeMatcher;
import org.openmrs.module.hospitalcore.matcher.DateMatcher;
import org.openmrs.module.hospitalcore.matcher.GenderMatcher;
import org.openmrs.module.hospitalcore.matcher.Matcher;
import org.openmrs.module.hospitalcore.matcher.RelativeNameMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("PatientSearchController")
@RequestMapping({"/module/hospitalcore/patientSearch.form"})
public class PatientSearchController {
	@RequestMapping(method = {RequestMethod.POST})
	public String searchPatient(@RequestParam(value = "phrase", required = false) String phrase, @RequestParam(value = "view", required = false) String view, @RequestParam(value = "currentPage", required = false) Integer currentPage, @RequestParam(value = "pageSize", required = false) Integer pageSize, HttpServletRequest request, Model model) {
		String prefix = Context.getAdministrationService().getGlobalProperty("hospitalcore.identifier_prefix");
		model.addAttribute("prefix", prefix);
		if (phrase.contains("-") && !phrase.contains(prefix))
			phrase = prefix + phrase;
		String gender = request.getParameter("gender");
		if (gender.equalsIgnoreCase("any"))
			gender = null;
		Integer age = getInt(request.getParameter("age"));
		Integer ageRange = getInt(request.getParameter("ageRange"));
		String relativeName = request.getParameter("relativeName");
		String date = request.getParameter("date");
		Integer dateRange = getInt(request.getParameter("dateRange"));
		HospitalCoreService hcs = (HospitalCoreService)Context.getService(HospitalCoreService.class);
		List<Patient> patients = hcs.searchPatient(phrase, gender, age.intValue(), ageRange.intValue(), date, dateRange.intValue(), relativeName);
		for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements(); ) {
			String parameterName = e.nextElement();
			model.addAttribute(parameterName, request.getParameter(parameterName));
		}
		if (currentPage.intValue() > 0)
			model.addAttribute("prevPage", Integer.valueOf(currentPage.intValue() - 1));
		if ((currentPage.intValue() + 1) * pageSize.intValue() <= patients.size())
			model.addAttribute("nextPage", Integer.valueOf(currentPage.intValue() + 1));
		List<Patient> renderedPatients = pagePatient(patients, currentPage.intValue(), pageSize.intValue());
		model.addAttribute("patients", renderedPatients);
		model.addAttribute("size", Integer.valueOf(patients.size()));
		return "/module/hospitalcore/patientSearch/" + view;
	}

	private List<Patient> filterPatients(HttpServletRequest request, List<Patient> patients) throws NumberFormatException, ParseException {
		List<Patient> filteredPatients = patients;
		String genderCriterion = request.getParameter("gender");
		if (!StringUtils.isBlank(genderCriterion))
			filteredPatients = select(filteredPatients, (Matcher)new GenderMatcher(new String(genderCriterion)));
		String ageCriterion = request.getParameter("age");
		if (!StringUtils.isBlank(ageCriterion)) {
			String ageRange = request.getParameter("ageRange");
			try {
				filteredPatients = select(filteredPatients, (Matcher)new AgeMatcher(new Integer(ageCriterion), new Integer(ageRange)));
			} catch (Exception e) {
				e.printStackTrace();
				throw new NumberFormatException("advancesearch.error.age");
			}
		}
		String relativeNameCriterion = request.getParameter("relativeName");
		if (!StringUtils.isBlank(relativeNameCriterion))
			filteredPatients = select(filteredPatients, (Matcher)new RelativeNameMatcher(relativeNameCriterion));
		String dateCriterion = request.getParameter("date");
		if (!StringUtils.isBlank(dateCriterion))
			try {
				String dateRange = request.getParameter("dateRange");
				filteredPatients = select(filteredPatients, (Matcher)new DateMatcher(dateCriterion, new Integer(dateRange)));
			} catch (Exception e) {
				e.printStackTrace();
				throw new NumberFormatException("advancesearch.error.date");
			}
		return filteredPatients;
	}

	private List<Patient> pagePatient(List<Patient> patients, int currentPage, int pageSize) {
		int firstIndex = pageSize * currentPage;
		List<Patient> page = new ArrayList<Patient>();
		for (int i = firstIndex; i < (currentPage + 1) * pageSize; i++) {
			if (i < patients.size())
				page.add(patients.get(i));
		}
		return page;
	}

	@RequestMapping(method = {RequestMethod.GET})
	public String showSearchBox(@RequestParam("view") String view, @RequestParam("resultBoxId") String resultBoxId, Model model) {
		model.addAttribute("view", view);
		model.addAttribute("resultBoxId", resultBoxId);
		return "/module/hospitalcore/patientSearch/searchBox";
	}

	private List<Patient> select(List<Patient> patients, Matcher matcher) {
		List<Patient> result = new ArrayList<Patient>();
		for (Patient patient : patients) {
			if (matcher.matches(patient))
				result.add(patient);
		}
		return result;
	}

	private Integer getInt(String value) {
		try {
			Integer number = Integer.valueOf(Integer.parseInt(value));
			return number;
		} catch (Exception e) {
			return Integer.valueOf(0);
		}
	}
}
