package com.sun.security.sasl.digest;

import javax.security.sasl.SaslException;

/**
  * Interface used for classes implementing integrity checking and privacy
  * for DIGEST-MD5 SASL mechanism implementation.
  */
interface SecurityCtx {

    /**
     * Wrap out-going message and return wrapped message
     *
     * @throws SaslException
     */
    byte[] wrap(byte[] dest, int start, int len)
        throws SaslException;

    /**
     * Unwrap incoming message and return original message
     *
     * @throws SaslException
     */
    byte[] unwrap(byte[] outgoing, int start, int len)
        throws SaslException;
}
