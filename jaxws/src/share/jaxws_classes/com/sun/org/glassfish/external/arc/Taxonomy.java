package com.sun.org.glassfish.external.arc;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
  Annotates classes, methods or fields as to the ARC taxonomy (stability level).
  Note that a class can be annotated as a whole, but that annotations on its methods
  and fields override the whole-class annotation.
 */
@Retention(RUNTIME)
@Documented
@Target( {METHOD, TYPE, ANNOTATION_TYPE, FIELD, PACKAGE})
public @interface Taxonomy  {
    public Stability stability() default Stability.UNSPECIFIED;
    public String description() default "";
}
