package com.sun.tools.javap;

import java.util.HashMap;
import java.util.Map;

/*
 *  Class from which to put/get shared resources.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class Context {
    public Context() {
       map = new HashMap<Class<?>, Object>();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> key) {
        return (T) map.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T put(Class<T> key, T value) {
        return (T) map.put(key, value);
    }

    Map<Class<?>, Object> map;
}
