package java.net;

import java.io.IOException;
import java.io.FileDescriptor;

import sun.net.sdp.SdpSupport;

/**
 * SocketImpl that supports the SDP protocol
 */
class SdpSocketImpl extends PlainSocketImpl {
    SdpSocketImpl() { }

    @Override
    protected void create(boolean stream) throws IOException {
        if (!stream)
            throw new UnsupportedOperationException("Must be a stream socket");
        fd = SdpSupport.createSocket();
        if (socket != null)
            socket.setCreated();
        if (serverSocket != null)
            serverSocket.setCreated();
    }
}
