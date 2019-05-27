package sun.misc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is used to prevent multiple calling of g_thread_init ()
 * and gdk_thread_init ().
 *
 * Since version 2.24 of GLib, calling g_thread_init () multiple times is
 * allowed, but it will crash for older versions. There are two ways to
 * find out if g_thread_init () has been called:
 * g_thread_get_initialized (), but it was introduced in 2.20
 * g_thread_supported (), but it is a macro and cannot be loaded with dlsym.
 *
 * usage:
 * <pre>
 * lock();
 * try {
 *    if (!getAndSetInitializationNeededFlag()) {
 *        //call to g_thread_init();
 *        //call to gdk_thread_init();
 *    }
 * } finally {
 *    unlock();
 * }
 * </pre>
 */
public final class GThreadHelper {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static boolean isGThreadInitialized = false;

    /**
     * Acquires the lock.
     */
    public static void lock() {
        LOCK.lock();
    }

    /**
     * Releases the lock.
     */
    public static void unlock() {
        LOCK.unlock();
    }

    /**
     * Gets current value of initialization flag and sets it to {@code true}.
     * MUST be called under the lock.
     *
     * A return value of {@code false} indicates that the calling code
     * should call the g_thread_init() and gdk_thread_init() functions
     * before releasing the lock.
     *
     * @return {@code true} if initialization has been completed.
     */
    public static boolean getAndSetInitializationNeededFlag() {
        boolean ret = isGThreadInitialized;
        isGThreadInitialized = true;
        return ret;
    }
}
