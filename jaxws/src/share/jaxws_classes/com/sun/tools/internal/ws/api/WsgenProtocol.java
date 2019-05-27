package com.sun.tools.internal.ws.api;

import java.lang.annotation.*;

/**
 * Allows to extend protocol for wsgen's wsdl[:protocol] switch.
 * This annotation must be specified on {@link WsgenExtension}
 * implementations.
 *
 * @since JAX-WS RI 2.1.6
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WsgenProtocol {
    /**
     * Token for wsgen -wsdl[:protocol]
     * @return Token for wsgen -wsdl[:protocol]
     */
    String token();

    /**
     * The corresponding lexical string used to create BindingID
     * @return lexical string used to create BindingID
     */
    String lexical();
}
