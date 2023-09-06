package org.openmrs.module.hospitalcore;

public enum IdentifierTypes {
    OPD(1), IPD(2);
    private final Integer value;
    IdentifierTypes(Integer value) {
        this.value = value;
    }


}
