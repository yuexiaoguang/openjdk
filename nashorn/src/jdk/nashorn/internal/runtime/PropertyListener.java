package jdk.nashorn.internal.runtime;

/**
 * Property change listener gets notified whenever properties are added/deleted/modified.
 */
public interface PropertyListener {
    /**
     * A new property is being added.
     *
     * @param object The ScriptObject to which property was added.
     * @param prop The new Property added.
     */
    public void propertyAdded(ScriptObject object, Property prop);

    /**
     * An existing property is being deleted.
     *
     * @param object The ScriptObject whose property is being deleted.
     * @param prop The property being deleted.
     */
    public void propertyDeleted(ScriptObject object, Property prop);

    /**
     * An existing Property is being replaced with a new Property.
     *
     * @param object The ScriptObject whose property is being modified.
     * @param oldProp The old property that is being replaced.
     * @param newProp The new property that replaces the old property.
     *
     */
    public void propertyModified(ScriptObject object, Property oldProp, Property newProp);

    /**
     * Given object's __proto__ has changed.
     *
     * @param object object whose __proto__ has changed.
     * @param oldProto old __proto__
     * @param newProto new __proto__
     */
    public void protoChanged(ScriptObject object, ScriptObject oldProto, ScriptObject newProto);
}
