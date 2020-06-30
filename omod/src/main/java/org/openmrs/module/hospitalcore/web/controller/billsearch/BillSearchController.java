package org.openmrs.module.hospitalcore.web.controller.billsearch;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreSearchBillController")
@RequestMapping({"/module/hospitalcore/findBill.htm"})
public class BillSearchController {
	@RequestMapping(method = {RequestMethod.GET})
	public String searchBill(@RequestParam("billId") String billId, Model model) {
		BillingService bs = (BillingService)Context.getService(BillingService.class);
		int receiptId = 0;
		try {
			receiptId = Integer.parseInt(billId);
		} catch (NumberFormatException e) {
			model.addAttribute("Found", "Cannot find bill");
			return "redirect:/module/billing/main.form";
		}
		PatientServiceBill patientServiceBill = bs.getPatientServiceBillByReceiptId(Integer.valueOf(receiptId));
		if (null != patientServiceBill)
			return "redirect:/module/billing/patientServiceBillForBD.list?patientId=" + patientServiceBill.getPatient().getId() + "&billId=" + patientServiceBill.getPatientServiceBillId();
		model.addAttribute("Found", "Cannot find bill");
		return "redirect:/module/billing/main.form";
	}
}
