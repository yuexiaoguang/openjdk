/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.runtime.output;

interface OutputBuffer {

    public String close();
    public OutputBuffer append(char ch);
    public OutputBuffer append(String s);
    public OutputBuffer append(char[] s, int from, int to);

}
