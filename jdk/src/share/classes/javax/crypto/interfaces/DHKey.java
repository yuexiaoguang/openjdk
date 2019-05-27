package javax.crypto.interfaces;

import javax.crypto.spec.DHParameterSpec;

/**
 * The interface to a Diffie-Hellman key.
 *
 * @since 1.4
 */
public interface DHKey {

    /**
     * Returns the key parameters.
     *
     * @return the key parameters
     */
    DHParameterSpec getParams();
}
