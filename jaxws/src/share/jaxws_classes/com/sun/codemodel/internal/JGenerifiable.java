package com.sun.codemodel.internal;

/**
 * Declarations that can have type variables.
 *
 * Something that can be made into a generic.
 */
public interface JGenerifiable {
    /**
     * Adds a new type variable to this declaration.
     */
    JTypeVar generify( String name );

    /**
     * Adds a new type variable to this declaration with a bound.
     */
    JTypeVar generify( String name, Class<?> bound );

    /**
     * Adds a new type variable to this declaration with a bound.
     */
    JTypeVar generify( String name, JClass bound );

    /**
     * Iterates all the type parameters of this class/interface.
     */
    JTypeVar[] typeParams();
}
