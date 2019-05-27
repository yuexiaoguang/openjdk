package sun.security.provider.certpath;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.*;
import java.util.*;

import sun.security.provider.certpath.PKIX.ValidatorParams;
import sun.security.x509.X509CertImpl;
import sun.security.util.Debug;

/**
 * This class implements the PKIX validation algorithm for certification
 * paths consisting exclusively of <code>X509Certificates</code>. It uses
 * the specified input parameter set (which must be a
 * <code>PKIXParameters</code> object).
 */
public final class PKIXCertPathValidator extends CertPathValidatorSpi {

    private static final Debug debug = Debug.getInstance("certpath");

    /**
     * Default constructor.
     */
    public PKIXCertPathValidator() {}

    @Override
    public CertPathChecker engineGetRevocationChecker() {
        return new RevocationChecker();
    }

    /**
     * Validates a certification path consisting exclusively of
     * <code>X509Certificate</code>s using the PKIX validation algorithm,
     * which uses the specified input parameter set.
     * The input parameter set must be a <code>PKIXParameters</code> object.
     *
     * @param cp the X509 certification path
     * @param params the input PKIX parameter set
     * @return the result
     * @throws CertPathValidatorException if cert path does not validate.
     * @throws InvalidAlgorithmParameterException if the specified
     *         parameters are inappropriate for this CertPathValidator
     */
    @Override
    public CertPathValidatorResult engineValidate(CertPath cp,
                                                  CertPathParameters params)
        throws CertPathValidatorException, InvalidAlgorithmParameterException
    {
        ValidatorParams valParams = PKIX.checkParams(cp, params);
        return validate(valParams);
    }

    private static PKIXCertPathValidatorResult validate(ValidatorParams params)
        throws CertPathValidatorException
    {
        if (debug != null)
            debug.println("PKIXCertPathValidator.engineValidate()...");

        // Retrieve the first certificate in the certpath
        // (to be used later in pre-screening)
        AdaptableX509CertSelector selector = null;
        List<X509Certificate> certList = params.certificates();
        if (!certList.isEmpty()) {
            selector = new AdaptableX509CertSelector();
            X509Certificate firstCert = certList.get(0);
            // check trusted certificate's subject
            selector.setSubject(firstCert.getIssuerX500Principal());
            // check the validity period
            selector.setValidityPeriod(firstCert.getNotBefore(),
                                       firstCert.getNotAfter());
            /*
             * Facilitate certification path construction with authority
             * key identifier and subject key identifier.
             */
            try {
                X509CertImpl firstCertImpl = X509CertImpl.toImpl(firstCert);
                selector.parseAuthorityKeyIdentifierExtension(
                            firstCertImpl.getAuthorityKeyIdentifierExtension());
            } catch (CertificateException | IOException e) {
                // ignore
            }
        }

        CertPathValidatorException lastException = null;

        // We iterate through the set of trust anchors until we find
        // one that works at which time we stop iterating
        for (TrustAnchor anchor : params.trustAnchors()) {
            X509Certificate trustedCert = anchor.getTrustedCert();
            if (trustedCert != null) {
                // if this trust anchor is not worth trying,
                // we move on to the next one
                if (selector != null && !selector.match(trustedCert)) {
                    if (debug != null) {
                        debug.println("NO - don't try this trustedCert");
                    }
                    continue;
                }

                if (debug != null) {
                    debug.println("YES - try this trustedCert");
                    debug.println("anchor.getTrustedCert()."
                        + "getSubjectX500Principal() = "
                        + trustedCert.getSubjectX500Principal());
                }
            } else {
                if (debug != null) {
                    debug.println("PKIXCertPathValidator.engineValidate(): "
                        + "anchor.getTrustedCert() == null");
                }
            }

            try {
                return validate(anchor, params);
            } catch (CertPathValidatorException cpe) {
                // remember this exception
                lastException = cpe;
            }
        }

        // could not find a trust anchor that verified
        // (a) if we did a validation and it failed, use that exception
        if (lastException != null) {
            throw lastException;
        }
        // (b) otherwise, generate new exception
        throw new CertPathValidatorException
            ("Path does not chain with any of the trust anchors",
             null, null, -1, PKIXReason.NO_TRUST_ANCHOR);
    }

    private static PKIXCertPathValidatorResult validate(TrustAnchor anchor,
                                                        ValidatorParams params)
        throws CertPathValidatorException
    {
        int certPathLen = params.certificates().size();

        // create PKIXCertPathCheckers
        List<PKIXCertPathChecker> certPathCheckers = new ArrayList<>();
        // add standard checkers that we will be using
        certPathCheckers.add(new UntrustedChecker());
        certPathCheckers.add(new AlgorithmChecker(anchor));
        certPathCheckers.add(new KeyChecker(certPathLen,
                                            params.targetCertConstraints()));
        certPathCheckers.add(new ConstraintsChecker(certPathLen));
        PolicyNodeImpl rootNode =
            new PolicyNodeImpl(null, PolicyChecker.ANY_POLICY, null, false,
                               Collections.singleton(PolicyChecker.ANY_POLICY),
                               false);
        PolicyChecker pc = new PolicyChecker(params.initialPolicies(),
                                             certPathLen,
                                             params.explicitPolicyRequired(),
                                             params.policyMappingInhibited(),
                                             params.anyPolicyInhibited(),
                                             params.policyQualifiersRejected(),
                                             rootNode);
        certPathCheckers.add(pc);
        // default value for date is current time
        BasicChecker bc = new BasicChecker(anchor, params.date(),
                                           params.sigProvider(), false);
        certPathCheckers.add(bc);

        boolean revCheckerAdded = false;
        List<PKIXCertPathChecker> checkers = params.certPathCheckers();
        for (PKIXCertPathChecker checker : checkers) {
            if (checker instanceof PKIXRevocationChecker) {
                if (revCheckerAdded) {
                    throw new CertPathValidatorException(
                        "Only one PKIXRevocationChecker can be specified");
                }
                revCheckerAdded = true;
                // if it's our own, initialize it
                if (checker instanceof RevocationChecker) {
                    ((RevocationChecker)checker).init(anchor, params);
                }
            }
        }
        // only add a RevocationChecker if revocation is enabled and
        // a PKIXRevocationChecker has not already been added
        if (params.revocationEnabled() && !revCheckerAdded) {
            certPathCheckers.add(new RevocationChecker(anchor, params));
        }
        // add user-specified checkers
        certPathCheckers.addAll(checkers);

        PKIXMasterCertPathValidator.validate(params.certPath(),
                                             params.certificates(),
                                             certPathCheckers);

        return new PKIXCertPathValidatorResult(anchor, pc.getPolicyTree(),
                                               bc.getPublicKey());
    }
}
