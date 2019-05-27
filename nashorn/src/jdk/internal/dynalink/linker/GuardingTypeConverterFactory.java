package jdk.internal.dynalink.linker;

import jdk.internal.dynalink.support.TypeUtilities;

/**
 * Optional interface that can be implemented by {@link GuardingDynamicLinker} implementations to provide
 * language-runtime specific implicit type conversion capabilities. Note that if you implement this interface, you will
 * very likely want to implement {@link ConversionComparator} interface too, as your additional language-specific
 * conversions, in absence of a strategy for prioritizing these conversions, will cause more ambiguity in selecting the
 * correct overload when trying to link to an overloaded POJO method.
 */
public interface GuardingTypeConverterFactory {
    /**
     * Returns a guarded type conversion that receives an Object of the specified source type and returns an Object
     * converted to the specified target type. The type of the invocation is targetType(sourceType), while the type of
     * the guard is boolean(sourceType). Note that this will never be invoked for type conversions allowed by the JLS
     * 5.3 "Method Invocation Conversion", see {@link TypeUtilities#isMethodInvocationConvertible(Class, Class)} for
     * details. An implementation can assume it is never requested to produce a converter for these conversions.
     *
     * @param sourceType source type
     * @param targetType the target type.
     * @return a guarded type conversion that contains a guarded invocation that can take an object (if it passes guard)
     * and return another object that is its representation coerced into the target type. In case the factory is certain
     * it is unable to handle a conversion, it can return null. In case the factory is certain that it can always handle
     * the conversion, it can return an unconditional invocation (one whose guard is null).
     * @throws Exception if there was an error during creation of the converter
     */
    public GuardedTypeConversion convertToType(Class<?> sourceType, Class<?> targetType) throws Exception;
}
