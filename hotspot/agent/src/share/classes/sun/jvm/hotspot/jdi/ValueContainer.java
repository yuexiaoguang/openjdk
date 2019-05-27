package sun.jvm.hotspot.jdi;

import com.sun.jdi.*;

/*
 * This interface allows us to pass fields, variables, and
 * array components through the same interfaces. This currently allows
 * more common code for type checking. In the future we could use it for
 * more.
 */
interface ValueContainer {
    Type type() throws ClassNotLoadedException;
    Type findType(String signature) throws ClassNotLoadedException;
    String typeName();
    String signature();
}
