package sun.nio.cs;

/*
 * FastPath char[]->byte[] encoder, REPLACE on malformed input or
 * unmappable input.
 */
public interface ArrayEncoder {
    int encode(char[] src, int off, int len, byte[] dst);
}
