package jdk.nashorn.internal.runtime.regexp.joni.encoding;

public final class ObjPtr<T> {
    public ObjPtr() {
        this(null);
    }

    public ObjPtr(T p) {
        this.p = p;
    }

    public T p;

}

