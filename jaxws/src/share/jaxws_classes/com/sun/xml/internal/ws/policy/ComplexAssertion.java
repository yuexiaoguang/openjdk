package com.sun.xml.internal.ws.policy;

import com.sun.xml.internal.ws.policy.sourcemodel.AssertionData;
import java.util.Collection;

/**
 * Complex assertion is an abstract class that serves as a base class for any assertion
 * that <b>MAY</b> contain nested policies.
 */
public abstract class ComplexAssertion extends PolicyAssertion {

    private final NestedPolicy nestedPolicy;

    protected ComplexAssertion() {
        super();

        this.nestedPolicy = NestedPolicy.createNestedPolicy(AssertionSet.emptyAssertionSet());
    }

    protected ComplexAssertion(final AssertionData data, final Collection<? extends PolicyAssertion> assertionParameters, final AssertionSet nestedAlternative) {
        super(data, assertionParameters);

        AssertionSet nestedSet = (nestedAlternative != null) ? nestedAlternative : AssertionSet.emptyAssertionSet();
        this.nestedPolicy = NestedPolicy.createNestedPolicy(nestedSet);
    }

    @Override
    public final boolean hasNestedPolicy() {
        return true;
    }

    @Override
    public final NestedPolicy getNestedPolicy() {
        return nestedPolicy;
    }
}
