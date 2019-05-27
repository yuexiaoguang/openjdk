package sun.jvmstat.monitor.remote;

import sun.jvmstat.monitor.*;

/**
 * Interface to support asynchronous polling of the exported
 * instrumentation of a target Java Virtual Machine.
 */
public interface BufferedMonitoredVm extends MonitoredVm {

    /**
     * Interface to get the bytes associated with the instrumentation
     * for the target Java Virtual Machine.
     *
     * @return byte[] - a byte array containing the current bytes
     *                  for the instrumentation exported by the
     *                  target Java Virtual Machine.
     */
    byte[] getBytes();

    /**
     * Interface to get the the size of the instrumentation buffer
     * for the target Java Virtual Machine.
     *
     * @return int - the size of the instrumentation buffer for the
     *               target Java Virtual Machine.
     */
    int getCapacity();
}
