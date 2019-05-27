package sun.management.counter.perf;

public class InstrumentationException extends RuntimeException {
    /**
     * Constructs a <tt>InstrumentationException</tt> with no
     * detail message.
     */
     public InstrumentationException() {
     }

    /**
     * Constructs a <tt>InstrumentationException</tt> with a specified
     * detail message.
     *
     * @param message the detail message
     */
     public InstrumentationException(String message) {
         super(message);
     }

     private static final long serialVersionUID = 8060117844393922797L;
}
