package sun.audio;
import java.io.IOException;

/**
 * Signals an invalid audio stream for the stream handler.
 */
final class InvalidAudioFormatException extends IOException {


    /**
     * Constructor.
     */
    InvalidAudioFormatException() {
        super();
    }

    /**
     * Constructor with a detail message.
     */
    InvalidAudioFormatException(String s) {
        super(s);
    }
}
