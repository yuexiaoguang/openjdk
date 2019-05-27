package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import jdk.internal.dynalink.support.Lookup;

/**
 * A dynamic method bound to exactly one Java method or constructor that is caller sensitive. Since the target method is
 * caller sensitive, it doesn't cache a method handle but rather uses the passed lookup object in
 * {@link #getTarget(java.lang.invoke.MethodHandles.Lookup)} to unreflect a method handle from the reflective member on
 * every request.
 */
class CallerSensitiveDynamicMethod extends SingleDynamicMethod {
    // Typed as "AccessibleObject" as it can be either a method or a constructor.
    // If we were Java8-only, we could use java.lang.reflect.Executable
    private final AccessibleObject target;
    private final MethodType type;

    public CallerSensitiveDynamicMethod(AccessibleObject target) {
        super(getName(target));
        this.target = target;
        this.type = getMethodType(target);
    }

    private static String getName(AccessibleObject target) {
        final Member m = (Member)target;
        return getMethodNameWithSignature(getMethodType(target), getClassAndMethodName(m.getDeclaringClass(),
                m.getName()));
    }

    @Override
    MethodType getMethodType() {
        return type;
    }

    private static MethodType getMethodType(AccessibleObject ao) {
        final boolean isMethod = ao instanceof Method;
        final Class<?> rtype = isMethod ? ((Method)ao).getReturnType() : ((Constructor<?>)ao).getDeclaringClass();
        final Class<?>[] ptypes = isMethod ? ((Method)ao).getParameterTypes() : ((Constructor<?>)ao).getParameterTypes();
        final MethodType type = MethodType.methodType(rtype, ptypes);
        final Member m = (Member)ao;
        return type.insertParameterTypes(0,
                isMethod ?
                        Modifier.isStatic(m.getModifiers()) ?
                                Object.class :
                                m.getDeclaringClass() :
                        StaticClass.class);
    }

    @Override
    boolean isVarArgs() {
        return target instanceof Method ? ((Method)target).isVarArgs() : ((Constructor<?>)target).isVarArgs();
    }

    @Override
    MethodHandle getTarget(MethodHandles.Lookup lookup) {
        if(target instanceof Method) {
            final MethodHandle mh = Lookup.unreflect(lookup, (Method)target);
            if(Modifier.isStatic(((Member)target).getModifiers())) {
                return StaticClassIntrospector.editStaticMethodHandle(mh);
            }
            return mh;
        }
        return StaticClassIntrospector.editConstructorMethodHandle(Lookup.unreflectConstructor(lookup,
                (Constructor<?>)target));
    }
}
