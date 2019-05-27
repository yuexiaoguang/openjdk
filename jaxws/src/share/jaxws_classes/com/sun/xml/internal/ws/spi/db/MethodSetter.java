package com.sun.xml.internal.ws.spi.db;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;


/**
 * MethodInjection
 */
public class MethodSetter extends PropertySetterBase {

    private Method method;

    public MethodSetter(Method m) {
        method = m;
        type = m.getParameterTypes()[0];
    }

    public Method getMethod() {
        return method;
    }

    public <A> A getAnnotation(Class<A> annotationType) {
        Class c = annotationType;
        return (A) method.getAnnotation(c);
    }

    public void set(final Object instance, Object resource) {
        final Object[] args = {resource};
        if (method.isAccessible()) {
            try {
                method.invoke(instance, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                    public Object run() throws IllegalAccessException {
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        try {
                            method.invoke(instance, args);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
            } catch (PrivilegedActionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
