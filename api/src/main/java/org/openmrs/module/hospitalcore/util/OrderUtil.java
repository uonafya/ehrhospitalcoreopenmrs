package org.openmrs.module.hospitalcore.util;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.openmrs.EncounterType;
import org.openmrs.OrderType;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;

public class OrderUtil {
	private static final String RADIOLOGY_ORDER_TYPE = "billing.encounterType.radiology";

	public static void saveRadiologyOrder(PatientServiceBillItem item) {
		String radiologyEncounterTypeName = GlobalPropertyUtil.getString("billing.encounterType.radiology", null);
		if (!StringUtils.isBlank(radiologyEncounterTypeName)) {
			EncounterType et = Context.getEncounterService().getEncounterType(radiologyEncounterTypeName);
		}
	}

	public static OrderType getOrderTypeByName(String orderTypeName) {
		OrderType orderType = null;
		List<OrderType> allOrderTypes = Context.getOrderService().getAllOrderTypes();
		Iterator<OrderType> allOrderTypesIterator = allOrderTypes.iterator();
		while (allOrderTypesIterator.hasNext()) {
			OrderType orderTypeTemp = allOrderTypesIterator.next();
			if (orderTypeTemp.getName().equals(orderTypeName))
				orderType = Context.getOrderService().getOrderType(orderTypeTemp.getId());
		}
		return orderType;
	}
}
