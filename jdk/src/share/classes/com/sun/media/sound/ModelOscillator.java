package com.sun.media.sound;

/**
 * This interface is used for oscillators.
 * See example in ModelDefaultOscillator which is a wavetable oscillator.
 */
public interface ModelOscillator {

    public int getChannels();

    /**
     * Attenuation is in cB.
     * @return
     */
    public float getAttenuation();

    public ModelOscillatorStream open(float samplerate);
}
