package com.sun.xml.internal.txw2.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Used along with {@link XmlElement} to write a CDATA section,
 * instead of the normal PCDATA.
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface XmlCDATA {
}
