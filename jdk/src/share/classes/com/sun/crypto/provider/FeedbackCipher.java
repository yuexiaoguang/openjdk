package com.sun.crypto.provider;

import java.security.InvalidKeyException;
import javax.crypto.*;

/**
 * This class represents a block cipher in one of its modes. It wraps
 * a SymmetricCipher maintaining the mode state and providing
 * the capability to encrypt amounts of data larger than a single block.
 */
abstract class FeedbackCipher {

    // the embedded block cipher
    final SymmetricCipher embeddedCipher;

    // the block size of the embedded block cipher
    final int blockSize;

    // the initialization vector
    byte[] iv;

    FeedbackCipher(SymmetricCipher embeddedCipher) {
        this.embeddedCipher = embeddedCipher;
        blockSize = embeddedCipher.getBlockSize();
    }

    final SymmetricCipher getEmbeddedCipher() {
        return embeddedCipher;
    }

    /**
     * Gets the block size of the embedded cipher.
     *
     * @return the block size of the embedded cipher
     */
    final int getBlockSize() {
        return blockSize;
    }

    /**
     * Gets the name of the feedback mechanism
     *
     * @return the name of the feedback mechanism
     */
    abstract String getFeedback();

    /**
     * Save the current content of this cipher.
     */
    abstract void save();

    /**
     * Restores the content of this cipher to the previous saved one.
     */
    abstract void restore();

    /**
     * Initializes the cipher in the specified mode with the given key
     * and iv.
     *
     * @param decrypting flag indicating encryption or decryption mode
     * @param algorithm the algorithm name (never null)
     * @param key the key (never null)
     * @param iv the iv (either null or blockSize bytes long)
     *
     * @exception InvalidKeyException if the given key is inappropriate for
     * initializing this cipher
     */
    abstract void init(boolean decrypting, String algorithm, byte[] key,
                       byte[] iv) throws InvalidKeyException;

   /**
     * Gets the initialization vector.
     *
     * @return the initialization vector
     */
    final byte[] getIV() {
        return iv;
    }

    /**
     * Resets the iv to its original value.
     * This is used when doFinal is called in the Cipher class, so that the
     * cipher can be reused (with its original iv).
     */
    abstract void reset();

    /**
     * Performs encryption operation.
     *
     * <p>The input <code>plain</code>, starting at <code>plainOffset</code>
     * and ending at <code>(plainOffset+plainLen-1)</code>, is encrypted.
     * The result is stored in <code>cipher</code>, starting at
     * <code>cipherOffset</code>.
     *
     * <p>The subclass that implements Cipher should ensure that
     * <code>init</code> has been called before this method is called.
     *
     * @param plain the input buffer with the data to be encrypted
     * @param plainOffset the offset in <code>plain</code>
     * @param plainLen the length of the input data
     * @param cipher the buffer for the encryption result
     * @param cipherOffset the offset in <code>cipher</code>
     * @return the number of bytes placed into <code>cipher</code>
     */
    abstract int encrypt(byte[] plain, int plainOffset, int plainLen,
                         byte[] cipher, int cipherOffset);
    /**
     * Performs encryption operation for the last time.
     *
     * <p>NOTE: For cipher feedback modes which does not perform
     * special handling for the last few blocks, this is essentially
     * the same as <code>encrypt(...)</code>. Given most modes do
     * not do special handling, the default impl for this method is
     * to simply call <code>encrypt(...)</code>.
     *
     * @param plain the input buffer with the data to be encrypted
     * @param plainOffset the offset in <code>plain</code>
     * @param plainLen the length of the input data
     * @param cipher the buffer for the encryption result
     * @param cipherOffset the offset in <code>cipher</code>
     * @return the number of bytes placed into <code>cipher</code>
     */
     int encryptFinal(byte[] plain, int plainOffset, int plainLen,
                      byte[] cipher, int cipherOffset)
         throws IllegalBlockSizeException, ShortBufferException {
         return encrypt(plain, plainOffset, plainLen, cipher, cipherOffset);
    }
    /**
     * Performs decryption operation.
     *
     * <p>The input <code>cipher</code>, starting at <code>cipherOffset</code>
     * and ending at <code>(cipherOffset+cipherLen-1)</code>, is decrypted.
     * The result is stored in <code>plain</code>, starting at
     * <code>plainOffset</code>.
     *
     * <p>The subclass that implements Cipher should ensure that
     * <code>init</code> has been called before this method is called.
     *
     * @param cipher the input buffer with the data to be decrypted
     * @param cipherOffset the offset in <code>cipher</code>
     * @param cipherLen the length of the input data
     * @param plain the buffer for the decryption result
     * @param plainOffset the offset in <code>plain</code>
     * @return the number of bytes placed into <code>plain</code>
     */
    abstract int decrypt(byte[] cipher, int cipherOffset, int cipherLen,
                         byte[] plain, int plainOffset);

    /**
     * Performs decryption operation for the last time.
     *
     * <p>NOTE: For cipher feedback modes which does not perform
     * special handling for the last few blocks, this is essentially
     * the same as <code>encrypt(...)</code>. Given most modes do
     * not do special handling, the default impl for this method is
     * to simply call <code>decrypt(...)</code>.
     *
     * @param cipher the input buffer with the data to be decrypted
     * @param cipherOffset the offset in <code>cipher</code>
     * @param cipherLen the length of the input data
     * @param plain the buffer for the decryption result
     * @param plainOffset the offset in <code>plain</code>
     * @return the number of bytes placed into <code>plain</code>
     */
     int decryptFinal(byte[] cipher, int cipherOffset, int cipherLen,
                      byte[] plain, int plainOffset)
         throws IllegalBlockSizeException, AEADBadTagException,
         ShortBufferException {
         return decrypt(cipher, cipherOffset, cipherLen, plain, plainOffset);
     }

    /**
     * Continues a multi-part update of the Additional Authentication
     * Data (AAD), using a subset of the provided buffer. If this
     * cipher is operating in either GCM or CCM mode, all AAD must be
     * supplied before beginning operations on the ciphertext (via the
     * {@code update} and {@code doFinal} methods).
     * <p>
     * NOTE: Given most modes do not accept AAD, default impl for this
     * method throws IllegalStateException.
     *
     * @param src the buffer containing the AAD
     * @param offset the offset in {@code src} where the AAD input starts
     * @param len the number of AAD bytes
     *
     * @throws IllegalStateException if this cipher is in a wrong state
     * (e.g., has not been initialized), does not accept AAD, or if
     * operating in either GCM or CCM mode and one of the {@code update}
     * methods has already been called for the active
     * encryption/decryption operation
     * @throws UnsupportedOperationException if this method
     * has not been overridden by an implementation
     *
     * @since 1.8
     */
    void updateAAD(byte[] src, int offset, int len) {
        throw new IllegalStateException("No AAD accepted");
    }

    /**
     * @return the number of bytes that are buffered internally inside
     * this FeedbackCipher instance.
     * @since 1.8
     */
    int getBufferedLength() {
        // Currently only AEAD cipher impl, e.g. GCM, buffers data
        // internally during decryption mode
        return 0;
    }
}
