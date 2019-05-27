/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.*;

/**
 * <P>StringValidator validates that XML content is a W3C string type.</P>
 * <P>The string datatype represents character strings in XML. The
 * value space of string is the set of finite-length sequences
 * of characters (as defined in [XML 1.0 Recommendation
 * (Second Edition)]) that match the Char production
 * from [XML 1.0 Recommendation (Second Edition)].
 * A character is an atomic unit of communication; it
 * is not further specified except to note that every
 * character has a corresponding Universal Code Set
 * code point ([ISO 10646],[Unicode] and [Unicode3]),
 * which is an integer.</P>
 */
public class StringDatatypeValidator implements DatatypeValidator {

    // construct a string datatype validator
    public StringDatatypeValidator() {
    }

    /**
     * Checks that "content" string is valid string value.
     * If invalid a Datatype validation exception is thrown.
     *
     * @param content       the string value that needs to be validated
     * @param context       the validation context
     * @throws InvalidDatatypeException if the content is
     *         invalid according to the rules for the validators
     */
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {
    }

}
