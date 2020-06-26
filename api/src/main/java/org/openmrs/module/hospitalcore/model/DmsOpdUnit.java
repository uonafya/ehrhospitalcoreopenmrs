package org.openmrs.module.hospitalcore.model;

import java.util.Date;
import org.openmrs.Concept;

public class DmsOpdUnit {
    private Integer id;

    private Integer unitNo;

    private Concept opdConceptId;

    private String opdWorkingDay;

    private String startTime;

    private String endTime;

    private Date unitActiveDate;

    private Date unitDeactiveDate;

    private Integer creator;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnitNo() {
        return this.unitNo;
    }

    public void setUnitNo(Integer unitNo) {
        this.unitNo = unitNo;
    }

    public Concept getOpdConceptId() {
        return this.opdConceptId;
    }

    public void setOpdConceptId(Concept opdConceptId) {
        this.opdConceptId = opdConceptId;
    }

    public String getOpdWorkingDay() {
        return this.opdWorkingDay;
    }

    public void setOpdWorkingDay(String opdWorkingDay) {
        this.opdWorkingDay = opdWorkingDay;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getUnitActiveDate() {
        return this.unitActiveDate;
    }

    public void setUnitActiveDate(Date unitActiveDate) {
        this.unitActiveDate = unitActiveDate;
    }

    public Date getUnitDeactiveDate() {
        return this.unitDeactiveDate;
    }

    public void setUnitDeactiveDate(Date unitDeactiveDate) {
        this.unitDeactiveDate = unitDeactiveDate;
    }

    public Integer getCreator() {
        return this.creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
}
