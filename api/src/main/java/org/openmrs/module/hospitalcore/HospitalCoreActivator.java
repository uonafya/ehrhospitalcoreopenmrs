package org.openmrs.module.hospitalcore;

import java.math.RoundingMode;
import java.util.Currency;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.hospitalcore.util.Money;

public class HospitalCoreActivator implements ModuleActivator {
	private Log log = LogFactory.getLog(getClass());

	public void contextRefreshed() {}

	public void started() {
		this.log.info("Started HOSPITALCORE Module");
		Money.init(Currency.getInstance("INR"), RoundingMode.HALF_EVEN);
	}

	public void stopped() {
		this.log.info("Stoped HOSPITALCORE Module");
	}

	public void willRefreshContext() {}

	public void willStart() {
		this.log.info("Starting HOSPITALCORE Module");
	}

	public void willStop() {
		this.log.info("Shutting down HOSPITALCORE Module");
	}
}
