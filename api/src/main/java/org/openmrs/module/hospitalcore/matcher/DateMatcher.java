package org.openmrs.module.hospitalcore.matcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;

public class DateMatcher implements Matcher<Patient> {
	private Date date;

	private Integer range;

	public DateMatcher(String date, Integer range) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		date = date + " 00:00:00";
		this.date = sdf.parse(date);
		this.range = range;
	}

	public boolean matches(Object obj) {
		Date beforeDate;
		Patient patient = (Patient)obj;
		Date checkedDate = getLastVisitDate(patient);
		if (this.range.intValue() == 1) {
			beforeDate = this.date;
		} else {
			beforeDate = calcDate(this.date, Integer.valueOf(-this.range.intValue()));
		}
		Date afterDate = calcDate(this.date, this.range);
		System.out.println("checkedDate ==> " + checkedDate);
		System.out.println("beforeDate ==> " + beforeDate);
		System.out.println("afterDate ==> " + afterDate);
		if (checkedDate != null) {
			if (checkedDate.compareTo(beforeDate) >= 0 && checkedDate.compareTo(afterDate) <= 0)
				return true;
		} else {
			System.out.println("Can't find the checked date of patient with id " + patient.getId());
		}
		return false;
	}

	private Date getLastVisitDate(Patient patient) {
		PatientDashboardService pds = (PatientDashboardService)Context.getService(PatientDashboardService.class);
		EncounterType encType = Context.getEncounterService().getEncounterType("OPDENCOUNTER");
		List<Encounter> encounters = pds.getEncounter(patient, new Location(Integer.valueOf(1)), encType, null);
		System.out.println("encounters.size() ==> " + encounters.size());
		Encounter latestEncounter = getLatestEncounter(encounters);
		if (latestEncounter != null)
			return latestEncounter.getDateCreated();
		return null;
	}

	private Encounter getLatestEncounter(List<Encounter> encounters) {
		Date d = new Date(Long.MIN_VALUE);
		Encounter latestEncounter = null;
		for (Encounter e : encounters) {
			if (d.before(e.getDateCreated())) {
				d = e.getDateCreated();
				latestEncounter = e;
			}
		}
		return latestEncounter;
	}

	private Date calcDate(Date date, Integer range) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(5, range.intValue());
		return c.getTime();
	}
}
