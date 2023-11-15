package org.openmrs.module.hospitalcore.model;

import org.openmrs.Concept;
import org.openmrs.User;

import java.io.Serializable;
import java.util.Date;

public class EhrReferralComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ehrReferralComponentId;

    private String referralType;
    private String referralCommunityUnit;

    public Integer getEhrReferralComponentId() {
        return ehrReferralComponentId;
    }

    public void setEhrReferralComponentId(Integer ehrReferralComponentId) {
        this.ehrReferralComponentId = ehrReferralComponentId;
    }

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

    public Concept getReferralReason() {
        return referralReason;
    }

    public void setReferralReason(Concept referralReason) {
        this.referralReason = referralReason;
    }

    public String getReferralNotes() {
        return referralNotes;
    }

    public void setReferralNotes(String referralNotes) {
        this.referralNotes = referralNotes;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getCreatorBy() {
        return creatorBy;
    }

    public void setCreatorBy(User creatorBy) {
        this.creatorBy = creatorBy;
    }

    private String referralCommunityName;
    private String referralFacilityLocation;
    private Concept referralReason;
   private String  referralNotes;
   private Date createdOn;
   private User creatorBy;
}
