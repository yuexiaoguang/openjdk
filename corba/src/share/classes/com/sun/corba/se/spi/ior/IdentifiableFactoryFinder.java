package com.sun.corba.se.spi.ior;

import org.omg.CORBA_2_3.portable.InputStream ;

/** Interface used to manage a group of related IdentifiableFactory instances.
 * Factories can be registered, and invoked through a create method, which
 * must be implemented to handle the case of no registered factory
 * appropriately.
 */
public interface IdentifiableFactoryFinder
{
    /** If there is a registered factory for id, use it to
     * read an Identifiable from is.  Otherwise create an
     * appropriate generic container, or throw an error.
     * The type of generic container, or error behavior is
     * a property of the implementation.
     */
    Identifiable create(int id, InputStream is);

    /** Register a factory for the given id.
     */
    void registerFactory( IdentifiableFactory factory ) ;
}
