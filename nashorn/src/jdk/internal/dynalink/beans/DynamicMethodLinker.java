package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.internal.dynalink.support.CallSiteDescriptorFactory;
import jdk.internal.dynalink.support.Guards;

/**
 * Simple linker that implements the "dyn:call" operation for {@link DynamicMethod} objects - the objects returned by
 * "dyn:getMethod" from {@link AbstractJavaLinker}.
 */
class DynamicMethodLinker implements TypeBasedGuardingDynamicLinker {
    @Override
    public boolean canLinkType(Class<?> type) {
        return DynamicMethod.class.isAssignableFrom(type);
    }

    @Override
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) {
        final Object receiver = linkRequest.getReceiver();
        if(!(receiver instanceof DynamicMethod)) {
            return null;
        }
        final CallSiteDescriptor desc = linkRequest.getCallSiteDescriptor();
        if(desc.getNameTokenCount() != 2 && desc.getNameToken(CallSiteDescriptor.SCHEME) != "dyn") {
            return null;
        }
        final String operator = desc.getNameToken(CallSiteDescriptor.OPERATOR);
        if(operator == "call") {
            final MethodHandle invocation = ((DynamicMethod)receiver).getInvocation(
                    CallSiteDescriptorFactory.dropParameterTypes(desc, 0, 1), linkerServices);
            if(invocation == null) {
                return null;
            }
            return new GuardedInvocation(MethodHandles.dropArguments(invocation, 0,
                    desc.getMethodType().parameterType(0)), Guards.getIdentityGuard(receiver));
        }
        return null;
    }
}
