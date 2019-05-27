package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;

/**
 * Execute the Namespace() function.
 */
public class FuncNamespace extends FunctionDef1Arg
{
    static final long serialVersionUID = -4695674566722321237L;

  /**
   * Execute the function.  The function must return
   * a valid object.
   * @param xctxt The current execution context.
   * @return A valid XObject.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    int context = getArg0AsNode(xctxt);

    String s;
    if(context != DTM.NULL)
    {
      DTM dtm = xctxt.getDTM(context);
      int t = dtm.getNodeType(context);
      if(t == DTM.ELEMENT_NODE)
      {
        s = dtm.getNamespaceURI(context);
      }
      else if(t == DTM.ATTRIBUTE_NODE)
      {

        // This function always returns an empty string for namespace nodes.
        // We check for those here.  Fix inspired by Davanum Srinivas.

        s = dtm.getNodeName(context);
        if(s.startsWith("xmlns:") || s.equals("xmlns"))
          return XString.EMPTYSTRING;

        s = dtm.getNamespaceURI(context);
      }
      else
        return XString.EMPTYSTRING;
    }
    else
      return XString.EMPTYSTRING;

    return ((null == s) ? XString.EMPTYSTRING : new XString(s));
  }
}
