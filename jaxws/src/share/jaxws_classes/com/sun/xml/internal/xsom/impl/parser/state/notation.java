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



class notation extends NGCCHandler {
    private String name;
    private String pub;
    private ForeignAttributesImpl fa;
    private String sys;
    private AnnotationImpl ann;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public notation(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie) {
        super(source, parent, cookie);
        $runtime = runtime;
        $_ngcc_current_state = 16;
    }

    public notation(NGCCRuntimeEx runtime) {
        this(null, runtime, runtime, -1);
    }

    private void action0()throws SAXException {
        loc = $runtime.copyLocator();
}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 2:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 209, null,AnnotationContext.NOTATION);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 1;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 16:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("notation"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    action0();
                    $_ngcc_current_state = 15;
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 14:
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
        case 15:
            {
                if((($ai = $runtime.getAttributeIndex("","name"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 224, null);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 4:
            {
                if(($ai = $runtime.getAttributeIndex("","system"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 2;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 8:
            {
                if(($ai = $runtime.getAttributeIndex("","public"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 4;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 0:
            {
                revertToParentFromEnterElement(makeResult(), super._cookie, $__uri, $__local, $__qname, $attrs);
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
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 14:
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
        case 15:
            {
                if((($ai = $runtime.getAttributeIndex("","name"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("notation")))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 224, null);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 4:
            {
                if(($ai = $runtime.getAttributeIndex("","system"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 2;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 1:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("notation"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 0;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 8:
            {
                if(($ai = $runtime.getAttributeIndex("","public"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 4;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromLeaveElement(makeResult(), super._cookie, $__uri, $__local, $__qname);
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
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 14:
            {
                if(($__uri.equals("") && $__local.equals("name"))) {
                    $_ngcc_current_state = 13;
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 15:
            {
                if(($__uri.equals("") && $__local.equals("name"))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 224, null);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 4:
            {
                if(($__uri.equals("") && $__local.equals("system"))) {
                    $_ngcc_current_state = 6;
                }
                else {
                    $_ngcc_current_state = 2;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 8:
            {
                if(($__uri.equals("") && $__local.equals("public"))) {
                    $_ngcc_current_state = 10;
                }
                else {
                    $_ngcc_current_state = 4;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromEnterAttribute(makeResult(), super._cookie, $__uri, $__local, $__qname);
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
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 9:
            {
                if(($__uri.equals("") && $__local.equals("public"))) {
                    $_ngcc_current_state = 4;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 12:
            {
                if(($__uri.equals("") && $__local.equals("name"))) {
                    $_ngcc_current_state = 8;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 5:
            {
                if(($__uri.equals("") && $__local.equals("system"))) {
                    $_ngcc_current_state = 2;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 4:
            {
                $_ngcc_current_state = 2;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 8:
            {
                $_ngcc_current_state = 4;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromLeaveAttribute(makeResult(), super._cookie, $__uri, $__local, $__qname);
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
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 10:
            {
                pub = $value;
                $_ngcc_current_state = 9;
            }
            break;
        case 14:
            {
                if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 15:
            {
                if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 224, null);
                    spawnChildFromText(h, $value);
                }
            }
            break;
        case 4:
            {
                if(($ai = $runtime.getAttributeIndex("","system"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 2;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 8:
            {
                if(($ai = $runtime.getAttributeIndex("","public"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 4;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 13:
            {
                name = $value;
                $_ngcc_current_state = 12;
            }
            break;
        case 6:
            {
                sys = $value;
                $_ngcc_current_state = 5;
            }
            break;
        case 0:
            {
                revertToParentFromText(makeResult(), super._cookie, $value);
            }
            break;
        }
    }

    public void onChildCompleted(Object $__result__, int $__cookie__, boolean $__needAttCheck__)throws SAXException {
        switch($__cookie__) {
        case 209:
            {
                ann = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 1;
            }
            break;
        case 224:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 14;
            }
            break;
        }
    }

    public boolean accepted() {
        return(($_ngcc_current_state == 0));
    }


      private Locator loc;
      private XSNotation makeResult() {
        return new NotationImpl( $runtime.document,ann,loc,fa,name,pub,sys);
      }

}
