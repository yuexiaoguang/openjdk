package com.sun.media.sound;

import javax.sound.midi.Receiver;

/**
 * Interface for Sequencers that are able to do the auto-connect
 * as required by MidiSystem.getSequencer()
 */
public interface AutoConnectSequencer {

    /**
     * Set the receiver that this device is
     * auto-connected. If non-null, the device
     * needs to re-connect itself to a suitable
     * device in open().
     */
    public void setAutoConnect(Receiver autoConnectReceiver);

}
