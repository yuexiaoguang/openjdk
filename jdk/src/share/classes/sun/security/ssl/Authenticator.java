package sun.security.ssl;

import java.util.Arrays;

/**
 * This class represents an SSL/TLS message authentication token,
 * which encapsulates a sequence number and ensures that attempts to
 * delete or reorder messages can be detected.
 *
 * Each SSL/TLS connection state contains a sequence number, which
 * is maintained separately for read and write states.  The sequence
 * number MUST be set to zero whenever a connection state is made the
 * active state.  Sequence numbers are of type uint64 and may not
 * exceed 2^64-1.  Sequence numbers do not wrap.  If a SSL/TLS
 * implementation would need to wrap a sequence number, it must
 * renegotiate instead.  A sequence number is incremented after each
 * record: specifically, the first record transmitted under a
 * particular connection state MUST use sequence number 0.
 */
class Authenticator {

    // byte array containing the additional authentication information for
    // each record
    private final byte[] block;

    // the block size of SSL v3.0:
    // sequence number + record type + + record length
    private static final int BLOCK_SIZE_SSL = 8 + 1 + 2;

    // the block size of TLS v1.0 and later:
    // sequence number + record type + protocol version + record length
    private static final int BLOCK_SIZE_TLS = 8 + 1 + 2 + 2;

    /**
     * Default construct, no message authentication token is initialized.
     *
     * Note that this construct can only be called for null MAC
     */
    Authenticator() {
        block = new byte[0];
    }

    /**
     * Constructs the message authentication token for the specified
     * SSL/TLS protocol.
     */
    Authenticator(ProtocolVersion protocolVersion) {
        if (protocolVersion.v >= ProtocolVersion.TLS10.v) {
            block = new byte[BLOCK_SIZE_TLS];
            block[9] = protocolVersion.major;
            block[10] = protocolVersion.minor;
        } else {
            block = new byte[BLOCK_SIZE_SSL];
        }
    }

    /**
     * Checks whether the sequence number is close to wrap.
     *
     * Sequence numbers are of type uint64 and may not exceed 2^64-1.
     * Sequence numbers do not wrap. When the sequence number is near
     * to wrap, we need to close the connection immediately.
     *
     * @return true if the sequence number is close to wrap
     */
    final boolean seqNumOverflow() {
        /*
         * Conservatively, we don't allow more records to be generated
         * when there are only 2^8 sequence numbers left.
         */
        return (block.length != 0 &&
                block[0] == (byte)0xFF && block[1] == (byte)0xFF &&
                block[2] == (byte)0xFF && block[3] == (byte)0xFF &&
                block[4] == (byte)0xFF && block[5] == (byte)0xFF &&
                block[6] == (byte)0xFF);
    }

    /**
     * Checks whether the sequence number close to renew.
     *
     * Sequence numbers are of type uint64 and may not exceed 2^64-1.
     * Sequence numbers do not wrap.  If a TLS
     * implementation would need to wrap a sequence number, it must
     * renegotiate instead.
     *
     * @return true if the sequence number is huge enough to renew
     */
    final boolean seqNumIsHuge() {
        /*
         * Conservatively, we should ask for renegotiation when there are
         * only 2^48 sequence numbers left.
         */
        return (block.length != 0 &&
                block[0] == (byte)0xFF && block[1] == (byte)0xFF);
    }

    /**
     * Gets the current sequence number.
     *
     * @return the byte array of the current sequence number
     */
    final byte[] sequenceNumber() {
        return Arrays.copyOf(block, 8);
    }

    /**
     * Acquires the current message authentication information with the
     * specified record type and fragment length, and then increases the
     * sequence number.
     *
     * @param  type the record type
     * @param  length the fragment of the record
     * @return the byte array of the current message authentication information
     */
    final byte[] acquireAuthenticationBytes(byte type, int length) {
        byte[] copy = block.clone();

        if (block.length != 0) {
            copy[8] = type;
            copy[copy.length - 2] = (byte)(length >> 8);
            copy[copy.length - 1] = (byte)(length);

            /*
             * Increase the sequence number in the block array
             * it is a 64-bit number stored in big-endian format
             */
            int k = 7;
            while ((k >= 0) && (++block[k] == 0)) {
                k--;
            }
        }

        return copy;
    }

}
