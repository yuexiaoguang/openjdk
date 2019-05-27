package com.sun.media.sound;

/**
 *  ModelDirectedPlayer is the one who is directed by ModelDirector
 *  to play ModelPerformer objects.
 */
public interface ModelDirectedPlayer {

    public void play(int performerIndex, ModelConnectionBlock[] connectionBlocks);
}
