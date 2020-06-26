package org.openmrs.module.hospitalcore.matcher;

import org.openmrs.Patient;

public class AgeMatcher implements Matcher<Patient> {
	private Integer age;

	private Integer range;

	public AgeMatcher(Integer age, Integer range) {
		this.age = age;
		this.range = range;
	}

	public boolean matches(Object patient) {
		if (((Patient)patient).getAge().intValue() >= this.age.intValue() - this.range.intValue() && ((Patient)patient).getAge().intValue() <= this.age.intValue() + this.range.intValue())
			return true;
		return false;
	}
}
