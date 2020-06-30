package org.openmrs.module.hospitalcore.web.controller.form;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreGetConceptInfoController")
@RequestMapping({"/module/hospitalcore/getConceptInfo.form"})
public class GetConceptInfoController {
	@RequestMapping(method = {RequestMethod.GET})
	public String getInfo(@RequestParam("name") String name, Model model) {
		Concept concept = Context.getConceptService().getConcept(name);
		model.addAttribute("conceptDescription", concept.getDescription().getDescription());
		model.addAttribute("conceptDatatype", concept.getDatatype().getName());
		return "/module/hospitalcore/form/getConceptInfo";
	}
}
