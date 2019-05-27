package com.sun.tools.jdi;

import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;

import java.io.IOException;

class SharedMemoryConnection extends Connection {
    private long id;
    private Object receiveLock = new Object();
    private Object sendLock = new Object();
    private Object closeLock = new Object();
    private boolean closed = false;

    private native byte receiveByte0(long id) throws IOException;
    private native void sendByte0(long id, byte b) throws IOException;
    private native void close0(long id);
    private native byte[] receivePacket0(long id)throws IOException;
    private native void sendPacket0(long id, byte b[]) throws IOException;

    // handshake with the target VM
    void handshake(long handshakeTimeout) throws IOException {
        byte[] hello = "JDWP-Handshake".getBytes("UTF-8");

        for (int i=0; i<hello.length; i++) {
            sendByte0(id, hello[i]);
        }
        for (int i=0; i<hello.length; i++) {
            byte b = receiveByte0(id);
            if (b != hello[i]) {
                throw new IOException("handshake failed - unrecognized message from target VM");
            }
        }
    }


    SharedMemoryConnection(long id) throws IOException {
        this.id = id;
    }

    public void close() {
        synchronized (closeLock) {
            if (!closed) {
                close0(id);
                closed = true;
            }
        }
    }

    public boolean isOpen() {
        synchronized (closeLock) {
            return !closed;
        }
    }

    public byte[] readPacket() throws IOException {
        if (!isOpen()) {
            throw new ClosedConnectionException("Connection closed");
        }
        byte b[];
        try {
            // only one thread may be reading at a time
            synchronized (receiveLock) {
                b  = receivePacket0(id);
            }
        } catch (IOException ioe) {
            if (!isOpen()) {
                throw new ClosedConnectionException("Connection closed");
            } else {
                throw ioe;
            }
        }
        return b;
    }

    public void writePacket(byte b[]) throws IOException {
        if (!isOpen()) {
            throw new ClosedConnectionException("Connection closed");
        }

        /*
         * Check the packet size
         */
        if (b.length < 11) {
            throw new IllegalArgumentException("packet is insufficient size");
        }
        int b0 = b[0] & 0xff;
        int b1 = b[1] & 0xff;
        int b2 = b[2] & 0xff;
        int b3 = b[3] & 0xff;
        int len = ((b0 << 24) | (b1 << 16) | (b2 << 8) | (b3 << 0));
        if (len < 11) {
            throw new IllegalArgumentException("packet is insufficient size");
        }

        /*
         * Check that the byte array contains the complete packet
         */
        if (len > b.length) {
            throw new IllegalArgumentException("length mis-match");
        }

        try {
            // only one thread may be writing at a time
            synchronized(sendLock) {
                sendPacket0(id, b);
            }
        } catch (IOException ioe) {
            if (!isOpen()) {
               throw new ClosedConnectionException("Connection closed");
            } else {
               throw ioe;
            }
        }
    }
}

