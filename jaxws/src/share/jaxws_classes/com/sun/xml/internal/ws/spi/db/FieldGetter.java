package com.sun.xml.internal.ws.spi.db;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;


/**
 * FieldGetter gets the value of a field from an instance.
 */
public class FieldGetter extends PropertyGetterBase {

    protected Field field;

    public FieldGetter(Field f) {
        field = f;
        type = f.getType();
    }

    public Field getField() {
        return field;
    }

    static class PrivilegedGetter implements PrivilegedExceptionAction {
        private Object value;
        private Field  field;
        private Object instance;
        public PrivilegedGetter(Field field, Object instance) {
            super();
            this.field = field;
            this.instance = instance;
        }
        public Object run() throws IllegalAccessException {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            value = field.get(instance);
            return null;
        }
    }

    public Object get(final Object instance) {
        if (field.isAccessible()) {
            try {
                return field.get(instance);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            PrivilegedGetter privilegedGetter = new PrivilegedGetter(field, instance);
            try {
                AccessController.doPrivileged(privilegedGetter);
            } catch (PrivilegedActionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return privilegedGetter.value;
        }
        return null;
    }

    public <A> A getAnnotation(Class<A> annotationType) {
        Class c = annotationType;
        return (A) field.getAnnotation(c);
    }
}
