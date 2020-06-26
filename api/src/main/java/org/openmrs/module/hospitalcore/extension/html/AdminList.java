package org.openmrs.module.hospitalcore.extension.html;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

public class AdminList extends AdministrationSectionExt {
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}

	public String getTitle() {
		return "hospitalcore.title";
	}

	public Map<String, String> getLinks() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("module/hospitalcore/departmentList.form", "hospitalcore.department.list");
		map.put("module/hospitalcore/listForm.form", "hospitalcore.form.list");
		return map;
	}
}
