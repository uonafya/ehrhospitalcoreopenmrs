package org.openmrs.module.hospitalcore.model;

public class PatientReferralObject {

    private String referralType;
    private String referralCommunityUnit;
    private String referralCommunityName;
    private String referralFacilityLocation;

    public String getReferralType() {
        return referralType;
    }

    public void setReferralType(String referralType) {
        this.referralType = referralType;
    }

    public String getReferralCommunityUnit() {
        return referralCommunityUnit;
    }

    public void setReferralCommunityUnit(String referralCommunityUnit) {
        this.referralCommunityUnit = referralCommunityUnit;
    }

    public String getReferralCommunityName() {
        return referralCommunityName;
    }

    public void setReferralCommunityName(String referralCommunityName) {
        this.referralCommunityName = referralCommunityName;
    }

    public String getReferralFacilityLocation() {
        return referralFacilityLocation;
    }

    public void setReferralFacilityLocation(String referralFacilityLocation) {
        this.referralFacilityLocation = referralFacilityLocation;
    }

    public String getReferralReason() {
        return referralReason;
    }

    public void setReferralReason(String referralReason) {
        this.referralReason = referralReason;
    }

    public String getReferralNotes() {
        return referralNotes;
    }

    public void setReferralNotes(String referralNotes) {
        this.referralNotes = referralNotes;
    }

    public String getPatientNames() {
        return patientNames;
    }

    public void setPatientNames(String patientNames) {
        this.patientNames = patientNames;
    }

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    private String referralReason;
    private String referralNotes;
    private String patientNames;
    private String patientIdentifier;

    private String creator;

    private String dateCreated;

    private String facilityNameReferredTo;

    public String getFacilityNameReferredTo() {
        return facilityNameReferredTo;
    }

    public void setFacilityNameReferredTo(String facilityNameReferredTo) {
        this.facilityNameReferredTo = facilityNameReferredTo;
    }

    public String getFacilityCodeReferredTo() {
        return facilityCodeReferredTo;
    }

    public void setFacilityCodeReferredTo(String facilityCodeReferredTo) {
        this.facilityCodeReferredTo = facilityCodeReferredTo;
    }

    public String getCommunityUnitCodeReferredTo() {
        return communityUnitCodeReferredTo;
    }

    public void setCommunityUnitCodeReferredTo(String communityUnitCodeReferredTo) {
        this.communityUnitCodeReferredTo = communityUnitCodeReferredTo;
    }

    public String getCommunityUnitNameReferredTo() {
        return communityUnitNameReferredTo;
    }

    public void setCommunityUnitNameReferredTo(String communityUnitNameReferredTo) {
        this.communityUnitNameReferredTo = communityUnitNameReferredTo;
    }

    private String facilityCodeReferredTo;
    private String communityUnitCodeReferredTo;
    private String communityUnitNameReferredTo;
}
