package com.sun.xml.internal.org.jvnet.fastinfoset.sax;

import org.xml.sax.SAXException;

public interface RestrictedAlphabetContentHandler {

    public void numericCharacters(char ch[], int start, int length) throws SAXException;

    public void dateTimeCharacters(char ch[], int start, int length) throws SAXException;

    public void alphabetCharacters(String alphabet, char ch[], int start, int length) throws SAXException;
}
