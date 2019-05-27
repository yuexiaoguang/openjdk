package com.sun.org.glassfish.external.probe.provider;

public interface StatsProviderManagerDelegate {

   public void register(StatsProviderInfo spInfo);
   public void unregister(Object statsProvider);
   public boolean hasListeners(String probeStr);

}
