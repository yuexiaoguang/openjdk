package com.sun.media.sound;

import javax.sound.midi.*;


/**
 * Helper class which allows to convert {@code Receiver}
 * to {@code MidiDeviceReceiver}.
 */
public final class MidiDeviceReceiverEnvelope implements MidiDeviceReceiver {

    private final MidiDevice device;
    private final Receiver receiver;

    /**
     * Creates a new {@code MidiDeviceReceiverEnvelope} object which
     * envelops the specified {@code Receiver}
     * and is owned by the specified {@code MidiDevice}.
     *
     * @param device the owner {@code MidiDevice}
     * @param receiver the {@code Receiver} to be enveloped
     */
    public MidiDeviceReceiverEnvelope(MidiDevice device, Receiver receiver) {
        if (device == null || receiver == null) {
            throw new NullPointerException();
        }
        this.device = device;
        this.receiver = receiver;
    }

    // Receiver implementation
    public void close() {
        receiver.close();
    }

    public void send(MidiMessage message, long timeStamp) {
        receiver.send(message, timeStamp);
    }

    // MidiDeviceReceiver implementation
    public MidiDevice getMidiDevice() {
        return device;
    }

    /**
     * Obtains the receiver enveloped
     * by this {@code MidiDeviceReceiverEnvelope} object.
     *
     * @return the enveloped receiver
     */
    public Receiver getReceiver() {
        return receiver;
    }
}
