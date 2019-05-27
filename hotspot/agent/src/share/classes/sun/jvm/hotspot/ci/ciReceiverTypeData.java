package sun.jvm.hotspot.ci;

import java.io.*;
import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.types.*;

public class ciReceiverTypeData extends ReceiverTypeData {
  public ciReceiverTypeData(DataLayout data) {
    super(data);
  }

  public Klass receiver(int row) {
      throw new InternalError("should not call");
  }

  public ciKlass receiverAt(int row) {
    //assert((uint)row < rowLimit(), "oob");
    ciMetadata recv = ciObjectFactory.getMetadata(addressAt(receiverCellIndex(row)));
    if (recv != null && !(recv instanceof ciKlass)) {
      System.err.println(recv);
    }
    //assert(recv == NULL || recv->isKlass(), "wrong type");
    return (ciKlass)recv;
  }

}
