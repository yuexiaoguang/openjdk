package sun.text.normalizer;

/**
 * <code>UnicodeMatcher</code> defines a protocol for objects that can
 * match a range of characters in a Replaceable string.
 */
public interface UnicodeMatcher {

    /**
     * The character at index i, where i < contextStart || i >= contextLimit,
     * is ETHER.  This allows explicit matching by rules and UnicodeSets
     * of text outside the context.  In traditional terms, this allows anchoring
     * at the start and/or end.
     * @stable ICU 2.0
     */
    static final char ETHER = '\uFFFF';

}

//eof
