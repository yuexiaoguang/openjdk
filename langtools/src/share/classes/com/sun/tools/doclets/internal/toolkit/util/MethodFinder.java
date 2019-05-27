package com.sun.tools.doclets.internal.toolkit.util;

import com.sun.javadoc.*;

/**
 * This class is useful for searching a method which has documentation
 * comment and documentation tags. The method is searched in all the
 * superclasses and interfaces(subsequently super-interfaces also)
 * recursively.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public abstract class MethodFinder {

    abstract boolean isCorrectMethod(MethodDoc method);

    public MethodDoc search(ClassDoc cd, MethodDoc method) {
        MethodDoc meth = searchInterfaces(cd, method);
        if (meth != null) {
            return meth;
        }
        ClassDoc icd = cd.superclass();
        if (icd != null) {
            meth = Util.findMethod(icd, method);
            if (meth != null) {
            if (isCorrectMethod(meth)) {
                    return meth;
                }
            }
            return search(icd, method);
        }
        return null;
    }

    public MethodDoc searchInterfaces(ClassDoc cd, MethodDoc method) {
        MethodDoc[] implementedMethods = (new ImplementedMethods(method, null)).build();
        for (int i = 0; i < implementedMethods.length; i++) {
            if (isCorrectMethod(implementedMethods[i])) {
                return implementedMethods[i];
            }
        }
        return null;
    }
}
