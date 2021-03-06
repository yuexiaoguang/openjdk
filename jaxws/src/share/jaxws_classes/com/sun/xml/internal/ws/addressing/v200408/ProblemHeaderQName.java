package com.sun.xml.internal.ws.addressing.v200408;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;

import static com.sun.xml.internal.ws.addressing.v200408.MemberSubmissionAddressingConstants.WSA_NAMESPACE_NAME;

@XmlRootElement(name="ProblemHeaderQName", namespace= WSA_NAMESPACE_NAME)
public class ProblemHeaderQName {
    @XmlValue
    private QName value;

    /** Creates a new instance of ProblemHeaderQName */
    public ProblemHeaderQName() {
    }

    public ProblemHeaderQName(QName name) {
        this.value = name;
    }
}
