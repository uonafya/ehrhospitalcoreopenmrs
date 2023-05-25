package org.openmrs.module.hospitalcore.db.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.EhrAppointmentDAO;
import org.openmrs.module.hospitalcore.model.EhrAppointment;
import org.openmrs.module.hospitalcore.model.EhrAppointmentBlock;
import org.openmrs.module.hospitalcore.model.EhrAppointmentDailyCount;
import org.openmrs.module.hospitalcore.model.EhrAppointmentType;
import org.openmrs.module.hospitalcore.model.EhrTimeSlot;
import org.openmrs.module.hospitalcore.util.DateUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.hospitalcore.model.EhrAppointment.EhrAppointmentStatus.INCONSULTATION;
import static org.openmrs.module.hospitalcore.model.EhrAppointment.EhrAppointmentStatus.RESCHEDULED;
import static org.openmrs.module.hospitalcore.model.EhrAppointment.EhrAppointmentStatusType.SCHEDULED;

public class HibernateEhrAppointmentDAO extends HibernateEhrSingleClassDAO implements EhrAppointmentDAO {

    public HibernateEhrAppointmentDAO() {
        super(EhrAppointment.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsByPatient(Patient patient) {
        return super.sessionFactory.getCurrentSession()
                .createCriteria(EhrAppointment.class)
                .add(Restrictions.eq("patient", patient)).list();
    }

    @Override
    @Transactional(readOnly = true)
    public EhrAppointment getEhrAppointmentByVisit(Visit visit) {
        return (EhrAppointment) super.sessionFactory
                .getCurrentSession()
                .createQuery(
                        "from " + mappedClass.getSimpleName()
                                + " at where at.visit = :visit")
                .setParameter("visit", visit).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public EhrAppointment getLastEhrAppointment(Patient patient) {
        String query = "select appointment from EhrAppointment as appointment"
                + " where appointment.patient = :patient and appointment.timeSlot.startDate ="
                + " (select max(ap.timeSlot.startDate) from EhrAppointment as ap inner join ap.timeSlot"
                + " where ap.patient = :patient)";

        List<EhrAppointment> appointment = super.sessionFactory
                .getCurrentSession().createQuery(query)
                .setParameter("patient", patient).list();

        if (appointment.size() > 0)
            return (EhrAppointment) appointment.get(0);
        else
            return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate,
                                                                Date toDate, Provider provider, EhrAppointmentType appointmentType,
                                                                List<EhrAppointment.EhrAppointmentStatus> statuses, Patient patient, VisitType visitType, Visit visit)
            throws APIException {
        if(fromDate == null && toDate == null ) {
            fromDate = DateUtils.getStartOfDay(new Date());
            toDate = DateUtils.getEndOfDay(new Date());
        }
        if (fromDate != null && toDate != null && !fromDate.before(toDate))
            throw new APIException("fromDate can not be later than toDate");

        else {
            String stringQuery = "SELECT appointment FROM EhrAppointment AS appointment WHERE appointment.voided = false";

            if (fromDate != null)
                stringQuery += " AND appointment.timeSlot.startDate >= :fromDate";
            if (toDate != null)
                stringQuery += " AND appointment.timeSlot.endDate <= :endDate";
            if (provider != null)
                stringQuery += " AND appointment.timeSlot.appointmentBlock.provider = :provider";
            if (statuses != null && statuses.size() > 0)
                stringQuery += " AND appointment.status IN (:statuses)";
            if (visitType != null)
                stringQuery += " AND appointment.appointmentType.visitType = :visitType";
            if (visit != null)
                stringQuery += " AND appointment.visit = :visit";
            if (appointmentType != null)
                stringQuery += " AND appointment.appointmentType=:appointmentType";
            if (patient != null) {
                stringQuery += " AND appointment.patient=:patient";
            }

            stringQuery += " ORDER BY appointment.timeSlot.startDate";

            Query query = super.sessionFactory.getCurrentSession().createQuery(
                    stringQuery);

            if (fromDate != null)
                query.setParameter("fromDate", fromDate);
            if (toDate != null)
                query.setParameter("endDate", toDate);
            if (provider != null)
                query.setParameter("provider", provider);
            if (statuses != null && statuses.size() > 0)
                query.setParameterList("statuses", statuses);
            if (visitType != null)
                query.setParameter("visitType", visitType);
            if (visit != null)
                query.setParameter("visit", visit);
            if (appointmentType != null)
                query.setParameter("appointmentType", appointmentType);
            if (patient != null)
                query.setParameter("patient", patient);

            return query.list();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate,
                                                                Date toDate, Provider provider, EhrAppointmentType appointmentType,
                                                                EhrAppointment.EhrAppointmentStatus status, Patient patient) throws APIException {
        return getEhrAppointmentsByConstraints(fromDate, toDate, provider,
                appointmentType, Arrays.asList(status), patient, null, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getEhrAppointmentsByStates(
            List<EhrAppointment.EhrAppointmentStatus> states) {
        String sQuery = "from EhrAppointment as appointment where appointment.voided = false and appointment.status in (:states)";

        Query query = super.sessionFactory.getCurrentSession().createQuery(
                sQuery);
        query.setParameterList("states", states);

        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointment> getPastEhrAppointmentsByStates(
            List<EhrAppointment.EhrAppointmentStatus> states) {
        String sQuery = "from EhrAppointment as appointment where appointment.timeSlot.endDate <= :endDate and appointment.voided = false and appointment.status in (:states)";

        Query query = super.sessionFactory.getCurrentSession().createQuery(
                sQuery);
        query.setParameterList("states", states);
        query.setParameter("endDate", Calendar.getInstance().getTime());

        return query.list();
    }

    @Override
    public List<EhrAppointment> getScheduledEhrAppointmentsForPatient(Patient patient) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
                mappedClass);
        criteria.add(Restrictions.eq("patient", patient));
        criteria.add(Restrictions.or(Restrictions.eq("status", SCHEDULED),
                Restrictions.eq("status", RESCHEDULED)));
        criteria.add(Restrictions.eq("voided", false));
        criteria.createAlias("timeSlot", "timeSlot");
        criteria.addOrder(Order.asc("timeSlot.startDate"));

        return criteria.list();
    }

    @Override
    public List<EhrAppointment> getEhrAppointmentsByAppointmentBlockAndAppointmentTypes(
            EhrAppointmentBlock appointmentBlock,
            List<EhrAppointmentType> appointmentTypes) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
                mappedClass);
        criteria.createAlias("timeSlot", "time_slot");
        criteria.add(Restrictions.eq("time_slot.appointmentBlock",
                appointmentBlock));

        if (appointmentTypes != null)
            criteria.add(Restrictions.in("appointmentType", appointmentTypes));
        criteria.add(Restrictions.eq("voided", false));

        return criteria.list();
    }

    @Override
    public List<EhrAppointment> getEhrAppointmentsInTimeSlot(EhrTimeSlot timeSlot) {
        return createEhrAppointmentsInTimeSlotCriteria(timeSlot).list();
    }

    @Override
    public List<EhrAppointment> getEhrAppointmentsInTimeSlotByStatus(
            EhrTimeSlot timeSlot, List<EhrAppointment.EhrAppointmentStatus> statuses) {
        return createEhrAppointmentsInTimeSlotByStatusCriteria(timeSlot, statuses)
                .list();

    }

    @Override
    public Integer getCountOfEhrAppointmentsInTimeSlot(EhrTimeSlot timeSlot) {
        return ((Number) createEhrAppointmentsInTimeSlotCriteria(timeSlot)
                .setProjection(Projections.rowCount()).uniqueResult())
                .intValue();
    }

    @Override
    public Integer getCountOfEhrAppointmentsInTimeSlotByStatus(EhrTimeSlot timeSlot,
                                                            List<EhrAppointment.EhrAppointmentStatus> statuses) {
        return ((Number) createEhrAppointmentsInTimeSlotByStatusCriteria(timeSlot,
                statuses).setProjection(Projections.rowCount()).uniqueResult())
                .intValue();
    }

    private Criteria createEhrAppointmentsInTimeSlotCriteria(EhrTimeSlot timeSlot) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
                EhrAppointment.class);
        criteria.add(Restrictions.eq("timeSlot", timeSlot));
        criteria.add(Restrictions.eq("voided", false));
        return criteria;
    }

    private Criteria createEhrAppointmentsInTimeSlotByStatusCriteria(
            EhrTimeSlot timeSlot, List<EhrAppointment.EhrAppointmentStatus> statuses) {
        Criteria criteria = createEhrAppointmentsInTimeSlotCriteria(timeSlot);
        criteria.add(Restrictions.in("status", statuses));
        return criteria;
    }

    @Override
    public List<EhrAppointmentDailyCount> getEhrAppointmentDailyCount(String fromDate, String toDate, Location location,
                                                                   Provider provider, EhrAppointment.EhrAppointmentStatus status) throws DAOException {
        String stringQuery = "SELECT count(appointment_id), date_format(ts.start_date,'%Y-%m-%d') as monthDate   "
                + "FROM hospitalcore_appointment `ap`   "
                + "LEFT JOIN  hospitalcore_time_slot `ts` ON (ap.time_slot_id = ts.time_slot_id)  "
                + "LEFT JOIN  hospitalcore_appointment_block `bl` ON (ts.appointment_block_id = bl.appointment_block_id)  "
                + "LEFT JOIN  location `loc` ON (bl.location_id = loc.location_id)   "
                + "LEFT JOIN  provider `pr` ON (bl.provider_id = pr.provider_id)  WHERE ap.voided = false  ";

        if (fromDate != null && toDate != null)
            stringQuery += "AND date_format(ts.start_date,'%Y-%m-%d') between ? AND ?  ";
        if (status != null)
            stringQuery += "AND ap.status = ?   ";
        if (location != null)
            stringQuery += "AND loc.location_id = ?  ";
        if (provider != null) {
            stringQuery += "AND pr.provider_id = ?   ";
        }

        stringQuery += "GROUP BY monthDate ";

        Query query = super.sessionFactory.getCurrentSession().createSQLQuery(stringQuery);

        if (fromDate != null)
            query.setParameter(0, fromDate);
        if (toDate != null)
            query.setParameter(1, toDate);
        if (status != null)
            query.setParameter(2, String.valueOf(status));
        if (location != null)
            query.setParameter(3, location.getId());
        if (provider != null) {
            if (location != null) {
                query.setParameter(4, provider.getId());
            } else {
                query.setParameter(3, provider.getId());
            }
        }
        List<EhrAppointmentDailyCount> dailyCounts = new ArrayList<EhrAppointmentDailyCount>();
        List<Object[]> values = query.list();
        for (Object[] obj : values) {
            EhrAppointmentDailyCount val = new EhrAppointmentDailyCount();
            val.setDailyCount(((BigInteger) obj[0]).intValue());
            val.setDate(obj[1].toString());
            dailyCounts.add(val);
        }
        return dailyCounts;

    }

    @Override
    public List<EhrAppointment> getScheduledEhrAppointmentsForPatients() throws DAOException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
                EhrAppointment.class);
        criteria.add(Restrictions.or(Restrictions.eq("status", SCHEDULED),
                Restrictions.eq("status", RESCHEDULED)));
        criteria.add(Restrictions.eq("voided", false));
        criteria.createAlias("timeSlot", "timeSlot");
        criteria.addOrder(Order.asc("timeSlot.startDate"));

        return criteria.list();
    }

    @Override
    public List<EhrAppointment> getEhrAppointmentsByProvider(Provider provider) throws DAOException {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
                mappedClass);
        criteria.add(Restrictions.or(Restrictions.eq("status", SCHEDULED),
                Restrictions.eq("status", RESCHEDULED), Restrictions.eq("status", INCONSULTATION)));
        criteria.add(Restrictions.eq("voided", false));
        criteria.createAlias("timeSlot", "timeSlot");
        criteria.add(Restrictions.eq("timeSlot.appointmentBlock.provider", provider));
        criteria.addOrder(Order.asc("timeSlot.startDate"));
        return criteria.list();
    }
}
