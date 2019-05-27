package jdk.internal.dynalink.support;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.GuardingDynamicLinker;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;

/**
 * A {@link GuardingDynamicLinker} that delegates sequentially to a list of other guarding dynamic linkers. The first
 * value returned from a component linker other than null is returned. If no component linker returns an invocation,
 * null is returned.
 */
public class CompositeGuardingDynamicLinker implements GuardingDynamicLinker, Serializable {

    private static final long serialVersionUID = 1L;

    private final GuardingDynamicLinker[] linkers;

    /**
     * Creates a new composite linker.
     *
     * @param linkers a list of component linkers.
     */
    public CompositeGuardingDynamicLinker(Iterable<? extends GuardingDynamicLinker> linkers) {
        final List<GuardingDynamicLinker> l = new LinkedList<>();
        for(GuardingDynamicLinker linker: linkers) {
            l.add(linker);
        }
        this.linkers = l.toArray(new GuardingDynamicLinker[l.size()]);
    }

    @Override
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, final LinkerServices linkerServices)
            throws Exception {
        for(final GuardingDynamicLinker linker: linkers) {
            final GuardedInvocation invocation = linker.getGuardedInvocation(linkRequest, linkerServices);
            if(invocation != null) {
                return invocation;
            }
        }
        return null;
    }
}
