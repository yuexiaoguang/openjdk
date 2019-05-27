package com.sun.org.apache.xerces.internal.xni.grammars;

/**
 * All information specific to DTD grammars.
 */
public interface XMLDTDDescription extends XMLGrammarDescription {

    /**
     * Return the root name of this DTD.
     *
     * @return  the root name. null if the name is unknown.
     */
    public String getRootName();

} // interface XMLDTDDescription
