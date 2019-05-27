package com.sun.xml.internal.ws.spi.db;



/**
 * Accesses a particular property of a bean.
 *
 * <p>
 * This interface allows JAX-RPC to access an element property of a JAXB bean.
 *
 * <p>
 * <b>Subject to change without notice</b>.
 */
public interface PropertyAccessor<B,V> {

    /**
     * Gets the value of the property of the given bean object.
     *
     * @param bean
     *      must not be null.
     * @throws AccessorException
     *      if failed to set a value. For example, the getter method
     *      may throw an exception.
     *
     * @since 2.0 EA1
     */
    public abstract V get(B bean) throws DatabindingException;

    /**
     * Sets the value of the property of the given bean object.
     *
     * @param bean
     *      must not be null.
     * @param value
     *      the value to be set. Setting value to null means resetting
     *      to the VM default value (even for primitive properties.)
     * @throws AccessorException
     *      if failed to set a value. For example, the setter method
     *      may throw an exception.
     *
     * @since 2.0 EA1
     */
    public abstract void set(B bean,V value) throws DatabindingException;
}
