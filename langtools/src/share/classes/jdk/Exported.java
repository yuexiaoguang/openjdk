package jdk;

import java.lang.annotation.*;

/**
  * Indicates whether or not a JDK specific type or package is an
  * exported part of the JDK suitable for use outside of the JDK
  * implementation itself.
  *
  * This annotation should only be applied to types and packages
  * <em>outside</em> of the Java SE namespaces of {@code java.*} and
  * {@code javax.*} packages.  For example, certain portions of {@code
  * com.sun.*} are official parts of the JDK meant to be generally
  * usable while other portions of {@code com.sun.*} are not.  This
  * annotation type allows those portions to be easily and
  * programmatically distinguished.
  *
  * <p>If in one release a type or package is
  * <code>@Exported(true)</code>, in a subsequent major release such a
  * type or package can transition to <code>@Exported(false)</code>.
  *
  * <p>If a type or package is <code>@Exported(false)</code> in a
  * release, it may be removed in a subsequent major release.
  *
  * <p>If a top-level type has an <code>@Exported</code> annotation,
  * any nested member types with the top-level type should have an
  * <code>@Exported</code> annotation with the same value.
  *
  * (In exceptional cases, if a nested type is going to be removed
  * before its enclosing type, the nested type's could be
  * <code>@Exported(false)</code> while its enclosing type was
  * <code>@Exported(true)</code>.)
  *
  * Likewise, if a package has an <code>@Exported</code> annotation,
  * top-level types within that package should also have an
  * <code>@Exported</code> annotation.
  *
  * Sometimes a top-level type may have a different
  * <code>@Exported</code> value than its package.
  *
  * @since 1.8
  */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PACKAGE})
@Exported
public @interface Exported {
    /**
     * Whether or not the annotated type or package is an exported part of the JDK.
     */
    boolean value() default true;
}
