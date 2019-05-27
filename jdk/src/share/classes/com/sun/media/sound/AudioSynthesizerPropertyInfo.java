package com.sun.media.sound;

/**
 * Information about property used in  opening <code>AudioSynthesizer</code>.
 */
public final class AudioSynthesizerPropertyInfo {

    /**
     * Constructs a <code>AudioSynthesizerPropertyInfo</code> object with a given
     * name and value. The <code>description</code> and <code>choices</code>
     * are initialized by <code>null</code> values.
     *
     * @param name the name of the property
     * @param value the current value or class used for values.
     *
     */
    public AudioSynthesizerPropertyInfo(String name, Object value) {
        this.name = name;
        if (value instanceof Class)
            valueClass = (Class)value;
        else
        {
            this.value = value;
            if (value != null)
                valueClass = value.getClass();
        }
    }
    /**
     * The name of the property.
     */
    public String name;
    /**
     * A brief description of the property, which may be null.
     */
    public String description = null;
    /**
     * The <code>value</code> field specifies the current value of
     * the property.
     */
    public Object value = null;
    /**
     * The <code>valueClass</code> field specifies class
     * used in <code>value</code> field.
     */
    public Class valueClass = null;
    /**
     * An array of possible values if the value for the field
     * <code>AudioSynthesizerPropertyInfo.value</code> may be selected
     * from a particular set of values; otherwise null.
     */
    public Object[] choices = null;

}
