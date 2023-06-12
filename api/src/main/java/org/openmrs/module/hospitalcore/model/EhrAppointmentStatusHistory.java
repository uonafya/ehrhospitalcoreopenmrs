/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;

import java.util.Date;

public class EhrAppointmentStatusHistory extends BaseOpenmrsData {

    private static final long serialVersionUID = 1L;

    private Integer appointmentStatusHistoryId;

    private EhrAppointment appointment;

    private EhrAppointment.EhrAppointmentStatus status;

    private Date startDate;

    private Date endDate;

    public EhrAppointmentStatusHistory() {

    }

    public EhrAppointmentStatusHistory(EhrAppointment appointment, EhrAppointment.EhrAppointmentStatus status, Date startDate, Date endDate) {
        setAppointment(appointment);
        setStatus(status);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Integer getAppointmentStatusHistoryId() {
        return appointmentStatusHistoryId;
    }

    public void setAppointmentStatusHistoryId(Integer appointmentStatusHistoryId) {
        this.appointmentStatusHistoryId = appointmentStatusHistoryId;
    }

    /**
     * @see org.openmrs.OpenmrsObject#getId()
     */
    @Override
    public Integer getId() {
        return getAppointmentStatusHistoryId();
    }

    /**
     * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
     */
    @Override
    public void setId(Integer id) {
        setAppointmentStatusHistoryId(id);
    }

    public EhrAppointment.EhrAppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(EhrAppointment.EhrAppointmentStatus status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public EhrAppointment getAppointment() {
        return appointment;
    }

    public void setAppointment(EhrAppointment appointment) {
        this.appointment = appointment;
    }
}