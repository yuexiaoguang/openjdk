package com.sun.xml.internal.rngom.binary;

import com.sun.xml.internal.rngom.binary.visitor.PatternFunction;
import com.sun.xml.internal.rngom.binary.visitor.PatternVisitor;

public class ErrorPattern extends Pattern {
  ErrorPattern() {
    super(false, EMPTY_CONTENT_TYPE, ERROR_HASH_CODE);
  }
  boolean samePattern(Pattern other) {
    return other instanceof ErrorPattern;
  }
  public void accept(PatternVisitor visitor) {
    visitor.visitError();
  }
  public Object apply(PatternFunction f) {
    return f.caseError(this);
  }
}
