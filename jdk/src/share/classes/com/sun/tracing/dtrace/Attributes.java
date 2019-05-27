package com.sun.tracing.dtrace;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;


/**
 * This annotation describes the interface's field attributes
 * for the probes in a provider.
 *
 * This annotation provides the contents of field-specific annotations
 * that specify the stability attributes and dependency class of a
 * particular field, for the probes in a provider.
 * <p>
 * The default interface attributes for unspecified fields is
 * Private/Private/Unknown.
 * <p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Attributes {
  /**
   * The stability level of the name.
   */
  StabilityLevel name() default StabilityLevel.PRIVATE;

  /**
   * The stability level of the data.
   */
  StabilityLevel data() default StabilityLevel.PRIVATE;

  /**
   * The interface attribute's dependency class.
   */
  DependencyClass dependency()  default DependencyClass.UNKNOWN;
}
