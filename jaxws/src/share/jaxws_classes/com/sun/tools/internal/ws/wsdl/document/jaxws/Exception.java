package com.sun.tools.internal.ws.wsdl.document.jaxws;

/**
 * class representing jaxws:exception
 */
public class Exception {

    public Exception(){}

    public Exception(CustomName name){
        this.className = name;
    }

    private CustomName className;
    /**
     * @return Returns the className.
     */
    public CustomName getClassName() {
        return className;
    }
    /**
     * @param className The className to set.
     */
    public void setClassName(CustomName className) {
        this.className = className;
    }
}
