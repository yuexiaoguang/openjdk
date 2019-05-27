package com.sun.tracing;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * An annotation used to override the name of a probe.
 * <p>
 * This annotation can be added to a method in a user-defined {@code Provider}
 * interface, to set the name that will be used for the generated probe
 * associated with that method.  Without this annotation, the name will be the
 * name of the method.
 * <p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProbeName {
    String value();
}

