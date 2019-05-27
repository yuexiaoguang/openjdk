package javax.xml.ws.handler;

/**
 *  <code>HandlerResolver</code> is an interface implemented
 *  by an application to get control over the handler chain
 *  set on proxy/dispatch objects at the time of their creation.
 *  <p>
 *  A <code>HandlerResolver</code> may be set on a <code>Service</code>
 *  using the <code>setHandlerResolver</code> method.
 * <p>
 *  When the runtime invokes a <code>HandlerResolver</code>, it will
 *  pass it a <code>PortInfo</code> object containing information
 *  about the port that the proxy/dispatch object will be accessing.
**/
public interface HandlerResolver {

  /**
   *  Gets the handler chain for the specified port.
   *
   *  @param portInfo Contains information about the port being accessed.
   *  @return java.util.List&lt;Handler> chain
  **/
  public java.util.List<Handler> getHandlerChain(PortInfo portInfo);
}
