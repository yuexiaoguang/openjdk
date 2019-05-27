package sun.print;

/**
 * Lookup services may implement this interface so that clients may
 * avoid blocking waiting for all services to be located.
 */
public interface BackgroundServiceLookup {

   /**
    *
    */
    public void getServicesInbackground(BackgroundLookupListener listener);

}
