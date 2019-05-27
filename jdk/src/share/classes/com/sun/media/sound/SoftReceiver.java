package com.sun.media.sound;

import java.util.TreeMap;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDeviceReceiver;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

/**
 * Software synthesizer MIDI receiver class.
 */
public final class SoftReceiver implements MidiDeviceReceiver {

    boolean open = true;
    private final Object control_mutex;
    private final SoftSynthesizer synth;
    TreeMap<Long, Object> midimessages;
    SoftMainMixer mainmixer;

    public SoftReceiver(SoftSynthesizer synth) {
        this.control_mutex = synth.control_mutex;
        this.synth = synth;
        this.mainmixer = synth.getMainMixer();
        if (mainmixer != null)
            this.midimessages = mainmixer.midimessages;
    }

    public MidiDevice getMidiDevice() {
        return synth;
    }

    public void send(MidiMessage message, long timeStamp) {

        synchronized (control_mutex) {
            if (!open)
                throw new IllegalStateException("Receiver is not open");
        }

        if (timeStamp != -1) {
            synchronized (control_mutex) {
                mainmixer.activity();
                while (midimessages.get(timeStamp) != null)
                    timeStamp++;
                if (message instanceof ShortMessage
                        && (((ShortMessage)message).getChannel() > 0xF)) {
                    midimessages.put(timeStamp, message.clone());
                } else {
                    midimessages.put(timeStamp, message.getMessage());
                }
            }
        } else {
            mainmixer.processMessage(message);
        }
    }

    public void close() {
        synchronized (control_mutex) {
            open = false;
        }
        synth.removeReceiver(this);
    }
}
