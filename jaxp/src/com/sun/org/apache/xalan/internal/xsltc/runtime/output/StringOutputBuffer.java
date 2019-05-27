/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.runtime.output;


class StringOutputBuffer implements OutputBuffer {
    private StringBuffer _buffer;

    public StringOutputBuffer() {
        _buffer = new StringBuffer();
    }

    public String close() {
        return _buffer.toString();
    }

    public OutputBuffer append(String s) {
        _buffer.append(s);
        return this;
    }

    public OutputBuffer append(char[] s, int from, int to) {
        _buffer.append(s, from, to);
        return this;
    }

    public OutputBuffer append(char ch) {
        _buffer.append(ch);
        return this;
    }
}
