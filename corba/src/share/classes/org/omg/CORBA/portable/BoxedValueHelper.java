package org.omg.CORBA.portable;
import java.io.Serializable;

public interface BoxedValueHelper {
    Serializable read_value(InputStream is);
    void write_value(OutputStream os, Serializable value);
    String get_id();
}
