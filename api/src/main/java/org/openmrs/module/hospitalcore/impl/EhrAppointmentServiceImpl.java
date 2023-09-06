package org.openmrs.module.hospitalcore.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.Provider;
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.appointments.model.Appointment;
import org.openmrs.module.hospitalcore.EhrAppointmentService;
import org.openmrs.module.hospitalcore.db.EhrAppointmentBlockDAO;
import org.openmrs.module.hospitalcore.db.EhrAppointmentDAO;
import org.openmrs.module.hospitalcore.db.EhrAppointmentRequestDAO;
import org.openmrs.module.hospitalcore.db.EhrAppointmentStatusHistoryDAO;
import org.openmrs.module.hospitalcore.db.EhrAppointmentTypeDAO;
import org.openmrs.module.hospitalcore.db.EhrProviderScheduleDAO;
import org.openmrs.module.hospitalcore.db.EhrTimeSlotDAO;
import org.openmrs.module.hospitalcore.exception.EhrTimeSlotFullException;
import org.openmrs.module.hospitalcore.model.EhrAppointment;
import org.openmrs.module.hospitalcore.model.EhrAppointmentBlock;
import org.openmrs.module.hospitalcore.model.EhrAppointmentDailyCount;
import org.openmrs.module.hospitalcore.model.EhrAppointmentRequest;
import org.openmrs.module.hospitalcore.model.EhrAppointmentStatusHistory;
import org.openmrs.module.hospitalcore.model.EhrAppointmentType;
import org.openmrs.module.hospitalcore.model.EhrProviderSchedule;
import org.openmrs.module.hospitalcore.model.EhrStudentT;
import org.openmrs.module.hospitalcore.model.EhrTimeSlot;
import org.openmrs.validator.ValidateUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EhrAppointmentServiceImpl extends BaseOpenmrsService implements EhrAppointmentService {

    protected final Log log = LogFactory.getLog(this.getClass());

    private EhrAppointmentTypeDAO ehrAppointmentTypeDAO;

    private EhrAppointmentBlockDAO ehrAppointmentBlockDAO;

    private EhrAppointmentDAO ehrAppointmentDAO;

    private EhrTimeSlotDAO ehrTimeSlotDAO;

    private EhrAppointmentStatusHistoryDAO ehrAppointmentStatusHistoryDAO;

    private EhrAppointmentRequestDAO ehrAppointmentRequestDAO;

    private EhrProviderScheduleDAO ehrProviderScheduleDAO;

    /**
     * Getters and Setters
     */

    public void setEhrAppointmentTypeDAO(EhrAppointmentTypeDAO ehrAppointmentTypeDAO) {
        this.ehrAppointmentTypeDAO = ehrAppointmentTypeDAO;
    }

    public EhrAppointmentTypeDAO getEhrAppointmentTypeDAO() {
        return ehrAppointmentTypeDAO;
    }

    public void setEhrAppointmentDAO(EhrAppointmentDAO ehrAppointmentDAO) {
        this.ehrAppointmentDAO = ehrAppointmentDAO;
    }

    public EhrAppointmentDAO getEhrAppointmentDAO() {
        return ehrAppointmentDAO;
    }

    public void setEhrAppointmentBlockDAO(EhrAppointmentBlockDAO ehrAppointmentBlockDAO) {
        this.ehrAppointmentBlockDAO = ehrAppointmentBlockDAO;
    }

    public EhrAppointmentBlockDAO getEhrAppointmentBlockDAO() {
        return ehrAppointmentBlockDAO;
    }

    public void setEhrTimeSlotDAO(EhrTimeSlotDAO ehrTimeSlotDAO) {
        this.ehrTimeSlotDAO = ehrTimeSlotDAO;
    }

    public EhrTimeSlotDAO getEhrTimeSlotDAO() {
        return ehrTimeSlotDAO;
    }

    public void setEhrAppointmentStatusHistoryDAO(
            EhrAppointmentStatusHistoryDAO ehrAppointmentStatusHistoryDAO) {
        this.ehrAppointmentStatusHistoryDAO = ehrAppointmentStatusHistoryDAO;
    }

    public EhrAppointmentStatusHistoryDAO getEhrAppointmentStatusHistoryDAO() {
        return ehrAppointmentStatusHistoryDAO;
    }

    public EhrAppointmentRequestDAO getEhrAppointmentRequestDAO() {
        return ehrAppointmentRequestDAO;
    }

    public void setEhrAppointmentRequestDAO(EhrAppointmentRequestDAO ehrAppointmentRequestDAO) {
        this.ehrAppointmentRequestDAO = ehrAppointmentRequestDAO;
    }

    public EhrProviderScheduleDAO getEhrProviderScheduleDAO() {
        return ehrProviderScheduleDAO;
    }

    public void setEhrProviderScheduleDAO(EhrProviderScheduleDAO ehrProviderScheduleDAO) {
        this.ehrProviderScheduleDAO = ehrProviderScheduleDAO;
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getAllEhrAppointmentTypes()
     */
    @Override
    @Transactional(readOnly = true)
    public Set<EhrAppointmentType> getAllEhrAppointmentTypes() {
        HashSet set = new HashSet();
        set.addAll(getEhrAppointmentTypeDAO().getAll());
        return set;
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getAllEhrAppointmentTypes(boolean)
     */
    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentType> getAllEhrAppointmentTypes(boolean includeRetired) {
        return getEhrAppointmentTypeDAO().getAll(includeRetired);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getEhrAppointmentType(java.lang.Integer)
     */
    @Override
    @Transactional(readOnly = true)
    public EhrAppointmentType getEhrAppointmentType(Integer appointmentTypeId) {
        return (EhrAppointmentType) getEhrAppointmentTypeDAO().getById(
                appointmentTypeId);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getEhrAppointmentTypeByUuid(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public EhrAppointmentType getEhrAppointmentTypeByUuid(String uuid) {
        return (EhrAppointmentType) getEhrAppointmentTypeDAO().getByUuid(uuid);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getEhrAppointmentTypes(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentType> getEhrAppointmentTypes(String fuzzySearchPhrase,
                                                     boolean includeRetired) {
        return getEhrAppointmentTypeDAO().getEhrAppointmentTypes(fuzzySearchPhrase,
                includeRetired);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentType> getEhrAppointmentTypes(String fuzzySearchPhrase) {
        return getEhrAppointmentTypes(fuzzySearchPhrase, true);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#saveEhrAppointmentType(EhrAppointmentType)
     */
    @Override
    @Transactional
    public EhrAppointmentType saveEhrAppointmentType(EhrAppointmentType appointmentType)
            throws APIException {
        ValidateUtil.validate(appointmentType);
        return (EhrAppointmentType) getEhrAppointmentTypeDAO().saveOrUpdate(
                appointmentType);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#retireEhrAppointmentType(EhrAppointmentType,
     *      java.lang.String)
     */
    @Override
    @Transactional
    public EhrAppointmentType retireEhrAppointmentType(
            EhrAppointmentType appointmentType, String reason) {
        return saveEhrAppointmentType(appointmentType);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#unretireEhrAppointmentType(EhrAppointmentType)
     */
    @Override
    @Transactional
    public EhrAppointmentType unretireEhrAppointmentType(
            EhrAppointmentType appointmentType) {
        return saveEhrAppointmentType(appointmentType);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#purgeEhrAppointmentType(EhrAppointmentType)
     */
    @Override
    @Transactional
    public void purgeEhrAppointmentType(EhrAppointmentType appointmentType) {
        getEhrAppointmentTypeDAO().delete(appointmentType);
    }

    // Appointment Block

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getAllEhrAppointmentBlocks()
     */
    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentBlock> getAllEhrAppointmentBlocks() {
        return getEhrAppointmentBlockDAO().getAll();
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getAllEhrAppointmentBlocks(boolean)
     */
    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentBlock> getAllEhrAppointmentBlocks(boolean includeVoided) {
        return getEhrAppointmentBlockDAO().getAllData(includeVoided);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getEhrAppointmentBlock(java.lang.Integer)
     */
    @Override
    @Transactional(readOnly = true)
    public EhrAppointmentBlock getEhrAppointmentBlock(Integer appointmentBlockId) {
        return (EhrAppointmentBlock) getEhrAppointmentBlockDAO().getById(
                appointmentBlockId);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getEhrAppointmentBlockByUuid(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public EhrAppointmentBlock getEhrAppointmentBlockByUuid(String uuid) {
        return (EhrAppointmentBlock) getEhrAppointmentBlockDAO().getByUuid(uuid);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#saveEhrAppointmentBlock(EhrAppointmentBlock)
     */
    @Override
    @Transactional
    public EhrAppointmentBlock saveEhrAppointmentBlock(
            EhrAppointmentBlock appointmentBlock) throws APIException {
        ValidateUtil.validate(appointmentBlock);
        return (EhrAppointmentBlock) getEhrAppointmentBlockDAO().saveOrUpdate(
                appointmentBlock);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#voidEhrAppointmentBlock(EhrAppointmentBlock,
     *      java.lang.String)
     */
    @Override
    @Transactional
    public EhrAppointmentBlock voidEhrAppointmentBlock(
            EhrAppointmentBlock appointmentBlock, String reason) {

        // first void all associated time slots
        for (EhrTimeSlot timeSlot : Context.getService(EhrAppointmentService.class)
                .getEhrTimeSlotsInAppointmentBlock(appointmentBlock)) {
            Context.getService(EhrAppointmentService.class).voidEhrTimeSlot(timeSlot,
                    reason);
        }

        return saveEhrAppointmentBlock(appointmentBlock);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#unvoidEhrAppointmentBlock(EhrAppointmentBlock)
     */
    // TODO what should this do regarding voided time slots within this block?
    @Override
    @Transactional
    public EhrAppointmentBlock unvoidEhrAppointmentBlock(
            EhrAppointmentBlock appointmentBlock) {
        return saveEhrAppointmentBlock(appointmentBlock);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#purgeEhrAppointmentBlock(EhrAppointmentBlock)
     */
    @Override
    @Transactional
    public void purgeEhrAppointmentBlock(EhrAppointmentBlock appointmentBlock) {
        getEhrAppointmentBlockDAO().delete(appointmentBlock);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getEhrAppointmentBlocks(Date fromDate,
     *                                                            Date toDate, String locations, Provider provider,
     *                                                            EhrAppointmentType ehrAppointmentType)
     */
    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentBlock> getEhrAppointmentBlocks(Date fromDate,
                                                       Date toDate, String locations, Provider provider,
                                                       EhrAppointmentType appointmentType) {
        return getEhrAppointmentBlocksByTypes(fromDate, toDate, locations,
                provider,
                (appointmentType != null)
                        ? Arrays.asList(appointmentType)
                        : null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentBlock> getEhrAppointmentBlocksByTypes(Date fromDate,
                                                              Date toDate, String locations, Provider provider,
                                                              List<EhrAppointmentType> appointmentTypes) {
        return getEhrAppointmentBlockDAO().getEhrAppointmentBlocks(fromDate, toDate,
                locations, provider, appointmentTypes);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getOverlappingEhrAppointmentBlocks(EhrAppointmentBlock)
     *      )
     */
    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentBlock> getOverlappingEhrAppointmentBlocks(
            EhrAppointmentBlock appointmentBlock) {
        return getEhrAppointmentBlockDAO().getOverlappingEhrAppointmentBlocks(
                appointmentBlock);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getAllEhrAppointments() {
        return getEhrAppointmentDAO().getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getAllEhrAppointments(boolean includeVoided) {
        return getEhrAppointmentDAO().getAllData(includeVoided);
    }

    @Override
    @Transactional(readOnly = true)
    public EhrAppointment getEhrAppointment(Integer appointmentId) {
        return (EhrAppointment) getEhrAppointmentDAO().getById(appointmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public EhrAppointment getEhrAppointmentByUuid(String uuid) {
        return (EhrAppointment) getEhrAppointmentDAO().getByUuid(uuid);
    }

    @Override
    @Transactional
    public EhrAppointment saveEhrAppointment(EhrAppointment appointment)
            throws APIException {
        ValidateUtil.validate(appointment);
        return (EhrAppointment) getEhrAppointmentDAO().saveOrUpdate(appointment);
    }

    @Override
    @Transactional
    public EhrAppointment voidEhrAppointment(EhrAppointment appointment, String reason) {
        return saveEhrAppointment(appointment);
    }

    @Override
    @Transactional
    public EhrAppointment unvoidEhrAppointment(EhrAppointment appointment) {
        return saveEhrAppointment(appointment);
    }

    @Override
    @Transactional
    public void purgeEhrAppointment(EhrAppointment appointment) {
        getEhrAppointmentStatusHistoryDAO().purgeHistoryBy(appointment);
        getEhrAppointmentDAO().delete(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsOfPatient(Patient patient) {
        return getEhrAppointmentDAO().getEhrAppointmentsByPatient(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public EhrAppointment getEhrAppointmentByVisit(Visit visit) {
        return getEhrAppointmentDAO().getEhrAppointmentByVisit(visit);
    }

    // TimeSlot

    @Override
    @Transactional
    public EhrTimeSlot saveEhrTimeSlot(EhrTimeSlot timeSlot) throws APIException {
        ValidateUtil.validate(timeSlot);
        return (EhrTimeSlot) getEhrTimeSlotDAO().saveOrUpdate(timeSlot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrTimeSlot> getAllEhrTimeSlots() {
        return getEhrTimeSlotDAO().getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrTimeSlot> getAllEhrTimeSlots(boolean includeVoided) {
        return getEhrTimeSlotDAO().getAllData(includeVoided);
    }

    @Override
    @Transactional(readOnly = true)
    public EhrTimeSlot getEhrTimeSlot(Integer timeSlotId) {
        return (EhrTimeSlot) getEhrTimeSlotDAO().getById(timeSlotId);
    }

    @Override
    @Transactional(readOnly = true)
    public EhrTimeSlot getEhrTimeSlotByUuid(String uuid) {
        return (EhrTimeSlot) getEhrTimeSlotDAO().getByUuid(uuid);
    }

    @Override
    @Transactional
    public EhrTimeSlot voidEhrTimeSlot(EhrTimeSlot timeSlot, String reason) {
        return saveEhrTimeSlot(timeSlot);
    }

    @Override
    @Transactional
    public EhrTimeSlot unvoidEhrTimeSlot(EhrTimeSlot timeSlot) {
        return saveEhrTimeSlot(timeSlot);
    }

    @Override
    @Transactional
    public void purgeEhrTimeSlot(EhrTimeSlot timeSlot) {
        getEhrTimeSlotDAO().delete(timeSlot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsInTimeSlot(EhrTimeSlot timeSlot) {
        return getEhrAppointmentDAO().getEhrAppointmentsInTimeSlot(timeSlot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsInTimeSlotThatAreNotCancelled(
            EhrTimeSlot timeSlot) {
        return getEhrAppointmentDAO().getEhrAppointmentsInTimeSlotByStatus(timeSlot,
                EhrAppointment.EhrAppointmentStatus.getNotCancelledAppointmentStatuses());
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountOfEhrAppointmentsInTimeSlot(EhrTimeSlot timeSlot) {
        return getEhrAppointmentDAO().getCountOfEhrAppointmentsInTimeSlot(timeSlot);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCountOfEhrAppointmentsInTimeSlotThatAreNotCancelled(
            EhrTimeSlot timeSlot) {
        return getEhrAppointmentDAO().getCountOfEhrAppointmentsInTimeSlotByStatus(
                timeSlot,
                EhrAppointment.EhrAppointmentStatus.getNotCancelledAppointmentStatuses());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrTimeSlot> getEhrTimeSlotsInAppointmentBlock(
            EhrAppointmentBlock appointmentBlock) {
        return getEhrTimeSlotDAO()
                .getEhrTimeSlotsByAppointmentBlock(appointmentBlock);
    }

    // AppointmentStatusHistory

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getAllEhrAppointmentStatusHistories()
     */
    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentStatusHistory> getAllEhrAppointmentStatusHistories() {
        return getEhrAppointmentStatusHistoryDAO().getAll();
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getEhrAppointmentStatusHistory(java.lang.Integer)
     */
    @Override
    @Transactional(readOnly = true)
    public EhrAppointmentStatusHistory getEhrAppointmentStatusHistory(
            Integer appointmentStatusHistoryId) {
        return (EhrAppointmentStatusHistory) getEhrAppointmentStatusHistoryDAO()
                .getById(appointmentStatusHistoryId);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#getEhrAppointmentStatusHistories(EhrAppointment.EhrAppointmentStatus)
     */
    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentStatusHistory> getEhrAppointmentStatusHistories(
            EhrAppointment.EhrAppointmentStatus status) {
        return getEhrAppointmentStatusHistoryDAO().getAll(status);
    }

    /**
     * @see org.openmrs.module.hospitalcore.EhrAppointmentService#saveEhrAppointmentBlock(EhrAppointmentBlock) EhrAppointmentStatusHistory(EhrAppointmentStatusHistory)
     */
    @Override
    @Transactional
    public EhrAppointmentStatusHistory saveEhrAppointmentStatusHistory(
            EhrAppointmentStatusHistory appointmentStatusHistory)
            throws APIException {
        ValidateUtil.validate(appointmentStatusHistory);
        return (EhrAppointmentStatusHistory) getEhrAppointmentStatusHistoryDAO()
                .saveOrUpdate(appointmentStatusHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentRequest> getAllEhrAppointmentRequests() {
        return ehrAppointmentRequestDAO.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentRequest> getAllEhrAppointmentRequests(boolean includeVoided) {
        return ehrAppointmentRequestDAO.getAllData(includeVoided);
    }

    @Override
    @Transactional(readOnly = true)
    public EhrAppointmentRequest getEhrAppointmentRequest(Integer appointmentRequestId) {
        return ehrAppointmentRequestDAO.getById(appointmentRequestId);
    }

    @Override
    @Transactional(readOnly = true)
    public EhrAppointmentRequest getEhrAppointmentRequestByUuid(String uuid) {
        return ehrAppointmentRequestDAO.getByUuid(uuid);
    }

    @Override
    public List<EhrAppointmentRequest> getEhrAppointmentRequestsByConstraints(Patient patient, EhrAppointmentType type, Provider provider,
                                                                        EhrAppointmentRequest.EhrAppointmentRequestStatus status) throws APIException {
        return ehrAppointmentRequestDAO.getEhrAppointmentRequestsByConstraints(patient, type, provider, status);
    }

    @Override
    @Transactional
    public EhrAppointmentRequest saveEhrAppointmentRequest(EhrAppointmentRequest appointmentRequest) throws APIException {
        return ehrAppointmentRequestDAO.saveOrUpdate(appointmentRequest);
    }

    @Override
    @Transactional
    public EhrAppointmentRequest voidEhrAppointmentRequest(EhrAppointmentRequest appointmentRequest, String reason) {
        return ehrAppointmentRequestDAO.saveOrUpdate(appointmentRequest);
    }

    @Override
    @Transactional
    public EhrAppointmentRequest unvoidEhrAppointmentRequest(EhrAppointmentRequest appointmentRequest) {
        return ehrAppointmentRequestDAO.saveOrUpdate(appointmentRequest);
    }

    @Override
    @Transactional
    public void purgeEhrAppointmentRequest(EhrAppointmentRequest appointmentRequest) {
        ehrAppointmentRequestDAO.delete(appointmentRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public EhrAppointment getLastEhrAppointment(Patient patient) {
        return getEhrAppointmentDAO().getLastEhrAppointment(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrTimeSlot> getEhrTimeSlotsByConstraints(EhrAppointmentType appointmentType, Date fromDate, Date toDate,
                                                    Provider provider, Location location) throws APIException {
        return getEhrTimeSlotsByConstraints(appointmentType, fromDate, toDate, provider, location, null);
    }

    @Override
    public List<EhrTimeSlot> getEhrTimeSlotsByConstraints(EhrAppointmentType appointmentType, Date fromDate, Date toDate, Provider provider, Location location, Patient excludeTimeSlotsWithPatient) throws APIException {

        List<EhrTimeSlot> suitableTimeSlots = getEhrTimeSlotsByConstraintsIncludingFull(
                appointmentType, fromDate, toDate, provider, location, excludeTimeSlotsWithPatient);

        List<EhrTimeSlot> availableTimeSlots = new LinkedList<EhrTimeSlot>();

        Integer duration = appointmentType.getDuration();
        for (EhrTimeSlot slot : suitableTimeSlots) {

            // Filter by time left
            if (getEhrTimeLeftInTimeSlot(slot) >= duration)
                availableTimeSlots.add(slot);
        }

        return availableTimeSlots;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrTimeSlot> getEhrTimeSlotsByConstraintsIncludingFull(EhrAppointmentType appointmentType, Date fromDate, Date toDate,
                                                                 Provider provider, Location location) throws APIException {
        return getEhrTimeSlotsByConstraintsIncludingFull(appointmentType, fromDate, toDate, provider, location, null);
    }

    @Override
    public List<EhrTimeSlot> getEhrTimeSlotsByConstraintsIncludingFull(EhrAppointmentType appointmentType, Date fromDate,
                                                                 Date toDate, Provider provider, Location location,
                                                                 Patient excludeTimeSlotsWithPatient) throws APIException {

        List<EhrTimeSlot> suitableTimeSlots = getEhrTimeSlotDAO().getEhrTimeSlotsByConstraints(appointmentType, fromDate, toDate, provider);

        List<EhrTimeSlot> availableTimeSlots = new LinkedList<EhrTimeSlot>();

        // Used to update the session to the correct one
        if (location != null) {
            location = Context.getLocationService().getLocation(location.getId());
        }

        // generate the set of locations to filter by
        Set<Location> relevantLocations = getAllLocationDescendants(location,
                null);
        relevantLocations.add(location);

        Set<EhrTimeSlot> timeSlotsToExclude = new HashSet<EhrTimeSlot>();

        // generate the set of time slots to exclude that the specified patient already has an appointment for of the specified type
        if (excludeTimeSlotsWithPatient != null) {
            for (EhrAppointment appointment: getEhrAppointmentsOfPatient(excludeTimeSlotsWithPatient)) {
                if (appointment.getAppointmentType() == appointmentType && appointment.getStatus().getType() != EhrAppointment.EhrAppointmentStatusType.CANCELLED) {
                    timeSlotsToExclude.add(appointment.getTimeSlot());
                }
            }
        }

        // now do the actual filtering
        for (EhrTimeSlot slot : suitableTimeSlots) {

            // Filter by based on the locations and excluded time slots
            if ( (location == null || relevantLocations.contains(slot.getAppointmentBlock().getLocation()))
                    && !timeSlotsToExclude.contains(slot) ) {
                availableTimeSlots.add(slot);
            }
        }

        return availableTimeSlots;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getPatientIdentifiersRepresentation(Patient patient) {
        LinkedList<String> identifiers = new LinkedList<String>();

        if (patient == null)
            return identifiers;

        for (PatientIdentifier identifier : patient.getIdentifiers()) {
            // Representation format: <identifier type name> : <identifier
            // value>
            // for example: "OpenMRS Identification Number: 7532AM-1"
            String representation = identifier.getIdentifierType().getName()
                    + ": " + identifier.getIdentifier();
            // Put preferred identifier first.
            if (identifier.getPreferred())
                identifiers.add(0, representation);
                // Insert to the end of the list
            else
                identifiers.add(identifiers.size(), representation);
        }

        return identifiers;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getEhrTimeLeftInTimeSlot(EhrTimeSlot timeSlot) {

        if (timeSlot == null) {
            return null;
        }

        Integer minutes = Minutes.minutesBetween(
                new DateTime(timeSlot.getStartDate()),
                new DateTime(timeSlot.getEndDate())).getMinutes();

        for (EhrAppointment appointment : Context.getService(
                        EhrAppointmentService.class)
                .getEhrAppointmentsInTimeSlotThatAreNotCancelled(timeSlot)) {
            minutes = minutes - appointment.getAppointmentType().getDuration();
        }

        return minutes;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Location> getAllLocationDescendants(Location location,
                                                   Set<Location> descendants) {
        if (descendants == null)
            descendants = new HashSet<Location>();

        if (location != null) {
            Set<Location> childLocations = location.getChildLocations();
            for (Location childLocation : childLocations) {
                descendants.add(childLocation);
                getAllLocationDescendants(childLocation, descendants);
            }
        }

        return descendants;
    }

    @Override
    public List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate,
                                                          Date toDate, Location location, Provider provider,
                                                          EhrAppointmentType type, EhrAppointment.EhrAppointmentStatus status) throws APIException {
        return getEhrAppointmentsByConstraints(fromDate, toDate, location,
                provider, type, null, status);
    }

    @Override
    public List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate,
                                                          Date toDate, Location location, Provider provider,
                                                          EhrAppointmentType type, Patient patient,
                                                          List<EhrAppointment.EhrAppointmentStatus> statuses) {

        if (statuses == null) {
            return getEhrAppointmentsByConstraints(fromDate, toDate, location,
                    provider, type, patient, new ArrayList<EhrAppointment.EhrAppointmentStatus>(), null, null);
        }

        return getEhrAppointmentsByConstraints(fromDate, toDate, location,
                provider, type, patient, statuses, null, null);

    }

    @Override
    public List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate,
                                                          Date toDate, Location location, Provider provider,
                                                          EhrAppointmentType type, Patient patient, List<EhrAppointment.EhrAppointmentStatus> statuses,
                                                          VisitType visitType, Visit visit) throws APIException{

        List<EhrAppointment> appointments = ehrAppointmentDAO
                .getEhrAppointmentsByConstraints(fromDate, toDate, provider, type,
                        statuses, patient, visitType, visit);

        List<EhrAppointment> appointmentsInLocation = new LinkedList<EhrAppointment>();

        // Used to update the session to the correct one
        if (location != null)
            location = Context.getLocationService().getLocation(
                    location.getId());

        Set<Location> relevantLocations = getAllLocationDescendants(location,
                null);
        relevantLocations.add(location);

        for (EhrAppointment appointment : appointments) {
            boolean satisfyingConstraints = true;

            // Filter by location
            if (location != null) {
                if (relevantLocations.contains(appointment.getTimeSlot()
                        .getAppointmentBlock().getLocation()))
                    appointmentsInLocation.add(appointment);
            } else
                appointmentsInLocation.add(appointment);

        }

        return appointmentsInLocation;

    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate,
                                                          Date toDate, Location location, Provider provider,
                                                          EhrAppointmentType type, Patient patient, EhrAppointment.EhrAppointmentStatus status)
            throws APIException {

        if (status == null) {
            return getEhrAppointmentsByConstraints(fromDate, toDate, location,
                    provider, type, patient, new ArrayList<EhrAppointment.EhrAppointmentStatus>());
        }

        return getEhrAppointmentsByConstraints(fromDate, toDate, location,
                provider, type, patient, Arrays.asList(status));
    }

    @Override
    @Transactional(readOnly = true)
    public Date getEhrAppointmentCurrentStatusStartDate(EhrAppointment appointment) {
        return ehrAppointmentStatusHistoryDAO
                .getStartDateOfCurrentStatus(appointment);
    }

    @Override
    @Transactional
    public void changeEhrAppointmentStatus(EhrAppointment appointment,
                                        EhrAppointment.EhrAppointmentStatus newStatus) {
        if (appointment != null) {

            Date currentDate = new Date();

            // get any previous status
            EhrAppointmentStatusHistory appointmentStatusHistory = getMostRecentEhrAppointmentStatusHistory(appointment);

            if (appointmentStatusHistory != null) {
                // prior to the implement of AM-196 we were *not* storing the "current" status of an appointment in the history table
                // we need to still handle legacy code that may not have been migrated, and create an entry in the history table
                if (appointmentStatusHistory.getEndDate() != null) {
                    EhrAppointmentStatusHistory history = new EhrAppointmentStatusHistory();
                    history.setAppointment(appointment);
                    history.setEndDate(currentDate);
                    history.setStartDate(getEhrAppointmentCurrentStatusStartDate(appointment));
                    history.setStatus(appointment.getStatus());
                    saveEhrAppointmentStatusHistory(appointmentStatusHistory);
                }
                // otherwise, just update the end date of the most recent status
                else {
                    appointmentStatusHistory.setEndDate(currentDate);
                    saveEhrAppointmentStatusHistory(appointmentStatusHistory);
                }
            }

            // now update the appointment itself
            appointment.setStatus(newStatus);
            saveEhrAppointment(appointment);

            // create an entry for the new status
            EhrAppointmentStatusHistory history = new EhrAppointmentStatusHistory();
            history.setAppointment(appointment);
            history.setStartDate(currentDate);
            history.setStatus(appointment.getStatus());

            saveEhrAppointmentStatusHistory(history);

            if (newStatus == EhrAppointment.EhrAppointmentStatus.COMPLETED
                    || newStatus == EhrAppointment.EhrAppointmentStatus.CANCELLED
                    || newStatus == EhrAppointment.EhrAppointmentStatus.MISSED) {
                Visit visit = appointment.getVisit();
                if (visit != null && visit.getStopDatetime() != null) {
                    visit.setStopDatetime(new Date());
                    Context.getVisitService().saveVisit(visit);
                }
            }
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Provider> getAllEhrProvidersSorted(boolean includeRetired) {
        List<Provider> providers = Context.getProviderService()
                .getAllProviders(includeRetired);
        Collections.sort(providers, new Comparator<Provider>() {

            public int compare(Provider pr1, Provider pr2) {
                return pr1.getName().toLowerCase()
                        .compareTo(pr2.getName().toLowerCase());
            }
        });
        return providers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentType> getAllEhrAppointmentTypesSorted(
            boolean includeRetired) {
        List<EhrAppointmentType> appointmentTypes = Context.getService(
                        EhrAppointmentService.class)
                .getAllEhrAppointmentTypes(includeRetired);
        Collections.sort(appointmentTypes, new Comparator<EhrAppointmentType>() {

            public int compare(EhrAppointmentType at1, EhrAppointmentType at2) {
                return at1.getName().toLowerCase()
                        .compareTo(at2.getName().toLowerCase());
            }
        });
        return appointmentTypes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsByStatus(
            List<EhrAppointment.EhrAppointmentStatus> states) {
        return ehrAppointmentDAO.getEhrAppointmentsByStates(states);
    }

    @Override
    @Transactional
    public List<EhrAppointment> cleanOpenEhrAppointments() {
        List<EhrAppointment.EhrAppointmentStatus> states = new LinkedList<EhrAppointment.EhrAppointmentStatus>();
        states.add(EhrAppointment.EhrAppointmentStatus.SCHEDULED);
        states.add(EhrAppointment.EhrAppointmentStatus.WAITING);
        states.add(EhrAppointment.EhrAppointmentStatus.WALKIN);
        states.add(EhrAppointment.EhrAppointmentStatus.INCONSULTATION);

        Date endOfYesterday = new DateTime().withTime(23, 59, 59, 999).minusDays(1).toDate();

        List<EhrAppointment> appointmentsInStates = ehrAppointmentDAO
                .getPastEhrAppointmentsByStates(states);
        if (appointmentsInStates == null)
            return new LinkedList<EhrAppointment>();
        Iterator<EhrAppointment> iter = appointmentsInStates.iterator();
        while (iter.hasNext()) {
            EhrAppointment appointment = iter.next();
            // Check if past appointment
            if (appointment.getTimeSlot().getEndDate().before(endOfYesterday)) {
                EhrAppointment.EhrAppointmentStatus status = appointment.getStatus();
                switch (status) {
                    case SCHEDULED :
                    case WAITING :
                    case WALKIN :
                        changeEhrAppointmentStatus(appointment,
                                EhrAppointment.EhrAppointmentStatus.MISSED);
                        break;
                    case INCONSULTATION :
                        changeEhrAppointmentStatus(appointment,
                                EhrAppointment.EhrAppointmentStatus.COMPLETED);
                        break;
                    default :
                        // No need to change
                        appointmentsInStates.remove(appointment);
                        break;
                }

            } else {
                appointmentsInStates.remove(appointment);
            }
        }

        return appointmentsInStates;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean verifyDuplicatedEhrAppointmentTypeName(
            EhrAppointmentType ehrAppointmentTypeName) {
        return ehrAppointmentTypeDAO
                .verifyDuplicatedEhrAppointmentTypeName(ehrAppointmentTypeName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getScheduledEhrAppointmentsForPatient(Patient patient) {
        return ehrAppointmentDAO.getScheduledEhrAppointmentsForPatient(patient);
    }

    @Override
    public List<EhrAppointment> getScheduledEhrAppointmentsForPatients() throws APIException {
        return ehrAppointmentDAO.getScheduledEhrAppointmentsForPatients();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<EhrAppointmentType, Double> getAverageEhrHistoryDurationByConditions(
            Date fromDate, Date endDate, EhrAppointment.EhrAppointmentStatus status) {
        Map<EhrAppointmentType, Double> averages = new HashMap<EhrAppointmentType, Double>();
        Map<EhrAppointmentType, Integer> counters = new HashMap<EhrAppointmentType, Integer>();

        List<EhrAppointmentStatusHistory> histories = ehrAppointmentStatusHistoryDAO
                .getHistoriesByInterval(fromDate, endDate, status);

        // Clean Not-Reasonable Durations
        Map<EhrAppointmentStatusHistory, Double> durations = new HashMap<EhrAppointmentStatusHistory, Double>();
        // 60 seconds * 1000 milliseconds in 1 minute
        int minutesConversion = 60000;
        int minutesInADay = 1440;
        for (EhrAppointmentStatusHistory history : histories) {
            Date startDate = history.getStartDate();
            Date toDate = history.getEndDate();
            Double duration = (double) ((toDate.getTime() / minutesConversion) - (startDate
                    .getTime() / minutesConversion));

            // Not reasonable to be more than a day
            if (duration < minutesInADay)
                durations.put(history, duration);
        }

        Double[] data = new Double[durations.size()];

        int i = 0;
        for (Map.Entry<EhrAppointmentStatusHistory, Double> entry : durations
                .entrySet()) {
            // Added Math.sqrt in order to lower the mean and variance
            data[i] = Math.sqrt(entry.getValue());
            i++;
        }

        // Compute Intervals
        double[] boundaries = confidenceInterval(data);
        //

        // sum up the durations by type
        for (Map.Entry<EhrAppointmentStatusHistory, Double> entry : durations
                .entrySet()) {
            EhrAppointmentType type = entry.getKey().getAppointment()
                    .getAppointmentType();
            Double duration = entry.getValue();

            // Added Math.sqrt in order to lower the mean and variance
            if ((Math.sqrt(duration) <= boundaries[1])) {
                if (averages.containsKey(type)) {
                    averages.put(type, averages.get(type) + duration);
                    counters.put(type, counters.get(type) + 1);
                } else {
                    averages.put(type, duration);
                    counters.put(type, 1);
                }
            }
        }

        // Compute average
        for (Map.Entry<EhrAppointmentType, Integer> counter : counters.entrySet())
            averages.put(counter.getKey(), averages.get(counter.getKey())
                    / counter.getValue());

        return averages;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Provider, Double> getAverageEhrHistoryDurationByConditionsPerProvider(
            Date fromDate, Date endDate, EhrAppointment.EhrAppointmentStatus status) {
        Map<Provider, Double> averages = new HashMap<Provider, Double>();
        Map<Provider, Integer> counters = new HashMap<Provider, Integer>();

        List<EhrAppointmentStatusHistory> histories = ehrAppointmentStatusHistoryDAO
                .getHistoriesByInterval(fromDate, endDate, status);

        // Clean Not-Reasonable Durations
        Map<EhrAppointmentStatusHistory, Double> durations = new HashMap<EhrAppointmentStatusHistory, Double>();
        // 60 seconds * 1000 milliseconds in 1 minute
        int minutesConversion = 60000;
        int minutesInADay = 1440;
        for (EhrAppointmentStatusHistory history : histories) {
            Date startDate = history.getStartDate();
            Date toDate = history.getEndDate();
            Double duration = (double) ((toDate.getTime() / minutesConversion) - (startDate
                    .getTime() / minutesConversion));
            // Not reasonable to be more than a day
            if (duration > 0 && duration < minutesInADay)
                durations.put(history, duration);
        }

        Double[] data = new Double[durations.size()];

        int i = 0;
        for (Map.Entry<EhrAppointmentStatusHistory, Double> entry : durations
                .entrySet()) {
            // Added Math.sqrt in order to lower the mean and variance
            data[i] = Math.sqrt(entry.getValue());
            i++;
        }

        // Compute Intervals
        double[] boundaries = confidenceInterval(data);
        //

        // sum up the durations by type
        for (Map.Entry<EhrAppointmentStatusHistory, Double> entry : durations
                .entrySet()) {
            Provider provider = entry.getKey().getAppointment().getTimeSlot()
                    .getAppointmentBlock().getProvider();
            Double duration = entry.getValue();

            // Added Math.sqrt in order to lower the mean and variance
            if ((Math.sqrt(duration) <= boundaries[1])) {
                if (averages.containsKey(provider)) {
                    averages.put(provider, averages.get(provider) + duration);
                    counters.put(provider, counters.get(provider) + 1);
                } else {
                    averages.put(provider, duration);
                    counters.put(provider, 1);
                }
            }
        }

        // Compute average
        for (Map.Entry<Provider, Integer> counter : counters.entrySet())
            averages.put(counter.getKey(), averages.get(counter.getKey())
                    / counter.getValue());

        return averages;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getEhrHistoryCountByConditions(Date fromDate, Date endDate,
                                               EhrAppointment.EhrAppointmentStatus status) {
        List<EhrAppointmentStatusHistory> histories = ehrAppointmentStatusHistoryDAO
                .getHistoriesByInterval(fromDate, endDate, status);

        return histories.size();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<EhrAppointmentType, Integer> getEhrAppointmentTypeDistribution(
            Date fromDate, Date toDate) {
        List<EhrAppointmentType> unretiredTypes = Context.getService(
                EhrAppointmentService.class).getAllEhrAppointmentTypes(false);
        Map<EhrAppointmentType, Integer> distribution = new HashMap<EhrAppointmentType, Integer>();

        for (EhrAppointmentType type : unretiredTypes) {
            Integer countOfType = ehrAppointmentTypeDAO.getEhrAppointmentTypeCount(
                    fromDate, toDate, type);
            if (countOfType > 0)
                distribution.put(type, countOfType);

        }

        return distribution;

    }

    @Override
    @Transactional
    public EhrAppointment bookEhrAppointment(EhrAppointment appointment,
                                       Boolean allowOverbook) throws EhrTimeSlotFullException {

        // can only book new appointments
        if (appointment.getId() != null) {
            throw new APIException(
                    "Cannot book appointment that has already been persisted");
        }

        // annoying that we have to do this, since it will be called during
        // save, but otherwise we might get a NPE below if time slot or
        // appointment type == null
        ValidateUtil.validate(appointment);

        if (!allowOverbook) {
            if (getEhrTimeLeftInTimeSlot(appointment.getTimeSlot()) < appointment
                    .getAppointmentType().getDuration()) {
                throw new EhrTimeSlotFullException();
            }
        }

        if (appointment.getStatus() == null){
            appointment.setStatus(EhrAppointment.EhrAppointmentStatus.SCHEDULED);
        }

        Context.getService(EhrAppointmentService.class).saveEhrAppointment(appointment);

        EhrAppointmentStatusHistory history = new EhrAppointmentStatusHistory(appointment, appointment.getStatus(), getEhrAppointmentCurrentStatusStartDate(appointment), null);
        Context.getService(EhrAppointmentService.class).saveEhrAppointmentStatusHistory(history);
        return appointment;
    }

    @Override
    @Transactional
    public List<EhrAppointmentStatusHistory> getEhrAppointmentStatusHistories(EhrAppointment appointment) {
        return getEhrAppointmentStatusHistoryDAO().getEhrAppointmentStatusHistories(appointment);
    }

    @Override
    public EhrAppointmentStatusHistory getMostRecentEhrAppointmentStatusHistory(EhrAppointment appointment) {
        return getEhrAppointmentStatusHistoryDAO().getMostRecentEhrAppointmentStatusHistory(appointment);
    }

    //provider schedules

    @Override
    public List<EhrProviderSchedule> getAllEhrProviderSchedules() {
        return getEhrProviderScheduleDAO().getAll();
    }

    @Override
    public List<EhrProviderSchedule> getAllEhrProviderSchedules(boolean includeVoided) {
        return getEhrProviderScheduleDAO().getAllData(includeVoided);
    }

    @Override
    public EhrProviderSchedule getEhrProviderSchedule(Integer ProviderScheduleId) {
        return (EhrProviderSchedule) getEhrProviderScheduleDAO().getById(ProviderScheduleId);
    }

    @Override
    public EhrProviderSchedule getEhrProviderScheduleByUuid(String uuid) {
        return (EhrProviderSchedule) getEhrProviderScheduleDAO().getByUuid(uuid);
    }

    @Override
    public EhrProviderSchedule saveEhrProviderSchedule(EhrProviderSchedule providerSchedule) throws APIException {
        ValidateUtil.validate(providerSchedule);
        return (EhrProviderSchedule) getEhrProviderScheduleDAO().saveOrUpdate(providerSchedule);
    }

    @Override
    public EhrProviderSchedule voidEhrProviderSchedule(EhrProviderSchedule providerSchedule, String reason) {
        providerSchedule.setVoided(true);
        providerSchedule.setVoidReason(reason);
        return (EhrProviderSchedule) getEhrProviderScheduleDAO().saveOrUpdate(providerSchedule);
    }

    @Override
    public void purgeEhrProviderSchedule(EhrProviderSchedule providerSchedule) {
        getEhrProviderScheduleDAO().delete(providerSchedule);
    }

    @Override
    public List<EhrProviderSchedule> getEhrProviderSchedulesByConstraints(Location location, Provider provider, List<EhrAppointmentType> appointmentTypes) {
        return getEhrProviderScheduleDAO().getEhrProviderScheduleByConstraints(location, provider,null);
    }

    @Override
    public EhrTimeSlot getEhrTimeslotForAppointment(Location location, Provider provider, EhrAppointmentType type, Date appointmentDate){
        EhrTimeSlot requiredTimeslot = null;

        List<EhrTimeSlot> timeSlots = getEhrTimeSlotsByConstraintsIncludingFull(type, setDateToStartOfDay(appointmentDate), setDateToEndOfDay(appointmentDate), provider, location);

        for (EhrTimeSlot timeSlot : timeSlots){
            if ((timeSlot.getStartDate().getTime() / 1000 <= appointmentDate.getTime() / 1000) && ( appointmentDate.getTime() / 1000 <= timeSlot.getEndDate().getTime() / 1000)){
                requiredTimeslot = timeSlot;

            }
        }

        return requiredTimeslot;
    }

    @Override
    public EhrTimeSlot createEhrTimeSlotUsingProviderSchedule(Date appointmentDate, Provider provider, Location location) {

        List<EhrProviderSchedule> ar = getEhrProviderScheduleDAO().getEhrProviderScheduleByConstraints(location, provider, appointmentDate);
        if (ar.size() > 0) {
            EhrTimeSlot timeSlot = new EhrTimeSlot();
            EhrProviderSchedule providerSchedule = ar.get(0);
            Date startDate = getDateAndTime(appointmentDate, providerSchedule.getStartTime());
            Date endDate = getDateAndTime(appointmentDate, providerSchedule.getEndTime());

            Set<EhrAppointmentType> types = new HashSet<EhrAppointmentType>();

            for (EhrAppointmentType type : providerSchedule.getTypes()) {
                types.add(type);
            }

            EhrAppointmentBlock appointmentBlock = new EhrAppointmentBlock(startDate, endDate, providerSchedule.getProvider(), providerSchedule.getLocation(), types);
            EhrAppointmentBlock block = (EhrAppointmentBlock) getEhrAppointmentBlockDAO().saveOrUpdate(appointmentBlock);

            timeSlot.setAppointmentBlock(block);
            timeSlot.setStartDate(block.getStartDate());
            timeSlot.setEndDate(block.getEndDate());

            return (EhrTimeSlot) getEhrTimeSlotDAO().saveOrUpdate(timeSlot);
        } else {
            throw new APIException("Appointment cannot be booked, Schedule does not exist");
        }


    }

    @Override
    public List<Appointment> getEhrAppointmentsByProvider(Provider provider) throws APIException {
        return ehrAppointmentDAO.getEhrAppointmentsByProvider(provider);
    }

    public Date getDateAndTime(Date date, Date time) {
        return new Date(
                date.getYear(), date.getMonth(), date.getDate(),
                time.getHours(), time.getMinutes(), time.getSeconds()
        );
    }

    @Override
    public List<EhrAppointment> getEarlyEhrAppointments(Date fromDate, Date toDate, Location location,
                                                  Provider provider, EhrAppointmentType appointmentType) throws APIException {
        List<EhrAppointment.EhrAppointmentStatus> statuses = new ArrayList<EhrAppointment.EhrAppointmentStatus>();
        statuses.add(EhrAppointment.EhrAppointmentStatus.COMPLETED);
        statuses.add(EhrAppointment.EhrAppointmentStatus.INCONSULTATION);

        List<EhrAppointment> allCompletedAppointments = getEhrAppointmentsByConstraints(fromDate,
                toDate, location, provider, appointmentType, null, statuses);

        List<EhrAppointment> earlyAppointments = new ArrayList<EhrAppointment>();
        for (EhrAppointment ap : allCompletedAppointments) {
            if (ap.getVisit().getStartDatetime().before(ap.getTimeSlot().getEndDate())) {
                earlyAppointments.add(ap);
            }
        }
        return earlyAppointments;
    }

    @Override
    public List<EhrAppointment> getLateEhrAppointments(Date fromDate, Date toDate, Location location,
                                                 Provider provider, EhrAppointmentType appointmentType) throws APIException {
        List<EhrAppointment.EhrAppointmentStatus> statuses = new ArrayList<EhrAppointment.EhrAppointmentStatus>();
        statuses.add(EhrAppointment.EhrAppointmentStatus.COMPLETED);
        statuses.add(EhrAppointment.EhrAppointmentStatus.INCONSULTATION);

        List<EhrAppointment> allCompletedAppointments = getEhrAppointmentsByConstraints(fromDate,
                toDate, location, provider, appointmentType, null, statuses);

        List<EhrAppointment> lateAppointments = new ArrayList<EhrAppointment>();
        for (EhrAppointment ap : allCompletedAppointments) {
            if (ap.getVisit().getStartDatetime().after(ap.getTimeSlot().getEndDate())) {
                lateAppointments.add(ap);
            }
        }

        return lateAppointments;
    }

    @Override
    public List<EhrAppointmentDailyCount> getEhrAppointmentDailyCount(String fromDate, String toDate, Location location,
                                                                   Provider provider, EhrAppointment.EhrAppointmentStatus status) throws APIException {
        return ehrAppointmentDAO.getEhrAppointmentDailyCount(fromDate, toDate, location, provider, status);
    }

    private List<EhrAppointmentBlock> getEhrAppointmentBlockList(Location location,
                                                              Date date, List<EhrAppointmentType> appointmentTypes) {
        return getEhrAppointmentBlocksByTypes(setDateToStartOfDay(date),
                setDateToEndOfDay(date), location.getId().toString(), null,
                appointmentTypes);
    }

    private Date setDateToEndOfDay(Date date) {
        return setupDate(date, 23, 59, 59);
    }

    private Date setDateToStartOfDay(Date date) {
        return setupDate(date, 0, 0, 0);
    }

    private Date setupDate(Date date, int hour, int minute, int second) {
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime(date);

        endDateCalendar.set(Calendar.HOUR, hour);
        endDateCalendar.set(Calendar.MINUTE, minute);
        endDateCalendar.set(Calendar.SECOND, second);
        return endDateCalendar.getTime();
    }

    private double[] confidenceInterval(Double[] data) {
        // Empty Dataset
        if (data.length == 0)
            return new double[]{0.0, 0.0};

        // Initialization
        double mean = 0;
        int count = data.length;
        int df = count - 1;
        // If Dataset consists of only one item
        if (df == 0)
            return new double[]{Double.MIN_VALUE, Double.MAX_VALUE};

        double alpha = 0.05;
        double tStat = EhrStudentT.tTable(df, alpha);

        // Compute Mean
        for (double val : data)
            mean += val;
        mean = mean / count;

        // Compute Variance
        double variance = 0;
        for (double val : data)
            variance += Math.pow((val - mean), 2);
        variance = variance / df;
        // If deviation is small - Suspected as "Clean of Noise"
        if (Math.sqrt(variance) <= 1)
            return new double[]{Double.MIN_VALUE, Double.MAX_VALUE};

        // Compute Confidence Interval Bounds.
        double[] boundaries = new double[2];
        double factor = tStat * (Math.sqrt(variance) / Math.sqrt(count));
        boundaries[0] = mean - factor;
        boundaries[1] = mean + factor;

        return boundaries;
    }
}
