package org.openmrs.module.hospitalcore.util;

import org.apache.commons.lang.StringUtils;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;

public class GlobalPropertyUtil {
	public static Boolean getBoolean(String globalPropertyName, Boolean defaultValue) {
		String value = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		Boolean result = defaultValue;
		if (!StringUtils.isBlank(value))
			result = Boolean.valueOf(Boolean.parseBoolean(value));
		return result;
	}

	public static String getString(String globalPropertyName, String defaultValue) {
		String value = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		String result = defaultValue;
		if (!StringUtils.isBlank(value))
			result = value;
		return result;
	}

	public static Integer getInteger(String globalPropertyName, Integer defaultValue) {
		String value = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		Integer result = defaultValue;
		if (!StringUtils.isBlank(value))
			try {
				result = Integer.valueOf(Integer.parseInt(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	public static void setString(String globalPropertyName, String value) {
		GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(globalPropertyName);
		if (gp != null) {
			gp.setPropertyValue(value);
			Context.getAdministrationService().saveGlobalProperty(gp);
		}
	}

	public static void saveGlobalProperty(String name, String description, Object value) {
		GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(name);
		if (gp == null)
			gp = new GlobalProperty();
		gp.setProperty(name);
		gp.setDescription(description);
		gp.setPropertyValue(value.toString());
		Context.getAdministrationService().saveGlobalProperty(gp);
	}
}
