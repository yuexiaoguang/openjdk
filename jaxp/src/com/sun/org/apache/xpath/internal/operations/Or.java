package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;

/**
 * The 'or' operation expression executer.
 */
public class Or extends Operation
{
    static final long serialVersionUID = -644107191353853079L;

  /**
   * OR two expressions and return the boolean result. Override
   * superclass method for optimization purposes.
   *
   * @param xctxt The runtime execution context.
   *
   * @return {@link com.sun.org.apache.xpath.internal.objects.XBoolean#S_TRUE} or
   * {@link com.sun.org.apache.xpath.internal.objects.XBoolean#S_FALSE}.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    XObject expr1 = m_left.execute(xctxt);

    if (!expr1.bool())
    {
      XObject expr2 = m_right.execute(xctxt);

      return expr2.bool() ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
    else
      return XBoolean.S_TRUE;
  }

  /**
   * Evaluate this operation directly to a boolean.
   *
   * @param xctxt The runtime execution context.
   *
   * @return The result of the operation as a boolean.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public boolean bool(XPathContext xctxt)
          throws javax.xml.transform.TransformerException
  {
    return (m_left.bool(xctxt) || m_right.bool(xctxt));
  }

}
