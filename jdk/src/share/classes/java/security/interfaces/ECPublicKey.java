package java.security.interfaces;

import java.security.PublicKey;
import java.security.spec.ECPoint;

/**
 * The interface to an elliptic curve (EC) public key.
 */
public interface ECPublicKey extends PublicKey, ECKey {

   /**
    * The class fingerprint that is set to indicate
    * serialization compatibility.
    */
    static final long serialVersionUID = -3314988629879632826L;

    /**
     * Returns the public point W.
     * @return the public point W.
     */
    ECPoint getW();
}
