package javax.annotation.processing;

import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;
import javax.lang.model.SourceVersion;


/**
 * An annotation used to indicate the latest source version an
 * annotation processor supports.  The {@link
 * Processor#getSupportedSourceVersion} method can construct its
 * result from the value of this annotation, as done by {@link
 * AbstractProcessor#getSupportedSourceVersion}.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface SupportedSourceVersion {
    /**
     * Returns the latest supported source version.
     * @return the latest supported source version
     */
    SourceVersion value();
}
