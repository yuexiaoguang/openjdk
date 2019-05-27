package jdk.internal.dynalink.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.internal.dynalink.DynamicLinker;
import jdk.internal.dynalink.linker.ConversionComparator.Comparison;

/**
 * Interface for services provided to {@link GuardingDynamicLinker} instances by the {@link DynamicLinker} that owns
 * them. You can think of it as the interface of the {@link DynamicLinker} that faces the {@link GuardingDynamicLinker}
 * s.
 */
public interface LinkerServices {
    /**
     * Similar to {@link MethodHandle#asType(MethodType)} except it also hooks in method handles produced by
     * {@link GuardingTypeConverterFactory} implementations, providing for language-specific type coercing of
     * parameters. It will apply {@link MethodHandle#asType(MethodType)} for all primitive-to-primitive,
     * wrapper-to-primitive, primitive-to-wrapper conversions as well as for all upcasts. For all other conversions,
     * it'll insert {@link MethodHandles#filterArguments(MethodHandle, int, MethodHandle...)} with composite filters
     * provided by {@link GuardingTypeConverterFactory} implementations. It doesn't use language-specific conversions on
     * the return type.
     *
     * @param handle target method handle
     * @param fromType the types of source arguments
     * @return a method handle that is a suitable combination of {@link MethodHandle#asType(MethodType)} and
     * {@link MethodHandles#filterArguments(MethodHandle, int, MethodHandle...)} with
     * {@link GuardingTypeConverterFactory} produced type converters as filters.
     */
    public MethodHandle asType(MethodHandle handle, MethodType fromType);

    /**
     * Given a source and target type, returns a method handle that converts between them. Never returns null; in worst
     * case it will return an identity conversion (that might fail for some values at runtime). You rarely need to use
     * this method directly; you should mostly rely on {@link #asType(MethodHandle, MethodType)} instead. You really
     * only need this method if you have a piece of your program that is written in Java, and you need to reuse existing
     * type conversion machinery in a non-invokedynamic context.
     * @param sourceType the type to convert from
     * @param targetType the type to convert to
     * @return a method handle performing the conversion.
     */
    public MethodHandle getTypeConverter(Class<?> sourceType, Class<?> targetType);

    /**
     * Returns true if there might exist a conversion between the requested types (either an automatic JVM conversion,
     * or one provided by any available {@link GuardingTypeConverterFactory}), or false if there definitely does not
     * exist a conversion between the requested types. Note that returning true does not guarantee that the conversion
     * will succeed at runtime (notably, if the "from" or "to" types are sufficiently generic), but returning false
     * guarantees that it would fail.
     *
     * @param from the source type for the conversion
     * @param to the target type for the conversion
     * @return true if there can be a conversion, false if there can not.
     */
    public boolean canConvert(Class<?> from, Class<?> to);

    /**
     * Creates a guarded invocation using the {@link DynamicLinker} that exposes this linker services interface. Linkers
     * can typically use them to delegate linking of wrapped objects.
     *
     * @param linkRequest a request for linking the invocation
     * @return a guarded invocation linked by the top-level linker (or any of its delegates). Can be null if no
     * available linker is able to link the invocation.
     * @throws Exception in case the top-level linker throws an exception
     */
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest) throws Exception;

    /**
     * Determines which of the two type conversions from a source type to the two target types is preferred. This is
     * used for dynamic overloaded method resolution. If the source type is convertible to exactly one target type with
     * a method invocation conversion, it is chosen, otherwise available {@link ConversionComparator}s are consulted.
     * @param sourceType the source type.
     * @param targetType1 one potential target type
     * @param targetType2 another potential target type.
     * @return one of Comparison constants that establish which - if any - of the target types is preferable for the
     * conversion.
     */
    public Comparison compareConversion(Class<?> sourceType, Class<?> targetType1, Class<?> targetType2);
}
