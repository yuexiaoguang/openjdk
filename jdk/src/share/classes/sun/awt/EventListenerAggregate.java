package sun.awt;

import java.lang.reflect.Array;
import java.util.EventListener;


/**
 * A class that assists in managing {@link java.util.EventListener}s of
 * the specified type. Its instance holds an array of listeners of the same
 * type and allows to perform the typical operations on the listeners.
 * This class is thread-safe.
 */
public class EventListenerAggregate {

    private EventListener[] listenerList;

    /**
     * Constructs an <code>EventListenerAggregate</code> object.
     *
     * @param listenerClass the type of the listeners to be managed by this object
     *
     * @throws NullPointerException if <code>listenerClass</code> is
     *         <code>null</code>
     * @throws ClassCastException if <code>listenerClass</code> is not
     *         assignable to <code>java.util.EventListener</code>
     */
    public EventListenerAggregate(Class<? extends EventListener> listenerClass) {
        if (listenerClass == null) {
            throw new NullPointerException("listener class is null");
        }

        listenerList = (EventListener[])Array.newInstance(listenerClass, 0);
    }

    private Class<?> getListenerClass() {
        return listenerList.getClass().getComponentType();
    }

    /**
     * Adds the listener to this aggregate.
     *
     * @param listener the listener to be added
     *
     * @throws ClassCastException if <code>listener</code> is not
     *         an instatce of <code>listenerClass</code> specified
     *         in the constructor
     */
    public synchronized void add(EventListener listener) {
        Class<?> listenerClass = getListenerClass();

        if (!listenerClass.isInstance(listener)) { // null is not an instance of any class
            throw new ClassCastException("listener " + listener + " is not " +
                    "an instance of listener class " + listenerClass);
        }

        EventListener[] tmp = (EventListener[])Array.newInstance(listenerClass, listenerList.length + 1);
        System.arraycopy(listenerList, 0, tmp, 0, listenerList.length);
        tmp[listenerList.length] = listener;
        listenerList = tmp;
    }

    /**
     * Removes a listener that is equal to the given one from this aggregate.
     * <code>equals()</code> method is used to compare listeners.
     *
     * @param listener the listener to be removed
     *
     * @return <code>true</code> if this aggregate contained the specified
     *         <code>listener</code>; <code>false</code> otherwise
     *
     * @throws ClassCastException if <code>listener</code> is not
     *         an instatce of <code>listenerClass</code> specified
     *         in the constructor
     */
    public synchronized boolean remove(EventListener listener) {
        Class<?> listenerClass = getListenerClass();

        if (!listenerClass.isInstance(listener)) { // null is not an instance of any class
            throw new ClassCastException("listener " + listener + " is not " +
                    "an instance of listener class " + listenerClass);
        }

        for (int i = 0; i < listenerList.length; i++) {
            if (listenerList[i].equals(listener)) {
                EventListener[] tmp = (EventListener[])Array.newInstance(listenerClass,
                                                                         listenerList.length - 1);
                System.arraycopy(listenerList, 0, tmp, 0, i);
                System.arraycopy(listenerList, i + 1, tmp, i, listenerList.length - i - 1);
                listenerList = tmp;

                return true;
            }
        }

        return false;
    }

    /**
     * Returns an array of all the listeners contained in this aggregate.
     * The array is the data structure in which listeners are stored internally.
     * The runtime type of the returned array is "array of <code>listenerClass</code>"
     * (<code>listenerClass</code> has been specified as a parameter to
     * the constructor of this class).
     *
     * @return all the listeners contained in this aggregate (an empty
     *         array if there are no listeners)
     */
    public synchronized EventListener[] getListenersInternal() {
        return listenerList;
    }

    /**
     * Returns an array of all the listeners contained in this aggregate.
     * The array is a copy of the data structure in which listeners are stored
     * internally.
     * The runtime type of the returned array is "array of <code>listenerClass</code>"
     * (<code>listenerClass</code> has been specified as a parameter to
     * the constructor of this class).
     *
     * @return a copy of all the listeners contained in this aggregate (an empty
     *         array if there are no listeners)
     */
    public synchronized EventListener[] getListenersCopy() {
        return (listenerList.length == 0) ? listenerList : listenerList.clone();
    }

    /**
     * Returns the number of lisetners in this aggregate.
     *
     * @return the number of lisetners in this aggregate
     */
    public synchronized int size() {
        return listenerList.length;
    }

    /**
     * Returns <code>true</code> if this aggregate contains no listeners,
     * <code>false</code> otherwise.
     *
     * @return <code>true</code> if this aggregate contains no listeners,
     *         <code>false</code> otherwise
     */
    public synchronized boolean isEmpty() {
        return listenerList.length == 0;
    }
}
