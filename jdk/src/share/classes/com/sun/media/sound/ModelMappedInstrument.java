package com.sun.media.sound;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.Patch;
import javax.sound.sampled.AudioFormat;

/**
 * This class is used to map instrument to another patch.
 */
public final class ModelMappedInstrument extends ModelInstrument {

    private final ModelInstrument ins;

    public ModelMappedInstrument(ModelInstrument ins, Patch patch) {
        super(ins.getSoundbank(), patch, ins.getName(), ins.getDataClass());
        this.ins = ins;
    }

    public Object getData() {
        return ins.getData();
    }

    public ModelPerformer[] getPerformers() {
        return ins.getPerformers();
    }

    public ModelDirector getDirector(ModelPerformer[] performers,
            MidiChannel channel, ModelDirectedPlayer player) {
        return ins.getDirector(performers, channel, player);
    }

    public ModelChannelMixer getChannelMixer(MidiChannel channel,
            AudioFormat format) {
        return ins.getChannelMixer(channel, format);
    }
}
