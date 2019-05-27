package sun.jvm.hotspot.debugger;

public class MachineDescriptionAMD64 extends MachineDescriptionTwosComplement implements MachineDescription {
  public long getAddressSize() {
    return 8;
  }

  public boolean isLP64() {
    return true;
  }

  public boolean isBigEndian() {
    return false;
  }
}
