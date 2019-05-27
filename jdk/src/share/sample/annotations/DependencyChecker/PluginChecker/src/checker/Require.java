/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
package checker;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that a plug-in depends on a module.
 */
@Retention(RetentionPolicy.CLASS)
@Repeatable(RequireContainer.class)
public @interface Require {

    /**
     * Returns the required module.
     *
     * @return required module.
     */
    Module value();

    /**
     * Returns the minimum supported version of a module.
     *
     * @return minimum supported version of a module.
     */
    int minVersion() default 1;

    /**
     * Returns the maximum supported version of a module.
     *
     * @return maximum supported version of a module.
     */
    int maxVersion() default Integer.MAX_VALUE;

    /**
     * Returns true if a module is optional. A module is optional if a system
     * works without that module but is missing some functionality. Returns false if a system
     * won't work without the specified module.
     *
     * @return true if module is optional. False otherwise.
     */
    boolean optional() default false;
}
