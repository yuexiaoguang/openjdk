package com.sun.xml.internal.fastinfoset.alphabet;

import com.sun.xml.internal.fastinfoset.EncodingConstants;
import com.sun.xml.internal.org.jvnet.fastinfoset.RestrictedAlphabet;

public final class BuiltInRestrictedAlphabets {
    public final static char[][] table =
            new char[EncodingConstants.RESTRICTED_ALPHABET_BUILTIN_END + 1][];

    static {
        table[RestrictedAlphabet.NUMERIC_CHARACTERS_INDEX] = RestrictedAlphabet.NUMERIC_CHARACTERS.toCharArray();
        table[RestrictedAlphabet.DATE_TIME_CHARACTERS_INDEX] = RestrictedAlphabet.DATE_TIME_CHARACTERS.toCharArray();
    }
}
