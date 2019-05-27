package com.sun.xml.internal.fastinfoset.stax.events;

import java.util.Iterator;
import com.sun.xml.internal.fastinfoset.CommonResourceBundle;
import java.util.NoSuchElementException;


public class EmptyIterator implements Iterator {
    public static final EmptyIterator instance = new EmptyIterator();
    /** Creates a new instance of EmptyIterator */
    private EmptyIterator() {
    }
    public static EmptyIterator getInstance() {
        return instance;
    }
    public boolean hasNext() {
        return false;
    }
    public Object next() throws NoSuchElementException {
        throw new NoSuchElementException();
    }
    public void remove() {
         throw new  UnsupportedOperationException(CommonResourceBundle.getInstance().getString("message.emptyIterator"));
    }
}
