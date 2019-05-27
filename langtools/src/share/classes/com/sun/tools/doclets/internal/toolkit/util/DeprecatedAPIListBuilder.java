package com.sun.tools.doclets.internal.toolkit.util;

import java.util.*;

import com.sun.javadoc.*;
import com.sun.tools.doclets.internal.toolkit.Configuration;

/**
 * Build list of all the deprecated packages, classes, constructors, fields and methods.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class DeprecatedAPIListBuilder {

    public static final int NUM_TYPES = 12;

    public static final int PACKAGE = 0;
    public static final int INTERFACE = 1;
    public static final int CLASS = 2;
    public static final int ENUM = 3;
    public static final int EXCEPTION = 4;
    public static final int ERROR = 5;
    public static final int ANNOTATION_TYPE = 6;
    public static final int FIELD = 7;
    public static final int METHOD = 8;
    public static final int CONSTRUCTOR = 9;
    public static final int ENUM_CONSTANT = 10;
    public static final int ANNOTATION_TYPE_MEMBER = 11;

    /**
     * List of deprecated type Lists.
     */
    private List<List<Doc>> deprecatedLists;


    /**
     * Constructor.
     *
     * @param configuration the current configuration of the doclet
     */
    public DeprecatedAPIListBuilder(Configuration configuration) {
        deprecatedLists = new ArrayList<List<Doc>>();
        for (int i = 0; i < NUM_TYPES; i++) {
            deprecatedLists.add(i, new ArrayList<Doc>());
        }
        buildDeprecatedAPIInfo(configuration);
    }

    /**
     * Build the sorted list of all the deprecated APIs in this run.
     * Build separate lists for deprecated packages, classes, constructors,
     * methods and fields.
     *
     * @param configuration the current configuration of the doclet.
     */
    private void buildDeprecatedAPIInfo(Configuration configuration) {
        PackageDoc[] packages = configuration.packages;
        PackageDoc pkg;
        for (int c = 0; c < packages.length; c++) {
            pkg = packages[c];
            if (Util.isDeprecated(pkg)) {
                getList(PACKAGE).add(pkg);
            }
        }
        ClassDoc[] classes = configuration.root.classes();
        for (int i = 0; i < classes.length; i++) {
            ClassDoc cd = classes[i];
            if (Util.isDeprecated(cd)) {
                if (cd.isOrdinaryClass()) {
                    getList(CLASS).add(cd);
                } else if (cd.isInterface()) {
                    getList(INTERFACE).add(cd);
                } else if (cd.isException()) {
                    getList(EXCEPTION).add(cd);
                } else if (cd.isEnum()) {
                    getList(ENUM).add(cd);
                } else if (cd.isError()) {
                    getList(ERROR).add(cd);
                } else if (cd.isAnnotationType()) {
                    getList(ANNOTATION_TYPE).add(cd);
                }
            }
            composeDeprecatedList(getList(FIELD), cd.fields());
            composeDeprecatedList(getList(METHOD), cd.methods());
            composeDeprecatedList(getList(CONSTRUCTOR), cd.constructors());
            if (cd.isEnum()) {
                composeDeprecatedList(getList(ENUM_CONSTANT), cd.enumConstants());
            }
            if (cd.isAnnotationType()) {
                composeDeprecatedList(getList(ANNOTATION_TYPE_MEMBER),
                        ((AnnotationTypeDoc) cd).elements());
            }
        }
        sortDeprecatedLists();
    }

    /**
     * Add the members into a single list of deprecated members.
     *
     * @param list List of all the particular deprecated members, e.g. methods.
     * @param members members to be added in the list.
     */
    private void composeDeprecatedList(List<Doc> list, MemberDoc[] members) {
        for (int i = 0; i < members.length; i++) {
            if (Util.isDeprecated(members[i])) {
                list.add(members[i]);
            }
        }
    }

    /**
     * Sort the deprecated lists for class kinds, fields, methods and
     * constructors.
     */
    private void sortDeprecatedLists() {
        for (int i = 0; i < NUM_TYPES; i++) {
            Collections.sort(getList(i));
        }
    }

    /**
     * Return the list of deprecated Doc objects of a given type.
     *
     * @param type the constant representing the type of list being returned.
     */
    public List<Doc> getList(int type) {
        return deprecatedLists.get(type);
    }

    /**
     * Return true if the list of a given type has size greater than 0.
     *
     * @param type the type of list being checked.
     */
    public boolean hasDocumentation(int type) {
        return (deprecatedLists.get(type)).size() > 0;
    }
}
