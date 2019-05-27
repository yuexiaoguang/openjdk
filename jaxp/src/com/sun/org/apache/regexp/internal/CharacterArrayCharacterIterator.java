/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.regexp.internal;

/**
 * Encapsulates char[] as CharacterIterator
 */
public final class CharacterArrayCharacterIterator implements CharacterIterator
{
    /** encapsulated */
    private final char[] src;
    /** offset in the char array */
    private final int off;
    /** used portion of the array */
    private final int len;

    /** @param src - encapsulated String */
    public CharacterArrayCharacterIterator(char[] src, int off, int len)
    {
        this.src = src;
        this.off = off;
        this.len = len;
    }

    /** @return a substring */
    public String substring(int beginIndex, int endIndex)
    {
        if (endIndex > len) {
            throw new IndexOutOfBoundsException("endIndex=" + endIndex
                                                + "; sequence size=" + len);
        }
        if (beginIndex < 0 || beginIndex > endIndex) {
            throw new IndexOutOfBoundsException("beginIndex=" + beginIndex
                                                + "; endIndex=" + endIndex);
        }
        return new String(src, off + beginIndex, endIndex - beginIndex);
    }

    /** @return a substring */
    public String substring(int beginIndex)
    {
        return substring(beginIndex, len);
    }

    /** @return a character at the specified position. */
    public char charAt(int pos)
    {
        return src[off + pos];
    }

    /** @return <tt>true</tt> iff if the specified index is after the end of the character stream */
    public boolean isEnd(int pos)
    {
        return (pos >= len);
    }
}
