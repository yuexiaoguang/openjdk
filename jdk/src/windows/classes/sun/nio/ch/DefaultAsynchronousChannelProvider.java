package sun.nio.ch;

import java.nio.channels.spi.AsynchronousChannelProvider;

/**
 * Creates this platform's default asynchronous channel provider
 */
public class DefaultAsynchronousChannelProvider {
    private DefaultAsynchronousChannelProvider() { }

    /**
     * Returns the default AsynchronousChannelProvider.
     */
    public static AsynchronousChannelProvider create() {
        return new WindowsAsynchronousChannelProvider();
    }
}
