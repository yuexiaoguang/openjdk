package com.sun.tools.internal.xjc.generator.bean.field;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.sun.tools.internal.xjc.generator.bean.ClassOutlineImpl;
import com.sun.tools.internal.xjc.model.CPropertyInfo;
import com.sun.tools.internal.xjc.outline.FieldOutline;

/**
 * Creates
 */
public final class GenericFieldRenderer implements FieldRenderer {
    private Constructor constructor;

    public GenericFieldRenderer( Class fieldClass ) {
        try {
            constructor = fieldClass.getDeclaredConstructor(new Class[]{ClassOutlineImpl.class,CPropertyInfo.class});
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    public FieldOutline generate(ClassOutlineImpl context, CPropertyInfo prop) {
        try {
            return (FieldOutline)constructor.newInstance(new Object[]{context,prop});
        } catch (InstantiationException e) {
            throw new InstantiationError(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if(t instanceof RuntimeException)
                throw (RuntimeException)t;
            if(t instanceof Error)
                throw (Error)t;

            // impossible
            throw new AssertionError(t);
        }
    }

}
