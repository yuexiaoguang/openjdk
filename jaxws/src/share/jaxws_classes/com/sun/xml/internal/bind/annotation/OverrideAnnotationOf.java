package com.sun.xml.internal.bind.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Designates an annotation from base class which shall be overriden by annotation placed together with this.
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface OverrideAnnotationOf {
    String value() default "content";
}
