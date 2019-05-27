package com.sun.xml.internal.ws.api.databinding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * MetadataReader
 */
public interface MetadataReader {

    public Annotation[] getAnnotations(Method m) ;

    public Annotation[][] getParameterAnnotations(final Method method);

    public <A extends Annotation> A getAnnotation(final Class<A> annType, final Method m);

    public <A extends Annotation> A getAnnotation(final Class<A> annType, final Class<?> cls);

    public Annotation[] getAnnotations(Class<?> c);

    public void getProperties(final Map<String, Object> prop, final Class<?> cls);

    public void getProperties(final Map<String, Object> prop, final Method method);

    public void getProperties(final Map<String, Object> prop, final Method method, int pos);

}
