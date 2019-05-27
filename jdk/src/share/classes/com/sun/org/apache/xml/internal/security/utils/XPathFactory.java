package com.sun.org.apache.xml.internal.security.utils;


/**
 * A Factory to return an XPathAPI instance. If Xalan is available it returns XalanXPathAPI. If not, then
 * it returns JDKXPathAPI.
 */
public abstract class XPathFactory {

    private static boolean xalanInstalled;

    static {
        try {
            Class<?> funcTableClass =
                ClassLoaderUtils.loadClass("com.sun.org.apache.xpath.internal.compiler.FunctionTable", XPathFactory.class);
            if (funcTableClass != null) {
                xalanInstalled = true;
            }
        } catch (Exception e) {
            //ignore
        }
    }

    protected synchronized static boolean isXalanInstalled() {
        return xalanInstalled;
    }

    /**
     * Get a new XPathFactory instance
     */
    public static XPathFactory newInstance() {
        if (!isXalanInstalled()) {
            return new JDKXPathFactory();
        }
        // Xalan is available
        if (XalanXPathAPI.isInstalled()) {
            return new XalanXPathFactory();
        }
        // Some problem was encountered in fixing up the Xalan FunctionTable so fall back to the
        // JDK implementation
        return new JDKXPathFactory();
    }

    /**
     * Get a new XPathAPI instance
     */
    public abstract XPathAPI newXPathAPI();

}
