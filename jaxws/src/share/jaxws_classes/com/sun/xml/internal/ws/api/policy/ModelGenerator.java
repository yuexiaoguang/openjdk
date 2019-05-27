package com.sun.xml.internal.ws.api.policy;

import com.sun.xml.internal.ws.policy.Policy;
import com.sun.xml.internal.ws.policy.sourcemodel.PolicyModelGenerator;
import com.sun.xml.internal.ws.policy.sourcemodel.PolicySourceModel;

public abstract class ModelGenerator extends PolicyModelGenerator {

    private static final SourceModelCreator CREATOR = new SourceModelCreator();

    /**
     * This private constructor avoids direct instantiation from outside the class.
     */
    private ModelGenerator() {
        super();
    }

    /**
     * Factory method that returns a ModelGenerator instance.
     *
     * @return A ModelGenerator instance.
     */
    public static PolicyModelGenerator getGenerator() {
        return PolicyModelGenerator.getCompactGenerator(CREATOR);
    }


    protected static class SourceModelCreator extends PolicySourceModelCreator {

        @Override
        protected PolicySourceModel create(Policy policy) {
            return SourceModel.createPolicySourceModel(policy.getNamespaceVersion(),
                                                       policy.getId(), policy.getName());

        }

    }

}
