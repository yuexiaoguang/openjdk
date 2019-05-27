package com.sun.media.sound;

import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.spi.MixerProvider;

/**
 * Provider for software audio mixer
 */
public final class SoftMixingMixerProvider extends MixerProvider {

    static SoftMixingMixer globalmixer = null;

    static Thread lockthread = null;

    static final Object mutex = new Object();

    public Mixer getMixer(Info info) {
        if (!(info == null || info == SoftMixingMixer.info)) {
            throw new IllegalArgumentException("Mixer " + info.toString()
                    + " not supported by this provider.");
        }
        synchronized (mutex) {
            if (lockthread != null)
                if (Thread.currentThread() == lockthread)
                    throw new IllegalArgumentException("Mixer "
                            + info.toString()
                            + " not supported by this provider.");
            if (globalmixer == null)
                globalmixer = new SoftMixingMixer();
            return globalmixer;
        }

    }

    public Info[] getMixerInfo() {
        return new Info[] { SoftMixingMixer.info };
    }

}
