package com.sun.xml.internal.ws.spi.db;

/**
 * PropertySetter
 */
public interface PropertySetter {

    public Class getType();

    public <A> A getAnnotation(Class<A> annotationType);

    public void set(Object instance, Object resource);
}
