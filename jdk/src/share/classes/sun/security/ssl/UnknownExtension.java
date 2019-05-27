package sun.security.ssl;

import java.io.IOException;

final class UnknownExtension extends HelloExtension {

    private final byte[] data;

    UnknownExtension(HandshakeInStream s, int len, ExtensionType type)
            throws IOException {
        super(type);
        data = new byte[len];
        // s.read() does not handle 0-length arrays.
        if (len != 0) {
            s.read(data);
        }
    }

    @Override
    int length() {
        return 4 + data.length;
    }

    @Override
    void send(HandshakeOutStream s) throws IOException {
        s.putInt16(type.id);
        s.putBytes16(data);
    }

    @Override
    public String toString() {
        return "Unsupported extension " + type + ", data: " +
            Debug.toString(data);
    }
}
