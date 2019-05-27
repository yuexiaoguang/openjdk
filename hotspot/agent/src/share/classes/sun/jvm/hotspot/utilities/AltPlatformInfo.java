package sun.jvm.hotspot.utilities;

public interface AltPlatformInfo {
  // Additional cpu types can be tested via this interface

  public boolean knownCPU(String cpu);
}