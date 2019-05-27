package com.sun.xml.internal.ws.policy;


/**
 * This interface defines method that is used to handle actual equality comparison and hash code generation for PolicyMapKey object.
 * <p/>
 * The different implementations of this interface may allow different strategies to be applied for operations mentioned above. This feature
 * is used within {@link WSPolicyMap} to restrict set of fields to be compared when searching different policy scope maps.
 */
interface PolicyMapKeyHandler {
    boolean areEqual(PolicyMapKey locator1, PolicyMapKey locator2);

    int generateHashCode(PolicyMapKey locator);
}
