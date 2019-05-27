package jdk.nashorn.internal.objects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The SpecializedConstructor annotation is used to flag more type specific constructors than the standard one in
 * Native objects. For example {@link jdk.nashorn.internal.objects.NativeArray#construct} takes an arbitrary number of
 * Object elements as an array. Call this constructor, including the varargs collector that allocates the array
 * upon each invocation, is much more expensive than calling a specialized constructor that takes one arguments
 * of, e.g. int type from the call site, such as
 * {@link jdk.nashorn.internal.objects.NativeArray#construct(boolean, Object, int)}.
 * {@link jdk.nashorn.internal.runtime.ScriptFunction} will try to look up the most specific function when
 * linking the callsite.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SpecializedConstructor {
    //empty
}
