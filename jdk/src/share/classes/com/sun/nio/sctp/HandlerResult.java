package com.sun.nio.sctp;

/**
 * Defines notification handler results.
 *
 * <P> The {@code HandlerResult} is used to determine the behavior of the
 * channel after it handles a notification from the SCTP stack. Essentially its
 * value determines if the channel should try to receive another notificaiton or
 * a message before returning.
 */
@jdk.Exported
public enum HandlerResult {
    /**
     * Try to receieve another message or notification.
     */
    CONTINUE,

    /**
     * Return without trying to receive any more data.
     */
    RETURN;
}
