package java.security.spec;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * This interface represents an elliptic curve (EC) finite field.
 * All specialized EC fields must implements this interface.
 */
public interface ECField {
    /**
     * Returns the field size in bits. Note: For prime finite
     * field ECFieldFp, size of prime p in bits is returned.
     * For characteristic 2 finite field ECFieldF2m, m is returned.
     * @return the field size in bits.
     */
    int getFieldSize();
}
