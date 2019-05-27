package javax.xml.ws.http;


import javax.xml.ws.Binding;

/** The <code>HTTPBinding</code> interface is an
 *  abstraction for the XML/HTTP binding.
**/
public interface HTTPBinding extends Binding {

  /**
   * A constant representing the identity of the XML/HTTP binding.
   */
  public static final String HTTP_BINDING = "http://www.w3.org/2004/08/wsdl/http";
}
