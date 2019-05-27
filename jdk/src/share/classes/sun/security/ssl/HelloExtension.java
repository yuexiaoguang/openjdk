package sun.security.ssl;

import java.io.IOException;

abstract class HelloExtension {

    final ExtensionType type;

    HelloExtension(ExtensionType type) {
        this.type = type;
    }

    // Length of the encoded extension, including the type and length fields
    abstract int length();

    abstract void send(HandshakeOutStream s) throws IOException;

    @Override
    public abstract String toString();

}
