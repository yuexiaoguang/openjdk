/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc;

import java.text.Collator;
import java.util.Locale;

public interface CollatorFactory {

    public Collator getCollator(String lang, String country);
    public Collator getCollator(Locale locale);
}
