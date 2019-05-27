package sun.java2d.pipe.hw;

import sun.java2d.pipe.BufferedContext;

/**
 * Classes implementing this interface can provide the {@code BufferedContext}
 * associated with or used by them.
 */
public interface BufferedContextProvider {
    /**
     * Retrieves a context associated with object implementing this
     * interface.
     *
     * @return associated context
     * @see sun.java2d.pipe.BufferedContext
     */
    public BufferedContext getContext();
}
