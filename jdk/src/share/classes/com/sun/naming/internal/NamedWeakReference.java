package com.sun.naming.internal;


/**
 * A NamedWeakReference is a WeakReference with an immutable string name.
 */
class NamedWeakReference<T> extends java.lang.ref.WeakReference<T> {

    private final String name;

    NamedWeakReference(T referent, String name) {
        super(referent);
        this.name = name;
    }

    String getName() {
        return name;
    }
}
