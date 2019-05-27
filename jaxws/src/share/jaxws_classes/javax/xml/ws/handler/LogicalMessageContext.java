package javax.xml.ws.handler;

import javax.xml.ws.LogicalMessage;

/** The <code>LogicalMessageContext</code> interface extends
 *  <code>MessageContext</code> to
 *  provide access to a the contained message as a protocol neutral
 *  LogicalMessage
**/
public interface LogicalMessageContext
                    extends MessageContext {

  /** Gets the message from this message context
   *
   *  @return The contained message; returns <code>null</code> if no
   *          message is present in this message context
  **/
  public LogicalMessage getMessage();
}
