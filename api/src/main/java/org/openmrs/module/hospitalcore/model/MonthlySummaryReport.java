package org.openmrs.module.hospitalcore.model;

import java.util.Date;

public class MonthlySummaryReport {

    private Integer monthlySummaryReportId;
    private Integer totalSales;
    private Integer ipCash;

    public Integer getMonthlySummaryReportId() {
        return monthlySummaryReportId;
    }

    public void setMonthlySummaryReportId(Integer monthlySummaryReportId) {
        this.monthlySummaryReportId = monthlySummaryReportId;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getIpCash() {
        return ipCash;
    }

    public void setIpCash(Integer ipCash) {
        this.ipCash = ipCash;
    }

    public Integer getOpTreatment() {
        return opTreatment;
    }

    public void setOpTreatment(Integer opTreatment) {
        this.opTreatment = opTreatment;
    }

    public Integer getMedicalExam() {
        return medicalExam;
    }

    public void setMedicalExam(Integer medicalExam) {
        this.medicalExam = medicalExam;
    }

    public Integer getMedicalReportsIncludingP3() {
        return medicalReportsIncludingP3;
    }

    public void setMedicalReportsIncludingP3(Integer medicalReportsIncludingP3) {
        this.medicalReportsIncludingP3 = medicalReportsIncludingP3;
    }

    public Integer getPhysioTherapy() {
        return physioTherapy;
    }

    public void setPhysioTherapy(Integer physioTherapy) {
        this.physioTherapy = physioTherapy;
    }

    public Integer getOccupationalTherapy() {
        return occupationalTherapy;
    }

    public void setOccupationalTherapy(Integer occupationalTherapy) {
        this.occupationalTherapy = occupationalTherapy;
    }

    public Integer getMedicalRecordsCardsAndFiles() {
        return medicalRecordsCardsAndFiles;
    }

    public void setMedicalRecordsCardsAndFiles(Integer medicalRecordsCardsAndFiles) {
        this.medicalRecordsCardsAndFiles = medicalRecordsCardsAndFiles;
    }

    public Integer getBookingFees() {
        return bookingFees;
    }

    public void setBookingFees(Integer bookingFees) {
        this.bookingFees = bookingFees;
    }

    public Integer getRentalServices() {
        return rentalServices;
    }

    public void setRentalServices(Integer rentalServices) {
        this.rentalServices = rentalServices;
    }

    public Integer getPublicHealthServices() {
        return publicHealthServices;
    }

    public void setPublicHealthServices(Integer publicHealthServices) {
        this.publicHealthServices = publicHealthServices;
    }

    public Integer getEntAndOtherClinics() {
        return entAndOtherClinics;
    }

    public void setEntAndOtherClinics(Integer entAndOtherClinics) {
        this.entAndOtherClinics = entAndOtherClinics;
    }

    public Integer getCashReceiptsCashFromDailyServices() {
        return cashReceiptsCashFromDailyServices;
    }

    public void setCashReceiptsCashFromDailyServices(Integer cashReceiptsCashFromDailyServices) {
        this.cashReceiptsCashFromDailyServices = cashReceiptsCashFromDailyServices;
    }

    public Integer getCashReceiptNhifReceipt() {
        return cashReceiptNhifReceipt;
    }

    public void setCashReceiptNhifReceipt(Integer cashReceiptNhifReceipt) {
        this.cashReceiptNhifReceipt = cashReceiptNhifReceipt;
    }

    public Integer getCashReceiptOtherDebtorsReceipt() {
        return cashReceiptOtherDebtorsReceipt;
    }

    public void setCashReceiptOtherDebtorsReceipt(Integer cashReceiptOtherDebtorsReceipt) {
        this.cashReceiptOtherDebtorsReceipt = cashReceiptOtherDebtorsReceipt;
    }

    public Integer getRevenueNotCollectedPatientNotYetPaidNhifPatients() {
        return revenueNotCollectedPatientNotYetPaidNhifPatients;
    }

    public void setRevenueNotCollectedPatientNotYetPaidNhifPatients(Integer revenueNotCollectedPatientNotYetPaidNhifPatients) {
        this.revenueNotCollectedPatientNotYetPaidNhifPatients = revenueNotCollectedPatientNotYetPaidNhifPatients;
    }

    public Integer getRevenueNotCollectedPatientNotYetPaidOtherDebtors() {
        return revenueNotCollectedPatientNotYetPaidOtherDebtors;
    }

    public void setRevenueNotCollectedPatientNotYetPaidOtherDebtors(Integer revenueNotCollectedPatientNotYetPaidOtherDebtors) {
        this.revenueNotCollectedPatientNotYetPaidOtherDebtors = revenueNotCollectedPatientNotYetPaidOtherDebtors;
    }

    public Integer getRevenueNotCollectedPatientNotYetPaidWaivers() {
        return revenueNotCollectedPatientNotYetPaidWaivers;
    }

    public void setRevenueNotCollectedPatientNotYetPaidWaivers(Integer revenueNotCollectedPatientNotYetPaidWaivers) {
        this.revenueNotCollectedPatientNotYetPaidWaivers = revenueNotCollectedPatientNotYetPaidWaivers;
    }

    public Integer getRevenueNotCollectedWriteOffsExemptions() {
        return revenueNotCollectedWriteOffsExemptions;
    }

    public void setRevenueNotCollectedWriteOffsExemptions(Integer revenueNotCollectedWriteOffsExemptions) {
        this.revenueNotCollectedWriteOffsExemptions = revenueNotCollectedWriteOffsExemptions;
    }

    public Integer getRevenueNotCollectedWriteOffsAbsconders() {
        return revenueNotCollectedWriteOffsAbsconders;
    }

    public void setRevenueNotCollectedWriteOffsAbsconders(Integer revenueNotCollectedWriteOffsAbsconders) {
        this.revenueNotCollectedWriteOffsAbsconders = revenueNotCollectedWriteOffsAbsconders;
    }

    private Integer maternity;
    private Integer xray;
    private Integer lab;
    private Integer theatre;
    private Integer mortuary;
    private Integer  opTreatment;
    private Integer pharmacy;
    private Integer medicalExam;
    private Integer medicalReportsIncludingP3;


    public Integer getMaternity() {
        return maternity;
    }

    public void setMaternity(Integer maternity) {
        this.maternity = maternity;
    }

    public Integer getXray() {
        return xray;
    }

    public void setXray(Integer xray) {
        this.xray = xray;
    }

    public Integer getLab() {
        return lab;
    }

    public void setLab(Integer lab) {
        this.lab = lab;
    }

    public Integer getTheatre() {
        return theatre;
    }

    public void setTheatre(Integer theatre) {
        this.theatre = theatre;
    }

    public Integer getMortuary() {
        return mortuary;
    }

    public void setMortuary(Integer mortuary) {
        this.mortuary = mortuary;
    }

    public Integer getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Integer pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Integer getDental() {
        return dental;
    }

    public void setDental(Integer dental) {
        this.dental = dental;
    }



    public Integer getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Integer ambulance) {
        this.ambulance = ambulance;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    private Integer dental;
    private Integer physioTherapy;
    private Integer occupationalTherapy;
    private Integer medicalRecordsCardsAndFiles;
    private Integer bookingFees;
    private Integer rentalServices;
    private Integer ambulance;
    private Integer publicHealthServices;
    private Integer entAndOtherClinics;
    private Integer other;
    private Date transactionDate;

    private Integer cashReceiptsCashFromDailyServices;
    private Integer cashReceiptNhifReceipt;

    private Integer cashReceiptOtherDebtorsReceipt;
    private Integer revenueNotCollectedPatientNotYetPaidNhifPatients;
    private Integer revenueNotCollectedPatientNotYetPaidOtherDebtors;
    private Integer revenueNotCollectedPatientNotYetPaidWaivers;
    private Integer revenueNotCollectedWriteOffsExemptions;
    private Integer revenueNotCollectedWriteOffsAbsconders;
}
