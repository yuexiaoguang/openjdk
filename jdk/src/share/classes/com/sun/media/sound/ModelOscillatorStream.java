package com.sun.media.sound;

import java.io.IOException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.VoiceStatus;

/**
 * This interface is used for audio streams from ModelOscillator.
 */
public interface ModelOscillatorStream {

    public void setPitch(float pitch); // Pitch is in cents!

    public void noteOn(MidiChannel channel, VoiceStatus voice, int noteNumber,
            int velocity);

    public void noteOff(int velocity);

    public int read(float[][] buffer, int offset, int len) throws IOException;

    public void close() throws IOException;
}
