package com.sun.xml.internal.ws.spi.db;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;


/**
 * FieldSetter
 */
public class FieldSetter extends PropertySetterBase {

    protected Field field;

    public FieldSetter(Field f) {
        field = f;
        type = f.getType();
    }

    public Field getField() {
        return field;
    }

    public void set(final Object instance, final Object resource) {
        if (field.isAccessible()) {
            try {
                field.set(instance, resource);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                    public Object run() throws IllegalAccessException {
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        field.set(instance, resource);
                        return null;
                    }
                });
            } catch (PrivilegedActionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public <A> A getAnnotation(Class<A> annotationType) {
        Class c = annotationType;
        return (A) field.getAnnotation(c);
    }
}
