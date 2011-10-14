/**
 *  Copyright 2010 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Hospital-core module.
 *
 *  Hospital-core module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Hospital-core module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Hospital-core module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.hospitalcore.web.controller.form;

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
@RequestMapping("/module/hospitalcore/showForm.form")
public class ShowFormController {

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(
			@RequestParam("formId") Integer formId,
			@RequestParam("mode") String mode,
			@RequestParam(value = "encounterId", required = false) Integer encounterId,
			@RequestParam(value = "redirect", required = false) String redirect,
			Model model) {

		CoreForm form = Context.getService(HospitalCoreService.class)
				.getCoreForm(formId);
		model.addAttribute("mode", mode);
		model.addAttribute("form", form);

		if (mode.equalsIgnoreCase("edit")) {
			model.addAttribute("encounterId", encounterId);
			model.addAttribute("redirect", redirect);
		}
		return "/module/hospitalcore/form/show";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveForm(
			HttpServletRequest request,
			@RequestParam("encounterId") Integer encounterId,
			@RequestParam(value = "redirect", required = false) String redirect,
			Model model) {

		Map<String, String> parameters = buildParameterList(request);
		Encounter encounter = Context.getEncounterService().getEncounter(
				encounterId);
		if (encounter != null) {
			for (String key : parameters.keySet()) {
				Concept concept = Context.getConceptService().getConcept(key);
				Obs obs = insertValue(encounter, concept, parameters.get(key));
				if (obs.getId() == null)
					encounter.addObs(obs);
			}
			Context.getEncounterService().saveEncounter(encounter);
			model.addAttribute("status", "success");
			return "/module/radiology/form/enterForm";
		}
		if (!StringUtils.isBlank(redirect))
			return redirect;
		else {
			return "/module/hospitalcore/form/enterForm";
		}
	}

	@SuppressWarnings("rawtypes")
	private Map<String, String> buildParameterList(HttpServletRequest request) {
		Map<String, String> parameters = new HashMap<String, String>();
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			String parameterName = (String) e.nextElement();
			if (!parameterName.equalsIgnoreCase("id"))
				if (!parameterName.equalsIgnoreCase("mode"))
					if (!parameterName.equalsIgnoreCase("encounterId"))
						if (!parameterName.equalsIgnoreCase("redirectUrl"))
							parameters.put(parameterName,
									request.getParameter(parameterName));

		}
		return parameters;
	}
	
	private Obs insertValue(Encounter encounter, Concept concept, String value) {

		Obs obs = getObs(encounter, concept);
		obs.setConcept(concept);
		if (concept.getDatatype().getName().equalsIgnoreCase("Text")) {
			value = value.replace("\n", "\\n");
			obs.setValueText(value);
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Numeric")) {
			obs.setValueNumeric(new Double(value));
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
