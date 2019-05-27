package com.sun.hotspot.igv.filter;

public interface FilterChainProvider {

    public FilterChain getFilterChain();

    public FilterChain getSequence();
}
