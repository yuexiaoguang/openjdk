package com.sun.tools.doclets.internal.toolkit.util;

import java.util.*;

import com.sun.javadoc.*;
import com.sun.tools.javac.jvm.Profile;
import com.sun.tools.doclets.internal.toolkit.*;

/**
 * Provides methods for creating an array of class, method and
 * field names to be included as meta keywords in the HTML header
 * of class pages.  These keywords improve search results
 * on browsers that look for keywords.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class MetaKeywords {

    /**
     * The global configuration information for this run.
     */
    private final Configuration configuration;

    /**
     * Constructor
     */
    public MetaKeywords(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Returns an array of strings where each element
     * is a class, method or field name.  This array is
     * used to create one meta keyword tag for each element.
     * Method parameter lists are converted to "()" and
     * overloads are combined.
     *
     * Constructors are not included because they have the same
     * name as the class, which is already included.
     * Nested class members are not included because their
     * definitions are on separate pages.
     */
    public String[] getMetaKeywords(ClassDoc classdoc) {
        ArrayList<String> results = new ArrayList<String>();

        // Add field and method keywords only if -keywords option is used
        if( configuration.keywords ) {
            results.addAll(getClassKeyword(classdoc));
            results.addAll(getMemberKeywords(classdoc.fields()));
            results.addAll(getMemberKeywords(classdoc.methods()));
        }
        return results.toArray(new String[]{});
    }

    /**
     * Get the current class for a meta tag keyword, as the first
     * and only element of an array list.
     */
    protected ArrayList<String> getClassKeyword(ClassDoc classdoc) {
        String cltypelower = classdoc.isInterface() ? "interface" : "class";
        ArrayList<String> metakeywords = new ArrayList<String>(1);
        metakeywords.add(classdoc.qualifiedName() + " " + cltypelower);
        return metakeywords;
    }

    /**
     * Get the package keywords.
     */
    public String[] getMetaKeywords(PackageDoc packageDoc) {
        if( configuration.keywords ) {
            String pkgName = Util.getPackageName(packageDoc);
            return new String[] { pkgName + " " + "package" };
        } else {
            return new String[] {};
        }
    }

    /**
     * Get the profile keywords.
     *
     * @param profile the profile being documented
     */
    public String[] getMetaKeywords(Profile profile) {
        if( configuration.keywords ) {
            String profileName = profile.name;
            return new String[] { profileName + " " + "profile" };
        } else {
            return new String[] {};
        }
    }

    /**
     * Get the overview keywords.
     */
    public String[] getOverviewMetaKeywords(String title, String docTitle) {
        if( configuration.keywords ) {
            String windowOverview = configuration.getText(title);
            String[] metakeywords = { windowOverview };
            if (docTitle.length() > 0 ) {
                metakeywords[0] += ", " + docTitle;
            }
            return metakeywords;
        } else {
            return new String[] {};
        }
    }

    /**
     * Get members for meta tag keywords as an array,
     * where each member name is a string element of the array.
     * The parameter lists are not included in the keywords;
     * therefore all overloaded methods are combined.<br>
     * Example: getValue(Object) is returned in array as getValue()
     *
     * @param memberdocs  array of MemberDoc objects to be added to keywords
     */
    protected ArrayList<String> getMemberKeywords(MemberDoc[] memberdocs) {
        ArrayList<String> results = new ArrayList<String>();
        String membername;
        for (int i=0; i < memberdocs.length; i++) {
            membername = memberdocs[i].name()
                             + (memberdocs[i].isMethod() ? "()" : "");
            if ( ! results.contains(membername) ) {
                results.add(membername);
            }
        }
        return results;
    }
}
