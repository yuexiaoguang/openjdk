package com.sun.xml.internal.org.jvnet.fastinfoset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface EncodingAlgorithm {

    public Object decodeFromBytes(byte[] b, int start, int length) throws EncodingAlgorithmException;

    public Object decodeFromInputStream(InputStream s) throws EncodingAlgorithmException, IOException;


    public void encodeToOutputStream(Object data, OutputStream s) throws EncodingAlgorithmException, IOException;


    public Object convertFromCharacters(char[] ch, int start, int length) throws EncodingAlgorithmException;

    public void convertToCharacters(Object data, StringBuffer s) throws EncodingAlgorithmException;

}
