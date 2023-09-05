package org.openmrs.module.hospitalcore;

import org.openmrs.OpenmrsObject;

/**
 * Abstract base class for wrappers of OpenmmrsObject subclasses
 */
public abstract class AbstractObjectWrapper<T extends OpenmrsObject> implements Wrapper<T> {

    protected T target;

    /**
     * Creates a new wrapper
     * @param target the target object
     */
    public AbstractObjectWrapper(T target) {
        this.target = target;
    }

    /**
     * @see Wrapper#getTarget()
     */
    @Override
    public T getTarget() {
        return target;
    }

    /**
     * Gets the target object id
     * @return the id
     */
    public Integer getId() {
        return target.getId();
    }

    /**
     * Gets the target object UUID
     * @return the UUID
     */
    public String getUuid() {
        return target.getUuid();
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractObjectWrapper that = (AbstractObjectWrapper) o;

        if (!target.equals(that.target)) return false;

        return true;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return target.hashCode();
    }
}
