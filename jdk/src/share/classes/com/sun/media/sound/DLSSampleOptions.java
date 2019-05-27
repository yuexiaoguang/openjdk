package com.sun.media.sound;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores options how to playback sampled data like pitch/tuning,
 * attenuation and loops.
 * It is stored as a "wsmp" chunk inside DLS files.
 */
public final class DLSSampleOptions {

    int unitynote;
    short finetune;
    int attenuation;
    long options;
    List<DLSSampleLoop> loops = new ArrayList<DLSSampleLoop>();

    public int getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(int attenuation) {
        this.attenuation = attenuation;
    }

    public short getFinetune() {
        return finetune;
    }

    public void setFinetune(short finetune) {
        this.finetune = finetune;
    }

    public List<DLSSampleLoop> getLoops() {
        return loops;
    }

    public long getOptions() {
        return options;
    }

    public void setOptions(long options) {
        this.options = options;
    }

    public int getUnitynote() {
        return unitynote;
    }

    public void setUnitynote(int unitynote) {
        this.unitynote = unitynote;
    }
}
