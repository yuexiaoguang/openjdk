package com.sun.xml.internal.ws.spi.db;

/**
 * This is the Setter of a bean property.
 */
public abstract class PropertySetterBase implements PropertySetter {
    protected Class type;

    public Class getType() {
        return type;
    }

    static public boolean setterPattern(java.lang.reflect.Method method) {
        return (method.getName().startsWith("set") &&
                method.getName().length() > 3 &&
                method.getReturnType().equals(void.class) &&
                method.getParameterTypes() != null &&
                method.getParameterTypes().length == 1);
    }
}
