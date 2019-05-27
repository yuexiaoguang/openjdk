package com.sun.xml.internal.rngom.dt.builtin;

import org.relaxng.datatype.Datatype;
import org.relaxng.datatype.DatatypeBuilder;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.DatatypeLibraryFactory;

import com.sun.xml.internal.rngom.xml.util.WellKnownNamespaces;

public class BuiltinDatatypeLibrary implements DatatypeLibrary {
    private final DatatypeLibraryFactory factory;
    private DatatypeLibrary xsdDatatypeLibrary = null;

    BuiltinDatatypeLibrary(DatatypeLibraryFactory factory) {
        this.factory = factory;
    }

    public DatatypeBuilder createDatatypeBuilder(String type)
        throws DatatypeException {
        xsdDatatypeLibrary =
            factory.createDatatypeLibrary(
                WellKnownNamespaces.XML_SCHEMA_DATATYPES);
        if (xsdDatatypeLibrary == null)
            throw new DatatypeException();

        if (type.equals("string") || type.equals("token")) {
            return new BuiltinDatatypeBuilder(
                xsdDatatypeLibrary.createDatatype(type));
        }
        throw new DatatypeException();
    }
    public Datatype createDatatype(String type) throws DatatypeException {
        return createDatatypeBuilder(type).createDatatype();
    }
}
