/** Java interface "Connection.java" generated from Poseidon for UML.
 *  Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 *  Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package com.sun.pept.transport;

import com.sun.pept.ept.EPTFactory;
import java.nio.ByteBuffer;
import java.util.*;

public interface Connection {

  ///////////////////////////////////////
  // operations

/**
 * <p>
 * Does ...
 * </p><p>
 *
 * </p><p>
 *
 * @param byteBuffer ...
 * </p>
 */
    public void write(ByteBuffer byteBuffer);
/**
 * <p>
 * Does ...
 * </p><p>
 *
 * @return a EPTFactory with ...
 * </p>
 */
    public EPTFactory getEPTFactory();
/**
 * <p>
 * Does ...
 * </p><p>
 *
 * @return a int with ...
 * </p><p>
 * @param byteBuffer ...
 * </p>
 */
    public int read(ByteBuffer byteBuffer);
/**
 * <p>
 * Does ...
 * </p><p>
 *
 * </p>
 */
    public ByteBuffer readUntilEnd();

} // end Connection
