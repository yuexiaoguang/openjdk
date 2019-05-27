package com.sun.jdi.request;

/**
 * Thrown to indicate that the requested event cannot be modified
 * because it is enabled. Filters can be added only to disabled
 * event requests.
 * Also thrown if an operation is attempted on a deleted request.
 * See {@link EventRequestManager#deleteEventRequest(EventRequest)}
 */
@jdk.Exported
public class InvalidRequestStateException extends RuntimeException {
    private static final long serialVersionUID = -3774632428543322148L;
    public InvalidRequestStateException()
    {
        super();
    }

    public InvalidRequestStateException(String s)
    {
        super(s);
    }
}
