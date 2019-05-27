/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.util.SymbolHash;

/**
 * A special factory to create/return built-in schema DVs and create user-defined DVs
 * that includes anyAtomicType, yearMonthDuration and dayTimeDuration
 */
public class ExtendedSchemaDVFactoryImpl extends BaseSchemaDVFactory {

    static SymbolHash fBuiltInTypes = new SymbolHash();
    static {
        createBuiltInTypes();
    }

    // create all built-in types
    static void createBuiltInTypes() {
        final String ANYATOMICTYPE     = "anyAtomicType";
        final String DURATION          = "duration";
        final String YEARMONTHDURATION = "yearMonthDuration";
        final String DAYTIMEDURATION   = "dayTimeDuration";

        createBuiltInTypes(fBuiltInTypes, XSSimpleTypeDecl.fAnyAtomicType);

        // add anyAtomicType
        fBuiltInTypes.put(ANYATOMICTYPE, XSSimpleTypeDecl.fAnyAtomicType);

        // add 2 duration types
        XSSimpleTypeDecl durationDV = (XSSimpleTypeDecl)fBuiltInTypes.get(DURATION);
        fBuiltInTypes.put(YEARMONTHDURATION, new XSSimpleTypeDecl(durationDV, YEARMONTHDURATION, XSSimpleTypeDecl.DV_YEARMONTHDURATION, XSSimpleType.ORDERED_PARTIAL, false, false, false, true, XSSimpleTypeDecl.YEARMONTHDURATION_DT));
        fBuiltInTypes.put(DAYTIMEDURATION, new XSSimpleTypeDecl(durationDV, DAYTIMEDURATION, XSSimpleTypeDecl.DV_DAYTIMEDURATION, XSSimpleType.ORDERED_PARTIAL, false, false, false, true, XSSimpleTypeDecl.DAYTIMEDURATION_DT));
    } //createBuiltInTypes()

    /**
     * Get a built-in simple type of the given name
     * REVISIT: its still not decided within the Schema WG how to define the
     *          ur-types and if all simple types should be derived from a
     *          complex type, so as of now we ignore the fact that anySimpleType
     *          is derived from anyType, and pass 'null' as the base of
     *          anySimpleType. It needs to be changed as per the decision taken.
     *
     * @param name  the name of the datatype
     * @return      the datatype validator of the given name
     */
    public XSSimpleType getBuiltInType(String name) {
        return (XSSimpleType)fBuiltInTypes.get(name);
    }

    /**
     * get all built-in simple types, which are stored in a hashtable keyed by
     * the name
     *
     * @return      a hashtable which contains all built-in simple types
     */
    public SymbolHash getBuiltInTypes() {
        return (SymbolHash)fBuiltInTypes.makeClone();
    }
}
