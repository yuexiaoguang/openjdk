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



class SimpleType_Restriction extends NGCCHandler {
    private Locator locator;
    private AnnotationImpl annotation;
    private String name;
    private UName baseTypeName;
    private Set finalSet;
    private ForeignAttributesImpl fa;
    private XSFacet facet;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public SimpleType_Restriction(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie, AnnotationImpl _annotation, Locator _locator, ForeignAttributesImpl _fa, String _name, Set _finalSet) {
        super(source, parent, cookie);
        $runtime = runtime;
        this.annotation = _annotation;
        this.locator = _locator;
        this.fa = _fa;
        this.name = _name;
        this.finalSet = _finalSet;
        $_ngcc_current_state = 13;
    }

    public SimpleType_Restriction(NGCCRuntimeEx runtime, AnnotationImpl _annotation, Locator _locator, ForeignAttributesImpl _fa, String _name, Set _finalSet) {
        this(null, runtime, runtime, -1, _annotation, _locator, _fa, _name, _finalSet);
    }

    private void action0()throws SAXException {

                result.addFacet(facet);

}

    private void action1()throws SAXException {

        result = new RestrictionSimpleTypeImpl(
                                        $runtime.document, annotation, locator, fa, name, name==null, finalSet, baseType );

}

    private void action2()throws SAXException {

            baseType = new DelayedRef.SimpleType(
              $runtime, rloc, $runtime.currentSchema, baseTypeName );

}

    private void action3()throws SAXException {
        rloc=$runtime.copyLocator();
}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 12:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || ((($ai = $runtime.getAttributeIndex("","base"))>=0 && ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || (((((((((((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minExclusive")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxExclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("totalDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("fractionDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("length"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("enumeration"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("whiteSpace"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("pattern")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType"))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 166, fa);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 10:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 164, annotation,AnnotationContext.SIMPLETYPE_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 5;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 4:
            {
                action1();
                $_ngcc_current_state = 2;
                $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 0:
            {
                revertToParentFromEnterElement(result, super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 5:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType"))) {
                        NGCCHandler h = new simpleType(this, super._source, $runtime, 158);
                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                    }
                    else {
                        unexpectedEnterElement($__qname);
                    }
                }
            }
            break;
        case 1:
            {
                if((((((((((((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minExclusive")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxExclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("totalDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("fractionDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("length"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("enumeration"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("whiteSpace"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("pattern")))) {
                    NGCCHandler h = new facet(this, super._source, $runtime, 153);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 2:
            {
                if((((((((((((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minExclusive")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxExclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("totalDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("fractionDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("length"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("enumeration"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("whiteSpace"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("pattern")))) {
                    NGCCHandler h = new facet(this, super._source, $runtime, 154);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 1;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 13:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    action3();
                    $_ngcc_current_state = 12;
                }
                else {
                    unexpectedEnterElement($__qname);
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
        case 12:
            {
                if((($ai = $runtime.getAttributeIndex("","base"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction")))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 166, fa);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 10:
            {
                $_ngcc_current_state = 5;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 4:
            {
                action1();
                $_ngcc_current_state = 2;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromLeaveElement(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 5:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 1:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 0;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
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
        case 12:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 166, fa);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 10:
            {
                $_ngcc_current_state = 5;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 4:
            {
                action1();
                $_ngcc_current_state = 2;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromEnterAttribute(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 5:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 8;
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
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
        case 7:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 4;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 10:
            {
                $_ngcc_current_state = 5;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 4:
            {
                action1();
                $_ngcc_current_state = 2;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromLeaveAttribute(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
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
        case 8:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 160);
                spawnChildFromText(h, $value);
            }
            break;
        case 12:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 166, fa);
                    spawnChildFromText(h, $value);
                }
            }
            break;
        case 10:
            {
                $_ngcc_current_state = 5;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 4:
            {
                action1();
                $_ngcc_current_state = 2;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 0:
            {
                revertToParentFromText(result, super._cookie, $value);
            }
            break;
        case 5:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        }
    }

    public void onChildCompleted(Object $__result__, int $__cookie__, boolean $__needAttCheck__)throws SAXException {
        switch($__cookie__) {
        case 160:
            {
                baseTypeName = ((UName)$__result__);
                action2();
                $_ngcc_current_state = 7;
            }
            break;
        case 164:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 5;
            }
            break;
        case 154:
            {
                facet = ((XSFacet)$__result__);
                action0();
                $_ngcc_current_state = 1;
            }
            break;
        case 166:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 10;
            }
            break;
        case 158:
            {
                baseType = ((SimpleTypeImpl)$__result__);
                $_ngcc_current_state = 4;
            }
            break;
        case 153:
            {
                facet = ((XSFacet)$__result__);
                action0();
                $_ngcc_current_state = 1;
            }
            break;
        }
    }

    public boolean accepted() {
        return(($_ngcc_current_state == 0));
    }


                /** computed simple type object */
                private RestrictionSimpleTypeImpl result;

                // reference to the base type
                private Ref.SimpleType baseType;

                // location of restriction
                private Locator rloc;

}
