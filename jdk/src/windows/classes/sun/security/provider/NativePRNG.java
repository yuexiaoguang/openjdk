package sun.security.provider;

/**
 * Native PRNG implementation for Windows. Currently a dummy, we do
 * not support a fully native PRNG on Windows.
 */
public final class NativePRNG {

    // return whether the NativePRNG is available
    static boolean isAvailable() {
        return false;
    }

    public static final class NonBlocking {
        static boolean isAvailable() {
            return false;
        }
    }

    public static final class Blocking {
        static boolean isAvailable() {
            return false;
        }
    }
}
