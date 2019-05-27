package com.sun.org.glassfish.gmbal;

/** Unchecked exception type used for all gmbal exceptions.
 */
public class GmbalException extends RuntimeException {
    private static final long serialVersionUID = -7478444176079980162L;
    public GmbalException( String msg ) {
        super( msg ) ;
    }

    public GmbalException( String msg, Throwable thr ) {
        super( msg, thr ) ;
    }
}
