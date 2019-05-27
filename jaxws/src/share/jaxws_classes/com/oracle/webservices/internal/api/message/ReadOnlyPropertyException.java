package com.oracle.webservices.internal.api.message;

/**
 * Used to indicate that {@link PropertySet#put(String, Object)} failed
 * because a property is read-only.
 */
public class ReadOnlyPropertyException extends IllegalArgumentException {
    private final String propertyName;

    public ReadOnlyPropertyException(String propertyName) {
        super(propertyName+" is a read-only property.");
        this.propertyName = propertyName;
    }

    /**
     * Gets the name of the property that was read-only.
     */
    public String getPropertyName() {
        return propertyName;
    }
}
