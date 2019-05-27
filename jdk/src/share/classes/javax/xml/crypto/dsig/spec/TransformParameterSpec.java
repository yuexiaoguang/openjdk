package javax.xml.crypto.dsig.spec;

import javax.xml.crypto.dsig.Transform;
import java.security.spec.AlgorithmParameterSpec;

/**
 * A specification of algorithm parameters for a {@link Transform}
 * algorithm. The purpose of this interface is to group (and provide type
 * safety for) all transform parameter specifications. All transform parameter
 * specifications must implement this interface.
 */
public interface TransformParameterSpec extends AlgorithmParameterSpec {}
