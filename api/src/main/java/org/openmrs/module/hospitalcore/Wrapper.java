package org.openmrs.module.hospitalcore;

/**
 * Interface for wrapper classes
 */
public interface Wrapper<T> {

    /**
     * Gets the wrapped object
     * @return the object
     */
    T getTarget();
}