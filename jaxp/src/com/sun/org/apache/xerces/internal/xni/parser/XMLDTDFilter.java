package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;

/**
 * Defines a DTD filter that acts as both a receiver and an emitter
 * of DTD events.
 */
public interface XMLDTDFilter
    extends XMLDTDHandler, XMLDTDSource {

} // interface XMLDTDFilter
