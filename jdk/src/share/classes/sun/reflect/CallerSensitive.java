package sun.reflect;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/**
 * A method annotated @CallerSensitive is sensitive to its calling class,
 * via {@link sun.reflect.Reflection#getCallerClass Reflection.getCallerClass},
 * or via some equivalent.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface CallerSensitive {
}
