package jdk.internal.dynalink.linker;

public class GuardedTypeConversion {
    private final GuardedInvocation conversionInvocation;
    private final boolean cacheable;

    public GuardedTypeConversion(final GuardedInvocation conversionInvocation, final boolean cacheable) {
        this.conversionInvocation = conversionInvocation;
        this.cacheable = cacheable;
    }

    public GuardedInvocation getConversionInvocation() {
        return conversionInvocation;
    }

    public boolean isCacheable() {
        return cacheable;
    }
}
