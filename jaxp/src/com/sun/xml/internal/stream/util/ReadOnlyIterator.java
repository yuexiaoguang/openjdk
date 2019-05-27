package com.sun.xml.internal.stream.util;

import java.util.Iterator;

public class ReadOnlyIterator implements Iterator {

    Iterator iterator = null;

    public ReadOnlyIterator(){
    }

    public ReadOnlyIterator(Iterator itr){
        iterator = itr;
    }

    /**
     * @return
     */
    public boolean hasNext() {
        if(iterator  != null)
            return iterator.hasNext();
        return false;
    }

    /**
     * @return
     */
    public Object next() {
        if(iterator  != null)
            return iterator.next();
        return null;
    }

    public void remove() {
        throw new  UnsupportedOperationException("Remove operation is not supported");
    }

}
