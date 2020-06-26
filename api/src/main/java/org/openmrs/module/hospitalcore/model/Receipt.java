package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class Receipt implements Serializable {
	private static final long serialVersionUID = -4573526140331707301L;

	private Integer id;

	private Date paidDate;

	public Date getPaidDate() {
		return this.paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
