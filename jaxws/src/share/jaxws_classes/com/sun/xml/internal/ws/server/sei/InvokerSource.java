package com.sun.xml.internal.ws.server.sei;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.api.message.Packet;

/**
 * Interface for determining Invoker for a given request
 */
public interface InvokerSource<T extends Invoker> {
        /**
         * Returns Invoker for the given request
         * @param request Packet for request
         * @return Selected invoker
         */
        public @NotNull T getInvoker(Packet request);
}
