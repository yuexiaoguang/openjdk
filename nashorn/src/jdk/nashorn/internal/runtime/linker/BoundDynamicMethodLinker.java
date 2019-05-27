package jdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.beans.BeansLinker;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.linker.LinkRequest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.internal.dynalink.support.Guards;

/**
 * Links {@code BoundDynamicMethod} objects. Passes through to Dynalink's BeansLinker for linking a dynamic method
 * (they only respond to "dyn:call"), and modifies the returned invocation to deal with the receiver binding.
 */
final class BoundDynamicMethodLinker implements TypeBasedGuardingDynamicLinker {
    @Override
    public boolean canLinkType(Class<?> type) {
        return type == BoundDynamicMethod.class;
    }

    @Override
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
        final Object objBoundDynamicMethod = linkRequest.getReceiver();
        if(!(objBoundDynamicMethod instanceof BoundDynamicMethod)) {
            return null;
        }

        final BoundDynamicMethod boundDynamicMethod = (BoundDynamicMethod)objBoundDynamicMethod;
        final Object dynamicMethod = boundDynamicMethod.getDynamicMethod();
        final Object boundThis = boundDynamicMethod.getBoundThis();

        // Replace arguments (boundDynamicMethod, this, ...) => (dynamicMethod, boundThis, ...) when delegating to
        // BeansLinker
        final Object[] args = linkRequest.getArguments();
        args[0] = dynamicMethod;
        args[1] = boundThis;

        // Use R(T0, T1, ...) => R(dynamicMethod.class, boundThis.class, ...) call site type when delegating to
        // BeansLinker.
        final CallSiteDescriptor descriptor = linkRequest.getCallSiteDescriptor();
        final MethodType type = descriptor.getMethodType();
        final Class<?> dynamicMethodClass = dynamicMethod.getClass();
        final CallSiteDescriptor newDescriptor = descriptor.changeMethodType(
                type.changeParameterType(0, dynamicMethodClass).changeParameterType(1, boundThis.getClass()));

        // Delegate to BeansLinker
        final GuardedInvocation inv = NashornBeansLinker.getGuardedInvocation(BeansLinker.getLinkerForClass(dynamicMethodClass),
                linkRequest.replaceArguments(newDescriptor, args), linkerServices);
        if(inv == null) {
            return null;
        }

        // Bind (dynamicMethod, boundThis) to the handle
        final MethodHandle boundHandle = MethodHandles.insertArguments(inv.getInvocation(), 0, dynamicMethod, boundThis);
        final Class<?> p0Type = type.parameterType(0);
        // Ignore incoming (boundDynamicMethod, this)
        final MethodHandle droppingHandle = MethodHandles.dropArguments(boundHandle, 0, p0Type, type.parameterType(1));
        // Identity guard on boundDynamicMethod object
        final MethodHandle newGuard = Guards.getIdentityGuard(boundDynamicMethod);

        return inv.replaceMethods(droppingHandle, newGuard.asType(newGuard.type().changeParameterType(0, p0Type)));
    }
}
