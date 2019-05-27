package org.jcp.xml.dsig.internal.dom;

import java.security.InvalidAlgorithmParameterException;

import javax.xml.crypto.dsig.spec.TransformParameterSpec;

/**
 * DOM-based implementation of Base64 Encoding Transform.
 * (Uses Apache XML-Sec Transform implementation)
 */
public final class DOMBase64Transform extends ApacheTransform {

    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException {
        if (params != null) {
            throw new InvalidAlgorithmParameterException("params must be null");
        }
    }
}
