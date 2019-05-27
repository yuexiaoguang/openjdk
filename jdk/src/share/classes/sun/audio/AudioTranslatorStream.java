package sun.audio;

import java.io.InputStream;
import java.io.IOException;

/**
 * Translator for native audio formats (not implemented in this release).
 */
public final class AudioTranslatorStream extends NativeAudioStream {

        private final int length = 0;

        public AudioTranslatorStream(InputStream in) throws IOException {
            super(in);
            // No translators supported yet.
            throw new InvalidAudioFormatException();
        }

        public int getLength() {
            return length;
        }
    }
