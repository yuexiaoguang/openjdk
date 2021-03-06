package com.sun.xml.internal.rngom.binary;

import com.sun.xml.internal.rngom.binary.visitor.PatternFunction;
import com.sun.xml.internal.rngom.binary.visitor.PatternVisitor;
import com.sun.xml.internal.rngom.nc.NameClass;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public final class ElementPattern extends Pattern {
  private Pattern p;
  private NameClass origNameClass;
  private NameClass nameClass;
  private boolean expanded = false;
  private boolean checkedRestrictions = false;
  private Locator loc;

  ElementPattern(NameClass nameClass, Pattern p, Locator loc) {
    super(false,
          ELEMENT_CONTENT_TYPE,
          combineHashCode(ELEMENT_HASH_CODE,
                          nameClass.hashCode(),
                          p.hashCode()));
    this.nameClass = nameClass;
    this.origNameClass = nameClass;
    this.p = p;
    this.loc = loc;
  }

    @Override
  void checkRestrictions(int context, DuplicateAttributeDetector dad, Alphabet alpha)
    throws RestrictionViolationException {
    if (alpha != null)
      alpha.addElement(origNameClass);
    if (checkedRestrictions)
      return;
    switch (context) {
    case DATA_EXCEPT_CONTEXT:
      throw new RestrictionViolationException("data_except_contains_element");
    case LIST_CONTEXT:
      throw new RestrictionViolationException("list_contains_element");
    case ATTRIBUTE_CONTEXT:
      throw new RestrictionViolationException("attribute_contains_element");
    }
    checkedRestrictions = true;
    try {
      p.checkRestrictions(ELEMENT_CONTEXT, new DuplicateAttributeDetector(), null);
    }
    catch (RestrictionViolationException e) {
      checkedRestrictions = false;
      e.maybeSetLocator(loc);
      throw e;
    }
  }

    @Override
  Pattern expand(SchemaPatternBuilder b) {
    if (!expanded) {
      expanded = true;
      p = p.expand(b);
      if (p.isNotAllowed())
        nameClass = NameClass.NULL;
    }
    return this;
  }

  boolean samePattern(Pattern other) {
    if (!(other instanceof ElementPattern))
      return false;
    ElementPattern ep = (ElementPattern)other;
    return nameClass.equals(ep.nameClass) && p == ep.p;
  }

    @Override
  void checkRecursion(int depth) throws SAXException {
    p.checkRecursion(depth + 1);
  }

  public void accept(PatternVisitor visitor) {
    visitor.visitElement(nameClass, p);
  }

  public Object apply(PatternFunction f) {
    return f.caseElement(this);
  }

  void setContent(Pattern p) {
    this.p = p;
  }

  public Pattern getContent() {
    return p;
  }

  public NameClass getNameClass() {
    return nameClass;
  }

  public Locator getLocator() {
    return loc;
  }
}
