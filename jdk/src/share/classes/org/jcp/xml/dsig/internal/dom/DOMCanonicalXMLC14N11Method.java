package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

import java.security.InvalidAlgorithmParameterException;

import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;

/**
 * DOM-based implementation of CanonicalizationMethod for Canonical XML 1.1
 * (with or without comments). Uses Apache XML-Sec Canonicalizer.
 */
public final class DOMCanonicalXMLC14N11Method extends ApacheCanonicalizer {

    public static final String C14N_11 = "http://www.w3.org/2006/12/xml-c14n11";
    public static final String C14N_11_WITH_COMMENTS
        = "http://www.w3.org/2006/12/xml-c14n11#WithComments";

    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException {
        if (params != null) {
            throw new InvalidAlgorithmParameterException("no parameters " +
                "should be specified for Canonical XML 1.1 algorithm");
        }
    }

    public Data transform(Data data, XMLCryptoContext xc)
        throws TransformException {

        // ignore comments if dereferencing same-document URI that requires
        // you to omit comments, even if the Transform says otherwise -
        // this is to be compliant with section 4.3.3.3 of W3C Rec.
        if (data instanceof DOMSubTreeData) {
            DOMSubTreeData subTree = (DOMSubTreeData) data;
            if (subTree.excludeComments()) {
                try {
                    apacheCanonicalizer = Canonicalizer.getInstance(C14N_11);
                } catch (InvalidCanonicalizerException ice) {
                    throw new TransformException
                        ("Couldn't find Canonicalizer for: " +
                         C14N_11 + ": " + ice.getMessage(), ice);
                }
            }
        }

        return canonicalize(data, xc);
    }
}
