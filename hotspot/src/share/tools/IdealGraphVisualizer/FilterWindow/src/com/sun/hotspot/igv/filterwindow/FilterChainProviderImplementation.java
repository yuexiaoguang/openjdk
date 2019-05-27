package com.sun.hotspot.igv.filterwindow;

import com.sun.hotspot.igv.filter.FilterChain;
import com.sun.hotspot.igv.filter.FilterChainProvider;

public class FilterChainProviderImplementation implements FilterChainProvider {

    public FilterChain getFilterChain() {
        return FilterTopComponent.findInstance().getFilterChain();
    }

    public FilterChain getSequence() {
        return FilterTopComponent.findInstance().getSequence();
    }
}
