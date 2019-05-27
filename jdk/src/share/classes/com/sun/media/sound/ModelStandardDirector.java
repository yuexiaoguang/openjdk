package com.sun.media.sound;

import java.util.Arrays;

/**
 * A standard director who chooses performers
 * by there keyfrom,keyto,velfrom,velto properties.
 */
public final class ModelStandardDirector implements ModelDirector {

    private final ModelPerformer[] performers;
    private final ModelDirectedPlayer player;
    private boolean noteOnUsed = false;
    private boolean noteOffUsed = false;

    public ModelStandardDirector(final ModelPerformer[] performers,
                                 final ModelDirectedPlayer player) {
        this.performers = Arrays.copyOf(performers, performers.length);
        this.player = player;
        for (final ModelPerformer p : this.performers) {
            if (p.isReleaseTriggered()) {
                noteOffUsed = true;
            } else {
                noteOnUsed = true;
            }
        }
    }

    public void close() {
    }

    public void noteOff(int noteNumber, int velocity) {
        if (!noteOffUsed)
            return;
        for (int i = 0; i < performers.length; i++) {
            ModelPerformer p = performers[i];
            if (p.getKeyFrom() <= noteNumber && p.getKeyTo() >= noteNumber) {
                if (p.getVelFrom() <= velocity && p.getVelTo() >= velocity) {
                    if (p.isReleaseTriggered()) {
                        player.play(i, null);
                    }
                }
            }
        }
    }

    public void noteOn(int noteNumber, int velocity) {
        if (!noteOnUsed)
            return;
        for (int i = 0; i < performers.length; i++) {
            ModelPerformer p = performers[i];
            if (p.getKeyFrom() <= noteNumber && p.getKeyTo() >= noteNumber) {
                if (p.getVelFrom() <= velocity && p.getVelTo() >= velocity) {
                    if (!p.isReleaseTriggered()) {
                        player.play(i, null);
                    }
                }
            }
        }
    }
}
