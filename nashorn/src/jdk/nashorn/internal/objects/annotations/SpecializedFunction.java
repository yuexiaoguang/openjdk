package jdk.nashorn.internal.objects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The SpecializedFunction annotation is used to flag more type specific functions than the standard one in
 * Native objects. For example {@link jdk.nashorn.internal.objects.NativeMath#max} takes an arbitrary number of
 * Object elements as an array. Call this function, including the varargs collector that allocates the array
 * upon each invocation, is much more expensive than calling a specialized function that takes two arguments
 * of, e.g. int type from the call site, such as {@link jdk.nashorn.internal.objects.NativeMath#max(Object, int, int)}.
 * {@link jdk.nashorn.internal.runtime.ScriptFunction} will try to look up the most specific function when
 * linking the callsite.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SpecializedFunction {
    //empty
}
