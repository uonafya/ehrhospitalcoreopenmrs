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

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.web.controller.form.validation.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreValidateFormController")
@RequestMapping("/module/hospitalcore/validateForm.form")
public class ValidateFormController {

	@RequestMapping(method = RequestMethod.GET)
	public String validate(@RequestParam("conceptName") String conceptName,
			@RequestParam("value") String value, Model model) {
		Concept concept = Context.getConceptService().getConcept(conceptName);

		String qualifiedName = "org.openmrs.module.hospitalcore.web.controller.form.validation."
				+ concept.getDatatype().getName();
		String message = null;
		try {
			Validator validator = (Validator) Class.forName(qualifiedName)
					.newInstance();
			if (!validator.validate(value)) {
				message = validator.getErrorMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("message", message);
		return null;
	}
}
