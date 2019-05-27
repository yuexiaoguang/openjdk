package sun.audio;

import java.io.*;

/**
 * An input stream to play AudioData.
 */
public class AudioDataStream extends ByteArrayInputStream {

    private final AudioData ad;

    /**
     * Constructor
     */
    public AudioDataStream(final AudioData data) {

        super(data.buffer);
        this.ad = data;
    }

    final AudioData getAudioData() {
        return ad;
    }
}
