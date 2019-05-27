package sun.jvm.hotspot.debugger.posix.elf;

public interface ELFStringTable {
    public String get(int index);
    public int getNumStrings();
}
