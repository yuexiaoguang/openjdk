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



class complexType_complexContent_body extends NGCCHandler {
    private AttributesHolder owner;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public complexType_complexContent_body(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie, AttributesHolder _owner) {
        super(source, parent, cookie);
        $runtime = runtime;
        this.owner = _owner;
        $_ngcc_current_state = 2;
    }

    public complexType_complexContent_body(NGCCRuntimeEx runtime, AttributesHolder _owner) {
        this(null, runtime, runtime, -1, _owner);
    }

    private void action0()throws SAXException {
        if(particle==null)
          particle = $runtime.parser.schemaSet.empty;
}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 1:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attributeGroup")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("anyAttribute")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("attribute"))))) {
                    NGCCHandler h = new attributeUses(this, super._source, $runtime, 1, owner);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    NGCCHandler h = new attributeUses(this, super._source, $runtime, 1, owner);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 0:
            {
                revertToParentFromEnterElement(particle, super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 2:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")))))) {
                    NGCCHandler h = new particle(this, super._source, $runtime, 3);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 1;
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
        case 1:
            {
                NGCCHandler h = new attributeUses(this, super._source, $runtime, 1, owner);
                spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromLeaveElement(particle, super._cookie, $__uri, $__local, $__qname);
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
        case 1:
            {
                NGCCHandler h = new attributeUses(this, super._source, $runtime, 1, owner);
                spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromEnterAttribute(particle, super._cookie, $__uri, $__local, $__qname);
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
        case 1:
            {
                NGCCHandler h = new attributeUses(this, super._source, $runtime, 1, owner);
                spawnChildFromLeaveAttribute(h, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromLeaveAttribute(particle, super._cookie, $__uri, $__local, $__qname);
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
        case 1:
            {
                NGCCHandler h = new attributeUses(this, super._source, $runtime, 1, owner);
                spawnChildFromText(h, $value);
            }
            break;
        case 0:
            {
                revertToParentFromText(particle, super._cookie, $value);
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
        case 1:
            {
                action0();
                $_ngcc_current_state = 0;
            }
            break;
        case 3:
            {
                particle = ((ParticleImpl)$__result__);
                $_ngcc_current_state = 1;
            }
            break;
        }
    }

    public boolean accepted() {
        return(($_ngcc_current_state == 0));
    }


      private ContentTypeImpl particle;

}
