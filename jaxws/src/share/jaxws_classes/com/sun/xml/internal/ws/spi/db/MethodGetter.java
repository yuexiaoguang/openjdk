package com.sun.xml.internal.ws.spi.db;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;


/**
 * MethodGetter
 */
public class MethodGetter extends PropertyGetterBase {

    private Method method;

    public MethodGetter(Method m) {
        method = m;
        type = m.getReturnType();
    }

    public Method getMethod() {
        return method;
    }

    public <A> A getAnnotation(Class<A> annotationType) {
        Class c = annotationType;
        return (A) method.getAnnotation(c);
    }


    static class PrivilegedGetter implements PrivilegedExceptionAction {
        private Object value;
        private Method method;
        private Object instance;
        public PrivilegedGetter(Method m, Object instance) {
            super();
            this.method = m;
            this.instance = instance;
        }
        public Object run() throws IllegalAccessException {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            try {
                value = method.invoke(instance, new Object[0]);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }

    public Object get(final Object instance) {
        final Object[] args = new Object[0];
        try {
            if (method.isAccessible()) {
                return method.invoke(instance, args);
            } else {
                PrivilegedGetter privilegedGetter = new PrivilegedGetter(method, instance);
                try {
                    AccessController.doPrivileged(privilegedGetter);
                } catch (PrivilegedActionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return privilegedGetter.value;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
