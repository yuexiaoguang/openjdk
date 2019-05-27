package jdk.internal.dynalink.support;

import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.linker.TypeBasedGuardingDynamicLinker;

/**
 * A linker that can't link any call site. Only used internally by {@link CompositeTypeBasedGuardingDynamicLinker}. Can
 * be used by other language runtimes if they need it though.
 */
public class BottomGuardingDynamicLinker implements TypeBasedGuardingDynamicLinker {

    /**
     * The sole instance of this stateless linker.
     */
    public static final BottomGuardingDynamicLinker INSTANCE = new BottomGuardingDynamicLinker();

    private BottomGuardingDynamicLinker() {
    }

    @Override
    public boolean canLinkType(Class<?> type) {
        return false;
    }

    @Override
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) {
        return null;
    }
}
