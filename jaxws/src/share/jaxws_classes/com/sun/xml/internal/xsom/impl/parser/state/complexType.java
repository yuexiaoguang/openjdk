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



class complexType extends NGCCHandler {
    private Integer finalValue;
    private String name;
    private String abstractValue;
    private Integer blockValue;
    private XSFacet facet;
    private ForeignAttributesImpl fa;
    private AnnotationImpl annotation;
    private ContentTypeImpl explicitContent;
    private UName baseTypeName;
    private String mixedValue;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public complexType(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie) {
        super(source, parent, cookie);
        $runtime = runtime;
        $_ngcc_current_state = 88;
    }

    public complexType(NGCCRuntimeEx runtime) {
        this(null, runtime, runtime, -1);
    }

    private void action0()throws SAXException {

            result.setContentType(explicitContent);

}

    private void action1()throws SAXException {

            baseType = $runtime.parser.schemaSet.anyType;
            makeResult(XSType.RESTRICTION);

}

    private void action2()throws SAXException {

                result.setExplicitContent(explicitContent);
                result.setContentType(
                  buildComplexExtensionContentModel(explicitContent));

}

    private void action3()throws SAXException {

                  baseType = new DelayedRef.Type(
                    $runtime, locator2, $runtime.currentSchema, baseTypeName );
                  makeResult(XSType.EXTENSION);

}

    private void action4()throws SAXException {
        locator2 = $runtime.copyLocator();
}

    private void action5()throws SAXException {

                result.setContentType(explicitContent);

}

    private void action6()throws SAXException {

                  baseType = new DelayedRef.Type(
                    $runtime, locator2, $runtime.currentSchema, baseTypeName );
                  makeResult(XSType.RESTRICTION);

}

    private void action7()throws SAXException {
        locator2 = $runtime.copyLocator();
}

    private void action8()throws SAXException {

                contentType = new BaseContentRef($runtime,baseType);
                makeResult(XSType.EXTENSION);
                result.setContentType(contentType);

}

    private void action9()throws SAXException {

                  baseType = new DelayedRef.Type(
                    $runtime, locator2, $runtime.currentSchema, baseTypeName );

}

    private void action10()throws SAXException {
        locator2 = $runtime.copyLocator();
}

    private void action11()throws SAXException {

                makeResult(XSType.RESTRICTION);
                result.setContentType(contentType);

}

    private void action12()throws SAXException {
        contentSimpleType.addFacet(facet);
}

    private void action13()throws SAXException {

                if(baseContentType==null) {
                  // contentType of the base type, which must be a complex type
                  baseContentType = new BaseContentSimpleTypeRef(baseType);
                }

                contentSimpleType = new RestrictionSimpleTypeImpl(
                  $runtime.document, null/*?*/, locator2, null,
                  null, true, Collections.EMPTY_SET, baseContentType );
                contentType = contentSimpleType;

}

    private void action14()throws SAXException {

                  baseType = new DelayedRef.Type(
                    $runtime, locator2, $runtime.currentSchema, baseTypeName );

}

    private void action15()throws SAXException {
        locator2 = $runtime.copyLocator();
}

    private void action16()throws SAXException {
        locator = $runtime.copyLocator();
}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 54:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 617, annotation,AnnotationContext.COMPLEXTYPE_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 52;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 76:
            {
                if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 72;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 49:
            {
                if((((((((((((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minExclusive")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxExclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("totalDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("fractionDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("length"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("enumeration"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("whiteSpace"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("pattern")))) {
                    NGCCHandler h = new facet(this, super._source, $runtime, 610);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 48;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 7:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    action7();
                    $_ngcc_current_state = 24;
                }
                else {
                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))) {
                        $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                        action4();
                        $_ngcc_current_state = 15;
                    }
                    else {
                        unexpectedEnterElement($__qname);
                    }
                }
            }
            break;
        case 61:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 626, annotation,AnnotationContext.COMPLEXTYPE_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 35;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 18:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))))))))) {
                    NGCCHandler h = new complexType_complexContent_body(this, super._source, $runtime, 571, result);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 12:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute")))))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 564, fa);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 26:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 582, annotation,AnnotationContext.COMPLEXTYPE_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 7;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 38:
            {
                action8();
                $_ngcc_current_state = 37;
                $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 44:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 68:
            {
                if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 67;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 35:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    action15();
                    $_ngcc_current_state = 59;
                }
                else {
                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))) {
                        $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                        action10();
                        $_ngcc_current_state = 44;
                    }
                    else {
                        unexpectedEnterElement($__qname);
                    }
                }
            }
            break;
        case 80:
            {
                if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 76;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 63:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 628, fa);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 88:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    action16();
                    $_ngcc_current_state = 84;
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 84:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 80;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 37:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))))) {
                    NGCCHandler h = new attributeUses(this, super._source, $runtime, 594, result);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 9:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))))))))) {
                    NGCCHandler h = new complexType_complexContent_body(this, super._source, $runtime, 560, result);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 19:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 573, annotation,AnnotationContext.COMPLEXTYPE_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 18;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 15:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 48:
            {
                if((((((((((((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minExclusive")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxExclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("totalDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("fractionDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("length"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("enumeration"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("whiteSpace"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("pattern")))) {
                    NGCCHandler h = new facet(this, super._source, $runtime, 609);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    action11();
                    $_ngcc_current_state = 47;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 47:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))))) {
                    NGCCHandler h = new attributeUses(this, super._source, $runtime, 606, result);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 24:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 28:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 584, fa);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 29:
            {
                if(($ai = $runtime.getAttributeIndex("","mixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 28;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 67:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleContent")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexContent")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute")))))))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 636, fa);
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
                    NGCCHandler h = new annotation(this, super._source, $runtime, 562, annotation,AnnotationContext.COMPLEXTYPE_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 9;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 41:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute")))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 599, fa);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 2:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleContent"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    $_ngcc_current_state = 63;
                }
                else {
                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexContent"))) {
                        $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                        $_ngcc_current_state = 29;
                    }
                    else {
                        if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))))))))) {
                            action1();
                            NGCCHandler h = new complexType_complexContent_body(this, super._source, $runtime, 557, result);
                            spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                        }
                        else {
                            unexpectedEnterElement($__qname);
                        }
                    }
                }
            }
            break;
        case 21:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute")))))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 575, fa);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 72:
            {
                if(($ai = $runtime.getAttributeIndex("","mixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 68;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 56:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || ((((((((((((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minExclusive")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxExclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxInclusive"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("totalDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("fractionDigits"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("length"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("maxLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("minLength"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("enumeration"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("whiteSpace"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("pattern"))) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute")))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 619, fa);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 39:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 597, annotation,AnnotationContext.COMPLEXTYPE_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 38;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 59:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 52:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType"))) {
                    NGCCHandler h = new simpleType(this, super._source, $runtime, 614);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 51;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 0:
            {
                revertToParentFromEnterElement(result, super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 51:
            {
                action13();
                $_ngcc_current_state = 49;
                $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 65:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 634, null,AnnotationContext.COMPLEXTYPE_DECL);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 2;
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
        case 54:
            {
                $_ngcc_current_state = 52;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 76:
            {
                if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 72;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 49:
            {
                $_ngcc_current_state = 48;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 6:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexContent"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 1;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 61:
            {
                $_ngcc_current_state = 35;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 46:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 34;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 36:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 34;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 18:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    NGCCHandler h = new complexType_complexContent_body(this, super._source, $runtime, 571, result);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 12:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 564, fa);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 26:
            {
                $_ngcc_current_state = 7;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 34:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleContent"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 1;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 38:
            {
                action8();
                $_ngcc_current_state = 37;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 44:
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
        case 68:
            {
                if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 67;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 1:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 0;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 80:
            {
                if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 76;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 37:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))) {
                    NGCCHandler h = new attributeUses(this, super._source, $runtime, 594, result);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 84:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 80;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 9:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))) {
                    NGCCHandler h = new complexType_complexContent_body(this, super._source, $runtime, 560, result);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 19:
            {
                $_ngcc_current_state = 18;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 15:
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
        case 48:
            {
                action11();
                $_ngcc_current_state = 47;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 47:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    NGCCHandler h = new attributeUses(this, super._source, $runtime, 606, result);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 8:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 6;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 24:
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
        case 29:
            {
                if(($ai = $runtime.getAttributeIndex("","mixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 28;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 67:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 636, fa);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 10:
            {
                $_ngcc_current_state = 9;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 41:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("extension"))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 599, fa);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 2:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) {
                    action1();
                    NGCCHandler h = new complexType_complexContent_body(this, super._source, $runtime, 557, result);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 21:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 575, fa);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 72:
            {
                if(($ai = $runtime.getAttributeIndex("","mixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 68;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 56:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 619, fa);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 39:
            {
                $_ngcc_current_state = 38;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 59:
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
        case 52:
            {
                $_ngcc_current_state = 51;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 17:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("restriction"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 6;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromLeaveElement(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 51:
            {
                action13();
                $_ngcc_current_state = 49;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 65:
            {
                $_ngcc_current_state = 2;
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
        case 29:
            {
                if(($__uri.equals("") && $__local.equals("mixed"))) {
                    $_ngcc_current_state = 31;
                }
                else {
                    $_ngcc_current_state = 28;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 54:
            {
                $_ngcc_current_state = 52;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 10:
            {
                $_ngcc_current_state = 9;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 76:
            {
                if(($__uri.equals("") && $__local.equals("final"))) {
                    $_ngcc_current_state = 78;
                }
                else {
                    $_ngcc_current_state = 72;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 49:
            {
                $_ngcc_current_state = 48;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 61:
            {
                $_ngcc_current_state = 35;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 72:
            {
                if(($__uri.equals("") && $__local.equals("mixed"))) {
                    $_ngcc_current_state = 74;
                }
                else {
                    $_ngcc_current_state = 68;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 39:
            {
                $_ngcc_current_state = 38;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 59:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 58;
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 26:
            {
                $_ngcc_current_state = 7;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 38:
            {
                action8();
                $_ngcc_current_state = 37;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 44:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 43;
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 68:
            {
                if(($__uri.equals("") && $__local.equals("name"))) {
                    $_ngcc_current_state = 70;
                }
                else {
                    $_ngcc_current_state = 67;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 52:
            {
                $_ngcc_current_state = 51;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromEnterAttribute(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 51:
            {
                action13();
                $_ngcc_current_state = 49;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 80:
            {
                if(($__uri.equals("") && $__local.equals("block"))) {
                    $_ngcc_current_state = 82;
                }
                else {
                    $_ngcc_current_state = 76;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 84:
            {
                if(($__uri.equals("") && $__local.equals("abstract"))) {
                    $_ngcc_current_state = 86;
                }
                else {
                    $_ngcc_current_state = 80;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 19:
            {
                $_ngcc_current_state = 18;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 15:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 14;
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 65:
            {
                $_ngcc_current_state = 2;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 48:
            {
                action11();
                $_ngcc_current_state = 47;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 24:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 23;
                }
                else {
                    unexpectedEnterAttribute($__qname);
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
        case 54:
            {
                $_ngcc_current_state = 52;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 76:
            {
                $_ngcc_current_state = 72;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 49:
            {
                $_ngcc_current_state = 48;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 30:
            {
                if(($__uri.equals("") && $__local.equals("mixed"))) {
                    $_ngcc_current_state = 28;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 73:
            {
                if(($__uri.equals("") && $__local.equals("mixed"))) {
                    $_ngcc_current_state = 68;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 61:
            {
                $_ngcc_current_state = 35;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 26:
            {
                $_ngcc_current_state = 7;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 38:
            {
                action8();
                $_ngcc_current_state = 37;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 68:
            {
                $_ngcc_current_state = 67;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 13:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 12;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 85:
            {
                if(($__uri.equals("") && $__local.equals("abstract"))) {
                    $_ngcc_current_state = 80;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 80:
            {
                $_ngcc_current_state = 76;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 84:
            {
                $_ngcc_current_state = 80;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 19:
            {
                $_ngcc_current_state = 18;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 48:
            {
                action11();
                $_ngcc_current_state = 47;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 29:
            {
                $_ngcc_current_state = 28;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 10:
            {
                $_ngcc_current_state = 9;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 77:
            {
                if(($__uri.equals("") && $__local.equals("final"))) {
                    $_ngcc_current_state = 72;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 72:
            {
                $_ngcc_current_state = 68;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 69:
            {
                if(($__uri.equals("") && $__local.equals("name"))) {
                    $_ngcc_current_state = 67;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 39:
            {
                $_ngcc_current_state = 38;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 22:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 21;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 81:
            {
                if(($__uri.equals("") && $__local.equals("block"))) {
                    $_ngcc_current_state = 76;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 42:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 41;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 52:
            {
                $_ngcc_current_state = 51;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromLeaveAttribute(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 51:
            {
                action13();
                $_ngcc_current_state = 49;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 57:
            {
                if(($__uri.equals("") && $__local.equals("base"))) {
                    $_ngcc_current_state = 56;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 65:
            {
                $_ngcc_current_state = 2;
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
        case 58:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 621);
                spawnChildFromText(h, $value);
            }
            break;
        case 54:
            {
                $_ngcc_current_state = 52;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 31:
            {
                mixedValue = $value;
                $_ngcc_current_state = 30;
            }
            break;
        case 76:
            {
                if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 72;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 49:
            {
                $_ngcc_current_state = 48;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 61:
            {
                $_ngcc_current_state = 35;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 26:
            {
                $_ngcc_current_state = 7;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 38:
            {
                action8();
                $_ngcc_current_state = 37;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 44:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 68:
            {
                if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 67;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 80:
            {
                if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 76;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 84:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 80;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 19:
            {
                $_ngcc_current_state = 18;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 15:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 86:
            {
                abstractValue = $value;
                $_ngcc_current_state = 85;
            }
            break;
        case 48:
            {
                action11();
                $_ngcc_current_state = 47;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 24:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 29:
            {
                if(($ai = $runtime.getAttributeIndex("","mixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 28;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 10:
            {
                $_ngcc_current_state = 9;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 72:
            {
                if(($ai = $runtime.getAttributeIndex("","mixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 68;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 43:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 601);
                spawnChildFromText(h, $value);
            }
            break;
        case 39:
            {
                $_ngcc_current_state = 38;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 59:
            {
                if(($ai = $runtime.getAttributeIndex("","base"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 23:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 577);
                spawnChildFromText(h, $value);
            }
            break;
        case 52:
            {
                $_ngcc_current_state = 51;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 78:
            {
                NGCCHandler h = new erSet(this, super._source, $runtime, 648);
                spawnChildFromText(h, $value);
            }
            break;
        case 70:
            {
                name = $value;
                $_ngcc_current_state = 69;
            }
            break;
        case 82:
            {
                NGCCHandler h = new erSet(this, super._source, $runtime, 653);
                spawnChildFromText(h, $value);
            }
            break;
        case 0:
            {
                revertToParentFromText(result, super._cookie, $value);
            }
            break;
        case 51:
            {
                action13();
                $_ngcc_current_state = 49;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 65:
            {
                $_ngcc_current_state = 2;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 74:
            {
                mixedValue = $value;
                $_ngcc_current_state = 73;
            }
            break;
        case 14:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 566);
                spawnChildFromText(h, $value);
            }
            break;
        }
    }

    public void onChildCompleted(Object $__result__, int $__cookie__, boolean $__needAttCheck__)throws SAXException {
        switch($__cookie__) {
        case 573:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 18;
            }
            break;
        case 636:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 65;
            }
            break;
        case 562:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 9;
            }
            break;
        case 577:
            {
                baseTypeName = ((UName)$__result__);
                action6();
                $_ngcc_current_state = 22;
            }
            break;
        case 648:
            {
                finalValue = ((Integer)$__result__);
                $_ngcc_current_state = 77;
            }
            break;
        case 614:
            {
                baseContentType = ((SimpleTypeImpl)$__result__);
                $_ngcc_current_state = 51;
            }
            break;
        case 653:
            {
                blockValue = ((Integer)$__result__);
                $_ngcc_current_state = 81;
            }
            break;
        case 566:
            {
                baseTypeName = ((UName)$__result__);
                action3();
                $_ngcc_current_state = 13;
            }
            break;
        case 621:
            {
                baseTypeName = ((UName)$__result__);
                action14();
                $_ngcc_current_state = 57;
            }
            break;
        case 617:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 52;
            }
            break;
        case 610:
            {
                facet = ((XSFacet)$__result__);
                action12();
                $_ngcc_current_state = 48;
            }
            break;
        case 626:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 35;
            }
            break;
        case 571:
            {
                explicitContent = ((ContentTypeImpl)$__result__);
                action5();
                $_ngcc_current_state = 17;
            }
            break;
        case 564:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 10;
            }
            break;
        case 582:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 7;
            }
            break;
        case 628:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 61;
            }
            break;
        case 594:
            {
                $_ngcc_current_state = 36;
            }
            break;
        case 560:
            {
                explicitContent = ((ContentTypeImpl)$__result__);
                action2();
                $_ngcc_current_state = 8;
            }
            break;
        case 606:
            {
                $_ngcc_current_state = 46;
            }
            break;
        case 609:
            {
                facet = ((XSFacet)$__result__);
                action12();
                $_ngcc_current_state = 48;
            }
            break;
        case 584:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 26;
            }
            break;
        case 599:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 39;
            }
            break;
        case 557:
            {
                explicitContent = ((ContentTypeImpl)$__result__);
                action0();
                $_ngcc_current_state = 1;
            }
            break;
        case 575:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 19;
            }
            break;
        case 601:
            {
                baseTypeName = ((UName)$__result__);
                action9();
                $_ngcc_current_state = 42;
            }
            break;
        case 619:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 54;
            }
            break;
        case 597:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 38;
            }
            break;
        case 634:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 2;
            }
            break;
        }
    }

    public boolean accepted() {
        return(($_ngcc_current_state == 0));
    }


      private ComplexTypeImpl result;
      private Ref.Type baseType;
      private Ref.ContentType contentType;

      // local variables for simpleContent/restriction
      private Ref.SimpleType baseContentType;
      private RestrictionSimpleTypeImpl contentSimpleType;

      private Locator locator,locator2;

      private static class BaseContentSimpleTypeRef implements Ref.SimpleType {
        private final Ref.Type baseType;
        private BaseContentSimpleTypeRef(Ref.Type _baseType ) { this.baseType = _baseType; }
        public XSSimpleType getType() {
          return (XSSimpleType)((XSComplexType)baseType.getType()).getContentType();
        }
      }


      // baseType and contentType must be computed before calling this method.
      private void makeResult( int derivationMethod ) {

        if(finalValue==null)
          finalValue = $runtime.finalDefault;
        if(blockValue==null)
          blockValue = $runtime.blockDefault;

        result = new ComplexTypeImpl(
            $runtime.document,
            annotation,
            locator,
            fa,
            name,
            name==null,
            $runtime.parseBoolean(abstractValue),
            derivationMethod,
            baseType,
            finalValue,
            blockValue,
            $runtime.parseBoolean(mixedValue)
        );
      }

      // I had to make them static inner classes (as opposed to anonymous ones),
      // so that they will not keep references to parser.

      private static class BaseComplexTypeContentRef implements Ref.ContentType {
        private final Ref.Type baseType;
        private BaseComplexTypeContentRef(Ref.Type _baseType) { this.baseType = _baseType; }
        public XSContentType getContentType() {
            return ((XSComplexType)baseType.getType()).getContentType();
        }
      }
      private static class InheritBaseContentTypeRef implements Ref.ContentType {
        private final Ref.Type baseType;
        private final XSContentType empty;
        private final XSContentType expContent;
        private final SchemaDocumentImpl currentDocument;
        private InheritBaseContentTypeRef(
          Ref.Type _baseType,XSContentType _explicitContent,NGCCRuntimeEx $runtime) {

          this.baseType = _baseType;
          this.currentDocument = $runtime.document;
          expContent = _explicitContent;
          this.empty = $runtime.parser.schemaSet.empty;
        }
          public XSContentType getContentType() {
              XSContentType baseContentType =
                  ((XSComplexType)baseType.getType()).getContentType();
              if(baseContentType==empty)
                  return expContent;
              else
                  return new ParticleImpl( currentDocument, null, new ModelGroupImpl(
                    currentDocument,
                      null, null, null, XSModelGroup.SEQUENCE,
                      new ParticleImpl[]{
                          (ParticleImpl)baseContentType,
                          (ParticleImpl)expContent}), null );
          }
      };

      private Ref.ContentType buildComplexExtensionContentModel( XSContentType explicitContent ) {

        if(explicitContent==$runtime.parser.schemaSet.empty)
          return new BaseComplexTypeContentRef(baseType);
        else
          return new InheritBaseContentTypeRef(baseType,explicitContent,$runtime);
      }

}
