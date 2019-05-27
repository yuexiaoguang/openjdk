package sun.jvm.hotspot.oops;

import java.util.*;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.memory.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.types.AddressField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;
import sun.jvm.hotspot.utilities.*;
import sun.jvm.hotspot.jdi.JVMTIThreadState;

/** A utility class encapsulating useful oop operations */

// initialize fields for java.lang.Class
public class java_lang_Class {

  // java.lang.Class fields
  static int klassOffset;
  static IntField oopSizeField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    // klass and oop_size are HotSpot magic fields and hence we can't
    // find them from InstanceKlass for java.lang.Class.
    Type jlc = db.lookupType("java_lang_Class");
    klassOffset = (int) jlc.getCIntegerField("_klass_offset").getValue();
    int oopSizeOffset = (int) jlc.getCIntegerField("_oop_size_offset").getValue();
    oopSizeField = new IntField(new NamedFieldIdentifier("oop_size"), oopSizeOffset, true);
  }

  /** get Klass* field at offset hc_klass_offset from a java.lang.Class object */
  public static Klass asKlass(Oop aClass) {
    return (Klass)Metadata.instantiateWrapperFor(aClass.getHandle().getAddressAt(klassOffset));
  }

  /** get oop_size field at offset oop_size_offset from a java.lang.Class object */
  public static long getOopSize(Oop aClass) {
    return java_lang_Class.oopSizeField.getValue(aClass);
  }
}
