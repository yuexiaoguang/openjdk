package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A helper class for extensible entities.
 */
public class ExtensibilityHelper {

    public ExtensibilityHelper() {
    }

    public void addExtension(TWSDLExtension e) {
        if (_extensions == null) {
            _extensions = new ArrayList();
        }
        _extensions.add(e);
    }

    public Iterable<TWSDLExtension> extensions() {
        if (_extensions == null) {
            return new ArrayList<TWSDLExtension>();
        } else {
            return _extensions;
        }
    }

    public void withAllSubEntitiesDo(EntityAction action) {
        if (_extensions != null) {
            for (Iterator iter = _extensions.iterator(); iter.hasNext();) {
                action.perform((Entity) iter.next());
            }
        }
    }

    public void accept(ExtensionVisitor visitor) throws Exception {
        if (_extensions != null) {
            for (Iterator iter = _extensions.iterator(); iter.hasNext();) {
                ((ExtensionImpl) iter.next()).accept(visitor);
            }
        }
    }

    private List<TWSDLExtension> _extensions;
}
