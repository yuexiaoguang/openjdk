package com.sun.org.apache.xerces.internal.util;

import java.util.Hashtable;

import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import org.w3c.dom.TypeInfo;

/**
 * Straight-forward implementation of {@link TypeInfo}.
 *
 * <p>
 * This class is immutable.
 */
public class TypeInfoImpl implements TypeInfo {

    private final String typeNamespace;
    private final String typeName;
        private final static String dtdNamespaceURI = "http://www.w3.org/TR/REC-xml";
        public TypeInfoImpl(){
                typeNamespace = null;
                typeName = null;
        }
    public TypeInfoImpl(String typeNamespace, String typeName) {
        this.typeNamespace = typeNamespace;
        this.typeName = typeName;
    }

    public TypeInfoImpl(XSTypeDefinition t) {
        this( t.getNamespace(), t.getName() );
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeNamespace() {
        return typeNamespace;
    }

    /**
     * Always returns false.
     */
    public boolean isDerivedFrom(String typeNamespaceArg,  String typeNameArg, int derivationMethod) {
        return false;
    }

    /**
     * Map from DTD type name ({@link String}) to {@link TypeInfo}.
     */
    private static final Hashtable dtdCache = new Hashtable();

    /**
     * Obtains a {@link TypeInfo} object from the DTD type name.
     * <p>
     * Since DTD has a very limited type names, we can actually
     * cache the {@link TypeInfo} objects.
     */
    public static TypeInfo getDTDTypeInfo( String name ) {
        TypeInfo t = (TypeInfo)dtdCache.get(name);
        if(t==null) throw new IllegalArgumentException("Unknown DTD datatype "+name);
        return t;
    }

    static {
        String[] typeNames = new String[]{
            "CDATA", "ID", "IDREF", "IDREFS", "NMTOKEN", "NMTOKENS",
            "ENTITY", "ENTITIES", "NOTATION"};
        for( int i=0; i<typeNames.length; i++ )
            dtdCache.put(typeNames[i],new TypeInfoImpl(dtdNamespaceURI,typeNames[i]));
    }
}
