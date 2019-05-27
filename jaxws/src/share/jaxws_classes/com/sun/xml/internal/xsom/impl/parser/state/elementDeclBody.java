package com.sun.xml.internal.xsom.impl.parser.state;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import com.sun.xml.internal.xsom.impl.parser.NGCCRuntimeEx;

    import com.sun.xml.internal.xsom.*;
    import com.sun.xml.internal.xsom.parser.*;
    import com.sun.xml.internal.xsom.impl.*;
    import com.sun.xml.internal.xsom.impl.parser.*;
    import org.xml.sax.Locator;
    import org.xml.sax.ContentHandler;
    import org.xml.sax.helpers.*;
    import java.util.*;
    import java.math.BigInteger;



class elementDeclBody extends NGCCHandler {
    private Integer finalValue;
    private String name;
    private String nillable;
    private String abstractValue;
    private Integer blockValue;
    private ForeignAttributesImpl fa;
    private AnnotationImpl annotation;
    private Locator locator;
    private String defaultValue;
    private IdentityConstraintImpl idc;
    private boolean isGlobal;
    private String fixedValue;
    private UName typeName;
    private UName substRef;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public elementDeclBody(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie, Locator _locator, boolean _isGlobal) {
        super(source, parent, cookie);
        $runtime = runtime;
        this.locator = _locator;
        this.isGlobal = _isGlobal;
        $_ngcc_current_state = 48;
    }

    public elementDeclBody(NGCCRuntimeEx runtime, Locator _locator, boolean _isGlobal) {
        this(null, runtime, runtime, -1, _locator, _isGlobal);
    }

    private void action0()throws SAXException {
        idcs.add(idc);
}

    private void action1()throws SAXException {

            type = new DelayedRef.Type(
              $runtime, locator, $runtime.currentSchema, typeName );

}

    private void action2()throws SAXException {

          substHeadRef = new DelayedRef.Element(
            $runtime, locator, $runtime.currentSchema, substRef );

}

    private void action3()throws SAXException {
        formSpecified = true;
}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 17:
            {
                if(($ai = $runtime.getAttributeIndex("","nillable"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 13;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 28:
            {
                if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 24;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 0:
            {
                if(((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) {
                    NGCCHandler h = new identityConstraint(this, super._source, $runtime, 6);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    revertToParentFromEnterElement(makeResult(), super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 32:
            {
                if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 28;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 24:
            {
                if(($ai = $runtime.getAttributeIndex("","form"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 23;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 11:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 24, null,AnnotationContext.ELEMENT_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 3;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 23:
            {
                if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 44:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 40;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 40:
            {
                if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 36;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 48:
            {
                if(((($ai = $runtime.getAttributeIndex("","default"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","fixed"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","form"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","block"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","final"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","name"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || (($ai = $runtime.getAttributeIndex("","abstract"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))))))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 1:
            {
                if(((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) {
                    NGCCHandler h = new identityConstraint(this, super._source, $runtime, 7);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 0;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 3:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType"))) {
                    NGCCHandler h = new simpleType(this, super._source, $runtime, 19);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) {
                        NGCCHandler h = new complexType(this, super._source, $runtime, 20);
                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                    }
                    else {
                        if(($ai = $runtime.getAttributeIndex("","type"))>=0) {
                            $runtime.consumeAttribute($ai);
                            $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                        }
                        else {
                            $_ngcc_current_state = 1;
                            $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                        }
                    }
                }
            }
            break;
        case 13:
            {
                if(($ai = $runtime.getAttributeIndex("","substitutionGroup"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 11;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 36:
            {
                if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 32;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        default:
            {
                unexpectedEnterElement($__qname);
            }
            break;
        }
    }

    public void leaveElement(String $__uri, String $__local, String $__qname) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 17:
            {
                if(($ai = $runtime.getAttributeIndex("","nillable"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 13;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 28:
            {
                if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 24;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromLeaveElement(makeResult(), super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 32:
            {
                if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 28;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 24:
            {
                if(($ai = $runtime.getAttributeIndex("","form"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 23;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 11:
            {
                $_ngcc_current_state = 3;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 23:
            {
                if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 44:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 40;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 40:
            {
                if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 36;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 48:
            {
                if((($ai = $runtime.getAttributeIndex("","default"))>=0 || (($ai = $runtime.getAttributeIndex("","fixed"))>=0 || (($ai = $runtime.getAttributeIndex("","form"))>=0 || (($ai = $runtime.getAttributeIndex("","block"))>=0 || (($ai = $runtime.getAttributeIndex("","final"))>=0 || (($ai = $runtime.getAttributeIndex("","name"))>=0 || ($ai = $runtime.getAttributeIndex("","abstract"))>=0))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 1:
            {
                $_ngcc_current_state = 0;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 3:
            {
                if(($ai = $runtime.getAttributeIndex("","type"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 1;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 13:
            {
                if(($ai = $runtime.getAttributeIndex("","substitutionGroup"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 11;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 36:
            {
                if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 32;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        default:
            {
                unexpectedLeaveElement($__qname);
            }
            break;
        }
    }

    public void enterAttribute(String $__uri, String $__local, String $__qname) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 17:
            {
                if(($__uri.equals("") && $__local.equals("nillable"))) {
                    $_ngcc_current_state = 19;
                }
                else {
                    $_ngcc_current_state = 13;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 28:
            {
                if(($__uri.equals("") && $__local.equals("fixed"))) {
                    $_ngcc_current_state = 30;
                }
                else {
                    $_ngcc_current_state = 24;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromEnterAttribute(makeResult(), super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 32:
            {
                if(($__uri.equals("") && $__local.equals("default"))) {
                    $_ngcc_current_state = 34;
                }
                else {
                    $_ngcc_current_state = 28;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 24:
            {
                if(($__uri.equals("") && $__local.equals("form"))) {
                    $_ngcc_current_state = 26;
                }
                else {
                    $_ngcc_current_state = 23;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 11:
            {
                $_ngcc_current_state = 3;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 23:
            {
                if(($__uri.equals("") && $__local.equals("name"))) {
                    $_ngcc_current_state = 22;
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 44:
            {
                if(($__uri.equals("") && $__local.equals("abstract"))) {
                    $_ngcc_current_state = 46;
                }
                else {
                    $_ngcc_current_state = 40;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 40:
            {
                if(($__uri.equals("") && $__local.equals("block"))) {
                    $_ngcc_current_state = 42;
                }
                else {
                    $_ngcc_current_state = 36;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 48:
            {
                if((($__uri.equals("") && $__local.equals("default")) || (($__uri.equals("") && $__local.equals("fixed")) || (($__uri.equals("") && $__local.equals("form")) || (($__uri.equals("") && $__local.equals("block")) || (($__uri.equals("") && $__local.equals("final")) || (($__uri.equals("") && $__local.equals("name")) || ($__uri.equals("") && $__local.equals("abstract"))))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 1:
            {
                $_ngcc_current_state = 0;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 3:
            {
                if(($__uri.equals("") && $__local.equals("type"))) {
                    $_ngcc_current_state = 6;
                }
                else {
                    $_ngcc_current_state = 1;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 13:
            {
                if(($__uri.equals("") && $__local.equals("substitutionGroup"))) {
                    $_ngcc_current_state = 15;
                }
                else {
                    $_ngcc_current_state = 11;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 36:
            {
                if(($__uri.equals("") && $__local.equals("final"))) {
                    $_ngcc_current_state = 38;
                }
                else {
                    $_ngcc_current_state = 32;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        default:
            {
                unexpectedEnterAttribute($__qname);
            }
            break;
        }
    }

    public void leaveAttribute(String $__uri, String $__local, String $__qname) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 21:
            {
                if(($__uri.equals("") && $__local.equals("name"))) {
                    $_ngcc_current_state = 17;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 17:
            {
                $_ngcc_current_state = 13;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 18:
            {
                if(($__uri.equals("") && $__local.equals("nillable"))) {
                    $_ngcc_current_state = 13;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 25:
            {
                if(($__uri.equals("") && $__local.equals("form"))) {
                    $_ngcc_current_state = 23;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 41:
            {
                if(($__uri.equals("") && $__local.equals("block"))) {
                    $_ngcc_current_state = 36;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 28:
            {
                $_ngcc_current_state = 24;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 32:
            {
                $_ngcc_current_state = 28;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromLeaveAttribute(makeResult(), super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 24:
            {
                $_ngcc_current_state = 23;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 11:
            {
                $_ngcc_current_state = 3;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 33:
            {
                if(($__uri.equals("") && $__local.equals("default"))) {
                    $_ngcc_current_state = 28;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 37:
            {
                if(($__uri.equals("") && $__local.equals("final"))) {
                    $_ngcc_current_state = 32;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 44:
            {
                $_ngcc_current_state = 40;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 14:
            {
                if(($__uri.equals("") && $__local.equals("substitutionGroup"))) {
                    $_ngcc_current_state = 11;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 40:
            {
                $_ngcc_current_state = 36;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 45:
            {
                if(($__uri.equals("") && $__local.equals("abstract"))) {
                    $_ngcc_current_state = 40;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 1:
            {
                $_ngcc_current_state = 0;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 3:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 13:
            {
                $_ngcc_current_state = 11;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 36:
            {
                $_ngcc_current_state = 32;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 5:
            {
                if(($__uri.equals("") && $__local.equals("type"))) {
                    $_ngcc_current_state = 1;
                    action1();
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 29:
            {
                if(($__uri.equals("") && $__local.equals("fixed"))) {
                    $_ngcc_current_state = 24;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        default:
            {
                unexpectedLeaveAttribute($__qname);
            }
            break;
        }
    }

    public void text(String $value) throws SAXException {
        int $ai;
        switch($_ngcc_current_state) {
        case 17:
            {
                if(($ai = $runtime.getAttributeIndex("","nillable"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 13;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 34:
            {
                defaultValue = $value;
                $_ngcc_current_state = 33;
            }
            break;
        case 22:
            {
                name = $value;
                $_ngcc_current_state = 21;
            }
            break;
        case 28:
            {
                if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 24;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 32:
            {
                if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 28;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 0:
            {
                revertToParentFromText(makeResult(), super._cookie, $value);
            }
            break;
        case 6:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 10);
                spawnChildFromText(h, $value);
            }
            break;
        case 24:
            {
                if(($ai = $runtime.getAttributeIndex("","form"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 23;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 11:
            {
                $_ngcc_current_state = 3;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 23:
            {
                if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 44:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 40;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 46:
            {
                abstractValue = $value;
                $_ngcc_current_state = 45;
            }
            break;
        case 19:
            {
                nillable = $value;
                $_ngcc_current_state = 18;
            }
            break;
        case 40:
            {
                if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 36;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 48:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                    spawnChildFromText(h, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                        NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                        spawnChildFromText(h, $value);
                    }
                    else {
                        if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                            NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                            spawnChildFromText(h, $value);
                        }
                        else {
                            if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                                NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                                spawnChildFromText(h, $value);
                            }
                            else {
                                if(($ai = $runtime.getAttributeIndex("","form"))>=0) {
                                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                                    spawnChildFromText(h, $value);
                                }
                                else {
                                    if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                                        NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                                        spawnChildFromText(h, $value);
                                    }
                                    else {
                                        if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                                            NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 69, fa);
                                            spawnChildFromText(h, $value);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            break;
        case 30:
            {
                fixedValue = $value;
                $_ngcc_current_state = 29;
            }
            break;
        case 1:
            {
                $_ngcc_current_state = 0;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 3:
            {
                if(($ai = $runtime.getAttributeIndex("","type"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 1;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 13:
            {
                if(($ai = $runtime.getAttributeIndex("","substitutionGroup"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 11;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 38:
            {
                NGCCHandler h = new erSet(this, super._source, $runtime, 55);
                spawnChildFromText(h, $value);
            }
            break;
        case 15:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 27);
                spawnChildFromText(h, $value);
            }
            break;
        case 26:
            {
                if($value.equals("unqualified")) {
                    NGCCHandler h = new qualification(this, super._source, $runtime, 40);
                    spawnChildFromText(h, $value);
                }
                else {
                    if($value.equals("qualified")) {
                        NGCCHandler h = new qualification(this, super._source, $runtime, 40);
                        spawnChildFromText(h, $value);
                    }
                }
            }
            break;
        case 36:
            {
                if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 32;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 42:
            {
                NGCCHandler h = new ersSet(this, super._source, $runtime, 60);
                spawnChildFromText(h, $value);
            }
            break;
        }
    }

    public void onChildCompleted(Object $__result__, int $__cookie__, boolean $__needAttCheck__)throws SAXException {
        switch($__cookie__) {
        case 24:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 3;
            }
            break;
        case 27:
            {
                substRef = ((UName)$__result__);
                action2();
                $_ngcc_current_state = 14;
            }
            break;
        case 10:
            {
                typeName = ((UName)$__result__);
                $_ngcc_current_state = 5;
            }
            break;
        case 60:
            {
                blockValue = ((Integer)$__result__);
                $_ngcc_current_state = 41;
            }
            break;
        case 69:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 44;
            }
            break;
        case 19:
            {
                type = ((SimpleTypeImpl)$__result__);
                $_ngcc_current_state = 1;
            }
            break;
        case 20:
            {
                type = ((ComplexTypeImpl)$__result__);
                $_ngcc_current_state = 1;
            }
            break;
        case 40:
            {
                form = ((Boolean)$__result__).booleanValue();
                action3();
                $_ngcc_current_state = 25;
            }
            break;
        case 6:
            {
                idc = ((IdentityConstraintImpl)$__result__);
                action0();
                $_ngcc_current_state = 0;
            }
            break;
        case 7:
            {
                idc = ((IdentityConstraintImpl)$__result__);
                action0();
                $_ngcc_current_state = 0;
            }
            break;
        case 55:
            {
                finalValue = ((Integer)$__result__);
                $_ngcc_current_state = 37;
            }
            break;
        }
    }

    public boolean accepted() {
        return((($_ngcc_current_state == 1) || (($_ngcc_current_state == 0) || (($_ngcc_current_state == 3) || (($_ngcc_current_state == 17) || (($_ngcc_current_state == 13) || ($_ngcc_current_state == 11)))))));
    }


      private boolean form;
      private boolean formSpecified;
      private Ref.Type type;
      private List idcs = new ArrayList();  // identity constraints

      private DelayedRef.Element substHeadRef;

      private ElementDecl makeResult() {
        if(finalValue==null)
          finalValue = new Integer($runtime.finalDefault);
        if(blockValue==null)
          blockValue = new Integer($runtime.blockDefault);

        if(!formSpecified)
          form = $runtime.elementFormDefault;
        if(isGlobal)  // global elements are always qualified
          form = true;

        String tns;
        if(form)    tns = $runtime.currentSchema.getTargetNamespace();
        else        tns = "";

        if( type==null ) {
                if( substHeadRef!=null )
                        type = new SubstGroupBaseTypeRef(substHeadRef);
                else
                        type = $runtime.parser.schemaSet.anyType;
        }

        ElementDecl ed = new ElementDecl(
            $runtime,
            $runtime.document,
            annotation,
            locator,
            fa,
            tns,
            name,
            !isGlobal,
            $runtime.createXmlString(defaultValue),
            $runtime.createXmlString(fixedValue),
            $runtime.parseBoolean(nillable),
            $runtime.parseBoolean(abstractValue),
            (java.lang.Boolean)(formSpecified ? form : null),
            type,
            substHeadRef,
            blockValue.intValue(),
            finalValue.intValue(),
            idcs);

        // if this element has anonymous complex type, it will be set here.
        if(type instanceof ComplexTypeImpl)
          ((ComplexTypeImpl)type).setScope(ed);
        return ed;
      }

}
