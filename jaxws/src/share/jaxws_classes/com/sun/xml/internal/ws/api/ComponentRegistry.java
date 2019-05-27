package com.sun.xml.internal.ws.api;

import java.util.Set;

import com.sun.istack.internal.NotNull;

/**
 * Registry for component delegates.  It is expected that implementations of
 * ComponentRegistry will delegate to registered {@link Component}s in its own
 * implementation of {@link Component#getSPI(java.lang.Class)}, either before or after it
 * considers its own SPI implementations.
 */
public interface ComponentRegistry extends Component {
        /**
         * Returns the set of {@link Component}s registered with this object
         * @return set of registered components
         */
    public @NotNull Set<Component> getComponents();
}
