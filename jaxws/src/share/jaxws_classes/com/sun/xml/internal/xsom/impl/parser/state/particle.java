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



class particle extends NGCCHandler {
    private AnnotationImpl annotation;
    private ElementDecl anonymousElementDecl;
    private WildcardImpl wcBody;
    private ModelGroupImpl term;
    private UName elementTypeName;
    private occurs occurs;
    private UName groupName;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public particle(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie) {
        super(source, parent, cookie);
        $runtime = runtime;
        $_ngcc_current_state = 1;
    }

    public particle(NGCCRuntimeEx runtime) {
        this(null, runtime, runtime, -1);
    }

    private void action0()throws SAXException {

          result = new ParticleImpl( $runtime.document, null, wcBody, wloc, occurs.max, occurs.min );

}

    private void action1()throws SAXException {
        wloc = $runtime.copyLocator();
}

    private void action2()throws SAXException {

              result = new ParticleImpl( $runtime.document, null,
                anonymousElementDecl,
                loc, occurs.max, occurs.min );

}

    private void action3()throws SAXException {

              result = new ParticleImpl( $runtime.document, annotation, new DelayedRef.Element(
                  $runtime, loc, $runtime.currentSchema, elementTypeName ),
                  loc, occurs.max, occurs.min );

}

    private void action4()throws SAXException {
        loc = $runtime.copyLocator();
}

    private void action5()throws SAXException {

          result = new ParticleImpl( $runtime.document, annotation, new DelayedRef.ModelGroup(
              $runtime, loc, $runtime.currentSchema, groupName ),
            loc, occurs.max, occurs.min );

}

    private void action6()throws SAXException {
        loc = $runtime.copyLocator();
}

    private void action7()throws SAXException {

            result = new ParticleImpl( $runtime.document, null, term, loc, occurs.max, occurs.min );

}

    private void action8()throws SAXException {

          compositorName = $localName;
          loc = $runtime.copyLocator();

}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 29:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))))))) {
                    NGCCHandler h = new modelGroupBody(this, super._source, $runtime, 136, loc,compositorName);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 4:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || ((($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) || ((($ai = $runtime.getAttributeIndex("","minOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) || ((($ai = $runtime.getAttributeIndex("","namespace"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) || (($ai = $runtime.getAttributeIndex("","processContents"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 107);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 26:
            {
                if(((($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) || ((($ai = $runtime.getAttributeIndex("","ref"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) || (($ai = $runtime.getAttributeIndex("","minOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 132);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 1:
            {
                if(((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    action8();
                    $_ngcc_current_state = 30;
                }
                else {
                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group"))) {
                        $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                        action6();
                        $_ngcc_current_state = 26;
                    }
                    else {
                        if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) {
                            $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                            action4();
                            $_ngcc_current_state = 16;
                        }
                        else {
                            if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))) {
                                $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                                action1();
                                $_ngcc_current_state = 4;
                            }
                            else {
                                unexpectedEnterElement($__qname);
                            }
                        }
                    }
                }
            }
            break;
        case 0:
            {
                revertToParentFromEnterElement(result, super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 20:
            {
                action5();
                $_ngcc_current_state = 19;
                $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 8:
            {
                if(($ai = $runtime.getAttributeIndex("","ref"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    if((($ai = $runtime.getAttributeIndex("","default"))>=0 || (($ai = $runtime.getAttributeIndex("","fixed"))>=0 || (($ai = $runtime.getAttributeIndex("","form"))>=0 || (($ai = $runtime.getAttributeIndex("","final"))>=0 || (($ai = $runtime.getAttributeIndex("","block"))>=0 || (($ai = $runtime.getAttributeIndex("","name"))>=0 || ($ai = $runtime.getAttributeIndex("","abstract"))>=0))))))) {
                        NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                    }
                    else {
                        unexpectedEnterElement($__qname);
                    }
                }
            }
            break;
        case 11:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 115, null,AnnotationContext.PARTICLE);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 10;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 25:
            {
                if(($ai = $runtime.getAttributeIndex("","ref"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 10:
            {
                action3();
                $_ngcc_current_state = 7;
                $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 3:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($ai = $runtime.getAttributeIndex("","namespace"))>=0 || ($ai = $runtime.getAttributeIndex("","processContents"))>=0))) {
                    NGCCHandler h = new wildcardBody(this, super._source, $runtime, 106, wloc);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 16:
            {
                if(((($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","default"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","fixed"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","form"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","final"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","block"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","ref"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","minOccurs"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || ((($ai = $runtime.getAttributeIndex("","name"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || (($ai = $runtime.getAttributeIndex("","abstract"))>=0 && (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("key")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("keyref"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("unique")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))))))))))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 21:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 127, null,AnnotationContext.PARTICLE);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 20;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 30:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || ((($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0 && ((((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || ((($ai = $runtime.getAttributeIndex("","minOccurs"))>=0 && ((((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))))))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 137);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
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
        case 29:
            {
                if(((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))) {
                    NGCCHandler h = new modelGroupBody(this, super._source, $runtime, 136, loc,compositorName);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 4:
            {
                if(((($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))) || ((($ai = $runtime.getAttributeIndex("","minOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))) || ((($ai = $runtime.getAttributeIndex("","namespace"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))) || ((($ai = $runtime.getAttributeIndex("","processContents"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 107);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 26:
            {
                if(((($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group"))) || ((($ai = $runtime.getAttributeIndex("","ref"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group"))) || (($ai = $runtime.getAttributeIndex("","minOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 132);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 2:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 0;
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
        case 8:
            {
                if(($ai = $runtime.getAttributeIndex("","ref"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    if(((($ai = $runtime.getAttributeIndex("","default"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","fixed"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","form"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","final"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","block"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","name"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || (($ai = $runtime.getAttributeIndex("","abstract"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")))))))))) {
                        NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                        spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                    }
                    else {
                        unexpectedLeaveElement($__qname);
                    }
                }
            }
            break;
        case 20:
            {
                action5();
                $_ngcc_current_state = 19;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 11:
            {
                $_ngcc_current_state = 10;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 25:
            {
                if(($ai = $runtime.getAttributeIndex("","ref"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 19:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 0;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 10:
            {
                action3();
                $_ngcc_current_state = 7;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 7:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 0;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 3:
            {
                if(((($ai = $runtime.getAttributeIndex("","namespace"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))) || ((($ai = $runtime.getAttributeIndex("","processContents"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))))) {
                    NGCCHandler h = new wildcardBody(this, super._source, $runtime, 106, wloc);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 16:
            {
                if(((($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","default"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","fixed"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","form"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","final"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","block"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","ref"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","minOccurs"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","name"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || (($ai = $runtime.getAttributeIndex("","abstract"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))))))))))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 21:
            {
                $_ngcc_current_state = 20;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 30:
            {
                if(((($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0 && ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))) || ((($ai = $runtime.getAttributeIndex("","minOccurs"))>=0 && ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))) || ((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 137);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 28:
            {
                if(((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence")))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 0;
                }
                else {
                    unexpectedLeaveElement($__qname);
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
        case 0:
            {
                revertToParentFromEnterAttribute(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 8:
            {
                if(($__uri.equals("") && $__local.equals("ref"))) {
                    $_ngcc_current_state = 14;
                }
                else {
                    if((($__uri.equals("") && $__local.equals("default")) || (($__uri.equals("") && $__local.equals("fixed")) || (($__uri.equals("") && $__local.equals("form")) || (($__uri.equals("") && $__local.equals("final")) || (($__uri.equals("") && $__local.equals("block")) || (($__uri.equals("") && $__local.equals("name")) || ($__uri.equals("") && $__local.equals("abstract"))))))))) {
                        NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                        spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                    }
                    else {
                        unexpectedEnterAttribute($__qname);
                    }
                }
            }
            break;
        case 20:
            {
                action5();
                $_ngcc_current_state = 19;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 11:
            {
                $_ngcc_current_state = 10;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 25:
            {
                if(($__uri.equals("") && $__local.equals("ref"))) {
                    $_ngcc_current_state = 24;
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 4:
            {
                if((($__uri.equals("") && $__local.equals("maxOccurs")) || (($__uri.equals("") && $__local.equals("minOccurs")) || (($__uri.equals("") && $__local.equals("namespace")) || ($__uri.equals("") && $__local.equals("processContents")))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 107);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 10:
            {
                action3();
                $_ngcc_current_state = 7;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 26:
            {
                if((($__uri.equals("") && $__local.equals("maxOccurs")) || (($__uri.equals("") && $__local.equals("ref")) || ($__uri.equals("") && $__local.equals("minOccurs"))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 132);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 3:
            {
                if((($__uri.equals("") && $__local.equals("namespace")) || ($__uri.equals("") && $__local.equals("processContents")))) {
                    NGCCHandler h = new wildcardBody(this, super._source, $runtime, 106, wloc);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 16:
            {
                if((($__uri.equals("") && $__local.equals("maxOccurs")) || (($__uri.equals("") && $__local.equals("default")) || (($__uri.equals("") && $__local.equals("fixed")) || (($__uri.equals("") && $__local.equals("form")) || (($__uri.equals("") && $__local.equals("final")) || (($__uri.equals("") && $__local.equals("block")) || (($__uri.equals("") && $__local.equals("ref")) || (($__uri.equals("") && $__local.equals("minOccurs")) || (($__uri.equals("") && $__local.equals("name")) || ($__uri.equals("") && $__local.equals("abstract")))))))))))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 21:
            {
                $_ngcc_current_state = 20;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 30:
            {
                if((($__uri.equals("") && $__local.equals("maxOccurs")) || ($__uri.equals("") && $__local.equals("minOccurs")))) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 137);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
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
        case 23:
            {
                if(($__uri.equals("") && $__local.equals("ref"))) {
                    $_ngcc_current_state = 21;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromLeaveAttribute(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 20:
            {
                action5();
                $_ngcc_current_state = 19;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 11:
            {
                $_ngcc_current_state = 10;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 10:
            {
                action3();
                $_ngcc_current_state = 7;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 21:
            {
                $_ngcc_current_state = 20;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 13:
            {
                if(($__uri.equals("") && $__local.equals("ref"))) {
                    $_ngcc_current_state = 11;
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
        case 4:
            {
                if(($ai = $runtime.getAttributeIndex("","processContents"))>=0) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 107);
                    spawnChildFromText(h, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","namespace"))>=0) {
                        NGCCHandler h = new occurs(this, super._source, $runtime, 107);
                        spawnChildFromText(h, $value);
                    }
                    else {
                        if(($ai = $runtime.getAttributeIndex("","minOccurs"))>=0) {
                            NGCCHandler h = new occurs(this, super._source, $runtime, 107);
                            spawnChildFromText(h, $value);
                        }
                        else {
                            if(($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0) {
                                NGCCHandler h = new occurs(this, super._source, $runtime, 107);
                                spawnChildFromText(h, $value);
                            }
                        }
                    }
                }
            }
            break;
        case 26:
            {
                if(($ai = $runtime.getAttributeIndex("","minOccurs"))>=0) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 132);
                    spawnChildFromText(h, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","ref"))>=0) {
                        NGCCHandler h = new occurs(this, super._source, $runtime, 132);
                        spawnChildFromText(h, $value);
                    }
                    else {
                        if(($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0) {
                            NGCCHandler h = new occurs(this, super._source, $runtime, 132);
                            spawnChildFromText(h, $value);
                        }
                    }
                }
            }
            break;
        case 14:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 118);
                spawnChildFromText(h, $value);
            }
            break;
        case 24:
            {
                NGCCHandler h = new qname(this, super._source, $runtime, 130);
                spawnChildFromText(h, $value);
            }
            break;
        case 0:
            {
                revertToParentFromText(result, super._cookie, $value);
            }
            break;
        case 20:
            {
                action5();
                $_ngcc_current_state = 19;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 8:
            {
                if(($ai = $runtime.getAttributeIndex("","ref"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                        NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                        spawnChildFromText(h, $value);
                    }
                    else {
                        if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                            NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                            spawnChildFromText(h, $value);
                        }
                        else {
                            if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                                NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                                spawnChildFromText(h, $value);
                            }
                            else {
                                if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                                    NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                                    spawnChildFromText(h, $value);
                                }
                                else {
                                    if(($ai = $runtime.getAttributeIndex("","form"))>=0) {
                                        NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                                        spawnChildFromText(h, $value);
                                    }
                                    else {
                                        if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                                            NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                                            spawnChildFromText(h, $value);
                                        }
                                        else {
                                            if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                                                NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 112, loc,false);
                                                spawnChildFromText(h, $value);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            break;
        case 11:
            {
                $_ngcc_current_state = 10;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 25:
            {
                if(($ai = $runtime.getAttributeIndex("","ref"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 10:
            {
                action3();
                $_ngcc_current_state = 7;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 3:
            {
                if(($ai = $runtime.getAttributeIndex("","processContents"))>=0) {
                    NGCCHandler h = new wildcardBody(this, super._source, $runtime, 106, wloc);
                    spawnChildFromText(h, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","namespace"))>=0) {
                        NGCCHandler h = new wildcardBody(this, super._source, $runtime, 106, wloc);
                        spawnChildFromText(h, $value);
                    }
                }
            }
            break;
        case 16:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                    spawnChildFromText(h, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                        NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                        spawnChildFromText(h, $value);
                    }
                    else {
                        if(($ai = $runtime.getAttributeIndex("","minOccurs"))>=0) {
                            NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                            spawnChildFromText(h, $value);
                        }
                        else {
                            if(($ai = $runtime.getAttributeIndex("","ref"))>=0) {
                                NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                                spawnChildFromText(h, $value);
                            }
                            else {
                                if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                                    NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                                    spawnChildFromText(h, $value);
                                }
                                else {
                                    if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                                        NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                                        spawnChildFromText(h, $value);
                                    }
                                    else {
                                        if(($ai = $runtime.getAttributeIndex("","form"))>=0) {
                                            NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                                            spawnChildFromText(h, $value);
                                        }
                                        else {
                                            if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                                                NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                                                spawnChildFromText(h, $value);
                                            }
                                            else {
                                                if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                                                    NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                                                    spawnChildFromText(h, $value);
                                                }
                                                else {
                                                    if(($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0) {
                                                        NGCCHandler h = new occurs(this, super._source, $runtime, 121);
                                                        spawnChildFromText(h, $value);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            break;
        case 21:
            {
                $_ngcc_current_state = 20;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 30:
            {
                if(($ai = $runtime.getAttributeIndex("","minOccurs"))>=0) {
                    NGCCHandler h = new occurs(this, super._source, $runtime, 137);
                    spawnChildFromText(h, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","maxOccurs"))>=0) {
                        NGCCHandler h = new occurs(this, super._source, $runtime, 137);
                        spawnChildFromText(h, $value);
                    }
                }
            }
            break;
        }
    }

    public void onChildCompleted(Object $__result__, int $__cookie__, boolean $__needAttCheck__)throws SAXException {
        switch($__cookie__) {
        case 136:
            {
                term = ((ModelGroupImpl)$__result__);
                action7();
                $_ngcc_current_state = 28;
            }
            break;
        case 107:
            {
                occurs = ((occurs)$__result__);
                $_ngcc_current_state = 3;
            }
            break;
        case 106:
            {
                wcBody = ((WildcardImpl)$__result__);
                action0();
                $_ngcc_current_state = 2;
            }
            break;
        case 121:
            {
                occurs = ((occurs)$__result__);
                $_ngcc_current_state = 8;
            }
            break;
        case 127:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 20;
            }
            break;
        case 137:
            {
                occurs = ((occurs)$__result__);
                $_ngcc_current_state = 29;
            }
            break;
        case 132:
            {
                occurs = ((occurs)$__result__);
                $_ngcc_current_state = 25;
            }
            break;
        case 118:
            {
                elementTypeName = ((UName)$__result__);
                $_ngcc_current_state = 13;
            }
            break;
        case 130:
            {
                groupName = ((UName)$__result__);
                $_ngcc_current_state = 23;
            }
            break;
        case 112:
            {
                anonymousElementDecl = ((ElementDecl)$__result__);
                action2();
                $_ngcc_current_state = 7;
            }
            break;
        case 115:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 10;
            }
            break;
        }
    }

    public boolean accepted() {
        return(($_ngcc_current_state == 0));
    }


        private Locator wloc;
        private Locator loc;
      private ParticleImpl result;
      private String compositorName;

}
