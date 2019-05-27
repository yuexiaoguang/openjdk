/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.regexp.internal;

/**
 * Encapsulates String as CharacterIterator.
 */
public final class StringCharacterIterator implements CharacterIterator
{
    /** encapsulated */
    private final String src;

    /** @param src - encapsulated String */
    public StringCharacterIterator(String src)
    {
        this.src = src;
    }

    /** @return a substring */
    public String substring(int beginIndex, int endIndex)
    {
        return src.substring(beginIndex, endIndex);
    }

    /** @return a substring */
    public String substring(int beginIndex)
    {
        return src.substring(beginIndex);
    }

    /** @return a character at the specified position. */
    public char charAt(int pos)
    {
        return src.charAt(pos);
    }

    /** @return <tt>true</tt> iff if the specified index is after the end of the character stream */
    public boolean isEnd(int pos)
    {
        return (pos >= src.length());
    }
}
