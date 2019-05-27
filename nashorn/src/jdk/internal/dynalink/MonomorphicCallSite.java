package jdk.internal.dynalink;

import java.lang.invoke.MethodHandle;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.support.AbstractRelinkableCallSite;

/**
 * A relinkable call site that implements monomorphic inline caching strategy. After it linked a method, it will keep it
 * until either its guard evaluates to false, or its switchpoint is invalidated, at which time it will throw away the
 * previous linkage, and trigger relinking with its associated {@link DynamicLinker}.
 */
public class MonomorphicCallSite extends AbstractRelinkableCallSite {
    /**
     * Creates a new call site with monomorphic inline caching strategy.
     * @param descriptor the descriptor for this call site
     */
    public MonomorphicCallSite(CallSiteDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public void relink(GuardedInvocation guardedInvocation, MethodHandle relink) {
        setTarget(guardedInvocation.compose(relink));
    }

    @Override
    public void resetAndRelink(GuardedInvocation guardedInvocation, MethodHandle relink) {
        relink(guardedInvocation, relink);
    }
}
