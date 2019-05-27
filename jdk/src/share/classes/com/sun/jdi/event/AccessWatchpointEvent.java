package com.sun.jdi.event;

import com.sun.jdi.*;

/**
 * Notification of a field access in the target VM. Field modifications
 * are not considered field accesses.
 */
@jdk.Exported
public interface AccessWatchpointEvent extends WatchpointEvent {
}
