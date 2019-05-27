package sun.jvm.hotspot.utilities.soql;

import java.util.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.runtime.*;

public interface JSJavaFactory {
   public JSJavaObject newJSJavaObject(Oop oop);
   public JSJavaKlass  newJSJavaKlass(Klass klass);
   public JSJavaField  newJSJavaField(Field f);
   public JSJavaThread newJSJavaThread(JavaThread jt);
   public JSJavaFrame  newJSJavaFrame(JavaVFrame vf);
   public JSJavaMethod newJSJavaMethod(Method m);
   public JSList       newJSList(List l);
   public JSMap        newJSMap(Map m);
   public JSJavaHeap   newJSJavaHeap();
   public JSJavaVM     newJSJavaVM();
   // checks for one of the above special cases
   public Object       newJSJavaWrapper(Object o);
}
