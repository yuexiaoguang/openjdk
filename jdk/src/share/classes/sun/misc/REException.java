package sun.misc;

/**
 * A class to signal exception from the RegexpPool class.
 */
public class REException extends Exception {

    private static final long serialVersionUID = 4656584872733646963L;

    REException (String s) {
        super(s);
    }
}
