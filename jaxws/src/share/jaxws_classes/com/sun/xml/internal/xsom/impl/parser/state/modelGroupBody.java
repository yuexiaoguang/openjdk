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

import java.util.Vector;


class modelGroupBody extends NGCCHandler {
    private AnnotationImpl annotation;
    private String compositorName;
    private Locator locator;
    private ParticleImpl childParticle;
    private ForeignAttributesImpl fa;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public modelGroupBody(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie, Locator _locator, String _compositorName) {
        super(source, parent, cookie);
        $runtime = runtime;
        this.locator = _locator;
        this.compositorName = _compositorName;
        $_ngcc_current_state = 6;
    }

    public modelGroupBody(NGCCRuntimeEx runtime, Locator _locator, String _compositorName) {
        this(null, runtime, runtime, -1, _locator, _compositorName);
    }

    private void action0()throws SAXException {

      XSModelGroup.Compositor compositor = null;
      if(compositorName.equals("all"))      compositor = XSModelGroup.ALL;
      if(compositorName.equals("sequence")) compositor = XSModelGroup.SEQUENCE;
      if(compositorName.equals("choice"))   compositor = XSModelGroup.CHOICE;
      if(compositor==null)
        throw new InternalError("unable to process "+compositorName);

      result = new ModelGroupImpl( $runtime.document, annotation, locator, fa, compositor,
            (ParticleImpl[])particles.toArray(new ParticleImpl[0]));

}

    private void action1()throws SAXException {

        particles.add(childParticle);

}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 0:
            {
                revertToParentFromEnterElement(result, super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 4:
            {
                if(($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation"))) {
                    NGCCHandler h = new annotation(this, super._source, $runtime, 174, null,AnnotationContext.MODELGROUP);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 2;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 2:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")))))) {
                    NGCCHandler h = new particle(this, super._source, $runtime, 171);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 1;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 6:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("annotation")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any"))))))) {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 176, null);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 176, null);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 1:
            {
                if((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("group")) || (($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("element")) || (((($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("all")) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("choice"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("sequence"))) || ($__uri.equals("http://www.w3.org/2001/XMLSchema") && $__local.equals("any")))))) {
                    NGCCHandler h = new particle(this, super._source, $runtime, 170);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    action0();
                    $_ngcc_current_state = 0;
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
        case 0:
            {
                revertToParentFromLeaveElement(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 4:
            {
                $_ngcc_current_state = 2;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 6:
            {
                NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 176, null);
                spawnChildFromLeaveElement(h, $__uri, $__local, $__qname);
            }
            break;
        case 1:
            {
                action0();
                $_ngcc_current_state = 0;
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
        case 0:
            {
                revertToParentFromEnterAttribute(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 4:
            {
                $_ngcc_current_state = 2;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 6:
            {
                NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 176, null);
                spawnChildFromEnterAttribute(h, $__uri, $__local, $__qname);
            }
            break;
        case 1:
            {
                action0();
                $_ngcc_current_state = 0;
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
        case 0:
            {
                revertToParentFromLeaveAttribute(result, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 4:
            {
                $_ngcc_current_state = 2;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 6:
            {
                NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 176, null);
                spawnChildFromLeaveAttribute(h, $__uri, $__local, $__qname);
            }
            break;
        case 1:
            {
                action0();
                $_ngcc_current_state = 0;
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
        case 0:
            {
                revertToParentFromText(result, super._cookie, $value);
            }
            break;
        case 4:
            {
                $_ngcc_current_state = 2;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 6:
            {
                NGCCHandler h = new foreignAttributes(this, super._source, $runtime, 176, null);
                spawnChildFromText(h, $value);
            }
            break;
        case 1:
            {
                action0();
                $_ngcc_current_state = 0;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        }
    }

    public void onChildCompleted(Object $__result__, int $__cookie__, boolean $__needAttCheck__)throws SAXException {
        switch($__cookie__) {
        case 174:
            {
                annotation = ((AnnotationImpl)$__result__);
                $_ngcc_current_state = 2;
            }
            break;
        case 176:
            {
                fa = ((ForeignAttributesImpl)$__result__);
                $_ngcc_current_state = 4;
            }
            break;
        case 171:
            {
                childParticle = ((ParticleImpl)$__result__);
                action1();
                $_ngcc_current_state = 1;
            }
            break;
        case 170:
            {
                childParticle = ((ParticleImpl)$__result__);
                action1();
                $_ngcc_current_state = 1;
            }
            break;
        }
    }

    public boolean accepted() {
        return((($_ngcc_current_state == 1) || (($_ngcc_current_state == 2) || (($_ngcc_current_state == 4) || ($_ngcc_current_state == 0)))));
    }


                private ModelGroupImpl result;

                private final List particles = new ArrayList();

}
