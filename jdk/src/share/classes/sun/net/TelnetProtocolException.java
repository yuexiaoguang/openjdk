package sun.net;

import java.io.*;

/**
 * An unexpected result was received by the client when talking to the
 * telnet server.
 */
public class TelnetProtocolException extends IOException {
    private static final long serialVersionUID = 8509127047257111343L;

    public TelnetProtocolException(String s) {
        super(s);
    }
}
