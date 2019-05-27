package jdk.internal.dynalink.linker;

/**
 * A guarding dynamic linker that can determine whether it can link the call site solely based on the type of the first
 * argument at linking invocation time. (The first argument is usually the receiver class). Most language-specific
 * linkers will fall into this category, as they recognize their native objects as Java objects of classes implementing
 * a specific language-native interface or superclass. The linker mechanism can optimize the dispatch for these linkers.
 */
public interface TypeBasedGuardingDynamicLinker extends GuardingDynamicLinker {
    /**
     * Returns true if the linker can link an invocation where the first argument (receiver) is of the specified type.
     *
     * @param type the type to link
     * @return true if the linker can link calls for the receiver type, or false otherwise.
     */
    public boolean canLinkType(Class<?> type);
}
