package com.sun.xml.internal.ws.encoding;

/**
 * A {@link javax.xml.stream.XMLStreamWriter} doesn't expose any method to
 * give encoding. An implementation of writer may implement
 * this interface to give the encoding with which the writer is created.
 */
public interface HasEncoding {
    public String getEncoding();
}
