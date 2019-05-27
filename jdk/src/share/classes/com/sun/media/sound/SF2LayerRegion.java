package com.sun.media.sound;

/**
 * Soundfont layer region.
 */
public final class SF2LayerRegion extends SF2Region {

    SF2Sample sample;

    public SF2Sample getSample() {
        return sample;
    }

    public void setSample(SF2Sample sample) {
        this.sample = sample;
    }
}
