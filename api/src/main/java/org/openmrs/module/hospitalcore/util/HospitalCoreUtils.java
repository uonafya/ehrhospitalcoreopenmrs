/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */


package org.openmrs.module.hospitalcore.util;

import java.util.HashMap;
import java.util.Map;

public class HospitalCoreUtils {

	public static Map<String, Object> buildParameters(Object... objs){
		Map<String, Object> parameters = new HashMap<String, Object>();
		String name = null;
		Object data = null;
		for(int i=0; i<objs.length; i++){
			if(i%2==0){
				name = objs[i].toString();
			} else {
				data = objs[i];
				parameters.put(name, data);
			}
		}
		return parameters;
	}
}
