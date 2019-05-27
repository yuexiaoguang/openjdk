package sun.jvm.hotspot.memory;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.runtime.*;

public class LinearAllocBlock extends VMObject {
   static {
      VM.registerVMInitializedObserver(new Observer() {
         public void update(Observable o, Object data) {
            initialize(VM.getVM().getTypeDataBase());
         }
      });
   }

   private static synchronized void initialize(TypeDataBase db) {
      Type type = db.lookupType("LinearAllocBlock");
      word_sizeField= type.getCIntegerField("_word_size");
   }

   // Fields
   private static CIntegerField word_sizeField;

   // Accessors
   public long word_size() {
      return word_sizeField.getValue(addr);
   }

   // Constructor
   public LinearAllocBlock(Address addr) {
      super(addr);
   }
}
