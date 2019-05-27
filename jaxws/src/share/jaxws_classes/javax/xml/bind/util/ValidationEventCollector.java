package javax.xml.bind.util;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link javax.xml.bind.ValidationEventHandler ValidationEventHandler}
 * implementation that collects all events.
 *
 * <p>
 * To use this class, create a new instance and pass it to the setEventHandler
 * method of the Validator, Unmarshaller, Marshaller class.  After the call to
 * validate or unmarshal completes, call the getEvents method to retrieve all
 * the reported errors and warnings.
 */
public class ValidationEventCollector implements ValidationEventHandler
{
    private final List<ValidationEvent> events = new ArrayList<ValidationEvent>();

    /**
     * Return an array of ValidationEvent objects containing a copy of each of
     * the collected errors and warnings.
     *
     * @return
     *      a copy of all the collected errors and warnings or an empty array
     *      if there weren't any
     */
    public ValidationEvent[] getEvents() {
        return events.toArray(new ValidationEvent[events.size()]);
    }

    /**
     * Clear all collected errors and warnings.
     */
    public void reset() {
        events.clear();
    }

    /**
     * Returns true if this event collector contains at least one
     * ValidationEvent.
     *
     * @return true if this event collector contains at least one
     *         ValidationEvent, false otherwise
     */
    public boolean hasEvents() {
        return !events.isEmpty();
    }

    public boolean handleEvent( ValidationEvent event ) {
        events.add(event);

        boolean retVal = true;
        switch( event.getSeverity() ) {
            case ValidationEvent.WARNING:
                retVal = true; // continue validation
                break;
            case ValidationEvent.ERROR:
                retVal = true; // continue validation
                break;
            case ValidationEvent.FATAL_ERROR:
                retVal = false; // halt validation
                break;
            default:
                _assert( false,
                         Messages.format( Messages.UNRECOGNIZED_SEVERITY,
                                 event.getSeverity() ) );
                break;
        }

        return retVal;
    }

    private static void _assert( boolean b, String msg ) {
        if( !b ) {
            throw new InternalError( msg );
        }
    }
}
