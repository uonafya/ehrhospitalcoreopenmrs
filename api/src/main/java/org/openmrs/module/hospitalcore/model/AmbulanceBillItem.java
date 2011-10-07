/**
 *  Copyright 2011 Health Information Systems Project of India
 *
 *  This file is part of Hospital-core module.
 *
 *  Hospital-core module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Hospital-core module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Hospital-core module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 */
public class AmbulanceBillItem implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer ambulanceBillItemId;
    
    private String name;

    private Ambulance ambulance;
    
    private AmbulanceBill ambulanceBill;
    
    private Integer numberOfTrip;
    
    private BigDecimal amount;
    
    private Date createdDate;
	
	private Boolean voided = false;
	
	private Date voidedDate;

	
    public String getName() {
    	return name;
    }

	
    public void setName(String name) {
    	this.name = name;
    }

	
    public Ambulance getAmbulance() {
    	return ambulance;
    }

	
    public void setAmbulance(Ambulance ambulance) {
    	this.ambulance = ambulance;
    }

	
    public AmbulanceBill getAmbulanceBill() {
    	return ambulanceBill;
    }

	
    public void setAmbulanceBill(AmbulanceBill ambulanceBill) {
    	this.ambulanceBill = ambulanceBill;
    }

	
    public Integer getNumberOfTrip() {
    	return numberOfTrip;
    }

	
    public void setNumberOfTrip(Integer numberOfTrip) {
    	this.numberOfTrip = numberOfTrip;
    }

	
    public BigDecimal getAmount() {
    	return amount;
    }

	
    public void setAmount(BigDecimal amount) {
    	this.amount = amount;
    }

	
    public Date getCreatedDate() {
    	return createdDate;
    }

	
    public void setCreatedDate(Date createdDate) {
    	this.createdDate = createdDate;
    }

	
    public Boolean getVoided() {
    	return voided;
    }

	
    public void setVoided(Boolean voided) {
    	this.voided = voided;
    }

	
    public Date getVoidedDate() {
    	return voidedDate;
    }

	
    public void setVoidedDate(Date voidedDate) {
    	this.voidedDate = voidedDate;
    }


	
    public Integer getAmbulanceBillItemId() {
    	return ambulanceBillItemId;
    }


	
    public void setAmbulanceBillItemId(Integer ambulanceBillItemId) {
    	this.ambulanceBillItemId = ambulanceBillItemId;
    }
}
