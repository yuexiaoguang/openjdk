package com.sun.corba.se.spi.monitoring;

import java.io.Closeable;
import com.sun.corba.se.spi.orb.ORB;
import com.sun.corba.se.spi.monitoring.MonitoredObject;
import java.util.*;

/**
 * <p>
 * Monitoring Manager will have a 1 to 1 association with the ORB. This
 * gives access to the top level Monitored Object, using which more
 * Monitored Objects and Attributes can be added and traversed.
 * </p>
 */
public interface MonitoringManager extends Closeable {

  ///////////////////////////////////////
  // operations

/**
 * <p>
 * Gets the Root Monitored Object which contains a Hierarchy Of Monitored
 * Objects exposing various Monitorable Attributes of Various modules.
 * </p>
 * <p>
 *
 * @param MonitoredObject ...
 * </p>
 */
    public MonitoredObject getRootMonitoredObject();
/**
 * <p>
 * Initialize is called whenever there is a start monitoring call to CORBA
 * MBean. This will result in triaging initialize to all the
 * MonitoredObjects and it's Monitored Attributes.
 * </p>
 *
 */
    public void clearState();

} // end MonitoringManager
