package javax.sound.midi;


/**
 * <p>{@code MidiDeviceTransmitter} is a {@code Transmitter} which represents
 * a MIDI input connector of a {@code MidiDevice}
 * (see {@link MidiDevice#getTransmitter()}).
 */
public interface MidiDeviceTransmitter extends Transmitter {

    /**
     * Obtains a MidiDevice object which is an owner of this Transmitter.
     * @return a MidiDevice object which is an owner of this Transmitter
     */
    public MidiDevice getMidiDevice();
}
