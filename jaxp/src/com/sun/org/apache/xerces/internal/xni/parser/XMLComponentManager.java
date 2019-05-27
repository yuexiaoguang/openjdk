package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.util.FeatureState;
import com.sun.org.apache.xerces.internal.util.PropertyState;

/**
 * The component manager manages a parser configuration and the components
 * that make up that configuration. The manager notifies each component
 * before parsing to allow the components to initialize their state; and
 * also any time that a parser feature or property changes.
 * <p>
 * The methods of the component manager allow components to query features
 * and properties that affect the operation of the component.
 */
public interface XMLComponentManager {

    //
    // XMLComponentManager methods
    //

    /**
     * Returns the state of a feature.
     *
     * @param featureId The feature identifier.
     *
     * @throws XMLConfigurationException Thrown on configuration error.
     */
    public boolean getFeature(String featureId)
        throws XMLConfigurationException;

    /**
     * Returns the state of a feature.
     * Does not throw exceptions.
     *
     * @param featureId The feature identifier.
     * @param defaultValue Default value if future is not available.
     */
    public boolean getFeature(String featureId, boolean defaultValue);

    /**
     * Returns the value of a property.
     *
     * @param propertyId The property identifier.
     *
    * @throws XMLConfigurationException Thrown on configuration error.
     */
    public Object getProperty(String propertyId)
        throws XMLConfigurationException;

    /**
     * Returns the value of a property.
     * Does not throw exceptions.
     *
     * @param propertyId The property identifier.
     * @param defaultObject Return value if property is not available.
     *
     */
    public Object getProperty(String propertyId, Object defaultObject);

    public FeatureState getFeatureState(String featureId);

    public PropertyState getPropertyState(String propertyId);

} // interface XMLComponentManager
