package com.sun.media.sound;

import java.io.IOException;

/**
 * Resampler stream interface.
 */
public interface SoftResamplerStreamer extends ModelOscillatorStream {

    public void open(ModelWavetable osc, float outputsamplerate)
            throws IOException;
}
