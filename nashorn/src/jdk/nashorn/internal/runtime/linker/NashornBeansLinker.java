package jdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.internal.dynalink.beans.BeansLinker;
import jdk.internal.dynalink.linker.ConversionComparator.Comparison;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.GuardingDynamicLinker;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.support.Lookup;
import jdk.nashorn.internal.runtime.ConsString;

/**
 * This linker delegates to a {@code BeansLinker} but passes it a special linker services object that has a modified
 * {@code asType} method that will ensure that we never pass internal engine objects that should not be externally
 * observable (currently only ConsString) to Java APIs, but rather that we flatten it into a String. We can't just add
 * this functionality as custom converters via {@code GuaardingTypeConverterFactory}, since they are not consulted when
 * the target method handle parameter signature is {@code Object}.
 */
public class NashornBeansLinker implements GuardingDynamicLinker {
    private static final MethodHandle EXPORT_ARGUMENT = new Lookup(MethodHandles.lookup()).findOwnStatic("exportArgument", Object.class, Object.class);

    private final BeansLinker beansLinker = new BeansLinker();

    @Override
    public GuardedInvocation getGuardedInvocation(final LinkRequest linkRequest, final LinkerServices linkerServices) throws Exception {
        return getGuardedInvocation(beansLinker, linkRequest, linkerServices);
    }

    /**
     * Delegates to the specified linker but injects its linker services wrapper so that it will apply all special
     * conversions that this class does.
     * @param delegateLinker the linker to which the actual work is delegated to.
     * @param linkRequest the delegated link request
     * @param linkerServices the original link services that will be augmented with special conversions
     * @return the guarded invocation from the delegate, possibly augmented with special conversions
     * @throws Exception if the delegate throws an exception
     */
    public static GuardedInvocation getGuardedInvocation(final GuardingDynamicLinker delegateLinker, final LinkRequest linkRequest, final LinkerServices linkerServices) throws Exception {
        return delegateLinker.getGuardedInvocation(linkRequest, new NashornBeansLinkerServices(linkerServices));
    }

    @SuppressWarnings("unused")
    private static Object exportArgument(final Object arg) {
        return arg instanceof ConsString ? arg.toString() : arg;
    }

    private static class NashornBeansLinkerServices implements LinkerServices {
        private final LinkerServices linkerServices;

        NashornBeansLinkerServices(final LinkerServices linkerServices) {
            this.linkerServices = linkerServices;
        }

        @Override
        public MethodHandle asType(final MethodHandle handle, final MethodType fromType) {
            final MethodHandle typed = linkerServices.asType(handle, fromType);

            final MethodType handleType = handle.type();
            final int paramCount = handleType.parameterCount();
            assert fromType.parameterCount() == handleType.parameterCount();

            MethodHandle[] filters = null;
            for(int i = 0; i < paramCount; ++i) {
                if(shouldConvert(handleType.parameterType(i), fromType.parameterType(i))) {
                    if(filters == null) {
                        filters = new MethodHandle[paramCount];
                    }
                    filters[i] = EXPORT_ARGUMENT;
                }
            }

            return filters != null ? MethodHandles.filterArguments(typed, 0, filters) : typed;
        }

        private static boolean shouldConvert(final Class<?> handleType, final Class<?> fromType) {
            return handleType == Object.class && fromType == Object.class;
        }

        @Override
        public MethodHandle getTypeConverter(final Class<?> sourceType, final Class<?> targetType) {
            return linkerServices.getTypeConverter(sourceType, targetType);
        }

        @Override
        public boolean canConvert(final Class<?> from, final Class<?> to) {
            return linkerServices.canConvert(from, to);
        }

        @Override
        public GuardedInvocation getGuardedInvocation(final LinkRequest linkRequest) throws Exception {
            return linkerServices.getGuardedInvocation(linkRequest);
        }

        @Override
        public Comparison compareConversion(final Class<?> sourceType, final Class<?> targetType1, final Class<?> targetType2) {
            return linkerServices.compareConversion(sourceType, targetType1, targetType2);
        }
    }
}
