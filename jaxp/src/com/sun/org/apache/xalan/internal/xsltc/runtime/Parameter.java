/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.runtime;

public class Parameter {

    public String  _name;
    public Object  _value;
    public boolean _isDefault;

    public Parameter(String name, Object value) {
        _name = name;
        _value = value;
        _isDefault = true;
    }

    public Parameter(String name, Object value, boolean isDefault) {
        _name = name;
        _value = value;
        _isDefault = isDefault;
    }
}
