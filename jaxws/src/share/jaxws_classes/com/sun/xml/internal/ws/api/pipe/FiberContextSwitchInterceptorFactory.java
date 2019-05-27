package com.sun.xml.internal.ws.api.pipe;

/**
 * Factory for (@link FiberContextSwitchInterceptor} instances
 */
public interface FiberContextSwitchInterceptorFactory {
        /**
         * Creates {@link FiberContextSwitchInterceptor} instance.
         * @return interceptor instance
         */
        public FiberContextSwitchInterceptor create();
}
