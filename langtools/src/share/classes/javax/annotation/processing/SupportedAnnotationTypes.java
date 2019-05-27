package javax.annotation.processing;

import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

/**
 * An annotation used to indicate what annotation types an annotation
 * processor supports.  The {@link
 * Processor#getSupportedAnnotationTypes} method can construct its
 * result from the value of this annotation, as done by {@link
 * AbstractProcessor#getSupportedAnnotationTypes}.  Only {@linkplain
 * Processor#getSupportedAnnotationTypes strings conforming to the
 * grammar} should be used as values.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface SupportedAnnotationTypes {
    /**
     * Returns the names of the supported annotation types.
     * @return the names of the supported annotation types
     */
    String [] value();
}
