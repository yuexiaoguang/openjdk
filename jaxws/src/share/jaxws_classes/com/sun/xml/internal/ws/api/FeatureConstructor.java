package com.sun.xml.internal.ws.api;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressingFeature;

import javax.xml.ws.WebServiceFeature;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation should be used on a constructor of classes extending {@link WebServiceFeature} other than
 * Spec defined features, to help JAX-WS runtime recognize feature extensions.
 * </p>
 * <p>
 * For WebServiceFeature annotations to be recognizable by JAX-WS runtime, the feature annotation should point
 * to a corresponding bean (class extending WebServiceFeature). Only one of the constructors in the bean MUST be marked
 * with @FeatureConstructor whose value captures the annotaion attribute names for the corresponding parameters.
 * </p>
 * For example,
 * @see MemberSubmissionAddressingFeature
 * @see MemberSubmissionAddressing
 *
 * @see com.sun.xml.internal.ws.developer.Stateful
 * @see com.sun.xml.internal.ws.developer.StatefulFeature
 */
@Retention(RUNTIME)
@Target(ElementType.CONSTRUCTOR)

public @interface FeatureConstructor {
    /**
     * The name of the parameter.
     */
    String[] value() default {};
}
