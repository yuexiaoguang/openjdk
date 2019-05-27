package com.sun.media.sound;

/**
 * This class is used to store loop points inside DLSSampleOptions class.
 */
public final class DLSSampleLoop {

    public final static int LOOP_TYPE_FORWARD = 0;
    public final static int LOOP_TYPE_RELEASE = 1;
    long type;
    long start;
    long length;

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }
}
