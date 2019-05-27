package com.sun.xml.internal.rngom.binary.visitor;

import com.sun.xml.internal.rngom.binary.AfterPattern;
import com.sun.xml.internal.rngom.binary.AttributePattern;
import com.sun.xml.internal.rngom.binary.ChoicePattern;
import com.sun.xml.internal.rngom.binary.DataExceptPattern;
import com.sun.xml.internal.rngom.binary.DataPattern;
import com.sun.xml.internal.rngom.binary.ElementPattern;
import com.sun.xml.internal.rngom.binary.EmptyPattern;
import com.sun.xml.internal.rngom.binary.ErrorPattern;
import com.sun.xml.internal.rngom.binary.GroupPattern;
import com.sun.xml.internal.rngom.binary.InterleavePattern;
import com.sun.xml.internal.rngom.binary.ListPattern;
import com.sun.xml.internal.rngom.binary.NotAllowedPattern;
import com.sun.xml.internal.rngom.binary.OneOrMorePattern;
import com.sun.xml.internal.rngom.binary.RefPattern;
import com.sun.xml.internal.rngom.binary.TextPattern;
import com.sun.xml.internal.rngom.binary.ValuePattern;

public interface PatternFunction {
    Object caseEmpty(EmptyPattern p);
    Object caseNotAllowed(NotAllowedPattern p);
    Object caseError(ErrorPattern p);
    Object caseGroup(GroupPattern p);
    Object caseInterleave(InterleavePattern p);
    Object caseChoice(ChoicePattern p);
    Object caseOneOrMore(OneOrMorePattern p);
    Object caseElement(ElementPattern p);
    Object caseAttribute(AttributePattern p);
    Object caseData(DataPattern p);
    Object caseDataExcept(DataExceptPattern p);
    Object caseValue(ValuePattern p);
    Object caseText(TextPattern p);
    Object caseList(ListPattern p);
    Object caseRef(RefPattern p);
    Object caseAfter(AfterPattern p);
}
