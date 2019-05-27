/** Java interface "Tie.java" generated from Poseidon for UML.
 *  Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 *  Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package com.sun.pept.presentation;

import com.sun.pept.ept.MessageInfo;
import java.util.*;

public interface Tie {

  ///////////////////////////////////////
  // operations

/**
 * <p>
 * Does ...
 * </p><p>
 *
 * </p><p>
 *
 * @param servant ...
 * </p>
 */
    public void _setServant(Object servant);
/**
 * <p>
 * Does ...
 * </p><p>
 *
 * @return a Object with ...
 * </p>
 */
    public Object _getServant();
/**
 * <p>
 * Does ...
 * </p><p>
 *
 * </p><p>
 *
 * @param messageInfo ...
 * </p>
 */
    public void _invoke(MessageInfo messageInfo);

} // end Tie
