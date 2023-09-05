package org.openmrs.module.hospitalcore.model;

import org.openmrs.Location;
import org.openmrs.LocationAttribute;
import org.openmrs.module.hospitalcore.AbstractCustomizableWrapper;

/**
 * A facility wrapper for a location
 */
public class Facility extends AbstractCustomizableWrapper<Location, LocationAttribute> {

    /**
     * Creates a new facility wrapper for a location
     * @param location the location
     */
    public Facility(Location location) {
        super(location);
    }

    /**
     * Gets the master facility code
     * @return the code
     */
    public String getMflCode() {
        return (String) getAsAttribute("8a845a89-6aa5-4111-81d3-0af31c45c002");
    }

    /**
     * Gets the country
     * @return the country
     */
    public String getCountry() {
        return target.getCountry();
    }

    /**
     * Gets the province
     * @return the province
     */
    public String getProvince() {
        return target.getStateProvince();
    }

    /**
     * Gets the county
     * @return the county
     */
    public String getCounty() {
        return target.getCountyDistrict();
    }

    /**
     * Gets the district
     * @return the district
     */
    public String getDistrict() {
        return target.getAddress6();
    }

    /**
     * Gets the division
     * @return the division
     */
    public String getDivision() {
        return target.getAddress5();
    }

    /**
     * Gets the post code
     * @return the post code
     */
    public String getPostCode() {
        return target.getPostalCode();
    }

    /**
     * Gets the landline telephone number
     * @return the number
     */
    public String getTelephoneLandline() {
        return (String) getAsAttribute("4ecb5b3f-1518-4056-a266-c4da1def45f5");
    }

    /**
     * Gets the mobile telephone number
     * @return the number
     */
    public String getTelephoneMobile() {
        return (String) getAsAttribute("8760f471-b2bb-4ded-8970-badf95d3bb44");
    }

    /**
     * Gets the fax telephone number
     * @return the number
     */
    public String getTelephoneFax() {
        return (String) getAsAttribute("29e1e758-d03e-4e84-a55e-288fa63d533a");
    }
}