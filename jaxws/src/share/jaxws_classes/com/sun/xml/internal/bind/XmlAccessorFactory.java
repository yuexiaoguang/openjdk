package com.sun.xml.internal.bind;

import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Identifies a user provided customized Accessor
 * to be used.
 */
@Retention(RUNTIME)
@Target({TYPE,PACKAGE})
public @interface XmlAccessorFactory {
    Class<? extends AccessorFactory> value();
}
