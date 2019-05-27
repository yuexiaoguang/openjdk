/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
package com.sun.tools.example.debug.bdi;

import com.sun.jdi.*;

interface ReferenceTypeSpec {
    /**
     * Does the specified ReferenceType match this spec.
     */
    boolean matches(ReferenceType refType);

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);
}