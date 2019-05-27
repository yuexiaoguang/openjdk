package jdk.nashorn.internal.runtime.linker;

import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.internal.dynalink.support.CallSiteDescriptorFactory;
import jdk.nashorn.internal.runtime.Context;

/**
 * Check java reflection permission for java reflective and java.lang.invoke access from scripts
 */
final class ReflectionCheckLinker implements TypeBasedGuardingDynamicLinker{
    @Override
    public boolean canLinkType(final Class<?> type) {
        return isReflectionClass(type);
    }

    private static boolean isReflectionClass(final Class<?> type) {
        if (type == Class.class || ClassLoader.class.isAssignableFrom(type)) {
            return true;
        }

        final String name = type.getName();
        return name.startsWith("java.lang.reflect.") || name.startsWith("java.lang.invoke.");
    }

    @Override
    public GuardedInvocation getGuardedInvocation(final LinkRequest origRequest, final LinkerServices linkerServices)
            throws Exception {
        checkLinkRequest(origRequest);
        // let the next linker deal with actual linking
        return null;
    }

    private static boolean isReflectiveCheckNeeded(final Class<?> type, final boolean isStatic) {
         // special handling for Proxy subclasses
         if (Proxy.class.isAssignableFrom(type)) {
            if (Proxy.isProxyClass(type)) {
                // real Proxy class - filter only static access
                return isStatic;
            }

            // fake Proxy subclass - filter it always!
            return true;
        }

        // check for any other reflective Class
        return isReflectionClass(type);
    }

    static void checkReflectionAccess(final Class<?> clazz, final boolean isStatic) {
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null && isReflectiveCheckNeeded(clazz, isStatic)) {
            checkReflectionPermission(sm);
        }
    }

    private static void checkLinkRequest(final LinkRequest origRequest) {
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            final LinkRequest requestWithoutContext = origRequest.withoutRuntimeContext(); // Nashorn has no runtime context
            final Object self = requestWithoutContext.getReceiver();
            // allow 'static' access on Class objects representing public classes of non-restricted packages
            if ((self instanceof Class) && Modifier.isPublic(((Class<?>)self).getModifiers())) {
                final CallSiteDescriptor desc = requestWithoutContext.getCallSiteDescriptor();
                if(CallSiteDescriptorFactory.tokenizeOperators(desc).contains("getProp")) {
                    if ("static".equals(desc.getNameToken(CallSiteDescriptor.NAME_OPERAND))) {
                        if (Context.isAccessibleClass((Class<?>)self) && !isReflectionClass((Class<?>)self)) {

                            // If "getProp:static" passes access checks, allow access.
                            return;
                        }
                    }
                }
            }
            checkReflectionPermission(sm);
        }
    }

    private static void checkReflectionPermission(final SecurityManager sm) {
        sm.checkPermission(new RuntimePermission(Context.NASHORN_JAVA_REFLECTION));
    }
}
