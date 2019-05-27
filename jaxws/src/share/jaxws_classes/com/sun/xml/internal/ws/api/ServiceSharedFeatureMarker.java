package com.sun.xml.internal.ws.api;

import javax.xml.ws.WebServiceFeature;

/**
 * Marker interface for {@link WebServiceFeature} derived classes that when instances are specified in the feature list to a service delegate must be
 * added to the feature list of any Stubs created by that delegate.  WebServiceFeature instances passed directly in the parameters of get...() or createDispatch()
 * must take precedence over feature instances passed during service delegate initialization.
 */
public interface ServiceSharedFeatureMarker {

}
