/**
 *  Copyright 2010 Health Information Systems Project of India
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
