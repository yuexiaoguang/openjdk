package com.sun.codemodel.internal.util;

import java.util.Comparator;
import com.sun.codemodel.internal.JClass;

/**
 * Comparator object that sorts {@link JClass}es in the order
 * of their names.
 */
public class ClassNameComparator implements Comparator<JClass> {
    private ClassNameComparator() {}

    public int compare(JClass l, JClass r) {
        return l.fullName().compareTo(r.fullName());
    }

    public static final Comparator<JClass> theInstance = new ClassNameComparator();
}
