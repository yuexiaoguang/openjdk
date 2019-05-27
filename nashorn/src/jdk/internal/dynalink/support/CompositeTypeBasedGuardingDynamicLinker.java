package jdk.internal.dynalink.support;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.GuardingDynamicLinker;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.linker.TypeBasedGuardingDynamicLinker;

/**
 * A composite type-based guarding dynamic linker. When a receiver of a not yet seen class is encountered, all linkers
 * are queried sequentially on their {@link TypeBasedGuardingDynamicLinker#canLinkType(Class)} method. The linkers
 * returning true are then bound to the class, and next time a receiver of same type is encountered, the linking is
 * delegated to those linkers only, speeding up dispatch.
 */
public class CompositeTypeBasedGuardingDynamicLinker implements TypeBasedGuardingDynamicLinker, Serializable {
    private static final long serialVersionUID = 1L;

    // Using a separate static class instance so there's no strong reference from the class value back to the composite
    // linker.
    private static class ClassToLinker extends ClassValue<List<TypeBasedGuardingDynamicLinker>> {
        private static final List<TypeBasedGuardingDynamicLinker> NO_LINKER = Collections.emptyList();
        private final TypeBasedGuardingDynamicLinker[] linkers;
        private final List<TypeBasedGuardingDynamicLinker>[] singletonLinkers;

        @SuppressWarnings("unchecked")
        ClassToLinker(TypeBasedGuardingDynamicLinker[] linkers) {
            this.linkers = linkers;
            singletonLinkers = new List[linkers.length];
            for(int i = 0; i < linkers.length; ++i) {
                singletonLinkers[i] = Collections.singletonList(linkers[i]);
            }
        }

        @Override
        protected List<TypeBasedGuardingDynamicLinker> computeValue(Class<?> clazz) {
            List<TypeBasedGuardingDynamicLinker> list = NO_LINKER;
            for(int i = 0; i < linkers.length; ++i) {
                final TypeBasedGuardingDynamicLinker linker = linkers[i];
                if(linker.canLinkType(clazz)) {
                    switch(list.size()) {
                        case 0: {
                            list = singletonLinkers[i];
                            break;
                        }
                        case 1: {
                            list = new LinkedList<>(list);
                        }
                        //$FALL-THROUGH$
                        default: {
                            list.add(linker);
                        }
                    }
                }
            }
            return list;
        }
    }

    private final ClassValue<List<TypeBasedGuardingDynamicLinker>> classToLinker;

    /**
     * Creates a new composite type-based linker.
     *
     * @param linkers the component linkers
     */
    public CompositeTypeBasedGuardingDynamicLinker(Iterable<? extends TypeBasedGuardingDynamicLinker> linkers) {
        final List<TypeBasedGuardingDynamicLinker> l = new LinkedList<>();
        for(TypeBasedGuardingDynamicLinker linker: linkers) {
            l.add(linker);
        }
        this.classToLinker = new ClassToLinker(l.toArray(new TypeBasedGuardingDynamicLinker[l.size()]));
    }

    @Override
    public boolean canLinkType(Class<?> type) {
        return !classToLinker.get(type).isEmpty();
    }

    @Override
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, final LinkerServices linkerServices)
            throws Exception {
        final Object obj = linkRequest.getReceiver();
        if(obj == null) {
            return null;
        }
        for(TypeBasedGuardingDynamicLinker linker: classToLinker.get(obj.getClass())) {
            final GuardedInvocation invocation = linker.getGuardedInvocation(linkRequest, linkerServices);
            if(invocation != null) {
                return invocation;
            }
        }
        return null;
    }

    /**
     * Optimizes a list of type-based linkers. If a group of adjacent linkers in the list all implement
     * {@link TypeBasedGuardingDynamicLinker}, they will be replaced with a single instance of
     * {@link CompositeTypeBasedGuardingDynamicLinker} that contains them.
     *
     * @param linkers the list of linkers to optimize
     * @return the optimized list
     */
    public static List<GuardingDynamicLinker> optimize(Iterable<? extends GuardingDynamicLinker> linkers) {
        final List<GuardingDynamicLinker> llinkers = new LinkedList<>();
        final List<TypeBasedGuardingDynamicLinker> tblinkers = new LinkedList<>();
        for(GuardingDynamicLinker linker: linkers) {
            if(linker instanceof TypeBasedGuardingDynamicLinker) {
                tblinkers.add((TypeBasedGuardingDynamicLinker)linker);
            } else {
                addTypeBased(llinkers, tblinkers);
                llinkers.add(linker);
            }
        }
        addTypeBased(llinkers, tblinkers);
        return llinkers;
    }

    private static void addTypeBased(List<GuardingDynamicLinker> llinkers,
            List<TypeBasedGuardingDynamicLinker> tblinkers) {
        switch(tblinkers.size()) {
            case 0: {
                break;
            }
            case 1: {
                llinkers.addAll(tblinkers);
                tblinkers.clear();
                break;
            }
            default: {
                llinkers.add(new CompositeTypeBasedGuardingDynamicLinker(tblinkers));
                tblinkers.clear();
                break;
            }
        }
    }
}
