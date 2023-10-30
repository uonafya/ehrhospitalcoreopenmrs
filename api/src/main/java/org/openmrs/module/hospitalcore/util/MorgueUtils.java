package org.openmrs.module.hospitalcore.util;

public class MorgueUtils {
    public static final Integer BODY_PENDING_ADMISSION = 0;
    public static final Integer BODY_CLEARED_FOR_ADMISSION = 1;
    public static final Integer BODY_ADMITTED = 2;
    public static final Integer BODY_DISCHARGED = 3;
    public static final Integer BODY_DECEASED = 99;

    public static String getStringValue(Integer status) {
        String state = "";
        if(status.equals(BODY_PENDING_ADMISSION)) {
            state = "Body pending admission";
        }
        else if(status.equals(BODY_CLEARED_FOR_ADMISSION)) {
            state = "Body cleared for admission";
        }
        else if(status.equals(BODY_ADMITTED)) {
            state = "Body admitted";
        }
        else if(status.equals(BODY_DISCHARGED)) {
            state = "Body discharged";
        }
        else if(status.equals(BODY_DECEASED)) {
            state = "Deceased";
        }
        return state;
    }
}
