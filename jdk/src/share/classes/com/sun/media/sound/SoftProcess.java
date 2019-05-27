package com.sun.media.sound;

/**
 * Control signal processor interface
 */
public interface SoftProcess extends SoftControl {

    public void init(SoftSynthesizer synth);

    public double[] get(int instance, String name);

    public void processControlLogic();

    public void reset();
}
