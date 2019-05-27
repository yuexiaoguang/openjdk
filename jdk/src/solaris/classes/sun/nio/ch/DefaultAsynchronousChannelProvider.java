package sun.nio.ch;

import java.nio.channels.spi.AsynchronousChannelProvider;
import java.security.AccessController;
import sun.security.action.GetPropertyAction;

/**
 * Creates this platform's default asynchronous channel provider
 */
public class DefaultAsynchronousChannelProvider {

    /**
     * Prevent instantiation.
     */
    private DefaultAsynchronousChannelProvider() { }

    @SuppressWarnings("unchecked")
    private static AsynchronousChannelProvider createProvider(String cn) {
        Class<AsynchronousChannelProvider> c;
        try {
            c = (Class<AsynchronousChannelProvider>)Class.forName(cn);
        } catch (ClassNotFoundException x) {
            throw new AssertionError(x);
        }
        try {
            return c.newInstance();
        } catch (IllegalAccessException | InstantiationException x) {
            throw new AssertionError(x);
        }

    }

    /**
     * Returns the default AsynchronousChannelProvider.
     */
    public static AsynchronousChannelProvider create() {
        String osname = AccessController
            .doPrivileged(new GetPropertyAction("os.name"));
        if (osname.equals("SunOS"))
            return createProvider("sun.nio.ch.SolarisAsynchronousChannelProvider");
        if (osname.equals("Linux"))
            return createProvider("sun.nio.ch.LinuxAsynchronousChannelProvider");
        if (osname.contains("OS X"))
            return createProvider("sun.nio.ch.BsdAsynchronousChannelProvider");
        throw new InternalError("platform not recognized");
    }
}
