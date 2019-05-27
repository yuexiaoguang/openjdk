package sun.corba;

import com.sun.corba.se.impl.io.ValueHandlerImpl;

public interface JavaCorbaAccess {
    public ValueHandlerImpl newValueHandlerImpl();
    public Class<?> loadClass(String className) throws ClassNotFoundException;
}
