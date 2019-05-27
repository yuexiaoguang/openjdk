package sun.security.ssl;

import java.io.IOException;

import javax.net.ssl.SSLProtocolException;

/*
 * For secure renegotiation, RFC5746 defines a new TLS extension,
 * "renegotiation_info" (with extension type 0xff01), which contains a
 * cryptographic binding to the enclosing TLS connection (if any) for
 * which the renegotiation is being performed.  The "extension data"
 * field of this extension contains a "RenegotiationInfo" structure:
 *
 *      struct {
 *          opaque renegotiated_connection<0..255>;
 *      } RenegotiationInfo;
 */
final class RenegotiationInfoExtension extends HelloExtension {
    private final byte[] renegotiated_connection;

    RenegotiationInfoExtension(byte[] clientVerifyData,
                byte[] serverVerifyData) {
        super(ExtensionType.EXT_RENEGOTIATION_INFO);

        if (clientVerifyData.length != 0) {
            renegotiated_connection =
                    new byte[clientVerifyData.length + serverVerifyData.length];
            System.arraycopy(clientVerifyData, 0, renegotiated_connection,
                    0, clientVerifyData.length);

            if (serverVerifyData.length != 0) {
                System.arraycopy(serverVerifyData, 0, renegotiated_connection,
                        clientVerifyData.length, serverVerifyData.length);
            }
        } else {
            // ignore both the client and server verify data.
            renegotiated_connection = new byte[0];
        }
    }

    RenegotiationInfoExtension(HandshakeInStream s, int len)
                throws IOException {
        super(ExtensionType.EXT_RENEGOTIATION_INFO);

        // check the extension length
        if (len < 1) {
            throw new SSLProtocolException("Invalid " + type + " extension");
        }

        int renegoInfoDataLen = s.getInt8();
        if (renegoInfoDataLen + 1 != len) {  // + 1 = the byte we just read
            throw new SSLProtocolException("Invalid " + type + " extension");
        }

        renegotiated_connection = new byte[renegoInfoDataLen];
        if (renegoInfoDataLen != 0) {
            s.read(renegotiated_connection, 0, renegoInfoDataLen);
        }
    }


    // Length of the encoded extension, including the type and length fields
    @Override
    int length() {
        return 5 + renegotiated_connection.length;
    }

    @Override
    void send(HandshakeOutStream s) throws IOException {
        s.putInt16(type.id);
        s.putInt16(renegotiated_connection.length + 1);
        s.putBytes8(renegotiated_connection);
    }

    boolean isEmpty() {
        return renegotiated_connection.length == 0;
    }

    byte[] getRenegotiatedConnection() {
        return renegotiated_connection;
    }

    @Override
    public String toString() {
        return "Extension " + type + ", renegotiated_connection: " +
                    (renegotiated_connection.length == 0 ? "<empty>" :
                    Debug.toString(renegotiated_connection));
    }

}
