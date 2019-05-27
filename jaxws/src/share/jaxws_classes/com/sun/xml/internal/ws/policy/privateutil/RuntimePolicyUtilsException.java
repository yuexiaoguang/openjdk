package com.sun.xml.internal.ws.policy.privateutil;

public final class RuntimePolicyUtilsException extends RuntimeException {

    RuntimePolicyUtilsException(final String message) {
        super(message);
    }

    RuntimePolicyUtilsException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
