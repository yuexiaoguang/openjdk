package com.sun.xml.internal.xsom.impl.parser.state;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import com.sun.xml.internal.xsom.impl.parser.NGCCRuntimeEx;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

    import com.sun.xml.internal.xsom.*;
    import com.sun.xml.internal.xsom.parser.*;
    import com.sun.xml.internal.xsom.impl.*;
    import com.sun.xml.internal.xsom.impl.parser.*;
    import org.xml.sax.Locator;
    import org.xml.sax.ContentHandler;
    import org.xml.sax.helpers.*;
    import java.util.*;
    import java.math.BigInteger;



public class Schema extends NGCCHandler {
    private Integer finalDefault;
    private boolean efd;
    private boolean afd;
    private Integer blockDefault;
    private ForeignAttributesImpl fa;
    private boolean includeMode;
    private AnnotationImpl anno;
    private ComplexTypeImpl ct;
    private ElementDecl e;
    private String defaultValue;
    private XSNotation notation;
    private AttGroupDeclImpl ag;
    private String fixedValue;
    private ModelGroupDeclImpl group;
    private AttributeDeclImpl ad;
    private SimpleTypeImpl st;
    private String expectedNamespace;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public Schema(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie, boolean _includeMode, String _expectedNamespace) {
        super(source, parent, cookie);
        $runtime = runtime;
        this.includeMode = _includeMode;
        this.expectedNamespace = _expectedNamespace;
        $_ngcc_current_state = 57;
    }

    public Schema(NGCCRuntimeEx runtime, boolean _includeMode, String _expectedNamespace) {
        this(null, runtime, runtime, -1, _includeMode, _expectedNamespace);
    }

    private void action0()throws SAXException {
        $runtime.checkDoubleDefError( $runtime.currentSchema.getAttGroupDecl(ag.getName()) );
            $runtime.currentSchema.addAttGroupDecl(ag,false);
}

    private void action1()throws SAXException {
        $runtime.currentSchema.addNotation(notation);
}

    private void action2()throws SAXException {
        $runtime.checkDoubleDefError( $runtime.currentSchema.getModelGroupDecl(group.getName()) );
            $runtime.currentSchema.addModelGroupDecl(group,false);
}

    private void action3()throws SAXException {
        $runtime.checkDoubleDefError( $runtime.currentSchema.getAttributeDecl(ad.getName()) );
            $runtime.currentSchema.addAttributeDecl(ad);
}

    private void action4()throws SAXException {
        locator = $runtime.copyLocator();
            defaultValue = null;
            fixedValue = null;
}

    private void action5()throws SAXException {
        $runtime.checkDoubleDefError( $runtime.currentSchema.getType(ct.getName()) );
            $runtime.currentSchema.addComplexType(ct,false);
}

    private void action6()throws SAXException {
        $runtime.checkDoubleDefError( $runtime.currentSchema.getType(st.getName()) );
            $runtime.currentSchema.addSimpleType(st,false);
}

    private void action7()throws SAXException {
        $runtime.checkDoubleDefError( $runtime.currentSchema.getElementDecl(e.getName()) );
            $runtime.currentSchema.addElementDecl(e);
}

    private void action8()throws SAXException {
        locator = $runtime.copyLocator();
}

    private void action9()throws SAXException {
        $runtime.currentSchema.setAnnotation(anno);
}

    private void action10()throws SAXException {
        $runtime.currentSchema.addForeignAttributes(fa);
}

    private void action11()throws SAXException {
        $runtime.finalDefault=this.finalDefault.intValue();
}

    private void action12()throws SAXException {
        $runtime.blockDefault=this.blockDefault.intValue();
}

    private void action13()throws SAXException {
        $runtime.elementFormDefault = efd;
}

    private void action14()throws SAXException {
        $runtime.attributeFormDefault = afd;
}

    private void action15()throws SAXException {
        Attributes test = $runtime.getCurrentAttributes();
        String tns = test.getValue("targetNamespace");

      if(!includeMode) {
        // importing
        if(tns==null) tns=""; // if not present, then the empty namespace
        $runtime.currentSchema = $runtime.parser.schemaSet.createSchema(tns,$runtime.copyLocator());
        if(expectedNamespace!=null && !expectedNamespace.equals(tns)) {
          $runtime.reportError(
            Messages.format("UnexpectedTargetnamespace.Import", tns, expectedNamespace, tns ),
            $runtime.getLocator());
        }
      } else {
        // including

        // check the consistency of @targetNamespace.
        // @targetNamespace must be null or equal to the target namespace of the schema
        if(tns!=null && expectedNamespace!=null && !expectedNamespace.equals(tns)) {
          $runtime.reportError(
            Messages.format("UnexpectedTargetnamespace.Include", tns, expectedNamespace, tns ) );
        }
        $runtime.chameleonMode = true;
      }

      // multiple inclusion test.
      if( $runtime.hasAlreadyBeenRead() ) {
          // skip this document
          $runtime.redirectSubtree(new DefaultHandler(),"","","" );
          return;
      }

      anno = (AnnotationImpl)$runtime.currentSchema.getAnnotation();
      $runtime.blockDefault = 0;
      $runtime.finalDefault = 0;
}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 49:
            {
                if(($ai = $runtime.getAttributeIndex("","attributeFormDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 45;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 36:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("notation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("include")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("redefine")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("import"))))))))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 527, null);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromEnterElement(this, super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 16:
            {
                if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 12;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 53:
            {
                if(($ai = $runtime.getAttributeIndex("","targetNamespace"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 49;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 37:
            {
                if(($ai = $runtime.getAttributeIndex("","finalDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 36;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 12:
            {
                if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 11;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 45:
            {
                if(($ai = $runtime.getAttributeIndex("","elementFormDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 41;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 41:
            {
                if(($ai = $runtime.getAttributeIndex("","blockDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 37;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 2:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 515, anno,AnnotationContext.SCHEMA);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("include"))) {
                        NGCCHandler h = new includeDecl(this, super._source, $runtime, 516);
                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                    }
                    else {
                        if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("import"))) {
                            NGCCHandler h = new importDecl(this, super._source, $runtime, 517);
                            spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                        }
                        else {
                            if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("redefine"))) {
                                NGCCHandler h = new redefine(this, super._source, $runtime, 518);
                                spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                            }
                            else {
                                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) {
                                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                                    action8();
                                    $_ngcc_current_state = 27;
                                }
                                else {
                                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType"))) {
                                        NGCCHandler h = new simpleType(this, super._source, $runtime, 520);
                                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                    }
                                    else {
                                        if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) {
                                            NGCCHandler h = new complexType(this, super._source, $runtime, 521);
                                            spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                        }
                                        else {
                                            if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))) {
                                                $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                                                action4();
                                                $_ngcc_current_state = 16;
                                            }
                                            else {
                                                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group"))) {
                                                    NGCCHandler h = new group(this, super._source, $runtime, 523);
                                                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                                }
                                                else {
                                                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("notation"))) {
                                                        NGCCHandler h = new notation(this, super._source, $runtime, 524);
                                                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                                    }
                                                    else {
                                                        if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup"))) {
                                                            NGCCHandler h = new attributeGroupDecl(this, super._source, $runtime, 525);
                                                            spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                                        }
                                                        else {
                                                            $_ngcc_current_state = 1;
                                                            $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
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
            }
            break;
        case 27:
            {
                if((($ai = $runtime.getAttributeIndex("","default"))>=0 || (($ai = $runtime.getAttributeIndex("","fixed"))>=0 || (($ai = $runtime.getAttributeIndex("","form"))>=0 || (($ai = $runtime.getAttributeIndex("","final"))>=0 || (($ai = $runtime.getAttributeIndex("","block"))>=0 || (($ai = $runtime.getAttributeIndex("","name"))>=0 || ($ai = $runtime.getAttributeIndex("","abstract"))>=0))))))) {
                    NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 57:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("schema"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    action15();
                    $_ngcc_current_state = 53;
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 11:
            {
                if((($ai = $runtime.getAttributeIndex("","name"))>=0 || ($ai = $runtime.getAttributeIndex("","form"))>=0)) {
                    NGCCHandler h = new attributeDeclBody(this, super._source, $runtime, 421, locator,false,defaultValue,fixedValue);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 1:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 504, anno,AnnotationContext.SCHEMA);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("include"))) {
                        NGCCHandler h = new includeDecl(this, super._source, $runtime, 505);
                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                    }
                    else {
                        if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("import"))) {
                            NGCCHandler h = new importDecl(this, super._source, $runtime, 506);
                            spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                        }
                        else {
                            if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("redefine"))) {
                                NGCCHandler h = new redefine(this, super._source, $runtime, 507);
                                spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                            }
                            else {
                                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) {
                                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                                    action8();
                                    $_ngcc_current_state = 27;
                                }
                                else {
                                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("simpleType"))) {
                                        NGCCHandler h = new simpleType(this, super._source, $runtime, 509);
                                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                    }
                                    else {
                                        if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("complexType"))) {
                                            NGCCHandler h = new complexType(this, super._source, $runtime, 510);
                                            spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                        }
                                        else {
                                            if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))) {
                                                $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                                                action4();
                                                $_ngcc_current_state = 16;
                                            }
                                            else {
                                                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group"))) {
                                                    NGCCHandler h = new group(this, super._source, $runtime, 512);
                                                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                                }
                                                else {
                                                    if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("notation"))) {
                                                        NGCCHandler h = new notation(this, super._source, $runtime, 513);
                                                        spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                                    }
                                                    else {
                                                        if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup"))) {
                                                            NGCCHandler h = new attributeGroupDecl(this, super._source, $runtime, 514);
                                                            spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                                                        }
                                                        else {
                                                            unexpectedEnterElement($__qname);
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
        case 49:
            {
                if(($ai = $runtime.getAttributeIndex("","attributeFormDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 45;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 36:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("schema"))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 527, null);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromLeaveElement(this, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 10:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 1;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 16:
            {
                if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 12;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 53:
            {
                if(($ai = $runtime.getAttributeIndex("","targetNamespace"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 49;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 26:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 1;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 37:
            {
                if(($ai = $runtime.getAttributeIndex("","finalDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 36;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 12:
            {
                if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 11;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 45:
            {
                if(($ai = $runtime.getAttributeIndex("","elementFormDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 41;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 41:
            {
                if(($ai = $runtime.getAttributeIndex("","blockDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    $_ngcc_current_state = 37;
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 27:
            {
                if(((($ai = $runtime.getAttributeIndex("","default"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","fixed"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","form"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","final"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","block"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || ((($ai = $runtime.getAttributeIndex("","name"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element"))) || (($ai = $runtime.getAttributeIndex("","abstract"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")))))))))) {
                    NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 11:
            {
                if(((($ai = $runtime.getAttributeIndex("","name"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))) || (($ai = $runtime.getAttributeIndex("","form"))>=0 && ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))))) {
                    NGCCHandler h = new attributeDeclBody(this, super._source, $runtime, 421, locator,false,defaultValue,fixedValue);
                    spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 1:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("schema"))) {
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
        case 49:
            {
                if(($__uri.equals("") && $__local.equals("attributeFormDefault"))) {
                    $_ngcc_current_state = 51;
                }
                else {
                    $_ngcc_current_state = 45;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 45:
            {
                if(($__uri.equals("") && $__local.equals("elementFormDefault"))) {
                    $_ngcc_current_state = 47;
                }
                else {
                    $_ngcc_current_state = 41;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 41:
            {
                if(($__uri.equals("") && $__local.equals("blockDefault"))) {
                    $_ngcc_current_state = 43;
                }
                else {
                    $_ngcc_current_state = 37;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 27:
            {
                if((($__uri.equals("") && $__local.equals("default")) || (($__uri.equals("") && $__local.equals("fixed")) || (($__uri.equals("") && $__local.equals("form")) || (($__uri.equals("") && $__local.equals("final")) || (($__uri.equals("") && $__local.equals("block")) || (($__uri.equals("") && $__local.equals("name")) || ($__uri.equals("") && $__local.equals("abstract"))))))))) {
                    NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromEnterAttribute(this, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 16:
            {
                if(($__uri.equals("") && $__local.equals("default"))) {
                    $_ngcc_current_state = 18;
                }
                else {
                    $_ngcc_current_state = 12;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 37:
            {
                if(($__uri.equals("") && $__local.equals("finalDefault"))) {
                    $_ngcc_current_state = 39;
                }
                else {
                    $_ngcc_current_state = 36;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 53:
            {
                if(($__uri.equals("") && $__local.equals("targetNamespace"))) {
                    $_ngcc_current_state = 55;
                }
                else {
                    $_ngcc_current_state = 49;
                    $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
                }
            }
            break;
        case 11:
            {
                if((($__uri.equals("") && $__local.equals("name")) || ($__uri.equals("") && $__local.equals("form")))) {
                    NGCCHandler h = new attributeDeclBody(this, super._source, $runtime, 421, locator,false,defaultValue,fixedValue);
                    spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        case 12:
            {
                if(($__uri.equals("") && $__local.equals("fixed"))) {
                    $_ngcc_current_state = 14;
                }
                else {
                    $_ngcc_current_state = 11;
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
        case 49:
            {
                $_ngcc_current_state = 45;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 38:
            {
                if(($__uri.equals("") && $__local.equals("finalDefault"))) {
                    $_ngcc_current_state = 36;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 0:
            {
                revertToParentFromLeaveAttribute(this, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 16:
            {
                $_ngcc_current_state = 12;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 13:
            {
                if(($__uri.equals("") && $__local.equals("fixed"))) {
                    $_ngcc_current_state = 11;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 53:
            {
                $_ngcc_current_state = 49;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 37:
            {
                $_ngcc_current_state = 36;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 17:
            {
                if(($__uri.equals("") && $__local.equals("default"))) {
                    $_ngcc_current_state = 12;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 12:
            {
                $_ngcc_current_state = 11;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 50:
            {
                if(($__uri.equals("") && $__local.equals("attributeFormDefault"))) {
                    $_ngcc_current_state = 45;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 42:
            {
                if(($__uri.equals("") && $__local.equals("blockDefault"))) {
                    $_ngcc_current_state = 37;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 45:
            {
                $_ngcc_current_state = 41;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 41:
            {
                $_ngcc_current_state = 37;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 54:
            {
                if(($__uri.equals("") && $__local.equals("targetNamespace"))) {
                    $_ngcc_current_state = 49;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 46:
            {
                if(($__uri.equals("") && $__local.equals("elementFormDefault"))) {
                    $_ngcc_current_state = 41;
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
        case 49:
            {
                if(($ai = $runtime.getAttributeIndex("","attributeFormDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 45;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 0:
            {
                revertToParentFromText(this, super._cookie, $value);
            }
            break;
        case 47:
            {
                if($value.equals("unqualified")) {
                    NGCCHandler h = new qualification(this, super._source, $runtime, 539);
                    spawnChildFromText(h, $value);
                }
                else {
                    if($value.equals("qualified")) {
                        NGCCHandler h = new qualification(this, super._source, $runtime, 539);
                        spawnChildFromText(h, $value);
                    }
                }
            }
            break;
        case 43:
            {
                NGCCHandler h = new ersSet(this, super._source, $runtime, 534);
                spawnChildFromText(h, $value);
            }
            break;
        case 16:
            {
                if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 12;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 53:
            {
                if(($ai = $runtime.getAttributeIndex("","targetNamespace"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 49;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 37:
            {
                if(($ai = $runtime.getAttributeIndex("","finalDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 36;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 12:
            {
                if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 11;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 14:
            {
                fixedValue = $value;
                $_ngcc_current_state = 13;
            }
            break;
        case 45:
            {
                if(($ai = $runtime.getAttributeIndex("","elementFormDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 41;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 41:
            {
                if(($ai = $runtime.getAttributeIndex("","blockDefault"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
                else {
                    $_ngcc_current_state = 37;
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        case 55:
            {
                $_ngcc_current_state = 54;
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 27:
            {
                if(($ai = $runtime.getAttributeIndex("","abstract"))>=0) {
                    NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                    spawnChildFromText(h, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                        NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                        spawnChildFromText(h, $value);
                    }
                    else {
                        if(($ai = $runtime.getAttributeIndex("","block"))>=0) {
                            NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                            spawnChildFromText(h, $value);
                        }
                        else {
                            if(($ai = $runtime.getAttributeIndex("","final"))>=0) {
                                NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                                spawnChildFromText(h, $value);
                            }
                            else {
                                if(($ai = $runtime.getAttributeIndex("","form"))>=0) {
                                    NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                                    spawnChildFromText(h, $value);
                                }
                                else {
                                    if(($ai = $runtime.getAttributeIndex("","fixed"))>=0) {
                                        NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
                                        spawnChildFromText(h, $value);
                                    }
                                    else {
                                        if(($ai = $runtime.getAttributeIndex("","default"))>=0) {
                                            NGCCHandler h = new elementDeclBody(this, super._source, $runtime, 439, locator,true);
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
        case 39:
            {
                NGCCHandler h = new erSet(this, super._source, $runtime, 529);
                spawnChildFromText(h, $value);
            }
            break;
        case 51:
            {
                if($value.equals("unqualified")) {
                    NGCCHandler h = new qualification(this, super._source, $runtime, 544);
                    spawnChildFromText(h, $value);
                }
                else {
                    if($value.equals("qualified")) {
                        NGCCHandler h = new qualification(this, super._source, $runtime, 544);
                        spawnChildFromText(h, $value);
                    }
                }
            }
            break;
        case 18:
            {
                defaultValue = $value;
                $_ngcc_current_state = 17;
            }
            break;
        case 11:
            {
                if(($ai = $runtime.getAttributeIndex("","form"))>=0) {
                    NGCCHandler h = new attributeDeclBody(this, super._source, $runtime, 421, locator,false,defaultValue,fixedValue);
                    spawnChildFromText(h, $value);
                }
                else {
                    if(($ai = $runtime.getAttributeIndex("","name"))>=0) {
                        NGCCHandler h = new attributeDeclBody(this, super._source, $runtime, 421, locator,false,defaultValue,fixedValue);
                        spawnChildFromText(h, $value);
                    }
                }
            }
            break;
        }
    }

    public void onChildCompleted(Object $__result__, int $__cookie__, boolean $__needAttCheck__)throws SAXException {
        switch($__cookie__) {
        case 527:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                action10();
                $_ngcc_current_state = 2;
            }
            break;
        case 534:
            {
                blockDefault = ((Integer)$__result__);
                action12();
                $_ngcc_current_state = 42;
            }
            break;
        case 439:
            {
                e = ((ElementDecl)$__result__);
                action7();
                $_ngcc_current_state = 26;
            }
            break;
        case 544:
            {
                afd = ((Boolean)$__result__).booleanValue();
                action14();
                $_ngcc_current_state = 50;
            }
            break;
        case 421:
            {
                ad = ((AttributeDeclImpl)$__result__);
                action3();
                $_ngcc_current_state = 10;
            }
            break;
        case 504:
            {
                anno = ((AnnotationImpl)$__result__);
                action9();
                $_ngcc_current_state = 1;
            }
            break;
        case 505:
            {
                $_ngcc_current_state = 1;
            }
            break;
        case 506:
            {
                $_ngcc_current_state = 1;
            }
            break;
        case 507:
            {
                $_ngcc_current_state = 1;
            }
            break;
        case 509:
            {
                st = ((SimpleTypeImpl)$__result__);
                action6();
                $_ngcc_current_state = 1;
            }
            break;
        case 510:
            {
                ct = ((ComplexTypeImpl)$__result__);
                action5();
                $_ngcc_current_state = 1;
            }
            break;
        case 512:
            {
                group = ((ModelGroupDeclImpl)$__result__);
                action2();
                $_ngcc_current_state = 1;
            }
            break;
        case 513:
            {
                notation = ((XSNotation)$__result__);
                action1();
                $_ngcc_current_state = 1;
            }
            break;
        case 514:
            {
                ag = ((AttGroupDeclImpl)$__result__);
                action0();
                $_ngcc_current_state = 1;
            }
            break;
        case 539:
            {
                efd = ((Boolean)$__result__).booleanValue();
                action13();
                $_ngcc_current_state = 46;
            }
            break;
        case 515:
            {
                anno = ((AnnotationImpl)$__result__);
                action9();
                $_ngcc_current_state = 1;
            }
            break;
        case 516:
            {
                $_ngcc_current_state = 1;
            }
            break;
        case 517:
            {
                $_ngcc_current_state = 1;
            }
            break;
        case 518:
            {
                $_ngcc_current_state = 1;
            }
            break;
        case 520:
            {
                st = ((SimpleTypeImpl)$__result__);
                action6();
                $_ngcc_current_state = 1;
            }
            break;
        case 521:
            {
                ct = ((ComplexTypeImpl)$__result__);
                action5();
                $_ngcc_current_state = 1;
            }
            break;
        case 523:
            {
                group = ((ModelGroupDeclImpl)$__result__);
                action2();
                $_ngcc_current_state = 1;
            }
            break;
        case 524:
            {
                notation = ((XSNotation)$__result__);
                action1();
                $_ngcc_current_state = 1;
            }
            break;
        case 525:
            {
                ag = ((AttGroupDeclImpl)$__result__);
                action0();
                $_ngcc_current_state = 1;
            }
            break;
        case 529:
            {
                finalDefault = ((Integer)$__result__);
                action11();
                $_ngcc_current_state = 38;
            }
            break;
        }
    }

    public boolean accepted() {
        return(($_ngcc_current_state == 0));
    }


      private String tns=null;  // it defaults to the no namespace.
      private Locator locator;

}
