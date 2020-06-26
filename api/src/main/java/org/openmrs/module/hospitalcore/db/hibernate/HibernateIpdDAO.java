package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.IpdDAO;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.openmrs.module.hospitalcore.model.IpdPatientVitalStatistics;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.WardBedStrength;

public class HibernateIpdDAO implements IpdDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<IpdPatientAdmission> getAllIpdPatientAdmission() throws DAOException {
		return this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmission.class).list();
	}

	public List<IpdPatientAdmission> getAllIndoorPatient() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmission.class);
		return criteria.list();
	}

	public List<IpdPatientAdmissionLog> getAllIndoorPatientFromAdmissionLog(String searchKey, int page, int pgSize) throws DAOException {
		String hql = "select ipal from IpdPatientAdmissionLog ipal where ipal.indoorStatus=1 and ipal.status like 'admitted' and (ipal.patientIdentifier LIKE '%" + searchKey + "%' OR ipal.patientName LIKE '" + searchKey + "%')";
		int firstResult = (page - 1) * pgSize;
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql).setFirstResult(firstResult).setMaxResults(pgSize);
		List<IpdPatientAdmissionLog> list = q.list();
		return list;
	}

	public int countGetAllIndoorPatientFromAdmissionLog(String searchKey, int page) throws DAOException {
		String hql = "select ipal from IpdPatientAdmissionLog ipal where ipal.indoorStatus=1 and ipal.status like 'admitted' and (ipal.patientIdentifier LIKE '%" + searchKey + "%' OR ipal.patientName LIKE '" + searchKey + "%')";
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		List<IpdPatientAdmissionLog> list = q.list();
		return list.size();
	}

	public List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(Integer patientId, Integer admissionWardId, String status, Integer min, Integer max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmissionLog.class, "ipdPatientAdmissionLog");
		if (patientId != null && patientId.intValue() > 0) {
			criteria.createAlias("ipdPatientAdmissionLog.patient", "patient");
			criteria.add((Criterion)Restrictions.eq("patient.patientId", patientId));
		}
		if (admissionWardId != null && admissionWardId.intValue() > 0)
			criteria.add((Criterion)Restrictions.eq("ipdPatientAdmissionLog.admissionWard.conceptId", admissionWardId));
		if (StringUtils.isNotBlank(status))
			criteria.add((Criterion)Restrictions.eq("ipdPatientAdmissionLog.status", status));
		if (max.intValue() > 0)
			criteria.setFirstResult(min.intValue()).setMaxResults(max.intValue());
		criteria.addOrder(Order.desc("ipdPatientAdmissionLog.admissionDate"));
		List<IpdPatientAdmissionLog> list = criteria.list();
		return list;
	}

	public List<IpdPatientAdmitted> getAllIpdPatientAdmitted() throws DAOException {
		return this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmitted.class).list();
	}

	public List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog() throws DAOException {
		return this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmittedLog.class).list();
	}

	public IpdPatientAdmission getIpdPatientAdmission(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmission.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (IpdPatientAdmission)criteria.uniqueResult();
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmissionLog.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (IpdPatientAdmissionLog)criteria.uniqueResult();
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(OpdPatientQueueLog opdLog) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmissionLog.class);
		criteria.add((Criterion)Restrictions.eq("opdLog", opdLog));
		return (IpdPatientAdmissionLog)criteria.uniqueResult();
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Encounter encounter) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmissionLog.class);
		criteria.add((Criterion)Restrictions.eq("ipdEncounter", encounter));
		return (IpdPatientAdmissionLog)criteria.uniqueResult();
	}

	public IpdPatientAdmitted getIpdPatientAdmitted(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmitted.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (IpdPatientAdmitted)criteria.uniqueResult();
	}

	public IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmittedLog.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (IpdPatientAdmittedLog)criteria.uniqueResult();
	}

	public IpdPatientAdmission saveIpdPatientAdmission(IpdPatientAdmission admission) throws DAOException {
		return (IpdPatientAdmission)this.sessionFactory.getCurrentSession().merge(admission);
	}

	public IpdPatientAdmissionLog saveIpdPatientAdmissionLog(IpdPatientAdmissionLog admissionLog) throws DAOException {
		return (IpdPatientAdmissionLog)this.sessionFactory.getCurrentSession().merge(admissionLog);
	}

	public IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted admitted) throws DAOException {
		return (IpdPatientAdmitted)this.sessionFactory.getCurrentSession().merge(admitted);
	}

	public IpdPatientAdmittedLog saveIpdPatientAdmittedLog(IpdPatientAdmittedLog admittedLog) throws DAOException {
		return (IpdPatientAdmittedLog)this.sessionFactory.getCurrentSession().merge(admittedLog);
	}

	public List<IpdPatientAdmission> searchIpdPatientAdmission(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, String wardId, String status) throws APIException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmission.class, "patientAdmission");
		if (StringUtils.isNotBlank(fromDate) && StringUtils.isBlank(toDate)) {
			String startFromDate = fromDate + " 00:00:00";
			String endFromDate = fromDate + " 23:59:59";
			try {
				criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("patientAdmission.admissionDate", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("patientAdmission.admissionDate", this.formatter.parse(endFromDate))));
			} catch (Exception e) {
				this.log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		} else if (StringUtils.isBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
			String startToDate = toDate + " 00:00:00";
			String endToDate = toDate + " 23:59:59";
			try {
				criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("patientAdmission.admissionDate", this.formatter.parse(startToDate)), (Criterion)Restrictions.le("patientAdmission.admissionDate", this.formatter.parse(endToDate))));
			} catch (Exception e) {
				this.log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		} else if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
			String startToDate = fromDate + " 00:00:00";
			String endToDate = toDate + " 23:59:59";
			try {
				criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("patientAdmission.admissionDate", this.formatter.parse(startToDate)), (Criterion)Restrictions.le("patientAdmission.admissionDate", this.formatter.parse(endToDate))));
			} catch (Exception e) {
				this.log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		}
		String prefix = Context.getAdministrationService().getGlobalProperty("registration.identifier_prefix");
		if (StringUtils.isNotBlank(patientSearch)) {
			if (patientSearch.contains("-") && !patientSearch.contains(prefix))
				patientSearch = prefix + patientSearch;
			if (patientSearch.contains(prefix)) {
				criteria.add((Criterion)Restrictions.eq("patientAdmission.patientIdentifier", patientSearch));
			} else {
				criteria.add((Criterion)Restrictions.like("patientAdmission.patientName", "%" + patientSearch + "%"));
			}
		}
		if (CollectionUtils.isNotEmpty(userIds)) {
			criteria.createAlias("patientAdmission.opdAmittedUser", "user");
			criteria.add(Restrictions.in("user.id", userIds));
		}
		criteria.createAlias("patientAdmission.admissionWard", "ward");
		criteria.add((Criterion)Restrictions.eq("ward.conceptId", Integer.valueOf(Integer.parseInt(wardId))));
		if (StringUtils.isNotBlank(status))
			criteria.add((Criterion)Restrictions.eq("patientAdmission.status", status));
		criteria.addOrder(Order.asc("patientAdmission.admissionDate"));
		List<IpdPatientAdmission> list = criteria.list();
		return list;
	}

	public List<IpdPatientAdmitted> searchIpdPatientAdmitted(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, String wardId, String status) throws APIException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmitted.class, "patientAdmitted");
		if (!StringUtils.isBlank(fromDate) && StringUtils.isBlank(toDate)) {
			String startFromDate = fromDate + " 00:00:00";
			String endFromDate = fromDate + " 23:59:59";
			try {
				criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("patientAdmitted.admissionDate", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("patientAdmitted.admissionDate", this.formatter.parse(endFromDate))));
			} catch (Exception e) {
				this.log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		} else if (StringUtils.isBlank(fromDate) && !StringUtils.isBlank(toDate)) {
			String startToDate = toDate + " 00:00:00";
			String endToDate = toDate + " 23:59:59";
			try {
				criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("patientAdmitted.admissionDate", this.formatter.parse(startToDate)), (Criterion)Restrictions.le("patientAdmitted.admissionDate", this.formatter.parse(endToDate))));
			} catch (Exception e) {
				this.log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		} else if (!StringUtils.isBlank(fromDate) && !StringUtils.isBlank(toDate)) {
			String startToDate = fromDate + " 00:00:00";
			String endToDate = toDate + " 23:59:59";
			try {
				criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("patientAdmitted.admissionDate", this.formatter.parse(startToDate)), (Criterion)Restrictions.le("patientAdmitted.admissionDate", this.formatter.parse(endToDate))));
			} catch (Exception e) {
				this.log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		}
		String prefix = Context.getAdministrationService().getGlobalProperty("registration.identifier_prefix");
		if (StringUtils.isNotBlank(patientSearch)) {
			if (patientSearch.contains("-") && !patientSearch.contains(prefix))
				patientSearch = prefix + patientSearch;
			if (patientSearch.contains(prefix)) {
				criteria.add((Criterion)Restrictions.eq("patientAdmitted.patientIdentifier", patientSearch));
			} else {
				criteria.add((Criterion)Restrictions.like("patientAdmitted.patientName", "%" + patientSearch + "%"));
			}
		}
		if (CollectionUtils.isNotEmpty(userIds)) {
			criteria.createAlias("patientAdmitted.ipdAdmittedUser", "user");
			criteria.add(Restrictions.in("user.id", userIds));
		}
		criteria.createAlias("patientAdmitted.admittedWard", "ward");
		criteria.add((Criterion)Restrictions.eq("ward.conceptId", Integer.valueOf(Integer.parseInt(wardId))));
		if (StringUtils.isNotBlank(status))
			criteria.add((Criterion)Restrictions.eq("patientAdmitted.status", status));
		return criteria.list();
	}

	public void removeIpdPatientAdmission(IpdPatientAdmission admission) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(admission);
	}

	public void removeIpdPatientAdmitted(IpdPatientAdmitted admitted) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(admitted);
	}

	public List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(Integer patientId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmittedLog.class);
		criteria.add((Criterion)Restrictions.eq("patient.id", patientId));
		criteria.addOrder(Order.desc("admissionDate"));
		return criteria.list();
	}

	public IpdPatientAdmitted getAdmittedByPatientId(Integer patientId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmitted.class);
		criteria.add((Criterion)Restrictions.eq("patient.id", patientId));
		List<IpdPatientAdmitted> list = criteria.list();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	public IpdPatientAdmitted getAdmittedByAdmissionLogId(IpdPatientAdmissionLog ipdPatientAdmissionLog) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmitted.class);
		criteria.add((Criterion)Restrictions.eq("patientAdmissionLog", ipdPatientAdmissionLog));
		return (IpdPatientAdmitted)criteria.uniqueResult();
	}

	public IpdPatientVitalStatistics saveIpdPatientVitalStatistics(IpdPatientVitalStatistics vitalStatistics) throws DAOException {
		return (IpdPatientVitalStatistics)this.sessionFactory.getCurrentSession().merge(vitalStatistics);
	}

	public List<Concept> getDiet() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Concept.class, "con");
		ConceptClass conClass = Context.getConceptService().getConceptClassByName("Diet");
		criteria.add((Criterion)Restrictions.eq("con.conceptClass", conClass));
		return criteria.list();
	}

	public List<IpdPatientVitalStatistics> getIpdPatientVitalStatistics(Integer patientId, Integer patientAdmissionLogId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientVitalStatistics.class);
		criteria.add((Criterion)Restrictions.eq("patient.personId", patientId));
		criteria.add((Criterion)Restrictions.eq("ipdPatientAdmissionLog.id", patientAdmissionLogId));
		return criteria.list();
	}

	public List<IpdPatientAdmitted> getAllIpdAdmittedPatientByWardId(Integer wardId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmitted.class);
		criteria.add((Criterion)Restrictions.eq("admittedWard.id", wardId));
		return this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmitted.class).list();
	}

	public WardBedStrength getWardBedStrengthByWardId(Integer wardId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(WardBedStrength.class);
		criteria.add((Criterion)Restrictions.eq("ward.id", wardId));
		List<WardBedStrength> list = criteria.list();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	public void saveWardBedStrength(WardBedStrength wardBedStrength) throws DAOException {
		this.sessionFactory.getCurrentSession().saveOrUpdate(wardBedStrength);
	}

	public IpdPatientAdmission getIpdPatientAdmissionByEncounter(Encounter encounter) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmission.class);
		criteria.add((Criterion)Restrictions.eq("ipdEncounter", encounter));
		return (IpdPatientAdmission)criteria.uniqueResult();
	}

	public IpdPatientAdmission getIpdPatientAdmissionByPatientId(Patient patientId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmission.class);
		criteria.add((Criterion)Restrictions.eq("patient", patientId));
		return (IpdPatientAdmission)criteria.uniqueResult();
	}
}
