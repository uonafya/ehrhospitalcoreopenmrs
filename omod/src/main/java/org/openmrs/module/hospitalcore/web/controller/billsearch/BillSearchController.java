/**
 *  Copyright 2012 Society for Health Information Systems Programmes, India (HISP India)
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

package org.openmrs.module.hospitalcore.web.controller.billsearch;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * Author: Thai Chuong June 10th 2012
 * Search bill by Bill Id
 * Supported issue #247 & #254
 */
@Controller("HospitalcoreSearchBillController")
@RequestMapping("/module/hospitalcore/findBill.htm")
public class BillSearchController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String searchBill(@RequestParam("billId") String billId, Model model) {
		BillingService bs = Context.getService(BillingService.class);
		PatientServiceBill patienServiceBill = bs.getPatientServiceBillById(Integer.parseInt(billId));
		
		if (null != patienServiceBill)
			return "redirect:/module/billing/patientServiceBill.list?patientId=" + patienServiceBill.getPatient().getId()
			        + "&billId=" + billId;
		
		model.addAttribute("Found", "Cannot find bill");
		return "redirect:/module/billing/main.form";
	}
}
