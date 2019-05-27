package javax.xml.ws.handler;

/** The <code>LogicalHandler</code> extends
 *  Handler to provide typesafety for the message context parameter.
**/
public interface LogicalHandler<C extends LogicalMessageContext> extends Handler<C> {
}
