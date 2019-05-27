package sun.nio.ch.sctp;

import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.Notification;

/**
 * All Notification implemenations MUST implement this interface to provide
 * access to the native association identidier.
 */
interface SctpNotification extends Notification {
    int assocId();
    void setAssociation(Association association);
}
