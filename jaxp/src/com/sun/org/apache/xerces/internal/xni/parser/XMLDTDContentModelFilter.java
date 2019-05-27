package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;

/**
 * Defines a DTD content model filter that acts as both a receiver and
 * an emitter of DTD content model events.
 */
public interface XMLDTDContentModelFilter
    extends XMLDTDContentModelHandler, XMLDTDContentModelSource {

} // interface XMLDTDContentModelFilter
