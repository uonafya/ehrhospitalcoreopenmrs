package org.openmrs.module.hospitalcore.task;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;
import org.openmrs.module.hospitalcore.model.MonthlySummaryReport;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.module.hospitalcore.util.DateUtils;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UpdateMonthlyTransactions extends AbstractTask {

    private static final Logger log = LoggerFactory.getLogger(UpdateMonthlyTransactions.class);

    @Override
    public void execute() {

        if (!isExecuting) {
            if (log.isDebugEnabled()) {
                log.debug("Updating the Monthly summary  service table");
            }

            startExecuting();
            try {
                //do all the work here
                Date today30MinutesBeforeMidNight = DateUtils.getDateTime(new Date(), 23, 30, 0, 0);
                Date todayEndOfDay = DateUtils.getEndOfDay(new Date());
                Date dateAndTimeNow = new Date();
                if(dateAndTimeNow.compareTo(today30MinutesBeforeMidNight) > 0 && dateAndTimeNow.compareTo(todayEndOfDay) < 0) {
                    performMonthlySummary();
                }

            }
            catch (Exception e) {
                log.error("Error while updating Monthly summary  service table ", e);
            }
            finally {
                stopExecuting();
            }
        }

    }
    private void performMonthlySummary() {
        BillingService billingService = Context.getService(BillingService.class);
        //get the last entry for the summary
        MonthlySummaryReport lastMonthlySummary = billingService.getLatestTransactionDate();
        MonthlySummaryReport monthlySummaryReport = null;
        Date todaysDate = DateUtils.getStartOfDay(new Date());

            //run the summary slightly after 11PM EAT, just run when the current time is past 11PM and less than 12 AM
            if (lastMonthlySummary != null) {
                Date lastTransactionDate = lastMonthlySummary.getTransactionDate();
                if (DateUtils.getStartOfDay(lastTransactionDate).compareTo(DateUtils.getStartOfDay(new Date())) < 0) {
                    monthlySummaryReport = new MonthlySummaryReport();
                    monthlySummaryReport.setTotalSales(getTotalCummulativeSalesForTheDay(billingService));
                    monthlySummaryReport.setIpCash(0);
                    monthlySummaryReport.setMaternity(0);
                    monthlySummaryReport.setXray(getDepartmentTotalsOnDateRange("Radiology"));
                    monthlySummaryReport.setLab(getDepartmentTotalsOnDateRange("Laboratory"));
                    monthlySummaryReport.setTheatre(0);
                    monthlySummaryReport.setMortuary(0);
                    monthlySummaryReport.setOpTreatment(getDepartmentTotalsOnDateRange("Procedure"));
                    monthlySummaryReport.setPharmacy(getPharmacySales());
                    monthlySummaryReport.setMedicalExam(getCumulativeProcedureTotals(getMedicalExaminationConcepts()));
                    monthlySummaryReport.setMedicalReportsIncludingP3(0);
                    monthlySummaryReport.setDental(getCumulativeProcedureTotals(getDentalConcepts()));
                    monthlySummaryReport.setPhysioTherapy(getCumulativeProcedureTotals(getPhysioTherapyConcepts()));
                    monthlySummaryReport.setOccupationalTherapy(getCumulativeProcedureTotals(getOccupationalTherapyConcepts()));
                    monthlySummaryReport.setMedicalRecordsCardsAndFiles(0);
                    monthlySummaryReport.setBookingFees(0);
                    monthlySummaryReport.setRentalServices(0);
                    monthlySummaryReport.setAmbulance(0);
                    monthlySummaryReport.setPublicHealthServices(0);
                    monthlySummaryReport.setEntAndOtherClinics(getCumulativeProcedureTotals(getEntAndOtherClinicConcepts()));
                    monthlySummaryReport.setOther(0);
                    monthlySummaryReport.setCashReceiptsCashFromDailyServices(0);
                    monthlySummaryReport.setCashReceiptNhifReceipt(0);
                    monthlySummaryReport.setCashReceiptOtherDebtorsReceipt(0);
                    monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidNhifPatients(0);
                    monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidOtherDebtors(0);
                    monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidWaivers(0);
                    monthlySummaryReport.setRevenueNotCollectedWriteOffsExemptions(0);
                    monthlySummaryReport.setRevenueNotCollectedWriteOffsAbsconders(0);
                    monthlySummaryReport.setTransactionDate(todaysDate);
                }
            } else {
                //create the monthly summary object with the corresponding values
                monthlySummaryReport = new MonthlySummaryReport();
                monthlySummaryReport.setTotalSales(getTotalCummulativeSalesForTheDay(billingService));
                monthlySummaryReport.setIpCash(0);
                monthlySummaryReport.setMaternity(0);
                monthlySummaryReport.setXray(getDepartmentTotalsOnDateRange("Radiology"));
                monthlySummaryReport.setLab(getDepartmentTotalsOnDateRange("Laboratory"));
                monthlySummaryReport.setTheatre(0);
                monthlySummaryReport.setMortuary(0);
                monthlySummaryReport.setOpTreatment(getDepartmentTotalsOnDateRange("Procedure"));
                monthlySummaryReport.setPharmacy(getPharmacySales());
                monthlySummaryReport.setMedicalExam(getCumulativeProcedureTotals(getMedicalExaminationConcepts()));
                monthlySummaryReport.setMedicalReportsIncludingP3(0);
                monthlySummaryReport.setDental(getCumulativeProcedureTotals(getDentalConcepts()));
                monthlySummaryReport.setPhysioTherapy(getCumulativeProcedureTotals(getPhysioTherapyConcepts()));
                monthlySummaryReport.setOccupationalTherapy(getCumulativeProcedureTotals(getOccupationalTherapyConcepts()));
                monthlySummaryReport.setMedicalRecordsCardsAndFiles(0);
                monthlySummaryReport.setBookingFees(0);
                monthlySummaryReport.setRentalServices(0);
                monthlySummaryReport.setAmbulance(0);
                monthlySummaryReport.setPublicHealthServices(0);
                monthlySummaryReport.setEntAndOtherClinics(getCumulativeProcedureTotals(getEntAndOtherClinicConcepts()));
                monthlySummaryReport.setOther(0);
                monthlySummaryReport.setCashReceiptsCashFromDailyServices(0);
                monthlySummaryReport.setCashReceiptNhifReceipt(0);
                monthlySummaryReport.setCashReceiptOtherDebtorsReceipt(0);
                monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidNhifPatients(0);
                monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidOtherDebtors(0);
                monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidWaivers(0);
                monthlySummaryReport.setRevenueNotCollectedWriteOffsExemptions(0);
                monthlySummaryReport.setRevenueNotCollectedWriteOffsAbsconders(0);
                monthlySummaryReport.setTransactionDate(todaysDate);
            }

        //Save the object in the database after, to persist for future use
        if(monthlySummaryReport != null) {
            billingService.saveMonthlySummaryReport(monthlySummaryReport);
        }
    }

    private Integer getSalesTotalsPerDay(BillingService billingService) {
        int totals = 0;
        List<PatientServiceBill> patientServiceBillList = billingService.getAllPatientServiceBillByDate(null, null);
        //loop through all that were done today and save to the database
        for(PatientServiceBill patientServiceBill : patientServiceBillList) {
            totals = totals +  patientServiceBill.getActualAmount().intValue();
        }

        return totals;
    }

    private Integer getPharmacySales() {
        InventoryCommonService inventoryCommonService = Context.getService(InventoryCommonService.class);
        List<InventoryStoreDrugPatient> inventoryStoreDrugPatientList = inventoryCommonService.getAllIssueByDateRange(
                null, null);
        int cummulativeSumPerPatient = 0;
        if (!inventoryStoreDrugPatientList.isEmpty()) {
            for (InventoryStoreDrugPatient isdp : inventoryStoreDrugPatientList) {
                if (isdp.getStatuss() != null && isdp.getStatuss() == 1) {

                    List<InventoryStoreDrugPatientDetail> inventoryStoreDrugPatientDetailList = new ArrayList<InventoryStoreDrugPatientDetail>(
                            inventoryCommonService.getDrugDetailOfPatient(isdp));
                    int productOfSum = 0;

                    for (InventoryStoreDrugPatientDetail inventoryStoreDrugPatientDetail : inventoryStoreDrugPatientDetailList) {
                        productOfSum = inventoryStoreDrugPatientDetail.getQuantity()
                                * inventoryStoreDrugPatientDetail.getTransactionDetail().getCostToPatient().intValue();
                        cummulativeSumPerPatient = cummulativeSumPerPatient + productOfSum;
                    }
                }
            }
        }
        return cummulativeSumPerPatient;
    }

    private Integer getTotalCummulativeSalesForTheDay(BillingService billingService) {
        return getSalesTotalsPerDay(billingService) + getPharmacySales();
    }

    private Integer getDepartmentTotalsOnDateRange(String ehrDepartment) {
        int totals = 0;
        HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
        List<PatientServiceBillItem> getBilledItemsPerDepartment = hospitalCoreService.getPatientServiceBillByDepartment(
                hospitalCoreService.getDepartmentByName(ehrDepartment), null, null);
        if (getBilledItemsPerDepartment.size() > 0) {
            for (PatientServiceBillItem patientServiceBillItem : getBilledItemsPerDepartment) {
                if (patientServiceBillItem.getActualAmount() != null) {
                    totals = totals +  patientServiceBillItem.getActualAmount().intValue();
                }
            }
        }
        return totals;
    }

    private Integer getCumulativeProcedureTotals(List<Concept> listOfProcedures){
        int totals = 0;
        List<PatientServiceBillItem> getTestsAndProcedures = Context.getService(HospitalCoreService.class).getBillableItemsBasedOnListOfItemsPassed(listOfProcedures);
        for(PatientServiceBillItem patientServiceBillItem: getTestsAndProcedures) {
            if(patientServiceBillItem.getActualAmount() != null) {
                totals = totals + patientServiceBillItem.getActualAmount().intValue();
            }
        }


       return totals;
    }

    private List<Concept> getMedicalExaminationConcepts() {
        ConceptService conceptService = Context.getConceptService();
        return Arrays.asList(
                conceptService.getConceptByUuid("432AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                conceptService.getConceptByUuid("367a3526-4a8b-4704-a686-a746e74032f3")
                );
    }

    private List<Concept> getDentalConcepts() {
        ConceptService conceptService = Context.getConceptService();
        List<Concept> allowedConcepts = new ArrayList<Concept>();
        allowedConcepts.add(conceptService.getConceptByUuid("30aff715-92de-4662-aa33-fa6b6179fed0"));
        Concept dentalOverallConcept = conceptService.getConceptByUuid("a59d59b9-f77f-4de0-bdb7-a942284718f2");
        for(ConceptAnswer parts:dentalOverallConcept.getAnswers()) {
            allowedConcepts.add(parts.getAnswerConcept());
        }
        return allowedConcepts;
    }

    private List<Concept> getOccupationalTherapyConcepts() {
        ConceptService conceptService = Context.getConceptService();
        return Arrays.asList(
                conceptService.getConceptByUuid("c2f85aed-695f-49b1-973e-002c37cbdebf")
        );
    }

    private List<Concept> getPhysioTherapyConcepts() {
        ConceptService conceptService = Context.getConceptService();
        return Arrays.asList(
                conceptService.getConceptByUuid("66b6e478-8f00-4344-adb6-851f23c4a5a7")
        );
    }

    private List<Concept> getEntAndOtherClinicConcepts() {
        ConceptService conceptService = Context.getConceptService();
        List<Concept> allowedConcepts = new ArrayList<Concept>();
        Concept specialClinicConcept = conceptService.getConceptByUuid("b5e0cfd3-1009-4527-8e36-83b5e902b3ea");
        Concept dentalOverallConcept = conceptService.getConceptByUuid("30aff715-92de-4662-aa33-fa6b6179fed0");
        Concept occupationalOverallConcept = conceptService.getConceptByUuid("c2f85aed-695f-49b1-973e-002c37cbdebf");
        Concept physioOverallConcept = conceptService.getConceptByUuid("66b6e478-8f00-4344-adb6-851f23c4a5a7");
        for(ConceptAnswer parts:specialClinicConcept.getAnswers()) {
            allowedConcepts.add(parts.getAnswerConcept());
        }
        allowedConcepts.remove(dentalOverallConcept);
        allowedConcepts.remove(occupationalOverallConcept);
        allowedConcepts.remove(physioOverallConcept);
        return allowedConcepts;
    }

}
