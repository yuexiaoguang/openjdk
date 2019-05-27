package com.sun.hotspot.igv.data;

public interface ChangedEventProvider<T> {

    public ChangedEvent<T> getChangedEvent();
}
