package com.sun.crypto.provider;

import java.io.*;
import java.security.*;
import javax.crypto.*;

final class SealedObjectForKeyProtector extends SealedObject {

    static final long serialVersionUID = -3650226485480866989L;

    SealedObjectForKeyProtector(Serializable object, Cipher c)
            throws IOException, IllegalBlockSizeException {
        super(object, c);
    }

    SealedObjectForKeyProtector(SealedObject so) {
        super(so);
    }

    AlgorithmParameters getParameters() {
        AlgorithmParameters params = null;
        if (super.encodedParams != null) {
            try {
                params = AlgorithmParameters.getInstance("PBE",
                    SunJCE.getInstance());
                params.init(super.encodedParams);
            } catch (NoSuchAlgorithmException nsae) {
                throw new RuntimeException(
                    "SunJCE provider is not configured properly");
            } catch (IOException io) {
                throw new RuntimeException("Parameter failure: "+
                    io.getMessage());
            }
        }
        return params;
    }
}
