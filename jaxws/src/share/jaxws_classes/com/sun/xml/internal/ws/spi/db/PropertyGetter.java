package com.sun.xml.internal.ws.spi.db;

/**
 * PropertyGetter
 */
public interface PropertyGetter {

    public Class getType();

    public <A> A getAnnotation(Class<A> annotationType);

    public Object get(Object instance);
}
