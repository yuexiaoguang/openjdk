package com.sun.tools.internal.jxc.gen.config;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import com.sun.tools.internal.jxc.NGCCRuntimeEx;

import java.util.List;
    import java.util.ArrayList;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public class Classes extends NGCCHandler {
    private String __text;
    private String exclude_content;
    private String include_content;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public Classes(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie) {
        super(source, parent, cookie);
        $runtime = runtime;
        $_ngcc_current_state = 12;
    }

    public Classes(NGCCRuntimeEx runtime) {
        this(null, runtime, runtime, -1);
    }

    private void action0()throws SAXException {
         this.excludes.add(exclude_content);
}

    private void action1()throws SAXException {
        $runtime.processList(__text);}

    private void action2()throws SAXException {
         this.includes.add(include_content);
}

    private void action3()throws SAXException {
        $runtime.processList(__text);}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 12:
            {
                if(($__uri.equals("") && $__local.equals("classes"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    $_ngcc_current_state = 11;
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 2:
            {
                if(($__uri.equals("") && $__local.equals("excludes"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    $_ngcc_current_state = 6;
                }
                else {
                    $_ngcc_current_state = 1;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 4:
            {
                $_ngcc_current_state = 3;
                $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 11:
            {
                if(($__uri.equals("") && $__local.equals("includes"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    $_ngcc_current_state = 10;
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
        case 4:
            {
                $_ngcc_current_state = 3;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 1:
            {
                if(($__uri.equals("") && $__local.equals("classes"))) {
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
                if(($__uri.equals("") && $__local.equals("excludes"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 1;
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
        case 8:
            {
                if(($__uri.equals("") && $__local.equals("includes"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 2;
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
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 4:
            {
                $_ngcc_current_state = 3;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromEnterAttribute(this, super._cookie, $__uri, $__local, $__qname);
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
        case 4:
            {
                $_ngcc_current_state = 3;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 0:
            {
                revertToParentFromLeaveAttribute(this, super._cookie, $__uri, $__local, $__qname);
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
        case 4:
            {
                exclude_content = $value;
                $_ngcc_current_state = 3;
                action0();
            }
            break;
        case 10:
            {
                __text = $value;
                $_ngcc_current_state = 9;
                action3();
            }
            break;
        case 3:
            {
                exclude_content = $value;
                $_ngcc_current_state = 3;
                action0();
            }
            break;
        case 6:
            {
                __text = $value;
                $_ngcc_current_state = 4;
                action1();
            }
            break;
        case 0:
            {
                revertToParentFromText(this, super._cookie, $value);
            }
            break;
        case 9:
            {
                include_content = $value;
                $_ngcc_current_state = 8;
                action2();
            }
            break;
        case 8:
            {
                include_content = $value;
                $_ngcc_current_state = 8;
                action2();
            }
            break;
        }
    }

    public void onChildCompleted(Object result, int cookie, boolean needAttCheck)throws SAXException {
        switch(cookie) {
        }
    }

    public boolean accepted() {
        return(($_ngcc_current_state == 0));
    }


       private List includes = new ArrayList();
       public List getIncludes() { return $runtime.getIncludePatterns(this.includes);}
       private List excludes = new ArrayList();
       public List getExcludes() { return $runtime.getExcludePatterns(this.excludes);}

}
