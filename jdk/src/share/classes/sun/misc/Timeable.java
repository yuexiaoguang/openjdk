package sun.misc;

/**
 * This interface is used by the Timer class.  A class that uses
 * Timer objects must implement this interface.
 */
public interface Timeable {
    /**
     * This method is executed every time a timer owned by this
     * object ticks.  An object can own more than one timer but
     * all timers call this method when they tick;
     * you can use the supplied timer parameter to determine
     * which timer has ticked.
     * @param timer a handle to the timer that has just ticked.
     */
    public void tick(Timer timer);
}
