package com.sun.beans.finder;

final class SignatureException extends RuntimeException {
    SignatureException(Throwable cause) {
        super(cause);
    }

    NoSuchMethodException toNoSuchMethodException(String message) {
        Throwable throwable = getCause();
        if (throwable instanceof NoSuchMethodException) {
            return (NoSuchMethodException) throwable;
        }
        NoSuchMethodException exception = new NoSuchMethodException(message);
        exception.initCause(throwable);
        return exception;
    }
}
