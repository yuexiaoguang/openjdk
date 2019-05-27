package com.sun.jmx.snmp.internal;

/**
 * Interface that every SNMP model must implement in order to be integrated in the engine framework.
 * <p><b>This API is a Sun Microsystems internal API  and is subject
 * to change without notice.</b></p>
 */
public interface SnmpModel {

    /**
     * Returns the sub system that manages this model.
     * @return The sub system.
     */
    public SnmpSubSystem getSubSystem();
    /**
     * A human readable model name.
     * @return The model name.
     */
    public String getName();
}
