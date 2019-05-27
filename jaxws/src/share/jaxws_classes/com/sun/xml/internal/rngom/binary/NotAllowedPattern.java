package com.sun.xml.internal.rngom.binary;

import com.sun.xml.internal.rngom.binary.visitor.PatternFunction;
import com.sun.xml.internal.rngom.binary.visitor.PatternVisitor;

public class NotAllowedPattern extends Pattern {
  NotAllowedPattern() {
    super(false, EMPTY_CONTENT_TYPE, NOT_ALLOWED_HASH_CODE);
  }
    @Override
  boolean isNotAllowed() {
    return true;
  }
  boolean samePattern(Pattern other) {
    // needs to work for UnexpandedNotAllowedPattern
    return other.getClass() == this.getClass();
  }
  public void accept(PatternVisitor visitor) {
    visitor.visitNotAllowed();
  }
  public Object apply(PatternFunction f) {
    return f.caseNotAllowed(this);
  }
}
