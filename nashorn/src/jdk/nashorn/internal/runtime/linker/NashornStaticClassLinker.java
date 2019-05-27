package jdk.nashorn.internal.runtime.linker;

import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.beans.BeansLinker;
import jdk.internal.dynalink.beans.StaticClass;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.GuardingDynamicLinker;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.internal.dynalink.support.Guards;
import jdk.nashorn.internal.runtime.Context;
import jdk.nashorn.internal.runtime.ECMAErrors;

/**
 * Internal linker for {@link StaticClass} objects, only ever used by Nashorn engine and not exposed to other engines.
 * It is used for extending the "new" operator on StaticClass in order to be able to instantiate interfaces and abstract
 * classes by passing a ScriptObject or ScriptFunction as their implementation, e.g.:
 * <pre>
 *   var r = new Runnable() { run: function() { print("Hello World" } }
 * </pre>
 * or for SAM types, even just passing a function:
 * <pre>
 *   var r = new Runnable(function() { print("Hello World" })
 * </pre>
 */
final class NashornStaticClassLinker implements TypeBasedGuardingDynamicLinker {
    private static final GuardingDynamicLinker staticClassLinker = BeansLinker.getLinkerForClass(StaticClass.class);

    @Override
    public boolean canLinkType(Class<?> type) {
        return type == StaticClass.class;
    }

    @Override
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
        final LinkRequest request = linkRequest.withoutRuntimeContext(); // Nashorn has no runtime context
        final Object self = request.getReceiver();
        if (self.getClass() != StaticClass.class) {
            return null;
        }
        final Class<?> receiverClass = ((StaticClass) self).getRepresentedClass();
        Bootstrap.checkReflectionAccess(receiverClass, true);
        final CallSiteDescriptor desc = request.getCallSiteDescriptor();
        // We intercept "new" on StaticClass instances to provide additional capabilities
        if ("new".equals(desc.getNameToken(CallSiteDescriptor.OPERATOR))) {
            // make sure new is on accessible Class
            Context.checkPackageAccess(receiverClass);

            // Is the class abstract? (This includes interfaces.)
            if (NashornLinker.isAbstractClass(receiverClass)) {
                // Change this link request into a link request on the adapter class.
                final Object[] args = request.getArguments();
                args[0] = JavaAdapterFactory.getAdapterClassFor(new Class<?>[] { receiverClass }, null,
                        linkRequest.getCallSiteDescriptor().getLookup());
                final LinkRequest adapterRequest = request.replaceArguments(request.getCallSiteDescriptor(), args);
                final GuardedInvocation gi = checkNullConstructor(delegate(linkerServices, adapterRequest), receiverClass);
                // Finally, modify the guard to test for the original abstract class.
                return gi.replaceMethods(gi.getInvocation(), Guards.getIdentityGuard(self));
            }
            // If the class was not abstract, just delegate linking to the standard StaticClass linker. Make an
            // additional check to ensure we have a constructor. We could just fall through to the next "return"
            // statement, except we also insert a call to checkNullConstructor() which throws an ECMAScript TypeError
            // with a more intuitive message when no suitable constructor is found.
            return checkNullConstructor(delegate(linkerServices, request), receiverClass);
        }
        // In case this was not a "new" operation, just delegate to the the standard StaticClass linker.
        return delegate(linkerServices, request);
    }

    private static GuardedInvocation delegate(LinkerServices linkerServices, final LinkRequest request) throws Exception {
        return NashornBeansLinker.getGuardedInvocation(staticClassLinker, request, linkerServices);
    }

    private static GuardedInvocation checkNullConstructor(final GuardedInvocation ctorInvocation, final Class<?> receiverClass) {
        if(ctorInvocation == null) {
            throw ECMAErrors.typeError("no.constructor.matches.args", receiverClass.getName());
        }
        return ctorInvocation;
    }
}
