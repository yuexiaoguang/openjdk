package com.sun.tools.hat.internal.model;

/**
 * Represents the value of a static field of a JavaClass
 */
public class JavaStatic {

    private JavaField field;
    private JavaThing value;

    public JavaStatic(JavaField field, JavaThing value) {
        this.field = field;
        this.value = value;
    }

    public void resolve(JavaClass clazz, Snapshot snapshot) {
        long id = -1;
        if (value instanceof JavaObjectRef) {
            id = ((JavaObjectRef)value).getId();
        }
        value = value.dereference(snapshot, field);
        if (value.isHeapAllocated() &&
            clazz.getLoader() == snapshot.getNullThing()) {
            // static fields are only roots if they are in classes
            //    loaded by the root classloader.
            JavaHeapObject ho = (JavaHeapObject) value;
            String s = "Static reference from " + clazz.getName()
                       + "." + field.getName();
            snapshot.addRoot(new Root(id, clazz.getId(),
                                      Root.JAVA_STATIC, s));
        }
    }

    public JavaField getField() {
        return field;
    }

    public JavaThing getValue() {
        return value;
    }
}
