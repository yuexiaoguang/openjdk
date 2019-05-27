package com.sun.beans.editors;

/**
 * Property editor for a java builtin "byte" type.
 */
import java.beans.*;

public class ByteEditor extends NumberEditor {

    public String getJavaInitializationString() {
        Object value = getValue();
        return (value != null)
                ? "((byte)" + value + ")"
                : "null";
    }

    public void setAsText(String text) throws IllegalArgumentException {
        setValue((text == null) ? null : Byte.decode(text));
    }

}
