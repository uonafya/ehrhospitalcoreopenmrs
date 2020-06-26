package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.RadiologyCommonDAO;
import org.openmrs.module.hospitalcore.model.RadiologyTest;

public class HibernateRadiologyCommonDAO implements RadiologyCommonDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	SimpleDateFormat formatterExt = new SimpleDateFormat("dd/MM/yyyy");

	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<RadiologyTest> getAllTest(Patient patient) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		criteria.add((Criterion)Restrictions.eq("status", "completed"));
		return criteria.list();
	}

	public ConceptAnswer getConceptAnswer(Concept concept) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ConceptAnswer.class);
		criteria.add((Criterion)Restrictions.eq("answerConcept", concept));
		criteria.add((Criterion)Restrictions.ne("concept", Context.getConceptService().getConcept("SPECIAL RADIOLOGY TESTS")));
		criteria.add((Criterion)Restrictions.ne("concept", Context.getConceptService().getConcept("ROUTINE RADIOLOGY TESTS")));
		return (ConceptAnswer)criteria.uniqueResult();
	}

	public List<RadiologyTest> getAllTest(Patient patient, String date) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class, "radiologytest");
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		String dat = date;
		String startFromDate = dat + " 00:00:00";
		String endFromDate = dat + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("radiologytest.date", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("radiologytest.date", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("status", "completed"));
		return criteria.list();
	}

	public List<RadiologyTest> getAllTest(Patient patient, String date, Concept concept) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class, "radiologytest");
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		String dat = date;
		String startFromDate = dat + " 00:00:00";
		String endFromDate = dat + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("radiologytest.date", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("radiologytest.date", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("status", "completed"));
		Collection<ConceptAnswer> conanss = concept.getAnswers();
		ArrayList<Concept> al = new ArrayList();
		for (ConceptAnswer conans : conanss)
			al.add(conans.getAnswerConcept());
		criteria.add(Restrictions.in("concept", al));
		return criteria.list();
	}

	public List<RadiologyTest> getAllSubTest(Patient patient, String date, Concept concept) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class, "radiologytest");
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		criteria.add((Criterion)Restrictions.eq("concept", concept));
		String dat = date;
		String startFromDate = dat + " 00:00:00";
		String endFromDate = dat + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("radiologytest.date", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("radiologytest.date", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("status", "completed"));
		return criteria.list();
	}
}
