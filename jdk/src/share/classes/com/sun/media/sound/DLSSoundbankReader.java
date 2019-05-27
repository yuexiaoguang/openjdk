package com.sun.media.sound;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.spi.SoundbankReader;

/**
 * This class is used to connect the DLSSoundBank class
 * to the SoundbankReader SPI interface.
 */
public final class DLSSoundbankReader extends SoundbankReader {

    public Soundbank getSoundbank(URL url)
            throws InvalidMidiDataException, IOException {
        try {
            return new DLSSoundbank(url);
        } catch (RIFFInvalidFormatException e) {
            return null;
        } catch(IOException ioe) {
            return null;
        }
    }

    public Soundbank getSoundbank(InputStream stream)
            throws InvalidMidiDataException, IOException {
        try {
            stream.mark(512);
            return new DLSSoundbank(stream);
        } catch (RIFFInvalidFormatException e) {
            stream.reset();
            return null;
        }
    }

    public Soundbank getSoundbank(File file)
            throws InvalidMidiDataException, IOException {
        try {
            return new DLSSoundbank(file);
        } catch (RIFFInvalidFormatException e) {
            return null;
        }
    }
}
