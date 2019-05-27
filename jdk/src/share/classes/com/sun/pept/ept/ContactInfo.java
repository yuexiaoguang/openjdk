/** Java interface "ContactInfo.java" generated from Poseidon for UML.
 *  Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 *  Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package com.sun.pept.ept;

import com.sun.pept.transport.Connection;
import java.util.*;

public interface ContactInfo extends EPTFactory {

  ///////////////////////////////////////
  // operations

/**
 * <p>
 * Does ...
 * </p><p>
 *
 * @return a Connection with ...
 * </p><p>
 * @param messageInfo ...
 * </p>
 */
    public Connection getConnection(MessageInfo messageInfo);

} // end ContactInfo
