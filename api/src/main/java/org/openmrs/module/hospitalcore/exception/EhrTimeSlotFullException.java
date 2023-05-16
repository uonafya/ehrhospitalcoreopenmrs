package org.openmrs.module.hospitalcore.exception;

public class EhrTimeSlotFullException extends Exception {

    private static final long serialVersionUID = 1L;

    public EhrTimeSlotFullException() {
        super();
    }

    public EhrTimeSlotFullException(String message) {
        super(message);
    }

}
