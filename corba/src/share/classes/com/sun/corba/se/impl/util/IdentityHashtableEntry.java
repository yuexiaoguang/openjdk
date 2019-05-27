package com.sun.corba.se.impl.util;

/**
 * IdentityHashtable collision list.
 */
class IdentityHashtableEntry {
    int hash;
    Object key;
    Object value;
    IdentityHashtableEntry next;
}
