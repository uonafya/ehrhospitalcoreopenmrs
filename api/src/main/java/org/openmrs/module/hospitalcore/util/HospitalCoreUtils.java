package org.openmrs.module.hospitalcore.util;

import java.util.HashMap;
import java.util.Map;

public class HospitalCoreUtils {
	public static Map<String, Object> buildParameters(Object... objs) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String name = null;
		Object data = null;
		for (int i = 0; i < objs.length; i++) {
			if (i % 2 == 0) {
				name = objs[i].toString();
			} else {
				data = objs[i];
				parameters.put(name, data);
			}
		}
		return parameters;
	}
}
