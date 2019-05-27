package com.sun.media.sound;

/**
 * Audio processor interface.
 */
public interface SoftAudioProcessor {

    public void globalParameterControlChange(int[] slothpath, long param,
            long value);

    public void init(float samplerate, float controlrate);

    public void setInput(int pin, SoftAudioBuffer input);

    public void setOutput(int pin, SoftAudioBuffer output);

    public void setMixMode(boolean mix);

    public void processAudio();

    public void processControlLogic();
}
