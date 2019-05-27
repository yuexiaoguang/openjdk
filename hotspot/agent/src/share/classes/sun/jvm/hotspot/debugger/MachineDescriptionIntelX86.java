package sun.jvm.hotspot.debugger;

public class MachineDescriptionIntelX86 extends MachineDescriptionTwosComplement implements MachineDescription {
  public long getAddressSize() {
    return 4;
  }

  public boolean isBigEndian() {
    return false;
  }
}
