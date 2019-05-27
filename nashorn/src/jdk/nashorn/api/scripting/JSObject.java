package jdk.nashorn.api.scripting;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * This interface can be implemented by an arbitrary Java class. Nashorn will
 * treat objects of such classes just like nashorn script objects. Usual nashorn
 * operations like obj[i], obj.foo, obj.func(), delete obj.foo will be glued
 * to appropriate method call of this interface.
 */
public interface JSObject {
    /**
     * Call this object as a JavaScript function. This is equivalent to
     * 'func.apply(thiz, args)' in JavaScript.
     *
     * @param thiz 'this' object to be passed to the function
     * @param args arguments to method
     * @return result of call
     */
    public Object call(final Object thiz, final Object... args);

    /**
     * Call this 'constructor' JavaScript function to create a new object.
     * This is equivalent to 'new func(arg1, arg2...)' in JavaScript.
     *
     * @param args arguments to method
     * @return result of constructor call
     */
    public Object newObject(final Object... args);

    /**
     * Evaluate a JavaScript expression.
     *
     * @param s JavaScript expression to evaluate
     * @return evaluation result
     */
    public Object eval(final String s);

    /**
     * Retrieves a named member of this JavaScript object.
     *
     * @param name of member
     * @return member
     */
    public Object getMember(final String name);

    /**
     * Retrieves an indexed member of this JavaScript object.
     *
     * @param index index slot to retrieve
     * @return member
     */
    public Object getSlot(final int index);

    /**
     * Does this object have a named member?
     *
     * @param name name of member
     * @return true if this object has a member of the given name
     */
    public boolean hasMember(final String name);

    /**
     * Does this object have a indexed property?
     *
     * @param slot index to check
     * @return true if this object has a slot
     */
    public boolean hasSlot(final int slot);

    /**
     * Remove a named member from this JavaScript object
     *
     * @param name name of the member
     */
    public void removeMember(final String name);

    /**
     * Set a named member in this JavaScript object
     *
     * @param name  name of the member
     * @param value value of the member
     */
    public void setMember(final String name, final Object value);

    /**
     * Set an indexed member in this JavaScript object
     *
     * @param index index of the member slot
     * @param value value of the member
     */
    public void setSlot(final int index, final Object value);

    // property and value iteration

    /**
     * Returns the set of all property names of this object.
     *
     * @return set of property names
     */
    public Set<String> keySet();

    /**
     * Returns the set of all property values of this object.
     *
     * @return set of property values.
     */
    public Collection<Object> values();

    // JavaScript instanceof check

    /**
     * Checking whether the given object is an instance of 'this' object.
     *
     * @param instance instace to check
     * @return true if the given 'instance' is an instance of this 'function' object
     */
    public boolean isInstance(final Object instance);

    /**
     * Checking whether this object is an instance of the given 'clazz' object.
     *
     * @param clazz clazz to check
     * @return true if this object is an instance of the given 'clazz'
     */
    public boolean isInstanceOf(final Object clazz);

    /**
     * ECMA [[Class]] property
     *
     * @return ECMA [[Class]] property value of this object
     */
    public String getClassName();

    /**
     * Is this a function object?
     *
     * @return if this mirror wraps a ECMAScript function instance
     */
    public boolean isFunction();

    /**
     * Is this a 'use strict' function object?
     *
     * @return true if this mirror represents a ECMAScript 'use strict' function
     */
    public boolean isStrictFunction();

    /**
     * Is this an array object?
     *
     * @return if this mirror wraps a ECMAScript array object
     */
    public boolean isArray();

    /**
     * Returns this object's numeric value.
     *
     * @return this object's numeric value.
     */
    public double toNumber();
}
