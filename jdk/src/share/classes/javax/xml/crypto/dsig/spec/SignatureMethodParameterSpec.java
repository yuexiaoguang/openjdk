package javax.xml.crypto.dsig.spec;

import javax.xml.crypto.dsig.SignatureMethod;
import java.security.spec.AlgorithmParameterSpec;

/**
 * A specification of algorithm parameters for an XML {@link SignatureMethod}
 * algorithm. The purpose of this interface is to group (and provide type
 * safety for) all signature method parameter specifications. All signature
 * method parameter specifications must implement this interface.
 */
public interface SignatureMethodParameterSpec extends AlgorithmParameterSpec {}
