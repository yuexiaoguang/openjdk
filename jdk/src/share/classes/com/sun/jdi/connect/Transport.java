package com.sun.jdi.connect;

import com.sun.jdi.connect.spi.TransportService;        // for javadoc

/**
 * A method of communication between a debugger and a target VM.
 *
 * <p> A Transport represents the transport mechanism used by a
 * {@link com.sun.jdi.connect.Connector Connector} to establish a
 * connection with a target VM. It consists of a name which is obtained
 * by invoking the {@link #name} method. Furthermore, a Transport
 * encapsulates a {@link com.sun.jdi.connect.spi.TransportService
 * TransportService} which is the underlying service used
 * to establish connections and exchange Java Debug Wire Protocol
 * (JDWP) packets with a target VM.
 */
@jdk.Exported
public interface Transport {
    /**
     * Returns a short identifier for the transport.
     *
     * @return the name of this transport.
     */
    String name();
}
