package org.openmrs.module.hospitalcore;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.appointments.model.Appointment;
import org.openmrs.module.hospitalcore.exception.EhrTimeSlotFullException;
import org.openmrs.module.hospitalcore.model.EhrAppointment;
import org.openmrs.module.hospitalcore.model.EhrAppointmentBlock;
import org.openmrs.module.hospitalcore.model.EhrAppointmentDailyCount;
import org.openmrs.module.hospitalcore.model.EhrAppointmentRequest;
import org.openmrs.module.hospitalcore.model.EhrAppointmentStatusHistory;
import org.openmrs.module.hospitalcore.model.EhrAppointmentType;
import org.openmrs.module.hospitalcore.model.EhrProviderSchedule;
import org.openmrs.module.hospitalcore.model.EhrTimeSlot;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EhrAppointmentService extends OpenmrsService {

        /**
         * Gets all appointment types.
         *
         * @return a list of appointment type objects.
         * <strong>Should</strong> get all appointment types
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_TYPES)
        Set<EhrAppointmentType> getAllEhrAppointmentTypes();

        /**
         * Get all appointment types based on includeRetired flag
         *
         * @param includeRetired
         * @return List of all appointment types
         * <strong>Should</strong> get all appointment types based on include retired flag.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_TYPES)
        List<EhrAppointmentType> getAllEhrAppointmentTypes(boolean includeRetired);

        /**
         * Gets an appointment type by its appointment type id.
         *
         * @param EhrAppointmentTypeId the appointment type id.
         * @return the appointment type object found with the given id, else null.
         * <strong>Should</strong> get correct appointment type
         */
       // @Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_TYPES)
        EhrAppointmentType getEhrAppointmentType(Integer EhrAppointmentTypeId);

        /**
         * Gets an appointment type by its UUID.
         *
         * @param uuid the appointment type UUID.
         * @return the appointment type object found with the given uuid, else null.
         * <strong>Should</strong> get correct appointment type
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_TYPES)
        EhrAppointmentType getEhrAppointmentTypeByUuid(String uuid);

        /**
         * Gets all appointment types (including retired) whose names are similar to or contain the
         * given search phrase.
         *
         * @param fuzzySearchPhrase the search phrase to use.
         * @return a list of all appointment types with names similar to or containing the given phrase
         * <strong>Should</strong> get correct appointment types
         * <strong>Should</strong> include retired appointment types
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_TYPES)
        List<EhrAppointmentType> getEhrAppointmentTypes(String fuzzySearchPhrase);

        /**
         * Gets all appointment types whose names are similar to or contain the given search phrase.
         *
         * @param fuzzySearchPhrase the search phrase to use.
         * @param includeRetired whether or not to include retired types
         * @return a list of all appointment types with names similar to or containing the given phrase
         * <strong>Should</strong> get correct appointment types
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_TYPES)
        List<EhrAppointmentType> getEhrAppointmentTypes(String fuzzySearchPhrase,
        boolean includeRetired);

        /**
         * Creates or updates the given appointment type in the database.
         *
         * @param EhrAppointmentType the appointment type to create or update.
         * @return the created or updated appointment type.
         * <strong>Should</strong> save new appointment type
         * <strong>Should</strong> save edited appointment type
         * <strong>Should</strong> save confidential appointment type
         * <strong>Should</strong> throw error when name is null
         * <strong>Should</strong> throw error when name is empty string
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_TYPES)
        EhrAppointmentType saveEhrAppointmentType(EhrAppointmentType EhrAppointmentType)
			throws APIException;

        /**
         * Retires a given appointment type.
         *
         * @param EhrAppointmentType the appointment type to retire.
         * @param reason the reason why the appointment type is retired.
         * @return the appointment type that has been retired.
         * <strong>Should</strong> retire given appointment type
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_TYPES)
        EhrAppointmentType retireEhrAppointmentType(EhrAppointmentType EhrAppointmentType,
                String reason);

        /**
         * Unretires an appointment type.
         *
         * @param EhrAppointmentType the appointment type to unretire.
         * @return the unretired appointment type
         * <strong>Should</strong> unretire given appointment type
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_TYPES)
        EhrAppointmentType unretireEhrAppointmentType(EhrAppointmentType EhrAppointmentType);

        /**
         * Completely removes an appointment type from the database. This is not reversible.
         *
         * @param EhrAppointmentType the appointment type to delete from the database.
         * <strong>Should</strong> delete given appointment type
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_TYPES)
        void purgeEhrAppointmentType(EhrAppointmentType EhrAppointmentType);

        // EhrAppointmentBlock
        /**
         * Gets all appointment blocks.
         *
         * @return a list of appointment block objects.
         * <strong>Should</strong> get all appointment blocks
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrAppointmentBlock> getAllEhrAppointmentBlocks();

        /**
         * Get all appointment blocks based on includeVoided flag
         *
         * @param includeVoided
         * @return List of all appointment blocks
         * <strong>Should</strong> get all appointment blocks based on include voided flag.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrAppointmentBlock> getAllEhrAppointmentBlocks(boolean includeVoided);

        /**
         * Gets an appointment block by its appointment block id.
         *
         * @param EhrAppointmentBlockId the appointment block id.
         * @return the appointment block object found with the given id, else null.
         * <strong>Should</strong> get correct appointment block
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        EhrAppointmentBlock getEhrAppointmentBlock(Integer EhrAppointmentBlockId);

        /**
         * Gets an appointment block by its UUID.
         *
         * @param uuid the appointment block UUID.
         * @return the appointment block object found with the given uuid, else null.
         * <strong>Should</strong> get correct appointment block
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        EhrAppointmentBlock getEhrAppointmentBlockByUuid(String uuid);

        /**
         * Creates or updates the given appointment block in the database.
         *
         * @param EhrAppointmentBlock the appointment block to create or update.
         * @return the created or updated appointment block.
         * <strong>Should</strong> save new appointment block
         * <strong>Should</strong> save a providerless appointment block
         * <strong>Should</strong> save edited appointment block
         * <strong>Should</strong> throw error when name is null
         * <strong>Should</strong> throw error when name is empty string
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_BLOCKS)
        EhrAppointmentBlock saveEhrAppointmentBlock(EhrAppointmentBlock EhrAppointmentBlock)
			throws APIException;

        /**
         * Voids a given appointment block.
         *
         * @param EhrAppointmentBlock the appointment block to void.
         * @param reason the reason why the appointment block is voided.
         * @return the appointment block that has been voided.
         * <strong>Should</strong> void given appointment block
         * <strong>Should</strong> void all associated time slots
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_BLOCKS)
        EhrAppointmentBlock voidEhrAppointmentBlock(EhrAppointmentBlock EhrAppointmentBlock,
                String reason);

        /**
         * Unvoids an appointment block.
         *
         * @param EhrAppointmentBlock the appointment block to unvoid.
         * @return the unvoided appointment block
         * <strong>Should</strong> unvoided given appointment block
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_BLOCKS)
        EhrAppointmentBlock unvoidEhrAppointmentBlock(EhrAppointmentBlock EhrAppointmentBlock);

        /**
         * Completely removes an appointment block from the database. This is not reversible.
         *
         * @param EhrAppointmentBlock the appointment block to delete from the database.
         * <strong>Should</strong> delete given appointment block
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_BLOCKS)
        void purgeEhrAppointmentBlock(EhrAppointmentBlock EhrAppointmentBlock);

        /**
         * Gets appointment blocks which have a given date, location, provider and list of appointment
         * types
         *
         * @return a list of appointment block objects.
         * <strong>Should</strong> get all appointment blocks which have contains in a given date interval and
         *         corresponds to a given locations, provider and appointment types.
         * <strong>Should</strong> not return voided appointment blocks
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrAppointmentBlock> getEhrAppointmentBlocksByTypes(Date fromDate,
                                                           Date toDate, String locations, Provider provider,
                                                           List<EhrAppointmentType> EhrAppointmentTypes);

        /**
         * Gets appointment blocks which have a given date and location.
         *
         * @return a list of appointment block objects.
         * <strong>Should</strong> get all appointment blocks which have contains in a given date interval and
         *         corresponds to a given locations, provider and appointment type.
         * <strong>Should</strong> not return voided appointment blocks
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrAppointmentBlock> getEhrAppointmentBlocks(Date fromDate, Date toDate,
                String locations, Provider provider, EhrAppointmentType EhrAppointmentType);

        /**
         * Gets appointment blocks which overlap to the given appointment block
         *
         * @return a list of appointment block objects.
         * <strong>Should</strong> get all appointment blocks which overlap to the given appointment block
         * <strong>Should</strong> allow overlapping providerless appointment blocks
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrAppointmentBlock> getOverlappingEhrAppointmentBlocks(
                EhrAppointmentBlock EhrAppointmentBlock);

        // Appointment
        /**
         * Gets all appointments.
         *
         * @return a list of appointment objects.
         * <strong>Should</strong> get all appointment
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getAllEhrAppointments();

        /**
         * Get all appointments based on includeVoided flag
         *
         * @param includeVoided
         * @return List of all appointments
         * <strong>Should</strong> get all appointments based on include voided flag.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        public List<EhrAppointment> getAllEhrAppointments(boolean includeVoided);

        /**
         * Gets an appointment by its appointment id.
         *
         * @param appointmentId the appointment id.
         * @return the appointment object found with the given id, else null.
         * <strong>Should</strong> get correct appointment
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        EhrAppointment getEhrAppointment(Integer appointmentId);

        /**
         * Gets an appointment by its UUID.
         *
         * @param uuid the appointment UUID.
         * @return the appointment object found with the given uuid, else null.
         * <strong>Should</strong> get correct appointment
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        EhrAppointment getEhrAppointmentByUuid(String uuid);

        /**
         * Creates or updates the given appointment in the database.
         *
         * @param appointment the appointment to create or update.
         * @return the created or updated appointment.
         * <strong>Should</strong> save new appointment
         * <strong>Should</strong> save edited appointment
         */
        //@Authorized(EhrAppointmentUtils.PRIV_SCHEDULE_APPOINTMENTS)
        EhrAppointment saveEhrAppointment(EhrAppointment appointment) throws APIException;

        /**
         * Voids a given appointment.
         *
         * @param appointment the appointment to void.
         * @param reason the reason why the appointment is voided.
         * @return the appointment that has been voided.
         * <strong>Should</strong> void given appointment
         */
        //@Authorized(EhrAppointmentUtils.PRIV_SCHEDULE_APPOINTMENTS)
        EhrAppointment voidEhrAppointment(EhrAppointment appointment, String reason);

        /**
         * Unvoids an appointment.
         *
         * @param appointment the appointment to unvoid.
         * @return the unvoid appointment
         * <strong>Should</strong> unvoid given appointment
         */
        //@Authorized(EhrAppointmentUtils.PRIV_SCHEDULE_APPOINTMENTS)
        EhrAppointment unvoidEhrAppointment(EhrAppointment appointment);

        /**
         * Completely removes an appointment from the database. This is not reversible.
         *
         * @param appointment the appointment to delete from the database.
         * <strong>Should</strong> delete given appointment
         */
        //@Authorized(EhrAppointmentUtils.PRIV_SCHEDULE_APPOINTMENTS)
        void purgeEhrAppointment(EhrAppointment appointment);

        /**
         * Returns all Appointments for a given Patient
         *
         * @param patient the patient id to search by.
         * @return all the appointments for the given patient id.
         * <strong>Should</strong> return all of the appointments for the given patient.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEhrAppointmentsOfPatient(Patient patient);

        /**
         * Returns the appointment corresponding to the given visit.
         *
         * @param visit the visit id to search by.
         * @return the appointment that is related to this visit, null if there isnt any.
         */
       //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        EhrAppointment getEhrAppointmentByVisit(Visit visit);

        // EhrTimeSlot

        /**
         * Gets all time slots.
         *
         * @return a list of time slot objects.
         * <strong>Should</strong> get all time slots
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrTimeSlot> getAllEhrTimeSlots();

        /**
         * Get all time slots based on includeVoided flag
         *
         * @param includeVoided
         * @return List of all time slots
         * <strong>Should</strong> get all time slots based on include voided flag.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        public List<EhrTimeSlot> getAllEhrTimeSlots(boolean includeVoided);

        /**
         * Creates or updates the given time slot in the database.
         *
         * @param EhrTimeSlot the time slot to create or update.
         * @return the created or updated time slot.
         * <strong>Should</strong> save new time slot
         * <strong>Should</strong> save edited time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_BLOCKS)
        EhrTimeSlot saveEhrTimeSlot(EhrTimeSlot EhrTimeSlot) throws APIException;

        /**
         * Gets a a time slot by its id.
         *
         * @param EhrTimeSlotId the time slot id.
         * @return the time slot object found with the given id, else null.
         * <strong>Should</strong> get correct time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        EhrTimeSlot getEhrTimeSlot(Integer EhrTimeSlotId);

        /**
         * Gets a time slot by its UUID.
         *
         * @param uuid the time slot UUID.
         * @return the time slot object found with the given uuid, else null.
         * <strong>Should</strong> get correct time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        EhrTimeSlot getEhrTimeSlotByUuid(String uuid);

        /**
         * Voids a given time slot.
         *
         * @param EhrTimeSlot the time slot to void.
         * @param reason the reason why the time slot is voided.
         * @return the time slot that has been voided.
         * <strong>Should</strong> void given time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_BLOCKS)
        EhrTimeSlot voidEhrTimeSlot(EhrTimeSlot EhrTimeSlot, String reason);

        /**
         * Unvoids a time slot.
         *
         * @param EhrTimeSlot the time slot to unvoid.
         * @return the unvoided time slot
         * <strong>Should</strong> unvoid given time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_BLOCKS)
        EhrTimeSlot unvoidEhrTimeSlot(EhrTimeSlot EhrTimeSlot);

        /**
         * Completely removes a time slot from the database. This is not reversible.
         *
         * @param EhrTimeSlot the time slot to delete from the database.
         * <strong>Should</strong> delete given time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_APPOINTMENT_BLOCKS)
        void purgeEhrTimeSlot(EhrTimeSlot EhrTimeSlot);

        /**
         * Should retrieve all appointments in the given time slot.
         *
         * @param EhrTimeSlot the time slot to search by.
         * @return the appointments in the given time slot.
         * <strong>Should</strong> not return voided appointments
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEhrAppointmentsInTimeSlot(EhrTimeSlot EhrTimeSlot);

        /**
         * Should retrieve all appointments in the given time slot that do not have a status that means
         * the appointment has been cancelled (ie status=CANCELLED, CANCELLED_AND_NEEDS_RESCHEDULE)
         *
         * @param EhrTimeSlot the time slot to search by.
         * @return the appointments in the given time slo
         * <strong>Should</strong> not return missed, cancelled, and needs_reschedule appointments.
         * <strong>Should</strong> not return voided appointments
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEhrAppointmentsInTimeSlotThatAreNotCancelled(
                EhrTimeSlot EhrTimeSlot);

        /**
         * Gets a count of the number of appointments in a time slot
         *
         * @param EhrTimeSlot the time slot to search by.
         * @return the count of appointments in the given time slot
         * <strong>Should</strong> not count voided appointments
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        Integer getCountOfEhrAppointmentsInTimeSlot(EhrTimeSlot EhrTimeSlot);

        /**
         * Gets a count of all appointments in the given time slot that do not have a status that means
         * the appointment has been cancelled (ie status=CANCELLED, CANCELLED_AND_NEEDS_RESCHEDULE)
         *
         * @param EhrTimeSlot the time slot to search by.
         * @return the count of appointments in the given time slot
         * <strong>Should</strong> not count missed, cancelled and needs rescheduled appointments.
         * <strong>Should</strong> not count voided appointments
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        Integer getCountOfEhrAppointmentsInTimeSlotThatAreNotCancelled(
                EhrTimeSlot EhrTimeSlot);

        /**
         * Should retrieve all time slots in the given appointment block.
         *
         * @param EhrAppointmentBlock - the appointment block to search by.
         * @return the time slots in the given appointment block.
         * <strong>Should</strong> not return voided time slots
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrTimeSlot> getEhrTimeSlotsInAppointmentBlock(
                EhrAppointmentBlock EhrAppointmentBlock);

        // Appointment Status History
        /**
         * Gets all appointment status histories.
         *
         * @return a list of appointment status history objects.
         * <strong>Should</strong> get all appointment status histories
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointmentStatusHistory> getAllEhrAppointmentStatusHistories();

        /**
         * Gets an appointment status by its appointment status history id.
         *
         * @param EhrAppointmentStatusHistoryId the appointment status history id.
         * @return the appointment status history object found with the given id, else null.
         * <strong>Should</strong> get correct appointment status history
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        EhrAppointmentStatusHistory getEhrAppointmentStatusHistory(
                Integer EhrAppointmentStatusHistoryId);

        /**
         * Gets all appointment status histories whose statuses are similar to or contain the given
         * status.
         *
         * @param status the search phrase to use.
         * @return a list of all appointment status histories with names identical to or containing the
         *         given status
         * <strong>Should</strong> get correct appointment status histories
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointmentStatusHistory> getEhrAppointmentStatusHistories(
                EhrAppointment.EhrAppointmentStatus status);

        /**
         * Creates or updates the given appointment status history in the database.
         *
         * @param EhrAppointmentStatusHistory the appointment status history to create or update.
         * @return the created or updated appointment status history.
         * <strong>Should</strong> save new appointment status history
         * <strong>Should</strong> save edited appointment status history
         */
        //@Authorized(EhrAppointmentUtils.PRIV_SCHEDULE_APPOINTMENTS)
        EhrAppointmentStatusHistory saveEhrAppointmentStatusHistory(
                EhrAppointmentStatusHistory EhrAppointmentStatusHistory)
			throws APIException;

        /**
         * Gets all appointments requests
         *
         * @return a list of appointment requests objects.
         * <strong>Should</strong> get all appointment requests
         */
        //@Authorized(EhrAppointmentUtils.PRIV_REQUEST_APPOINTMENTS)
        List<EhrAppointmentRequest> getAllEhrAppointmentRequests();

        /**
         * Get all appointments requests based on includeVoided flag
         *
         * @param includeVoided
         * @return List of all appointment requests
         * <strong>Should</strong> get all appointment request based on include voided flag.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_REQUEST_APPOINTMENTS)
        public List<EhrAppointmentRequest> getAllEhrAppointmentRequests(boolean includeVoided);

        /**
         * Gets an appointment requests by its id.
         *
         * @param EhrAppointmentRequestId the appointment request id.
         * @return the appointment request object found with the given id, else null.
         * <strong>Should</strong> get correct appointment request
         */
        //@Authorized(EhrAppointmentUtils.PRIV_REQUEST_APPOINTMENTS)
        EhrAppointmentRequest getEhrAppointmentRequest(Integer EhrAppointmentRequestId);

        /**
         * Gets an appointment request by its UUID.
         *
         * @param uuid the appointment request UUID.
         * @return the appointment request object found with the given uuid, else null.
         * <strong>Should</strong> get correct appointment request
         */
        //@Authorized(EhrAppointmentUtils.PRIV_REQUEST_APPOINTMENTS)
        EhrAppointmentRequest getEhrAppointmentRequestByUuid(String uuid);

        /**
         * Retrieves Appointments Requests that satisfy the given constraints
         *
         * @param patient - The patient
         * @param type - The appointment type
         * @param provider - The requested provider
         * @param status - The appointment request status
         * @return a list of appointment requests that satisfy the given constraints
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointmentRequest> getEhrAppointmentRequestsByConstraints(Patient patient, EhrAppointmentType type, Provider provider,
                                                                     EhrAppointmentRequest.EhrAppointmentRequestStatus status) throws APIException;

        /**
         * Creates or updates the given appointment requests in the database.
         *
         * @param EhrAppointmentRequest the appointment request to create or update.
         * @return the created or updated appointment request.
         * <strong>Should</strong> save new appointment request
         * <strong>Should</strong> save edited appointment request
         */
        //@Authorized(EhrAppointmentUtils.PRIV_REQUEST_APPOINTMENTS)
        EhrAppointmentRequest saveEhrAppointmentRequest(EhrAppointmentRequest EhrAppointmentRequest) throws APIException;

        /**
         * Voids a given appointment request
         *
         * @param EhrAppointmentRequest the appointment request to void.
         * @param reason the reason why the appointment request is voided.
         * @return the appointment request that has been voided.
         * <strong>Should</strong> void given appointment request
         */
        //@Authorized(EhrAppointmentUtils.PRIV_REQUEST_APPOINTMENTS)
        EhrAppointmentRequest voidEhrAppointmentRequest(EhrAppointmentRequest EhrAppointmentRequest, String reason);

        /**
         * Unvoids an appointment request
         *
         * @param EhrAppointmentRequest the appointment request to unvoid.
         * @return the unvoid appointment request
         * <strong>Should</strong> unvoid given appointment request
         */
        //@Authorized(EhrAppointmentUtils.PRIV_REQUEST_APPOINTMENTS)
        EhrAppointmentRequest unvoidEhrAppointmentRequest(EhrAppointmentRequest EhrAppointmentRequest);

        /**
         * Completely removes an appointment request from the database. This is not reversible.
         *
         * @param EhrAppointmentRequest the appointment request to delete from the database.
         * <strong>Should</strong> delete given appointment request
         */
        //@Authorized(EhrAppointmentUtils.PRIV_REQUEST_APPOINTMENTS)
        void purgeEhrAppointmentRequest(EhrAppointmentRequest EhrAppointmentRequest);

        /**
         * Retrieves the most recent appointment for a given patient.
         *
         * @param patient the patient for which we are retrieving.
         * @return The most recent appointment for the given patient, null if no appointments were set.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        EhrAppointment getLastEhrAppointment(Patient patient);

        /**
         * Return a list of time slots that stands within the given constraints.
         *
         * @param EhrAppointmentType - Type of the appointment
         * @param fromDate - (optional) earliest start date. (defaults to current date)
         * @param toDate - (optional) latest start date.
         * @param provider - (optional) the appointment's provider.
         * @param location - (optional) the appointment's location. (or predecessor location)
         * @return List of EhrTimeSlots that stands within the given constraints, null if illegal values
         *         (fromDate>toDate or null EhrAppointmentType)
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrTimeSlot> getEhrTimeSlotsByConstraints(EhrAppointmentType EhrAppointmentType,
                Date fromDate, Date toDate, Provider provider, Location location)
			throws APIException;

        /**
         * Return a list of time slots that stands within the given constraints.
         *
         * @param EhrAppointmentType - Type of the appointment
         * @param fromDate - (optional) earliest start date. (defaults to current date)
         * @param toDate - (optional) latest start date.
         * @param provider - (optional) the appointment's provider.
         * @param location - (optional) the appointment's location. (or predecessor location)
         * @param excludeEhrTimeSlotsWithPatient (optional) will exclude all time slots that have an existing appointment for
         *                                    this patient for the selected service type that doesn't have a status type
         *                                    of CANCELLED (to prevent allow duplicate patient appointments for the same service and time)
         * @return List of EhrTimeSlots that stands within the given constraints, null if illegal values
         *         (fromDate>toDate or null EhrAppointmentType)
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrTimeSlot> getEhrTimeSlotsByConstraints(EhrAppointmentType EhrAppointmentType,
                Date fromDate, Date toDate, Provider provider, Location location,
                Patient excludeEhrTimeSlotsWithPatient)
            throws APIException;

        /**
         * Return a list of time slots that stands within the given constraints.
         *
         * @param EhrAppointmentType - Type of the appointment
         * @param fromDate - (optional) earliest start date.
         * @param toDate - (optional) latest start date.
         * @param provider - (optional) the appointment's provider.
         * @param location - (optional) the appointment's location. (or predecessor location)
         * @return List of EhrTimeSlots that stands within the given constraints, null if illegal values
         *         (fromDate>toDate or null EhrAppointmentType)
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrTimeSlot> getEhrTimeSlotsByConstraintsIncludingFull(
                EhrAppointmentType EhrAppointmentType, Date fromDate, Date toDate,
                Provider provider, Location location) throws APIException;

        /**
         * Return a list of time slots that stands within the given constraints.
         *
         * @param EhrAppointmentType - Type of the appointment
         * @param fromDate - (optional) earliest start date.
         * @param toDate - (optional) latest start date.
         * @param provider - (optional) the appointment's provider.
         * @param location - (optional) the appointment's location. (or predecessor location)
         * @param excludeEhrTimeSlotsWithPatient optional) will exclude all time slots that have an existing appointment for
         *                                    this patient for the selected service type that doesn't have a status type
         *                                    of CANCELLED (to prevent allow duplicate patient appointments for the same service and time)
         * @return List of EhrTimeSlots that stands within the given constraints, null if illegal values
         *         (fromDate>toDate or null EhrAppointmentType)
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        List<EhrTimeSlot> getEhrTimeSlotsByConstraintsIncludingFull(
                EhrAppointmentType EhrAppointmentType, Date fromDate, Date toDate,
                Provider provider, Location location, Patient excludeEhrTimeSlotsWithPatient) throws APIException;

        /**
         * Returns a list of strings, where each string represents an identifier of the given patient
         * and its value. The preferred identifier will be the first in the list. The format of each
         * string will be: "<identifier name>: <identifier value>" for example:
         * "Old Identification Number: 2142"
         *
         * @param patient the patient.
         * @return a list of strings where each string represents an identifier of the patient.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<String> getPatientIdentifiersRepresentation(Patient patient);

        /**
         * Returns the amount of minutes left in a given time slot.
         *
         * @param EhrTimeSlot the given time slot.
         * @return The amount of minutes left in the given time slot. Returns null if the given time
         *         slot was null;
         * <strong>Should</strong> ignore appointments with statuses that reflect a "cancelled" appointment
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_BLOCKS)
        Integer getEhrTimeLeftInTimeSlot(EhrTimeSlot EhrTimeSlot);

        /**
         * [Utility Method] Returns all the descendants of a given location recursively. Call with null
         * descendants.
         *
         * @param location the location that is ancestor to all of the location in the returned set.
         * @param descendants the result set which is being built recursively.
         * @return A set that contains all of the descendants of the given location.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        Set<Location> getAllLocationDescendants(Location location,
                                                Set<Location> descendants);

        /**
         * Retrieves Appointments that satisfy the given constraints
         *
         * @param fromDate - The appointment start date
         * @param toDate - The appointment end date
         * @param location - The appointment location
         * @param provider - The appointment provider
         * @param type - The appointment type
         * @param status - The appointment status
         * @return a list of appointments that satisfy the given constraints
         * <strong>Should</strong> sort by associated time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate, Date toDate,
                Location location, Provider provider, EhrAppointmentType type,
                EhrAppointment.EhrAppointmentStatus status) throws APIException;

        /**
         * Retrieves Appointments that satisfy the given constraints
         *
         * @param fromDate - The appointment start date
         * @param toDate - The appointment end date
         * @param location - The appointment location
         * @param provider - The appointment provider
         * @param type - The appointment type
         * @param status - The appointment status
         * @param patient - The patient
         * @return a list of appointments that satisfy the given constraints
         * <strong>Should</strong> sort by associated time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate, Date toDate,
                Location location, Provider provider, EhrAppointmentType type,
                Patient patient, EhrAppointment.EhrAppointmentStatus status) throws APIException;

        /**
         * Retrieves Appointments that satisfy the given constraints
         *
         * @param fromDate - The appointment start date
         * @param toDate - The appointment end date
         * @param location - The appointment location
         * @param provider - The appointment provider
         * @param type - The appointment type
         * @param patient - The patient
         * @param appointmentStatuses - The appointment status list
         * @return a list of appointments that satisfy the given constraints
         * <strong>Should</strong> sort by associated time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate, Date toDate,
                Location location, Provider provider, EhrAppointmentType type,
                Patient patient, List<EhrAppointment.EhrAppointmentStatus> appointmentStatuses);

        /**
         * Retrieves Appointments that satisfy the given constraints
         *
         * @param fromDate - The appointment start date
         * @param toDate - The appointment end date
         * @param location - The appointment location
         * @param provider - The appointment provider
         * @param type - The appointment type
         * @param patient - The patient
         * @param appointmentStatuses - The appointment status list
         * @param visitType - The visit type of the appointment
         * @param visit - The appointment visit
         * @return a list of appointments that satisfy the given constraints
         * <strong>Should</strong> sort by associated time slot
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate, Date toDate,
                                                       Location location, Provider provider, EhrAppointmentType type,
                                                       Patient patient, List<EhrAppointment.EhrAppointmentStatus> appointmentStatuses,
                                                       VisitType visitType, Visit visit) throws APIException;
        /**
         * Retrives the start date of the current status of a given appointment.
         *
         * @param appointment - The appointment.
         * @return the start date of the current status of a given appointment.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        Date getEhrAppointmentCurrentStatusStartDate(EhrAppointment appointment);

        /**
         * Changes the given appointment status.
         *
         * @param appointment - The appointment
         * @param newStatus - The new status
         */
        //@Authorized(EhrAppointmentUtils.PRIV_SCHEDULE_APPOINTMENTS)
        void changeEhrAppointmentStatus(EhrAppointment appointment,
                EhrAppointment.EhrAppointmentStatus newStatus);

        /**
         * Computes the average duration (in Minutes) of a status history by appointment type
         *
         * @param fromDate The lower bound of the date interval.
         * @param endDate The upper bound of the date interval.
         * @param status The AppointmentStatus status to filter histories by.
         * @return A map of EhrAppointmentType,Average duration pairs.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS_STATISTICS)
        Map<EhrAppointmentType, Double> getAverageEhrHistoryDurationByConditions(
                Date fromDate, Date endDate, EhrAppointment.EhrAppointmentStatus status);

        /**
         * Computes the average duration (in Minutes) of a status history by provider
         *
         * @param fromDate The lower bound of the date interval.
         * @param endDate The upper bound of the date interval.
         * @param status The AppointmentStatus status to filter histories by.
         * @return A map of Provider,Average duration pairs.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS_STATISTICS)
        public Map<Provider, Double> getAverageEhrHistoryDurationByConditionsPerProvider(
                Date fromDate, Date endDate, EhrAppointment.EhrAppointmentStatus status);

        /**
         * Retrieves the amount of status history objects in the given criteria
         *
         * @param fromDate The lower bound of the date interval.
         * @param endDate The upper bound of the date interval.
         * @param status The AppointmentStatus status to filter histories by.
         * @return The amount of status history objects in the given criteria
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS_STATISTICS)
        Integer getEhrHistoryCountByConditions(Date fromDate, Date endDate,
                EhrAppointment.EhrAppointmentStatus status);

        /**
         * Retrieves the distribution of appointment types in the given appointments dates range.
         *
         * @param fromDate The lower bound of the date range.
         * @param toDate The upper bound of the date range.
         * @return Map of <EhrAppointmentType,Integer> that reflects the appointment types distribution in
         *         the given range.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS_STATISTICS)
        public Map<EhrAppointmentType, Integer> getEhrAppointmentTypeDistribution(
                Date fromDate, Date toDate);

        // Utility Methods

        /**
         * [Utility Method] Retrieves all providers sorted ascending alphabetically
         *
         * @param includeRetired whether to include retired providers
         * @return sorted list of providers
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<Provider> getAllEhrProvidersSorted(boolean includeRetired);

        /**
         * [Utility Method] Retrieves all appointment types sorted ascending alphabetically
         *
         * @param includeRetired whether to include retired appointment types
         * @return sorted list of appointment types
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENT_TYPES)
        List<EhrAppointmentType> getAllEhrAppointmentTypesSorted(boolean includeRetired);

        /**
         * Retrieves list of unvoided appointments that their current status is one of the given states.
         *
         * @param states List of states to retrieve by.
         * @return list of unvoided appointments that their current status is one of the given states.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEhrAppointmentsByStatus(List<EhrAppointment.EhrAppointmentStatus> states);

        /**
         * Update the status of PAST appointments according to the following conditions: "SCHEDULED"
         * will be updated to "MISSED" "WAITING" or "WALKIN" will be updated to "MISSED"
         * "INCONSULTATION" will be updated to "COMPLETED"
         *
         * @return List of the updated appointments
         */
        //@Authorized(EhrAppointmentUtils.PRIV_SCHEDULE_APPOINTMENTS)
        List<EhrAppointment> cleanOpenEhrAppointments();

        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        boolean verifyDuplicatedEhrAppointmentTypeName(EhrAppointmentType EhrAppointmentType);

        /**
         * Retrieves the list of scheduled (appts in states SCHEDULED or RESCHEDULED for a patient)
         *
         * @param patient
         * @return
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getScheduledEhrAppointmentsForPatient(Patient patient);

        List<EhrAppointment> getScheduledEhrAppointmentsForPatients() throws APIException;

        /**
         * Books a new appointment
         *
         * @param appointment
         * @param allowOverbook
         * @return The newly-created appointment
         * <strong>Should</strong> throw exception if this appointment has already been persisted
         * <strong>Should</strong> throw exception if not enough available time in time slot and
         *         allowOverbook = false
         */
       // @Authorized(EhrAppointmentUtils.PRIV_SCHEDULE_APPOINTMENTS)
        EhrAppointment bookEhrAppointment(EhrAppointment appointment, Boolean allowOverbook)
			throws EhrTimeSlotFullException;

        /**
         * retrieves all the statuses of an appointment
         * @param appointment
         * @return
         */
        //@Authorized
        List<EhrAppointmentStatusHistory> getEhrAppointmentStatusHistories(EhrAppointment appointment);

        /**
         * retrieves the most recent status of an appointment
         * @param appointment
         * @return
         */
        //@Authorized()
        EhrAppointmentStatusHistory getMostRecentEhrAppointmentStatusHistory(EhrAppointment appointment);

        EhrTimeSlot getEhrTimeslotForAppointment(Location location, Provider provider, EhrAppointmentType type, Date appointmentDate);

        /**
         * returns list of early appointments
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getEarlyEhrAppointments(Date fromDate, Date toDate,  Location location,
                Provider provider, EhrAppointmentType EhrAppointmentType) throws APIException;

        /**
         * returns list of late appointments
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointment> getLateEhrAppointments(Date fromDate, Date toDate,  Location location,
                Provider provider, EhrAppointmentType EhrAppointmentType) throws APIException;
        /** returns list of appointments aggregated by date
         * @param fromDate
         * @param toDate
         * @param location
         * @param provider
         * @param status
         * @return
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_APPOINTMENTS)
        List<EhrAppointmentDailyCount> getEhrAppointmentDailyCount(String fromDate, String toDate, Location location,
                                                                Provider provider, EhrAppointment.EhrAppointmentStatus status) throws APIException;

        // EhrProviderSchedule

        /**
         * Gets all provider schedule.
         *
         * @return a list of provider schedule objects.
         * <strong>Should</strong> get all provider schedule
         */
        //@Authorized()
        List<EhrProviderSchedule> getAllEhrProviderSchedules();

        /**
         * Get all provider schedule based on includeVoided flag
         *
         * @param includeVoided
         * @return List of all provider schedule
         * <strong>Should</strong> get all provider schedule based on include voided flag.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_PROVIDER_SCHEDULES)
        List<EhrProviderSchedule> getAllEhrProviderSchedules(boolean includeVoided);

        /**
         * Gets an provider schedule by its provider schedule id.
         *
         * @param EhrProviderScheduleId the provider schedule id.
         * @return the provider schedule object found with the given id, else null.
         * <strong>Should</strong> get correct provider schedule
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_PROVIDER_SCHEDULES)
        EhrProviderSchedule getEhrProviderSchedule(Integer EhrProviderScheduleId);

        /**
         * Gets an provider schedule by its UUID.
         *
         * @param uuid the provider schedule UUID.
         * @return the provider schedule object found with the given uuid, else null.
         * <strong>Should</strong> get correct provider schedule
         */
        //@Authorized()
        EhrProviderSchedule getEhrProviderScheduleByUuid(String uuid);

        /**
         * Creates or updates the given provider schedule in the database.
         *
         * @param EhrProviderSchedule the provider schedule to create or update.
         * @return the created or updated provider schedule.
         * <strong>Should</strong> save new provider schedule
         * <strong>Should</strong> save a providerless provider schedule
         * <strong>Should</strong> save edited provider schedule
         * <strong>Should</strong> throw error when name is null
         * <strong>Should</strong> throw error when name is empty string
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_PROVIDER_SCHEDULES)
        EhrProviderSchedule saveEhrProviderSchedule(EhrProviderSchedule EhrProviderSchedule)
			throws APIException;

        /**
         * Voids a given provider schedule.
         *
         * @param EhrProviderSchedule the provider schedule to void.
         * @param reason              the reason why the provider schedule is voided.
         * @return the provider schedule that has been voided.
         * <strong>Should</strong> void given provider schedule
         * <strong>Should</strong> void all associated time slots
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_PROVIDER_SCHEDULES)
        EhrProviderSchedule voidEhrProviderSchedule(EhrProviderSchedule EhrProviderSchedule,
                String reason);

        /**
         * Completely removes an provider schedule from the database. This is not reversible.
         *
         * @param EhrProviderSchedule the provider schedule to delete from the database.
         * <strong>Should</strong> delete given provider schedule
         */
        //@Authorized(EhrAppointmentUtils.PRIV_MANAGE_PROVIDER_SCHEDULES)
        void purgeEhrProviderSchedule(EhrProviderSchedule EhrProviderSchedule);

        /**
         * Gets provider schedule which have a given date, location, provider and list of appointment
         * types
         *
         * @return a list of provider schedule objects.
         */
        //@Authorized(EhrAppointmentUtils.PRIV_VIEW_PROVIDER_SCHEDULES)
        List<EhrProviderSchedule> getEhrProviderSchedulesByConstraints(Location locations, Provider provider, List<EhrAppointmentType> EhrAppointmentTypes);

        //@Authorized
        EhrTimeSlot createEhrTimeSlotUsingProviderSchedule(Date appointmentDate, Provider provider, Location location);

        List<Appointment> getEhrAppointmentsByProvider(Provider provider) throws APIException;

}
