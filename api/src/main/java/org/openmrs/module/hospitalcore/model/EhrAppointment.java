package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import org.openmrs.Visit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EhrAppointment extends BaseOpenmrsData implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum EhrAppointmentStatusType {
        SCHEDULED, ACTIVE, CANCELLED, MISSED, COMPLETED
    }

    // TODO confirm that "WALK-IN" should be considered active and "RESCHEDULED"
    // should be scheduled
    public enum EhrAppointmentStatus {

        SCHEDULED("Scheduled", EhrAppointmentStatusType.SCHEDULED),
        RESCHEDULED("Rescheduled", EhrAppointmentStatusType.SCHEDULED),
        WALKIN("Walk-In", EhrAppointmentStatusType.ACTIVE),
        WAITING("Waiting", EhrAppointmentStatusType.ACTIVE),
        INCONSULTATION("In-Consultation", EhrAppointmentStatusType.ACTIVE),
        CANCELLED("Cancelled", EhrAppointmentStatusType.CANCELLED),
        CANCELLED_AND_NEEDS_RESCHEDULE("Cancelled and Needs Reschedule", EhrAppointmentStatusType.CANCELLED),
        MISSED("Missed", EhrAppointmentStatusType.MISSED),
        COMPLETED("Completed", EhrAppointmentStatusType.COMPLETED);

        private final String name;

        private final EhrAppointmentStatusType type;

        private EhrAppointmentStatus(final String name,
                                  final EhrAppointmentStatusType type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return this.name;
        }

        public EhrAppointmentStatusType getType() {
            return this.type;
        }

        @Override
        public String toString() {
            return name;
        }

        public static List<EhrAppointmentStatus> getAppointmentsStatusByTypes(
                List<EhrAppointmentStatusType> appointmentStatusTypes) {
            List<EhrAppointmentStatus> appointmentStatuses = new ArrayList<EhrAppointmentStatus>();

            for (EhrAppointmentStatus appointmentStatus : EhrAppointmentStatus
                    .values()) {
                if (appointmentStatusTypes
                        .contains(appointmentStatus.getType())) {
                    appointmentStatuses.add(appointmentStatus);
                }
            }

            return appointmentStatuses;
        }

        public static List<EhrAppointmentStatus> getAppointmentsStatusByType(
                EhrAppointmentStatusType appointmentStatusType) {
            return getAppointmentsStatusByTypes(Collections
                    .singletonList(appointmentStatusType));
        }

        public static List<EhrAppointmentStatus> getNotCancelledAppointmentStatuses() {
            return getAppointmentsStatusByTypes(Arrays.asList(
                    EhrAppointmentStatusType.ACTIVE,
                    EhrAppointmentStatusType.COMPLETED,
                    EhrAppointmentStatusType.MISSED,
                    EhrAppointmentStatusType.SCHEDULED));
        }

    }

    private Integer appointmentId;

    private EhrTimeSlot timeSlot;

    private Visit visit;

    private Patient patient;

    private EhrAppointmentStatus status;

    private String reason;

    private String cancelReason;

    private EhrAppointmentType appointmentType;

    public EhrAppointment() {

    }

    public EhrAppointment(Integer appointmentId) {
        setId(appointmentId);
    }

    public EhrAppointment(EhrTimeSlot timeSlot, Visit visit, Patient patient,
                       EhrAppointmentType appointmentType, EhrAppointmentStatus status) {
        setTimeSlot(timeSlot);
        setVisit(visit);
        setPatient(patient);
        setStatus(status);
        setAppointmentType(appointmentType);
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @see org.openmrs.OpenmrsObject#getId()
     */
    @Override
    public Integer getId() {
        return getAppointmentId();
    }

    /**
     * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
     */
    @Override
    public void setId(Integer id) {
        setAppointmentId(id);
    }

    public EhrTimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(EhrTimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public EhrAppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(EhrAppointmentStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public EhrAppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(EhrAppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }
}
