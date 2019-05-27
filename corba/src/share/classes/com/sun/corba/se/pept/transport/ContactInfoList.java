package com.sun.corba.se.pept.transport;

import java.util.Iterator;

/**
 * <p> <code>ContactInfoList</code> contains one or more
 * {@link com.sun.corba.se.pept.transport.ContactInfo ContactInfo}.
 */
public interface ContactInfoList
{
    /**
     * Used to get a
     * {@link com.sun.corba.se.pept.transport.ContactInfoListIterator
     * ContactInfoListIterator} to retrieve individual
     * {@link com.sun.corba.se.pept.transport.ContactInfo ContactInfo}
     * from the list.
     *
     * @return A
     * {@link com.sun.corba.se.pept.transport.ContactInfoListIterator
     * ContactInfoListIterator}.
     */
    public Iterator iterator();
}
