package org.openmrs.module.hospitalcore.web.controller.form;

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
	public String showForm(@RequestParam("id") Integer formId, 
			@RequestParam("mode") String mode,
			@RequestParam(value="encounterId", required=false) Integer encounterId,
			Model model) {
		
		CoreForm form = Context.getService(HospitalCoreService.class).getCoreForm(formId);
		model.addAttribute("mode", mode);
		model.addAttribute("form", form);
		
		if(mode.equalsIgnoreCase("edit")){
			model.addAttribute("encounterId", encounterId);
		}
		return "/module/hospitalcore/form/show";
	}

}
