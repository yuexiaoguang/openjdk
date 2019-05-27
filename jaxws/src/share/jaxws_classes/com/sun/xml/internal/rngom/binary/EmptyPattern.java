package com.sun.xml.internal.rngom.binary;

import com.sun.xml.internal.rngom.binary.visitor.PatternFunction;
import com.sun.xml.internal.rngom.binary.visitor.PatternVisitor;

public class EmptyPattern extends Pattern {
  EmptyPattern() {
    super(true, EMPTY_CONTENT_TYPE, EMPTY_HASH_CODE);
  }
  boolean samePattern(Pattern other) {
    return other instanceof EmptyPattern;
  }
  public void accept(PatternVisitor visitor) {
    visitor.visitEmpty();
  }
  public Object apply(PatternFunction f) {
    return f.caseEmpty(this);
  }
    @Override
  void checkRestrictions(int context, DuplicateAttributeDetector dad, Alphabet alpha)
    throws RestrictionViolationException {
    switch (context) {
    case DATA_EXCEPT_CONTEXT:
      throw new RestrictionViolationException("data_except_contains_empty");
    case START_CONTEXT:
      throw new RestrictionViolationException("start_contains_empty");
    }
  }
}
