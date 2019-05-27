package com.sun.crypto.provider;

import java.security.*;
import javax.crypto.*;
import static com.sun.crypto.provider.AESConstants.AES_BLOCK_SIZE;

/**
 * This class represents the GCTR function defined in NIST 800-38D
 * under section 6.5. It needs to be constructed w/ an initialized
 * cipher object, and initial counter block(ICB). Given an input X
 * of arbitrary length, it processes and returns an output which has
 * the same length as X.
 *
 * <p>This function is used in the implementation of GCM mode.
 */
final class GCTR {

    // these fields should not change after the object has been constructed
    private final SymmetricCipher aes;
    private final byte[] icb;

    // the current counter value
    private byte[] counter;

    // needed for save/restore calls
    private byte[] counterSave = null;

    // NOTE: cipher should already be initialized
    GCTR(SymmetricCipher cipher, byte[] initialCounterBlk) {
        this.aes = cipher;
        this.icb = initialCounterBlk;
        this.counter = icb.clone();
    }

    // input must be multiples of 128-bit blocks when calling update
    int update(byte[] in, int inOfs, int inLen, byte[] out, int outOfs) {
        if (inLen - inOfs > in.length) {
            throw new RuntimeException("input length out of bound");
        }
        if (inLen < 0 || inLen % AES_BLOCK_SIZE != 0) {
            throw new RuntimeException("input length unsupported");
        }
        if (out.length - outOfs < inLen) {
            throw new RuntimeException("output buffer too small");
        }

        byte[] encryptedCntr = new byte[AES_BLOCK_SIZE];

        int numOfCompleteBlocks = inLen / AES_BLOCK_SIZE;
        for (int i = 0; i < numOfCompleteBlocks; i++) {
            aes.encryptBlock(counter, 0, encryptedCntr, 0);
            for (int n = 0; n < AES_BLOCK_SIZE; n++) {
                int index = (i * AES_BLOCK_SIZE + n);
                out[outOfs + index] =
                    (byte) ((in[inOfs + index] ^ encryptedCntr[n]));
            }
            GaloisCounterMode.increment32(counter);
        }
        return inLen;
    }

    // input can be arbitrary size when calling doFinal
    protected int doFinal(byte[] in, int inOfs, int inLen, byte[] out,
                          int outOfs) throws IllegalBlockSizeException {
        try {
            if (inLen < 0) {
                throw new IllegalBlockSizeException("Negative input size!");
            } else if (inLen > 0) {
                int lastBlockSize = inLen % AES_BLOCK_SIZE;
                int completeBlkLen = inLen - lastBlockSize;
                // process the complete blocks first
                update(in, inOfs, completeBlkLen, out, outOfs);
                if (lastBlockSize != 0) {
                    // do the last partial block
                    byte[] encryptedCntr = new byte[AES_BLOCK_SIZE];
                    aes.encryptBlock(counter, 0, encryptedCntr, 0);
                    for (int n = 0; n < lastBlockSize; n++) {
                        out[outOfs + completeBlkLen + n] =
                            (byte) ((in[inOfs + completeBlkLen + n] ^
                                     encryptedCntr[n]));
                    }
                }
            }
        } finally {
            reset();
        }
        return inLen;
    }

    /**
     * Resets the content of this object to when it's first constructed.
     */
    void reset() {
        System.arraycopy(icb, 0, counter, 0, icb.length);
        counterSave = null;
    }

    /**
     * Save the current content of this object.
     */
    void save() {
        this.counterSave = this.counter.clone();
    }

    /**
     * Restores the content of this object to the previous saved one.
     */
    void restore() {
        this.counter = this.counterSave;
    }
}
