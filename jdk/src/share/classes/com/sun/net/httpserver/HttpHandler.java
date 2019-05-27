package com.sun.net.httpserver;

import java.io.IOException;

/**
 * A handler which is invoked to process HTTP exchanges. Each
 * HTTP exchange is handled by one of these handlers.
 */
@jdk.Exported
public interface HttpHandler {
    /**
     * Handle the given request and generate an appropriate response.
     * See {@link HttpExchange} for a description of the steps
     * involved in handling an exchange.
     * @param exchange the exchange containing the request from the
     *      client and used to send the response
     * @throws NullPointerException if exchange is <code>null</code>
     */
    public abstract void handle (HttpExchange exchange) throws IOException;
}
