package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.InventoryCommonDAO;
import org.openmrs.module.hospitalcore.model.InventoryDrug;
import org.openmrs.module.hospitalcore.model.InventoryDrugFormulation;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;

public class HibernateInventoryCommonDAO implements InventoryCommonDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	SimpleDateFormat formatterExt = new SimpleDateFormat("dd/MM/yyyy");

	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(Patient patient) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(InventoryStoreDrugPatient.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		return criteria.list();
	}

	public List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(Patient patient, String date) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(InventoryStoreDrugPatient.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		if (!date.equals("all")) {
			String startDate = date + " 00:00:00";
			String endDate = date + " 23:59:59";
			try {
				criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("createdOn", this.formatter.parse(startDate)), (Criterion)Restrictions.le("createdOn", this.formatter.parse(endDate))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return criteria.list();
	}

	public List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(InventoryStoreDrugPatient isdpd) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(InventoryStoreDrugPatientDetail.class);
		criteria.add((Criterion)Restrictions.eq("storeDrugPatient", isdpd));
		return criteria.list();
	}

	public InventoryDrug getDrugByName(String name) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(InventoryDrug.class, "drug").add((Criterion)Restrictions.eq("drug.name", name));
		return (InventoryDrug)criteria.uniqueResult();
	}

	public List<Concept> getDrugFrequency() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Concept.class, "con");
		ConceptClass conClass = Context.getConceptService().getConceptClassByName("Frequency");
		criteria.add((Criterion)Restrictions.eq("con.conceptClass", conClass));
		return criteria.list();
	}

	public InventoryDrugFormulation getDrugFormulationById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(InventoryDrugFormulation.class, "drugFormulation").add((Criterion)Restrictions.eq("drugFormulation.id", id));
		return (InventoryDrugFormulation)criteria.uniqueResult();
	}
}
