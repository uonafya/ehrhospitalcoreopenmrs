package org.openmrs.module.hospitalcore.task;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.MonthlySummaryReport;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                performMonthlySummary();

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
        //create the monthly summary object with the corresponding values
        MonthlySummaryReport monthlySummaryReport = new MonthlySummaryReport();
        monthlySummaryReport.setTotalSales(getSalesTotalsPerDay(billingService));
        monthlySummaryReport.setIpCash(0);
        monthlySummaryReport.setMaternity(0);
        monthlySummaryReport.setXray(0);
        monthlySummaryReport.setLab(0);
        monthlySummaryReport.setTheatre(0);
        monthlySummaryReport.setMortuary(0);
        monthlySummaryReport.setOpTreatment(0);
        monthlySummaryReport.setPharmacy(0);
        monthlySummaryReport.setMedicalExam(0);
        monthlySummaryReport.setMedicalReportsIncludingP3(0);
        monthlySummaryReport.setDental(0);
        monthlySummaryReport.setPhysioTherapy(0);
        monthlySummaryReport.setOccupationalTherapy(0);
        monthlySummaryReport.setMedicalRecordsCardsAndFiles(0);
        monthlySummaryReport.setBookingFees(0);
        monthlySummaryReport.setRentalServices(0);
        monthlySummaryReport.setAmbulance(0);
        monthlySummaryReport.setPublicHealthServices(0);
        monthlySummaryReport.setEntAndOtherClinics(0);
        monthlySummaryReport.setOther(0);
        monthlySummaryReport.setCashReceiptsCashFromDailyServices(0);
        monthlySummaryReport.setCashReceiptNhifReceipt(0);
        monthlySummaryReport.setCashReceiptOtherDebtorsReceipt(0);
        monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidNhifPatients(0);
        monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidOtherDebtors(0);
        monthlySummaryReport.setRevenueNotCollectedPatientNotYetPaidWaivers(0);
        monthlySummaryReport.setRevenueNotCollectedWriteOffsExemptions(0);
        monthlySummaryReport.setRevenueNotCollectedWriteOffsAbsconders(0);
        monthlySummaryReport.setTransactionDate(new Date());

        //Save the object in the database after, to persist for future use
        billingService.saveMonthlySummaryReport(monthlySummaryReport);
    }

    Integer getSalesTotalsPerDay(BillingService billingService) {
        int totals = 0;
        List<PatientServiceBill> patientServiceBillList = billingService.getAllPatientServiceBillByDate(null, null);
        //loop through all that were done today and save to the database
        for(PatientServiceBill patientServiceBill : patientServiceBillList) {
            totals = totals +  patientServiceBill.getActualAmount().intValue();
        }

        return totals;
    }
}
