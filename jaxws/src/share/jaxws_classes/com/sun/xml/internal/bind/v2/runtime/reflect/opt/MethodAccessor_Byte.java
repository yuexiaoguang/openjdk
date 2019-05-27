package com.sun.xml.internal.bind.v2.runtime.reflect.opt;

import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;

/**
 * Template {@link Accessor} for boolean getter/setter.
 *
 * <p>
 * All the MethodAccessors are generated from <code>MethodAccessor_B y t e</code>
 */
public class MethodAccessor_Byte extends Accessor {
    public MethodAccessor_Byte() {
        super(Byte.class);
    }

    public Object get(Object bean) {
        return ((Bean)bean).get_byte();
    }

    public void set(Object bean, Object value) {
        ((Bean)bean).set_byte( value==null ? Const.default_value_byte : (Byte)value );
    }
}
