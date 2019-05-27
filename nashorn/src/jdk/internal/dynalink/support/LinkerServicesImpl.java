package jdk.internal.dynalink.support;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import jdk.internal.dynalink.linker.ConversionComparator.Comparison;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.GuardingDynamicLinker;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;

/**
 * Default implementation of the {@link LinkerServices} interface.
 */
public class LinkerServicesImpl implements LinkerServices {

    private static final RuntimePermission GET_CURRENT_LINK_REQUEST = new RuntimePermission("dynalink.getCurrentLinkRequest");
    private static final ThreadLocal<LinkRequest> threadLinkRequest = new ThreadLocal<>();

    private final TypeConverterFactory typeConverterFactory;
    private final GuardingDynamicLinker topLevelLinker;

    /**
     * Creates a new linker services object.
     *
     * @param typeConverterFactory the type converter factory exposed by the services.
     * @param topLevelLinker the top level linker used by the services.
     */
    public LinkerServicesImpl(final TypeConverterFactory typeConverterFactory,
            final GuardingDynamicLinker topLevelLinker) {
        this.typeConverterFactory = typeConverterFactory;
        this.topLevelLinker = topLevelLinker;
    }

    @Override
    public boolean canConvert(Class<?> from, Class<?> to) {
        return typeConverterFactory.canConvert(from, to);
    }

    @Override
    public MethodHandle asType(MethodHandle handle, MethodType fromType) {
        return typeConverterFactory.asType(handle, fromType);
    }

    @Override
    public MethodHandle getTypeConverter(Class<?> sourceType, Class<?> targetType) {
        return typeConverterFactory.getTypeConverter(sourceType, targetType);
    }

    @Override
    public Comparison compareConversion(Class<?> sourceType, Class<?> targetType1, Class<?> targetType2) {
        return typeConverterFactory.compareConversion(sourceType, targetType1, targetType2);
    }

    @Override
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest) throws Exception {
        final LinkRequest prevLinkRequest = threadLinkRequest.get();
        threadLinkRequest.set(linkRequest);
        try {
            return topLevelLinker.getGuardedInvocation(linkRequest, this);
        } finally {
            threadLinkRequest.set(prevLinkRequest);
        }
    }

    /**
     * Returns the currently processed link request, or null if the method is invoked outside of the linking process.
     * @return the currently processed link request, or null.
     * @throws SecurityException if the calling code doesn't have the {@code "dynalink.getCurrentLinkRequest"} runtime
     * permission.
     */
    public static LinkRequest getCurrentLinkRequest() {
        SecurityManager sm = System.getSecurityManager();
        if(sm != null) {
            sm.checkPermission(GET_CURRENT_LINK_REQUEST);
        }
        return threadLinkRequest.get();
    }
}
