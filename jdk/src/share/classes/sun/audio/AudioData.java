package sun.audio;

import java.io.*;
import java.util.Arrays;

import javax.sound.sampled.*;


/**
 * A clip of audio data. This data can be used to construct an
 * AudioDataStream, which can be played. <p>
 */

 /*
  * the idea here is that the AudioData object encapsulates the
  * data you need to play an audio clip based on a defined set
  * of data.  to do this, you require the audio data (a byte
  * array rather than an arbitrary input stream) and a format
  * object.
  */
public final class AudioData {

    private static final AudioFormat DEFAULT_FORMAT =
        new AudioFormat(AudioFormat.Encoding.ULAW,
                        8000,   // sample rate
                        8,      // sample size in bits
                        1,      // channels
                        1,      // frame size in bytes
                        8000,   // frame rate
                        true ); // bigendian (irrelevant for 8-bit data)

    AudioFormat format;   // carry forth the format array amusement
    byte buffer[];

    /**
     * Constructor
     */
    public AudioData(final byte[] buffer) {
        // if we cannot extract valid format information, we resort to assuming
        // the data will be 8k mono u-law in order to provide maximal backwards
        // compatibility....
        this(DEFAULT_FORMAT, buffer);

        // okay, we need to extract the format and the byte buffer of data
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer));
            this.format = ais.getFormat();
            ais.close();
            // $$fb 2002-10-27: buffer contains the file header now!
        } catch (IOException e) {
            // use default format
        } catch (UnsupportedAudioFileException e1 ) {
            // use default format
        }
    }


    /**
     * Non-public constructor; this is the one we use in ADS and CADS
     * constructors.
     */
    AudioData(final AudioFormat format, final byte[] buffer) {
        this.format = format;
        if (buffer != null) {
            this.buffer = Arrays.copyOf(buffer, buffer.length);
        }
    }
}
