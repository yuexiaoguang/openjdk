package com.sun.xml.internal.fastinfoset;

public interface OctetBufferListener {
    /**
     * Callback method that will be called before the
     * (@link Decoder) octet buffer content is going to be changed.
     * So it will be possible to preserve a read data by
     * cloning, or perform other actions.
     */
    public void onBeforeOctetBufferOverwrite();
}
