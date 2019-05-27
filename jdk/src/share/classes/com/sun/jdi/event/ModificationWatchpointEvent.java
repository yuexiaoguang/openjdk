package com.sun.jdi.event;

import com.sun.jdi.*;

/**
 * Notification of a field modification in the
 * target VM.
 */
@jdk.Exported
public interface ModificationWatchpointEvent extends WatchpointEvent {

    /**
     * Value that will be assigned to the field when the instruction
     * completes.
     */
    Value valueToBe();
}
