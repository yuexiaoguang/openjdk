package sun.jvm.hotspot.memory;

import java.io.*;
import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.utilities.*;

public class SymbolTable extends sun.jvm.hotspot.utilities.Hashtable {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("SymbolTable");
    theTableField  = type.getAddressField("_the_table");
  }

  // Fields
  private static AddressField theTableField;

  // Accessors
  public static SymbolTable getTheTable() {
    Address tmp = theTableField.getValue();
    return (SymbolTable) VMObjectFactory.newObject(SymbolTable.class, tmp);
  }

  public SymbolTable(Address addr) {
    super(addr);
  }

  /** Clone of VM's "temporary" probe routine, as the SA currently
      does not support mutation so lookup() would have no effect
      anyway. Returns null if the given string is not in the symbol
      table. */
  public Symbol probe(String name) {
    try {
      return probe(toModifiedUTF8Bytes(name));
    } catch (IOException e) {
      return null;
    }
  }

  /** Clone of VM's "temporary" probe routine, as the SA currently
      does not support mutation so lookup() would have no effect
      anyway. Returns null if the given string is not in the symbol
      table. */
  public Symbol probe(byte[] name) {
    long hashValue = hashSymbol(name);
    for (HashtableEntry e = (HashtableEntry) bucket(hashToIndex(hashValue)); e != null; e = (HashtableEntry) e.next()) {
      if (e.hash() == hashValue) {
         Symbol sym = Symbol.create(e.literalValue());
         if (sym.equals(name)) {
           return sym;
         }
      }
    }
    return null;
  }

  public interface SymbolVisitor {
    public void visit(Symbol sym);
  }

  public void symbolsDo(SymbolVisitor visitor) {
    int numBuckets = tableSize();
    for (int i = 0; i < numBuckets; i++) {
      for (HashtableEntry e = (HashtableEntry) bucket(i); e != null;
           e = (HashtableEntry) e.next()) {
        visitor.visit(Symbol.create(e.literalValue()));
      }
    }
  }

  private static byte[] toModifiedUTF8Bytes(String name) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(name);
    dos.flush();
    byte[] buf = baos.toByteArray();
    byte[] res = new byte[buf.length - 2];
    // skip the length part
    System.arraycopy(buf, 2, res, 0, res.length);
    return res;
  }
}
