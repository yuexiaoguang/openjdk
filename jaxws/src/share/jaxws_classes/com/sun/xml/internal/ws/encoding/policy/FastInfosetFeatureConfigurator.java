package com.sun.xml.internal.ws.encoding.policy;

import com.sun.xml.internal.ws.api.fastinfoset.FastInfosetFeature;
import com.sun.xml.internal.ws.policy.AssertionSet;
import com.sun.xml.internal.ws.policy.Policy;
import com.sun.xml.internal.ws.policy.PolicyAssertion;
import com.sun.xml.internal.ws.policy.PolicyException;
import com.sun.xml.internal.ws.policy.PolicyMap;
import com.sun.xml.internal.ws.policy.PolicyMapKey;
import com.sun.xml.internal.ws.policy.jaxws.spi.PolicyFeatureConfigurator;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.xml.namespace.QName;

import static com.sun.xml.internal.ws.encoding.policy.EncodingConstants.OPTIMIZED_FI_SERIALIZATION_ASSERTION;
import javax.xml.ws.WebServiceFeature;

/**
 * A configurator provider for FastInfoset policy assertions.
 */
public class FastInfosetFeatureConfigurator implements PolicyFeatureConfigurator {

    public static final QName enabled = new QName("enabled");

    /**
     * Process FastInfoset policy assertions.
     *
     * @param key Key to identify the endpoint scope.
     * @param policyMap the policy map.
     * @throws PolicyException If retrieving the policy triggered an exception.
     */
     public Collection<WebServiceFeature> getFeatures(final PolicyMapKey key, final PolicyMap policyMap) throws PolicyException {
        final Collection<WebServiceFeature> features = new LinkedList<WebServiceFeature>();
        if ((key != null) && (policyMap != null)) {
            Policy policy = policyMap.getEndpointEffectivePolicy(key);
            if (null!=policy && policy.contains(OPTIMIZED_FI_SERIALIZATION_ASSERTION)) {
                Iterator <AssertionSet> assertions = policy.iterator();
                while(assertions.hasNext()){
                    AssertionSet assertionSet = assertions.next();
                    Iterator<PolicyAssertion> policyAssertion = assertionSet.iterator();
                    while(policyAssertion.hasNext()){
                        PolicyAssertion assertion = policyAssertion.next();
                        if(OPTIMIZED_FI_SERIALIZATION_ASSERTION.equals(assertion.getName())){
                            String value = assertion.getAttributeValue(enabled);
                            boolean isFastInfosetEnabled = Boolean.valueOf(value.trim());
                            features.add(new FastInfosetFeature(isFastInfosetEnabled));
                        } // end-if non optional fast infoset assertion found
                    } // next assertion
                } // next alternative
            } // end-if policy contains fast infoset assertion
        }
        return features;
    }

}
