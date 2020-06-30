package org.openmrs.module.hospitalcore.web.controller.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreShowFormController")
@RequestMapping({"/module/hospitalcore/showForm.form"})
public class ShowFormController {
	@RequestMapping(method = {RequestMethod.GET})
	public String showForm(@RequestParam("formId") Integer formId, @RequestParam("mode") String mode, @RequestParam(value = "encounterId", required = false) Integer encounterId, @RequestParam(value = "redirect", required = false) String redirect, Model model) {
		CoreForm form = ((HospitalCoreService)Context.getService(HospitalCoreService.class)).getCoreForm(formId);
		model.addAttribute("mode", mode);
		model.addAttribute("form", form);
		if (mode.equalsIgnoreCase("edit")) {
			model.addAttribute("encounterId", encounterId);
			model.addAttribute("redirect", redirect);
		}
		if (encounterId != null) {
			StringBuilder builder = new StringBuilder();
			Encounter encounter = Context.getEncounterService().getEncounter(encounterId);
			for (Obs obs : encounter.getAllObs())
				builder.append(obs.getConcept().getName().getName() + "==" + getObsValue(obs) + "||");
			model.addAttribute("values", builder.toString());
		}
		return "/module/hospitalcore/form/show";
	}

	private static String getObsValue(Obs obs) {
		Concept concept = obs.getConcept();
		if (concept.getDatatype().getName().equalsIgnoreCase("Text"))
			return obs.getValueText();
		if (concept.getDatatype().getName().equalsIgnoreCase("Numeric"))
			return obs.getValueNumeric().toString();
		if (concept.getDatatype().getName().equalsIgnoreCase("Coded"))
			return obs.getValueCoded().getName().getName();
		if (concept.getDatatype().getName().equalsIgnoreCase("Datetime")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(obs.getValueDatetime());
		}
		return null;
	}

	@RequestMapping(method = {RequestMethod.POST})
	public String saveForm(HttpServletRequest request, @RequestParam("encounterId") Integer encounterId, @RequestParam(value = "redirect", required = false) String redirect, Model model) throws ParseException {
		Map<String, String> parameters = buildParameterList(request);
		Encounter encounter = Context.getEncounterService().getEncounter(encounterId);
		if (encounter != null) {
			for (String key : parameters.keySet()) {
				Concept concept = Context.getConceptService().getConcept(key);
				Obs obs = insertValue(encounter, concept, parameters.get(key));
				if (obs.getId() == null)
					encounter.addObs(obs);
			}
			Context.getEncounterService().saveEncounter(encounter);
			model.addAttribute("status", "success");
		}
		if (!StringUtils.isBlank(redirect))
			return redirect;
		return "/module/hospitalcore/form/enterForm";
	}

	private Map<String, String> buildParameterList(HttpServletRequest request) {
		Map<String, String> parameters = new HashMap<String, String>();
		for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements(); ) {
			String parameterName = e.nextElement();
			if (!parameterName.equalsIgnoreCase("id") &&
					!parameterName.equalsIgnoreCase("mode") &&
					!parameterName.equalsIgnoreCase("encounterId") &&
					!parameterName.equalsIgnoreCase("redirect"))
				parameters.put(parameterName, request.getParameter(parameterName));
		}
		return parameters;
	}

	private Obs insertValue(Encounter encounter, Concept concept, String value) throws ParseException {
		Obs obs = getObs(encounter, concept);
		obs.setConcept(concept);
		if (concept.getDatatype().getName().equalsIgnoreCase("Text")) {
			value = value.replace("\n", "\\n");
			obs.setValueText(value);
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Numeric")) {
			obs.setValueNumeric(new Double(value));
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Datetime")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			obs.setValueDatetime(sdf.parse(value));
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Coded")) {
			Concept answerConcept = Context.getConceptService().getConcept(value);
			obs.setValueCoded(answerConcept);
		}
		return obs;
	}

	private Obs getObs(Encounter encounter, Concept concept) {
		for (Obs obs : encounter.getAllObs()) {
			if (obs.getConcept().equals(concept))
				return obs;
		}
		return new Obs();
	}
}
