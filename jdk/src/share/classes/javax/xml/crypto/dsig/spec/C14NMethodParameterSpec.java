package javax.xml.crypto.dsig.spec;

import javax.xml.crypto.dsig.CanonicalizationMethod;

/**
 * A specification of algorithm parameters for a {@link CanonicalizationMethod}
 * Algorithm. The purpose of this interface is to group (and provide type
 * safety for) all canonicalization (C14N) parameter specifications. All
 * canonicalization parameter specifications must implement this interface.
 */
public interface C14NMethodParameterSpec extends TransformParameterSpec {}
