package com.sun.xml.internal.rngom.parse.compact;

import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.builder.DataPatternBuilder;
import com.sun.xml.internal.rngom.ast.builder.Div;
import com.sun.xml.internal.rngom.ast.builder.ElementAnnotationBuilder;
import com.sun.xml.internal.rngom.ast.builder.Grammar;
import com.sun.xml.internal.rngom.ast.builder.GrammarSection;
import com.sun.xml.internal.rngom.ast.builder.Include;
import com.sun.xml.internal.rngom.ast.builder.IncludedGrammar;
import com.sun.xml.internal.rngom.ast.builder.NameClassBuilder;
import com.sun.xml.internal.rngom.ast.builder.SchemaBuilder;
import com.sun.xml.internal.rngom.ast.builder.Scope;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import com.sun.xml.internal.rngom.ast.om.ParsedNameClass;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;
import com.sun.xml.internal.rngom.parse.Context;
import com.sun.xml.internal.rngom.parse.IllegalSchemaException;
import com.sun.xml.internal.rngom.parse.Parseable;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;

import com.sun.xml.internal.rngom.util.Localizer;
import com.sun.xml.internal.rngom.xml.util.WellKnownNamespaces;


public class CompactSyntax implements Context, CompactSyntaxConstants {
  private static final int IN_ELEMENT = 0;
  private static final int IN_ATTRIBUTE = 1;
  private static final int IN_ANY_NAME = 2;
  private static final int IN_NS_NAME = 4;

  private String defaultNamespace;
  private String compatibilityPrefix = null;
  private SchemaBuilder sb;
  private NameClassBuilder ncb;
  private String sourceUri;
  /**
   * This is what we are parsing right now.
   */
  private CompactParseable parseable;
  private ErrorHandler eh;
  private final Hashtable namespaceTable = new Hashtable();
  private final Hashtable datatypesTable = new Hashtable();
  private boolean hadError = false;
  private static final Localizer localizer = new Localizer(new Localizer(Parseable.class),CompactSyntax.class);
  private final Hashtable attributeNameTable = new Hashtable();
  private boolean annotationsIncludeElements = false;

  /**
   * String that represents the inherited namespace.
   *
   * <p>
   * HACK: we always allocate a new String instance so that
   * we can distinguish inherited value from the explicitly
   * given value.
   */
  private /*final*/ String inheritedNs; // essentially final but JavaCC don't let us declare it as so.

  final class LocatedString {
    private final String str;
    private final Token tok;

    LocatedString(String str, Token tok) {
      this.str = str;
      this.tok = tok;
    }

    String getString() {
      return str;
    }

    Location getLocation() {
      return makeLocation(tok);
    }

    Token getToken() {
      return tok;
    }

  }

  public CompactSyntax(CompactParseable parseable, Reader r, String sourceUri, SchemaBuilder sb, ErrorHandler eh, String inheritedNs) {
    this(r);
    this.sourceUri = sourceUri;
    this.parseable = parseable;
    this.sb = sb;
    this.ncb = sb.getNameClassBuilder();
    this.eh = eh;
    // this causes the root pattern to have non-null annotations
    // which is useful because it gives a context to trang
    this.topLevelComments = sb.makeCommentList();
    this.inheritedNs = defaultNamespace = new String(inheritedNs);
  }

  ParsedPattern parse(Scope scope) throws IllegalSchemaException {
    try {
      ParsedPattern p = Input(scope);
      if (!hadError)
        return p;
    }
    catch (ParseException e) {
      error("syntax_error", e.getMessage(), e.currentToken.next);
    }
    catch (EscapeSyntaxException e) {
      reportEscapeSyntaxException(e);
    }
    throw new IllegalSchemaException();
  }

  ParsedPattern parseInclude(IncludedGrammar g) throws IllegalSchemaException {
    try {
      ParsedPattern p = IncludedGrammar(g);
      if (!hadError)
        return p;
    }
    catch (ParseException e) {
      error("syntax_error", e.getMessage(), e.currentToken.next);
    }
    catch (EscapeSyntaxException e) {
      reportEscapeSyntaxException(e);
    }
    throw new IllegalSchemaException();
  }

  private void checkNsName(int context, LocatedString ns) {
    if ((context & IN_NS_NAME) != 0)
      error("ns_name_except_contains_ns_name", ns.getToken());
  }

  private void checkAnyName(int context, Token t) {
    if ((context & IN_NS_NAME) != 0)
      error("ns_name_except_contains_any_name", t);
    if ((context & IN_ANY_NAME) != 0)
      error("any_name_except_contains_any_name", t);
  }

  private void error(String key, Token tok) {
    doError(localizer.message(key), tok);
  }

  private void error(String key, String arg, Token tok) {
    doError(localizer.message(key, arg), tok);
  }

  private void error(String key, String arg1, String arg2, Token tok) {
    doError(localizer.message(key, arg1, arg2), tok);
  }

  private void doError(String message, Token tok) {
    hadError = true;
    if (eh != null) {
      LocatorImpl loc = new LocatorImpl();
      loc.setLineNumber(tok.beginLine);
      loc.setColumnNumber(tok.beginColumn);
      loc.setSystemId(sourceUri);
      try {
        eh.error(new SAXParseException(message, loc));
      }
      catch (SAXException se) {
        throw new BuildException(se);
      }
    }
  }

  private void reportEscapeSyntaxException(EscapeSyntaxException e) {
    if (eh != null) {
      LocatorImpl loc = new LocatorImpl();
      loc.setLineNumber(e.getLineNumber());
      loc.setColumnNumber(e.getColumnNumber());
      loc.setSystemId(sourceUri);
      try {
        eh.error(new SAXParseException(localizer.message(e.getKey()), loc));
      }
      catch (SAXException se) {
        throw new BuildException(se);
      }
    }
  }

  private static String unquote(String s) {
    if (s.length() >= 6 && s.charAt(0) == s.charAt(1)) {
      s = s.replace('\u0000', '\u005cn');
      return s.substring(3, s.length() - 3);
    }
    else
      return s.substring(1, s.length() - 1);
  }

  Location makeLocation(Token t) {
    return sb.makeLocation(sourceUri, t.beginLine, t.beginColumn);
  }

  private static ParsedPattern[] addPattern(ParsedPattern[] patterns, int i, ParsedPattern p) {
    if (i >= patterns.length) {
      ParsedPattern[] oldPatterns = patterns;
      patterns = new ParsedPattern[oldPatterns.length*2];
      System.arraycopy(oldPatterns, 0, patterns, 0, oldPatterns.length);
    }
    patterns[i] = p;
    return patterns;
  }

  String getCompatibilityPrefix() {
    if (compatibilityPrefix == null) {
      compatibilityPrefix = "a";
      while (namespaceTable.get(compatibilityPrefix) != null)
        compatibilityPrefix = compatibilityPrefix + "a";
    }
    return compatibilityPrefix;
  }

  public String resolveNamespacePrefix(String prefix) {
    String result = (String)namespaceTable.get(prefix);
    if (result.length() == 0)
      return null;
    return result;
  }

  public Enumeration prefixes() {
    return namespaceTable.keys();
  }

  public String getBaseUri() {
    return sourceUri;
  }

  public boolean isUnparsedEntity(String entityName) {
    return false;
  }

  public boolean isNotation(String notationName) {
    return false;
  }

  public Context copy() {
    return this;
  }

  private Context getContext() {
    return this;
  }

  private CommentList getComments() {
    return getComments(getTopLevelComments());
  }

  private CommentList topLevelComments;

  private CommentList getTopLevelComments() {
    CommentList tem = topLevelComments;
    topLevelComments = null;
    return tem;
  }

  private void noteTopLevelComments() {
    topLevelComments = getComments(topLevelComments);
  }

  private void topLevelComments(GrammarSection section) {
    section.topLevelComment(getComments(null));
  }

  private Token lastCommentSourceToken = null;

  private CommentList getComments(CommentList comments) {
    Token nextToken = getToken(1);
    if (lastCommentSourceToken != nextToken) {
      if (lastCommentSourceToken == null)
        lastCommentSourceToken = token;
      do {
        lastCommentSourceToken = lastCommentSourceToken.next;
        Token t = lastCommentSourceToken.specialToken;
        if (t != null) {
          while (t.specialToken != null)
            t = t.specialToken;
          if (comments == null)
            comments = sb.makeCommentList();
          for (; t != null; t = t.next) {
            String s = mungeComment(t.image);
            Location loc = makeLocation(t);
            if (t.next != null
                && t.next.kind == CompactSyntaxConstants.SINGLE_LINE_COMMENT_CONTINUE) {
              StringBuffer buf = new StringBuffer(s);
              do {
                t = t.next;
                buf.append('\u005cn');
                buf.append(mungeComment(t.image));
              } while (t.next != null
                       && t.next.kind == CompactSyntaxConstants.SINGLE_LINE_COMMENT_CONTINUE);
              s = buf.toString();
            }
            comments.addComment(s, loc);
          }
        }
      } while (lastCommentSourceToken != nextToken);
    }
    return comments;
  }

  private ParsedPattern afterComments(ParsedPattern p) {
    CommentList comments = getComments(null);
    if (comments == null)
      return p;
    return sb.commentAfter(p, comments);
  }

  private ParsedNameClass afterComments(ParsedNameClass nc) {
    CommentList comments = getComments(null);
    if (comments == null)
      return nc;
    return ncb.commentAfter(nc, comments);
  }

  private static String mungeComment(String image) {
    int i = image.indexOf('#') + 1;
    while (i < image.length() && image.charAt(i) == '#')
      i++;
    if (i < image.length() && image.charAt(i) == ' ')
      i++;
    return image.substring(i);
  }

  private Annotations getCommentsAsAnnotations() {
    CommentList comments = getComments();
    if (comments == null)
      return null;
    return sb.makeAnnotations(comments, getContext());
  }

  private Annotations addCommentsToChildAnnotations(Annotations a) {
    CommentList comments = getComments();
    if (comments == null)
      return a;
    if (a == null)
      a = sb.makeAnnotations(null, getContext());
    a.addComment(comments);
    return a;
  }

  private Annotations addCommentsToLeadingAnnotations(Annotations a) {
    CommentList comments = getComments();
    if (comments == null)
      return a;
    if (a == null)
      return sb.makeAnnotations(comments, getContext());
    a.addLeadingComment(comments);
    return a;
  }

  private Annotations getTopLevelCommentsAsAnnotations() {
    CommentList comments = getTopLevelComments();
    if (comments == null)
      return null;
    return sb.makeAnnotations(comments, getContext());
  }

  private void clearAttributeList() {
    attributeNameTable.clear();
  }

  private void addAttribute(Annotations a, String ns, String localName, String prefix, String value, Token tok) {
    String key = ns + "#" + localName;
    if (attributeNameTable.get(key) != null)
      error("duplicate_attribute", ns, localName, tok);
    else {
      attributeNameTable.put(key, key);
      a.addAttribute(ns, localName, prefix, value, makeLocation(tok));
    }
  }

  private void checkExcept(Token[] except) {
    if (except[0] != null)
      error("except_missing_parentheses", except[0]);
  }

  private String lookupPrefix(String prefix, Token t) {
    String ns = (String)namespaceTable.get(prefix);
    if (ns == null) {
      error("undeclared_prefix", prefix, t);
      return "#error";
    }
    return ns;
  }
  private String lookupDatatype(String prefix, Token t) {
    String ns = (String)datatypesTable.get(prefix);
    if (ns == null) {
      error("undeclared_prefix", prefix, t);
      return ""; // XXX
    }
    return ns;
  }
  private String resolve(String str) {
    try {
     return new URL(new URL(sourceUri), str).toString();
    }
    catch (MalformedURLException e) { }
    return str;
  }

  final public ParsedPattern Input(Scope scope) throws ParseException {
  ParsedPattern p;
    Preamble();
    if (jj_2_1(2147483647)) {
      p = TopLevelGrammar(scope);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
      case 10:
      case 17:
      case 18:
      case 19:
      case 26:
      case 27:
      case 28:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case DOCUMENTATION:
      case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
      case PREFIXED_NAME:
      case LITERAL:
        p = Expr(true, scope, null, null);
                                         p = afterComments(p);
        jj_consume_token(0);
        break;
      default:
        jj_la1[0] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public void TopLevelLookahead() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREFIXED_NAME:
      jj_consume_token(PREFIXED_NAME);
      jj_consume_token(1);
      break;
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      Identifier();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
        jj_consume_token(1);
        break;
      case 2:
        jj_consume_token(2);
        break;
      case 3:
        jj_consume_token(3);
        break;
      case 4:
        jj_consume_token(4);
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case 5:
    case 6:
    case 7:
      LookaheadGrammarKeyword();
      break;
    case 1:
      LookaheadBody();
      LookaheadAfterAnnotations();
      break;
    case DOCUMENTATION:
    case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
      LookaheadDocumentation();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
        LookaheadBody();
        break;
      default:
        jj_la1[2] = jj_gen;
        ;
      }
      LookaheadAfterAnnotations();
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void LookaheadAfterAnnotations() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      Identifier();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 2:
        jj_consume_token(2);
        break;
      case 3:
        jj_consume_token(3);
        break;
      case 4:
        jj_consume_token(4);
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case 5:
    case 6:
    case 7:
      LookaheadGrammarKeyword();
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void LookaheadGrammarKeyword() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 5:
      jj_consume_token(5);
      break;
    case 6:
      jj_consume_token(6);
      break;
    case 7:
      jj_consume_token(7);
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void LookaheadDocumentation() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOCUMENTATION:
        jj_consume_token(DOCUMENTATION);
        break;
      case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
        jj_consume_token(DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT);
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOCUMENTATION_CONTINUE:
          ;
          break;
        default:
          jj_la1[8] = jj_gen;
          break label_2;
        }
        jj_consume_token(DOCUMENTATION_CONTINUE);
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOCUMENTATION:
      case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_1;
      }
    }
  }

  final public void LookaheadBody() throws ParseException {
    jj_consume_token(1);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
      case 2:
      case 5:
      case 6:
      case 7:
      case 8:
      case 10:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 26:
      case 27:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
      case PREFIXED_NAME:
      case LITERAL:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PREFIXED_NAME:
        jj_consume_token(PREFIXED_NAME);
        break;
      case 5:
      case 6:
      case 7:
      case 10:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 26:
      case 27:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
        UnprefixedName();
        break;
      case 2:
        jj_consume_token(2);
        break;
      case LITERAL:
        jj_consume_token(LITERAL);
        break;
      case 8:
        jj_consume_token(8);
        break;
      case 1:
        LookaheadBody();
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(9);
  }

  final public ParsedPattern IncludedGrammar(IncludedGrammar g) throws ParseException {
  Annotations a;
  ParsedPattern p;
    Preamble();
    if (jj_2_2(2147483647)) {
      a = GrammarBody(g, g, getTopLevelCommentsAsAnnotations());
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
      case 10:
      case DOCUMENTATION:
      case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
        a = Annotations();
        jj_consume_token(10);
        jj_consume_token(11);
        a = GrammarBody(g, g, a);
                                                                topLevelComments(g);
        jj_consume_token(12);
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    p = afterComments(g.endIncludedGrammar(sb.makeLocation(sourceUri, 1, 1), a));
    jj_consume_token(0);
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern TopLevelGrammar(Scope scope) throws ParseException {
  Annotations a = getTopLevelCommentsAsAnnotations();
  Grammar g;
  ParsedPattern p;
    g = sb.makeGrammar(scope);
    a = GrammarBody(g, g, a);
    p = afterComments(g.endGrammar(sb.makeLocation(sourceUri, 1, 1), a));
    jj_consume_token(0);
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public void Preamble() throws ParseException {
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 13:
      case 14:
      case 16:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 13:
      case 14:
        NamespaceDecl();
        break;
      case 16:
        DatatypesDecl();
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    namespaceTable.put("xml", WellKnownNamespaces.XML);
    if (datatypesTable.get("xsd") == null)
      datatypesTable.put("xsd", WellKnownNamespaces.XML_SCHEMA_DATATYPES);
  }

  final public void NamespaceDecl() throws ParseException {
  LocatedString prefix = null;
  boolean isDefault = false;
  String namespaceName;
    noteTopLevelComments();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 13:
      jj_consume_token(13);
      prefix = UnprefixedName();
      break;
    case 14:
      jj_consume_token(14);
                   isDefault = true;
      jj_consume_token(13);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 5:
      case 6:
      case 7:
      case 10:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 26:
      case 27:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
        prefix = UnprefixedName();
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(2);
    namespaceName = NamespaceName();
    if (isDefault)
      defaultNamespace = namespaceName;
    if (prefix != null) {
      if (prefix.getString().equals("xmlns"))
        error("xmlns_prefix", prefix.getToken());
      else if (prefix.getString().equals("xml")) {
        if (!namespaceName.equals(WellKnownNamespaces.XML))
          error("xml_prefix_bad_uri", prefix.getToken());
      }
      else if (namespaceName.equals(WellKnownNamespaces.XML))
        error("xml_uri_bad_prefix", prefix.getToken());
      else {
        if (namespaceName.equals(WellKnownNamespaces.RELAX_NG_COMPATIBILITY_ANNOTATIONS))
          compatibilityPrefix = prefix.getString();
        namespaceTable.put(prefix.getString(), namespaceName);
      }
    }
  }

  final public String NamespaceName() throws ParseException {
  String r;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LITERAL:
      r = Literal();
      break;
    case 15:
      jj_consume_token(15);
                               r = this.inheritedNs;
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return r;}
    throw new Error("Missing return statement in function");
  }

  final public void DatatypesDecl() throws ParseException {
  LocatedString prefix;
  String uri;
    noteTopLevelComments();
    jj_consume_token(16);
    prefix = UnprefixedName();
    jj_consume_token(2);
    uri = Literal();
    datatypesTable.put(prefix.getString(), uri);
  }

  final public ParsedPattern AnnotatedPrimaryExpr(boolean topLevel, Scope scope, Token[] except) throws ParseException {
  Annotations a;
  ParsedPattern p;
  ParsedElementAnnotation e;
  Token t;
    a = Annotations();
    p = PrimaryExpr(topLevel, scope, a, except);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FANNOTATE:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_5;
      }
      t = jj_consume_token(FANNOTATE);
      e = AnnotationElement(false);
       if (topLevel)
         error("top_level_follow_annotation", t);
       else
         p = sb.annotateAfter(p, e);
    }
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern PrimaryExpr(boolean topLevel, Scope scope, Annotations a, Token[] except) throws ParseException {
  ParsedPattern p;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 26:
      p = ElementExpr(scope, a);
      break;
    case 27:
      p = AttributeExpr(scope, a);
      break;
    case 10:
      p = GrammarExpr(scope, a);
      break;
    case 33:
      p = ExternalRefExpr(scope, a);
      break;
    case 31:
      p = ListExpr(scope, a);
      break;
    case 32:
      p = MixedExpr(scope, a);
      break;
    case 28:
      p = ParenExpr(topLevel, scope, a);
      break;
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      p = IdentifierExpr(scope, a);
      break;
    case 34:
      p = ParentExpr(scope, a);
      break;
    case 35:
    case 36:
    case PREFIXED_NAME:
      p = DataExpr(topLevel, scope, a, except);
      break;
    case LITERAL:
      p = ValueExpr(topLevel, a);
      break;
    case 18:
      p = TextExpr(a);
      break;
    case 17:
      p = EmptyExpr(a);
      break;
    case 19:
      p = NotAllowedExpr(a);
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern EmptyExpr(Annotations a) throws ParseException {
  Token t;
    t = jj_consume_token(17);
    {if (true) return sb.makeEmpty(makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern TextExpr(Annotations a) throws ParseException {
  Token t;
    t = jj_consume_token(18);
    {if (true) return sb.makeText(makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern NotAllowedExpr(Annotations a) throws ParseException {
  Token t;
    t = jj_consume_token(19);
    {if (true) return sb.makeNotAllowed(makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern Expr(boolean topLevel, Scope scope, Token t, Annotations a) throws ParseException {
  List patterns = new ArrayList();
  ParsedPattern p;
  boolean[] hadOccur = new boolean[1];
  Token[] except = new Token[1];
    p = UnaryExpr(topLevel, scope, hadOccur, except);
    patterns.add(p);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 20:
    case 21:
    case 22:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 20:
     checkExcept(except);
        label_6:
        while (true) {
          t = jj_consume_token(20);
          p = UnaryExpr(topLevel, scope, null, except);
       patterns.add(p); checkExcept(except);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 20:
            ;
            break;
          default:
            jj_la1[20] = jj_gen;
            break label_6;
          }
        }
      p = sb.makeChoice(patterns, makeLocation(t), a);
        break;
      case 21:
        label_7:
        while (true) {
          t = jj_consume_token(21);
          p = UnaryExpr(topLevel, scope, null, except);
       patterns.add(p); checkExcept(except);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 21:
            ;
            break;
          default:
            jj_la1[21] = jj_gen;
            break label_7;
          }
        }
      p = sb.makeInterleave(patterns, makeLocation(t), a);
        break;
      case 22:
        label_8:
        while (true) {
          t = jj_consume_token(22);
          p = UnaryExpr(topLevel, scope, null, except);
       patterns.add(p); checkExcept(except);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 22:
            ;
            break;
          default:
            jj_la1[22] = jj_gen;
            break label_8;
          }
        }
      p = sb.makeGroup(patterns, makeLocation(t), a);
        break;
      default:
        jj_la1[23] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[24] = jj_gen;
      ;
    }
    if (patterns.size() == 1 && a != null) {
      if (hadOccur[0])
        p = sb.annotate(p, a);
      else
        p = sb.makeGroup(patterns, makeLocation(t), a);
    }
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern UnaryExpr(boolean topLevel, Scope scope, boolean[] hadOccur, Token[] except) throws ParseException {
  ParsedPattern p;
  Token t;
  ParsedElementAnnotation e;
    p = AnnotatedPrimaryExpr(topLevel, scope, except);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 23:
    case 24:
    case 25:
     if (hadOccur != null) hadOccur[0] = true;
     p = afterComments(p);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 23:
        t = jj_consume_token(23);
              checkExcept(except); p = sb.makeOneOrMore(p, makeLocation(t), null);
        break;
      case 24:
        t = jj_consume_token(24);
                checkExcept(except); p = sb.makeOptional(p, makeLocation(t), null);
        break;
      case 25:
        t = jj_consume_token(25);
                checkExcept(except); p = sb.makeZeroOrMore(p, makeLocation(t), null);
        break;
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case FANNOTATE:
          ;
          break;
        default:
          jj_la1[26] = jj_gen;
          break label_9;
        }
        t = jj_consume_token(FANNOTATE);
        e = AnnotationElement(false);
        if (topLevel)
          error("top_level_follow_annotation", t);
        else
          p = sb.annotateAfter(p, e);
      }
      break;
    default:
      jj_la1[27] = jj_gen;
      ;
    }
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern ElementExpr(Scope scope, Annotations a) throws ParseException {
  Token t;
  ParsedNameClass nc;
  ParsedPattern p;
    t = jj_consume_token(26);
    nc = NameClass(IN_ELEMENT, null);
    jj_consume_token(11);
    p = Expr(false, scope, null, null);
    p = afterComments(p);
    jj_consume_token(12);
    {if (true) return sb.makeElement(nc, p, makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern AttributeExpr(Scope scope, Annotations a) throws ParseException {
  Token t;
  ParsedNameClass nc;
  ParsedPattern p;
    t = jj_consume_token(27);
    nc = NameClass(IN_ATTRIBUTE, null);
    jj_consume_token(11);
    p = Expr(false, scope, null, null);
    p = afterComments(p);
    jj_consume_token(12);
    {if (true) return sb.makeAttribute(nc, p, makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass NameClass(int context, Annotations[] pa) throws ParseException {
  Annotations a;
  ParsedNameClass nc;
    a = Annotations();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 5:
    case 6:
    case 7:
    case 10:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 26:
    case 27:
    case 28:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
    case PREFIXED_NAME:
      nc = PrimaryNameClass(context, a);
      nc = AnnotateAfter(nc);
      nc = NameClassAlternatives(context, nc, pa);
      break;
    case 25:
      nc = AnyNameExceptClass(context, a, pa);
      break;
    case PREFIX_STAR:
      nc = NsNameExceptClass(context, a, pa);
      break;
    default:
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass AnnotateAfter(ParsedNameClass nc) throws ParseException {
  ParsedElementAnnotation e;
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FANNOTATE:
        ;
        break;
      default:
        jj_la1[29] = jj_gen;
        break label_10;
      }
      jj_consume_token(FANNOTATE);
      e = AnnotationElement(false);
                                               nc = ncb.annotateAfter(nc, e);
    }
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass NameClassAlternatives(int context, ParsedNameClass nc, Annotations[] pa) throws ParseException {
  Token t;
  ParsedNameClass[] nameClasses;
  int nNameClasses;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 20:
      nameClasses = new ParsedNameClass[2];
      nameClasses[0] = nc;
      nNameClasses = 1;
      label_11:
      while (true) {
        t = jj_consume_token(20);
        nc = BasicNameClass(context);
        nc = AnnotateAfter(nc);
      if (nNameClasses >= nameClasses.length) {
        ParsedNameClass[] oldNameClasses = nameClasses;
        nameClasses = new ParsedNameClass[oldNameClasses.length*2];
        System.arraycopy(oldNameClasses, 0, nameClasses, 0, oldNameClasses.length);
      }
      nameClasses[nNameClasses++] = nc;
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 20:
          ;
          break;
        default:
          jj_la1[30] = jj_gen;
          break label_11;
        }
      }
      Annotations a;
      if (pa == null)
        a = null;
      else {
        a = pa[0];
        pa[0] = null;
      }
      nc = ncb.makeChoice(Arrays.asList(nameClasses).subList(0,nNameClasses), makeLocation(t), a);
      break;
    default:
      jj_la1[31] = jj_gen;
      ;
    }
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass BasicNameClass(int context) throws ParseException {
  Annotations a;
  ParsedNameClass nc;
    a = Annotations();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 5:
    case 6:
    case 7:
    case 10:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 26:
    case 27:
    case 28:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
    case PREFIXED_NAME:
      nc = PrimaryNameClass(context, a);
      break;
    case 25:
    case PREFIX_STAR:
      nc = OpenNameClass(context, a);
      break;
    default:
      jj_la1[32] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass PrimaryNameClass(int context, Annotations a) throws ParseException {
  ParsedNameClass nc;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 5:
    case 6:
    case 7:
    case 10:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 26:
    case 27:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      nc = UnprefixedNameClass(context, a);
      break;
    case PREFIXED_NAME:
      nc = PrefixedNameClass(a);
      break;
    case 28:
      nc = ParenNameClass(context, a);
      break;
    default:
      jj_la1[33] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass OpenNameClass(int context, Annotations a) throws ParseException {
  Token t;
  LocatedString ns;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREFIX_STAR:
      ns = NsName();
                  checkNsName(context, ns); {if (true) return ncb.makeNsName(ns.getString(), ns.getLocation(), a);}
      break;
    case 25:
      t = jj_consume_token(25);
              checkAnyName(context, t); {if (true) return ncb.makeAnyName(makeLocation(t), a);}
      break;
    default:
      jj_la1[34] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass UnprefixedNameClass(int context, Annotations a) throws ParseException {
  LocatedString name;
    name = UnprefixedName();
    String ns;
    if ((context & (IN_ATTRIBUTE|IN_ELEMENT)) == IN_ATTRIBUTE)
      ns = "";
    else
      ns = defaultNamespace;
    {if (true) return ncb.makeName(ns, name.getString(), null, name.getLocation(), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass PrefixedNameClass(Annotations a) throws ParseException {
  Token t;
    t = jj_consume_token(PREFIXED_NAME);
    String qn = t.image;
    int colon = qn.indexOf(':');
    String prefix = qn.substring(0, colon);
    {if (true) return ncb.makeName(lookupPrefix(prefix, t), qn.substring(colon + 1), prefix, makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass NsNameExceptClass(int context, Annotations a, Annotations[] pa) throws ParseException {
  LocatedString ns;
  ParsedNameClass nc;
    ns = NsName();
    checkNsName(context, ns);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 30:
      nc = ExceptNameClass(context | IN_NS_NAME);
     nc = ncb.makeNsName(ns.getString(), nc, ns.getLocation(), a);
      nc = AnnotateAfter(nc);
      break;
    default:
      jj_la1[35] = jj_gen;
      nc = ncb.makeNsName(ns.getString(), ns.getLocation(), a);
      nc = AnnotateAfter(nc);
      nc = NameClassAlternatives(context, nc, pa);
    }
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public LocatedString NsName() throws ParseException {
  Token t;
    t = jj_consume_token(PREFIX_STAR);
    String qn = t.image;
    String prefix = qn.substring(0, qn.length() - 2);
    {if (true) return new LocatedString(lookupPrefix(prefix, t), t);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass AnyNameExceptClass(int context, Annotations a, Annotations[] pa) throws ParseException {
  Token t;
  ParsedNameClass nc;
    t = jj_consume_token(25);
    checkAnyName(context, t);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 30:
      nc = ExceptNameClass(context | IN_ANY_NAME);
     nc = ncb.makeAnyName(nc, makeLocation(t), a);
      nc = AnnotateAfter(nc);
      break;
    default:
      jj_la1[36] = jj_gen;
      nc = ncb.makeAnyName(makeLocation(t), a);
      nc = AnnotateAfter(nc);
      nc = NameClassAlternatives(context, nc, pa);
    }
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass ParenNameClass(int context, Annotations a) throws ParseException {
  Token t;
  ParsedNameClass nc;
  Annotations[] pa = new Annotations[]{ a };
    t = jj_consume_token(28);
    nc = NameClass(context, pa);
                                        nc = afterComments(nc);
    jj_consume_token(29);
    if (pa[0] != null)
      nc = ncb.makeChoice(Collections.singletonList(nc), makeLocation(t), pa[0]);
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedNameClass ExceptNameClass(int context) throws ParseException {
  ParsedNameClass nc;
    jj_consume_token(30);
    nc = BasicNameClass(context);
    {if (true) return nc;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern ListExpr(Scope scope, Annotations a) throws ParseException {
  Token t;
  ParsedPattern p;
    t = jj_consume_token(31);
    jj_consume_token(11);
    p = Expr(false, scope, null, null);
    p = afterComments(p);
    jj_consume_token(12);
    {if (true) return sb.makeList(p, makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern MixedExpr(Scope scope, Annotations a) throws ParseException {
  Token t;
  ParsedPattern p;
    t = jj_consume_token(32);
    jj_consume_token(11);
    p = Expr(false, scope, null, null);
    p = afterComments(p);
    jj_consume_token(12);
    {if (true) return sb.makeMixed(p, makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern GrammarExpr(Scope scope, Annotations a) throws ParseException {
  Token t;
  Grammar g;
    t = jj_consume_token(10);
                  g = sb.makeGrammar(scope);
    jj_consume_token(11);
    a = GrammarBody(g, g, a);
                                 topLevelComments(g);
    jj_consume_token(12);
    {if (true) return g.endGrammar(makeLocation(t), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern ParenExpr(boolean topLevel, Scope scope, Annotations a) throws ParseException {
  Token t;
  ParsedPattern p;
    t = jj_consume_token(28);
    p = Expr(topLevel, scope, t, a);
                                            p = afterComments(p);
    jj_consume_token(29);
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public Annotations GrammarBody(GrammarSection section, Scope scope, Annotations a) throws ParseException {
  ParsedElementAnnotation e;
    label_12:
    while (true) {
      if (jj_2_3(2)) {
        ;
      } else {
        break label_12;
      }
      e = AnnotationElementNotKeyword();
     if (a == null) a = sb.makeAnnotations(null, getContext()); a.addElement(e);
    }
    label_13:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
      case 5:
      case 6:
      case 7:
      case DOCUMENTATION:
      case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
        ;
        break;
      default:
        jj_la1[37] = jj_gen;
        break label_13;
      }
      GrammarComponent(section, scope);
    }
    {if (true) return a;}
    throw new Error("Missing return statement in function");
  }

  final public void GrammarComponent(GrammarSection section, Scope scope) throws ParseException {
  ParsedElementAnnotation e;
  Annotations a;
    a = Annotations();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 5:
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      Definition(section, scope, a);
      break;
    case 7:
      Include(section, scope, a);
      break;
    case 6:
      Div(section, scope, a);
      break;
    default:
      jj_la1[38] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_14:
    while (true) {
      if (jj_2_4(2)) {
        ;
      } else {
        break label_14;
      }
      e = AnnotationElementNotKeyword();
                                                    section.topLevelAnnotation(e);
    }
  }

  final public void Definition(GrammarSection section, Scope scope, Annotations a) throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      Define(section, scope, a);
      break;
    case 5:
      Start(section, scope, a);
      break;
    default:
      jj_la1[39] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Start(GrammarSection section, Scope scope, Annotations a) throws ParseException {
  Token t;
  GrammarSection.Combine combine;
  ParsedPattern p;
    t = jj_consume_token(5);
    combine = AssignOp();
    p = Expr(false, scope, null, null);
    section.define(GrammarSection.START, combine, p, makeLocation(t), a);
  }

  final public void Define(GrammarSection section, Scope scope, Annotations a) throws ParseException {
  LocatedString name;
  GrammarSection.Combine combine;
  ParsedPattern p;
    name = Identifier();
    combine = AssignOp();
    p = Expr(false, scope, null, null);
    section.define(name.getString(), combine, p, name.getLocation(), a);
  }

  final public GrammarSection.Combine AssignOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 2:
      jj_consume_token(2);
        {if (true) return null;}
      break;
    case 4:
      jj_consume_token(4);
           {if (true) return GrammarSection.COMBINE_CHOICE;}
      break;
    case 3:
      jj_consume_token(3);
           {if (true) return GrammarSection.COMBINE_INTERLEAVE;}
      break;
    default:
      jj_la1[40] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public void Include(GrammarSection section, Scope scope, Annotations a) throws ParseException {
  Token t;
  String href;
  String ns;
  Include include = section.makeInclude();
    t = jj_consume_token(7);
    href = Literal();
    ns = Inherit();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 11:
      jj_consume_token(11);
      a = IncludeBody(include, scope, a);
                                            topLevelComments(include);
      jj_consume_token(12);
      break;
    default:
      jj_la1[41] = jj_gen;
      ;
    }
    try {
      include.endInclude(parseable, resolve(href), ns, makeLocation(t), a);
    }
    catch (IllegalSchemaException e) { }
  }

  final public Annotations IncludeBody(GrammarSection section, Scope scope, Annotations a) throws ParseException {
  ParsedElementAnnotation e;
    label_15:
    while (true) {
      if (jj_2_5(2)) {
        ;
      } else {
        break label_15;
      }
      e = AnnotationElementNotKeyword();
     if (a == null) a = sb.makeAnnotations(null, getContext()); a.addElement(e);
    }
    label_16:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
      case 5:
      case 6:
      case DOCUMENTATION:
      case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
        ;
        break;
      default:
        jj_la1[42] = jj_gen;
        break label_16;
      }
      IncludeComponent(section, scope);
    }
    {if (true) return a;}
    throw new Error("Missing return statement in function");
  }

  final public void IncludeComponent(GrammarSection section, Scope scope) throws ParseException {
  ParsedElementAnnotation e;
  Annotations a;
    a = Annotations();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 5:
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      Definition(section, scope, a);
      break;
    case 6:
      IncludeDiv(section, scope, a);
      break;
    default:
      jj_la1[43] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_17:
    while (true) {
      if (jj_2_6(2)) {
        ;
      } else {
        break label_17;
      }
      e = AnnotationElementNotKeyword();
                                                    section.topLevelAnnotation(e);
    }
  }

  final public void Div(GrammarSection section, Scope scope, Annotations a) throws ParseException {
  Token t;
  Div div = section.makeDiv();
    t = jj_consume_token(6);
    jj_consume_token(11);
    a = GrammarBody(div, scope, a);
                                                 topLevelComments(div);
    jj_consume_token(12);
    div.endDiv(makeLocation(t), a);
  }

  final public void IncludeDiv(GrammarSection section, Scope scope, Annotations a) throws ParseException {
  Token t;
  Div div = section.makeDiv();
    t = jj_consume_token(6);
    jj_consume_token(11);
    a = IncludeBody(div, scope, a);
                                                 topLevelComments(div);
    jj_consume_token(12);
    div.endDiv(makeLocation(t), a);
  }

  final public ParsedPattern ExternalRefExpr(Scope scope, Annotations a) throws ParseException {
  Token t;
  String href;
  String ns;
    t = jj_consume_token(33);
    href = Literal();
    ns = Inherit();
    try {
      {if (true) return sb.makeExternalRef(parseable, resolve(href), ns, scope, makeLocation(t), a);}
    }
    catch (IllegalSchemaException e) {
      {if (true) return sb.makeErrorPattern();}
    }
    throw new Error("Missing return statement in function");
  }

  final public String Inherit() throws ParseException {
  String ns = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 15:
      jj_consume_token(15);
      jj_consume_token(2);
      ns = Prefix();
      break;
    default:
      jj_la1[44] = jj_gen;
      ;
    }
    if (ns == null)
      ns = defaultNamespace;
    {if (true) return ns;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern ParentExpr(Scope scope, Annotations a) throws ParseException {
  LocatedString name;
    jj_consume_token(34);
             a = addCommentsToChildAnnotations(a);
    name = Identifier();
    if(scope==null) {
      error("parent_ref_outside_grammar",name.getToken());
      {if (true) return sb.makeErrorPattern();}
    } else {
      {if (true) return scope.makeParentRef(name.getString(), name.getLocation(), a);}
    }
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern IdentifierExpr(Scope scope, Annotations a) throws ParseException {
  LocatedString name;
    name = Identifier();
    if(scope==null) {
      error("ref_outside_grammar",name.getToken());
      {if (true) return sb.makeErrorPattern();}
    } else {
      {if (true) return scope.makeRef(name.getString(), name.getLocation(), a);}
    }
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern ValueExpr(boolean topLevel, Annotations a) throws ParseException {
  LocatedString s;
    s = LocatedLiteral();
    if (topLevel && annotationsIncludeElements) {
      error("top_level_follow_annotation", s.getToken());
      a = null;
    }
    {if (true) return sb.makeValue("", "token", s.getString(), getContext(), defaultNamespace, s.getLocation(), a);}
    throw new Error("Missing return statement in function");
  }

  final public ParsedPattern DataExpr(boolean topLevel, Scope scope, Annotations a, Token[] except) throws ParseException {
  Token datatypeToken;
  Location loc;
  String datatype;
  String datatypeUri = null;
  String s = null;
  ParsedPattern e = null;
  DataPatternBuilder dpb;
    datatypeToken = DatatypeName();
    datatype = datatypeToken.image;
    loc = makeLocation(datatypeToken);
    int colon = datatype.indexOf(':');
    if (colon < 0)
      datatypeUri = "";
    else {
      String prefix = datatype.substring(0, colon);
      datatypeUri = lookupDatatype(prefix, datatypeToken);
      datatype = datatype.substring(colon + 1);
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LITERAL:
      s = Literal();
      if (topLevel && annotationsIncludeElements) {
        error("top_level_follow_annotation", datatypeToken);
        a = null;
      }
      {if (true) return sb.makeValue(datatypeUri, datatype, s, getContext(), defaultNamespace, loc, a);}
      break;
    default:
      jj_la1[48] = jj_gen;
        dpb = sb.makeDataPatternBuilder(datatypeUri, datatype, loc);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 11:
        Params(dpb);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 30:
          e = Except(scope, except);
          break;
        default:
          jj_la1[45] = jj_gen;
          ;
        }
        break;
      default:
        jj_la1[47] = jj_gen;
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 30:
          e = Except(scope, except);
          break;
        default:
          jj_la1[46] = jj_gen;
          ;
        }
      }
        {if (true) return e == null ? dpb.makePattern(loc, a) : dpb.makePattern(e, loc, a);}
    }
    throw new Error("Missing return statement in function");
  }

  final public Token DatatypeName() throws ParseException {
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 35:
      t = jj_consume_token(35);
      break;
    case 36:
      t = jj_consume_token(36);
      break;
    case PREFIXED_NAME:
      t = jj_consume_token(PREFIXED_NAME);
      break;
    default:
      jj_la1[49] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  final public LocatedString Identifier() throws ParseException {
  LocatedString s;
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      t = jj_consume_token(IDENTIFIER);
                      s = new LocatedString(t.image, t);
      break;
    case ESCAPED_IDENTIFIER:
      t = jj_consume_token(ESCAPED_IDENTIFIER);
                               s = new LocatedString(t.image.substring(1), t);
      break;
    default:
      jj_la1[50] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public String Prefix() throws ParseException {
  Token t;
  String prefix;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      t = jj_consume_token(IDENTIFIER);
                      prefix = t.image;
      break;
    case ESCAPED_IDENTIFIER:
      t = jj_consume_token(ESCAPED_IDENTIFIER);
                               prefix = t.image.substring(1);
      break;
    case 5:
    case 6:
    case 7:
    case 10:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 26:
    case 27:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
      t = Keyword();
                    prefix = t.image;
      break;
    default:
      jj_la1[51] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return lookupPrefix(prefix, t);}
    throw new Error("Missing return statement in function");
  }

  final public LocatedString UnprefixedName() throws ParseException {
  LocatedString s;
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      s = Identifier();
      break;
    case 5:
    case 6:
    case 7:
    case 10:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 26:
    case 27:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
      t = Keyword();
                     s = new LocatedString(t.image, t);
      break;
    default:
      jj_la1[52] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public void Params(DataPatternBuilder dpb) throws ParseException {
    jj_consume_token(11);
    label_18:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
      case 5:
      case 6:
      case 7:
      case 10:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 26:
      case 27:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case DOCUMENTATION:
      case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
        ;
        break;
      default:
        jj_la1[53] = jj_gen;
        break label_18;
      }
      Param(dpb);
    }
    jj_consume_token(12);
  }

  final public void Param(DataPatternBuilder dpb) throws ParseException {
  LocatedString name;
  Annotations a;
  String value;
    a = Annotations();
    name = UnprefixedName();
    jj_consume_token(2);
                                                  a = addCommentsToLeadingAnnotations(a);
    value = Literal();
    dpb.addParam(name.getString(), value, getContext(), defaultNamespace, name.getLocation(), a);
  }

  final public ParsedPattern Except(Scope scope, Token[] except) throws ParseException {
  Annotations a;
  ParsedPattern p;
  Token t;
  Token[] innerExcept = new Token[1];
    t = jj_consume_token(30);
    a = Annotations();
    p = PrimaryExpr(false, scope, a, innerExcept);
    checkExcept(innerExcept);
    except[0] = t;
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedElementAnnotation Documentation() throws ParseException {
  CommentList comments = getComments();
  ElementAnnotationBuilder eab;
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOCUMENTATION:
      t = jj_consume_token(DOCUMENTATION);
      break;
    case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
      t = jj_consume_token(DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT);
      break;
    default:
      jj_la1[54] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    eab = sb.makeElementAnnotationBuilder(WellKnownNamespaces.RELAX_NG_COMPATIBILITY_ANNOTATIONS,
                                          "documentation",
                                          getCompatibilityPrefix(),
                                          makeLocation(t),
                                          comments,
                                          getContext());
    eab.addText(mungeComment(t.image), makeLocation(t), null);
    label_19:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOCUMENTATION_CONTINUE:
        ;
        break;
      default:
        jj_la1[55] = jj_gen;
        break label_19;
      }
      t = jj_consume_token(DOCUMENTATION_CONTINUE);
                                  eab.addText("\u005cn" + mungeComment(t.image), makeLocation(t), null);
    }
    {if (true) return eab.makeElementAnnotation();}
    throw new Error("Missing return statement in function");
  }

  final public Annotations Annotations() throws ParseException {
  CommentList comments = getComments();
  Annotations a = null;
  ParsedElementAnnotation e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOCUMENTATION:
    case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
      a = sb.makeAnnotations(comments, getContext());
      label_20:
      while (true) {
        e = Documentation();
                           a.addElement(e);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOCUMENTATION:
        case DOCUMENTATION_AFTER_SINGLE_LINE_COMMENT:
          ;
          break;
        default:
          jj_la1[56] = jj_gen;
          break label_20;
        }
      }
      comments = getComments();
      if (comments != null)
        a.addLeadingComment(comments);
      break;
    default:
      jj_la1[57] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 1:
      jj_consume_token(1);
         if (a == null) a = sb.makeAnnotations(comments, getContext()); clearAttributeList(); annotationsIncludeElements = false;
      label_21:
      while (true) {
        if (jj_2_7(2)) {
          ;
        } else {
          break label_21;
        }
        PrefixedAnnotationAttribute(a, false);
      }
      label_22:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 5:
        case 6:
        case 7:
        case 10:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        case 18:
        case 19:
        case 26:
        case 27:
        case 31:
        case 32:
        case 33:
        case 34:
        case 35:
        case 36:
        case IDENTIFIER:
        case ESCAPED_IDENTIFIER:
        case PREFIXED_NAME:
          ;
          break;
        default:
          jj_la1[58] = jj_gen;
          break label_22;
        }
        e = AnnotationElement(false);
                                        a.addElement(e); annotationsIncludeElements = true;
      }
         a.addComment(getComments());
      jj_consume_token(9);
      break;
    default:
      jj_la1[59] = jj_gen;
      ;
    }
    if (a == null && comments != null)
      a = sb.makeAnnotations(comments, getContext());
    {if (true) return a;}
    throw new Error("Missing return statement in function");
  }

  final public void AnnotationAttribute(Annotations a) throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREFIXED_NAME:
      PrefixedAnnotationAttribute(a, true);
      break;
    case 5:
    case 6:
    case 7:
    case 10:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 26:
    case 27:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      UnprefixedAnnotationAttribute(a);
      break;
    default:
      jj_la1[60] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void PrefixedAnnotationAttribute(Annotations a, boolean nested) throws ParseException {
  Token t;
  String value;
    t = jj_consume_token(PREFIXED_NAME);
    jj_consume_token(2);
    value = Literal();
    String qn = t.image;
    int colon = qn.indexOf(':');
    String prefix = qn.substring(0, colon);
    String ns = lookupPrefix(prefix, t);
    if (ns == this.inheritedNs)
      error("inherited_annotation_namespace", t);
    else if (ns.length() == 0 && !nested)
      error("unqualified_annotation_attribute", t);
    else if (ns.equals(WellKnownNamespaces.RELAX_NG) && !nested)
      error("relax_ng_namespace", t);
    /*else if (ns.length() == 0
             && qn.length() - colon - 1 == 5
             && qn.regionMatches(colon + 1, "xmlns", 0, 5))
      error("xmlns_annotation_attribute", t);*/
    else if (ns.equals(WellKnownNamespaces.XMLNS))
      error("xmlns_annotation_attribute_uri", t);
    else {
      if (ns.length() == 0)
        prefix = null;
      addAttribute(a, ns, qn.substring(colon + 1), prefix, value, t);
    }
  }

  final public void UnprefixedAnnotationAttribute(Annotations a) throws ParseException {
  LocatedString name;
  String value;
    name = UnprefixedName();
    jj_consume_token(2);
    value = Literal();
    if (name.getString().equals("xmlns"))
      error("xmlns_annotation_attribute", name.getToken());
    else
      addAttribute(a, "", name.getString(), null, value, name.getToken());
  }

  final public ParsedElementAnnotation AnnotationElement(boolean nested) throws ParseException {
  ParsedElementAnnotation a;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREFIXED_NAME:
      a = PrefixedAnnotationElement(nested);
      break;
    case 5:
    case 6:
    case 7:
    case 10:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 26:
    case 27:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      a = UnprefixedAnnotationElement();
      break;
    default:
      jj_la1[61] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return a;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedElementAnnotation AnnotationElementNotKeyword() throws ParseException {
  ParsedElementAnnotation a;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREFIXED_NAME:
      a = PrefixedAnnotationElement(false);
      break;
    case IDENTIFIER:
    case ESCAPED_IDENTIFIER:
      a = IdentifierAnnotationElement();
      break;
    default:
      jj_la1[62] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return a;}
    throw new Error("Missing return statement in function");
  }

  final public ParsedElementAnnotation PrefixedAnnotationElement(boolean nested) throws ParseException {
  CommentList comments = getComments();
  Token t;
  ElementAnnotationBuilder eab;
    t = jj_consume_token(PREFIXED_NAME);
    String qn = t.image;
    int colon = qn.indexOf(':');
    String prefix = qn.substring(0, colon);
    String ns = lookupPrefix(prefix, t);
    if (ns == this.inheritedNs) {
      error("inherited_annotation_namespace", t);
      ns = "";
    }
    else if (!nested && ns.equals(WellKnownNamespaces.RELAX_NG)) {
      error("relax_ng_namespace", t);
      ns = "";
    }
    else {
      if (ns.length() == 0)
        prefix = null;
    }
    eab = sb.makeElementAnnotationBuilder(ns, qn.substring(colon + 1), prefix,
                                          makeLocation(t), comments, getContext());
    AnnotationElementContent(eab);
    {if (true) return eab.makeElementAnnotation();}
    throw new Error("Missing return statement in function");
  }

  final public ParsedElementAnnotation UnprefixedAnnotationElement() throws ParseException {
  CommentList comments = getComments();
  LocatedString name;
  ElementAnnotationBuilder eab;
    name = UnprefixedName();
    eab = sb.makeElementAnnotationBuilder("", name.getString(), null,
                                          name.getLocation(), comments, getContext());
    AnnotationElementContent(eab);
    {if (true) return eab.makeElementAnnotation();}
    throw new Error("Missing return statement in function");
  }

  final public ParsedElementAnnotation IdentifierAnnotationElement() throws ParseException {
  CommentList comments = getComments();
  LocatedString name;
  ElementAnnotationBuilder eab;
    name = Identifier();
    eab = sb.makeElementAnnotationBuilder("", name.getString(), null,
                                          name.getLocation(), comments, getContext());
    AnnotationElementContent(eab);
    {if (true) return eab.makeElementAnnotation();}
    throw new Error("Missing return statement in function");
  }

  final public void AnnotationElementContent(ElementAnnotationBuilder eab) throws ParseException {
  ParsedElementAnnotation e;
    jj_consume_token(1);
        clearAttributeList();
    label_23:
    while (true) {
      if (jj_2_8(2)) {
        ;
      } else {
        break label_23;
      }
      AnnotationAttribute(eab);
    }
    label_24:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 5:
      case 6:
      case 7:
      case 10:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 26:
      case 27:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
      case PREFIXED_NAME:
      case LITERAL:
        ;
        break;
      default:
        jj_la1[63] = jj_gen;
        break label_24;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LITERAL:
        AnnotationElementLiteral(eab);
        label_25:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 8:
            ;
            break;
          default:
            jj_la1[64] = jj_gen;
            break label_25;
          }
          jj_consume_token(8);
          AnnotationElementLiteral(eab);
        }
        break;
      case 5:
      case 6:
      case 7:
      case 10:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 26:
      case 27:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case IDENTIFIER:
      case ESCAPED_IDENTIFIER:
      case PREFIXED_NAME:
        e = AnnotationElement(true);
                                   eab.addElement(e);
        break;
      default:
        jj_la1[65] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    eab.addComment(getComments());
    jj_consume_token(9);
  }

  final public void AnnotationElementLiteral(ElementAnnotationBuilder eab) throws ParseException {
  Token t;
  CommentList comments = getComments();
    t = jj_consume_token(LITERAL);
                  eab.addText(unquote(t.image), makeLocation(t), comments);
  }

  final public String Literal() throws ParseException {
  Token t;
  String s;
  StringBuffer buf;
    t = jj_consume_token(LITERAL);
    s = unquote(t.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 8:
      buf = new StringBuffer(s);
      label_26:
      while (true) {
        jj_consume_token(8);
        t = jj_consume_token(LITERAL);
                         buf.append(unquote(t.image));
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 8:
          ;
          break;
        default:
          jj_la1[66] = jj_gen;
          break label_26;
        }
      }
      s = buf.toString();
      break;
    default:
      jj_la1[67] = jj_gen;
      ;
    }
    {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public LocatedString LocatedLiteral() throws ParseException {
  Token t;
  Token t2;
  String s;
  StringBuffer buf;
    t = jj_consume_token(LITERAL);
    s = unquote(t.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 8:
      buf = new StringBuffer(s);
      label_27:
      while (true) {
        jj_consume_token(8);
        t2 = jj_consume_token(LITERAL);
                          buf.append(unquote(t2.image));
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 8:
          ;
          break;
        default:
          jj_la1[68] = jj_gen;
          break label_27;
        }
      }
      s = buf.toString();
      break;
    default:
      jj_la1[69] = jj_gen;
      ;
    }
    {if (true) return new LocatedString(s, t);}
    throw new Error("Missing return statement in function");
  }

  final public Token Keyword() throws ParseException {
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 26:
      t = jj_consume_token(26);
      break;
    case 27:
      t = jj_consume_token(27);
      break;
    case 13:
      t = jj_consume_token(13);
      break;
    case 31:
      t = jj_consume_token(31);
      break;
    case 32:
      t = jj_consume_token(32);
      break;
    case 10:
      t = jj_consume_token(10);
      break;
    case 17:
      t = jj_consume_token(17);
      break;
    case 18:
      t = jj_consume_token(18);
      break;
    case 34:
      t = jj_consume_token(34);
      break;
    case 33:
      t = jj_consume_token(33);
      break;
    case 19:
      t = jj_consume_token(19);
      break;
    case 5:
      t = jj_consume_token(5);
      break;
    case 7:
      t = jj_consume_token(7);
      break;
    case 14:
      t = jj_consume_token(14);
      break;
    case 15:
      t = jj_consume_token(15);
      break;
    case 35:
      t = jj_consume_token(35);
      break;
    case 36:
      t = jj_consume_token(36);
      break;
    case 16:
      t = jj_consume_token(16);
      break;
    case 6:
      t = jj_consume_token(6);
      break;
    default:
      jj_la1[70] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_3R_43() {
    if (jj_scan_token(1)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_52()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(9)) return true;
    return false;
  }

  private boolean jj_3R_51() {
    if (jj_scan_token(ESCAPED_IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_50() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_41() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_50()) {
    jj_scanpos = xsp;
    if (jj_3R_51()) return true;
    }
    return false;
  }

  private boolean jj_3R_47() {
    if (jj_scan_token(PREFIXED_NAME)) return true;
    if (jj_3R_56()) return true;
    return false;
  }

  private boolean jj_3R_55() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(40)) {
    jj_scanpos = xsp;
    if (jj_scan_token(43)) return true;
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_scan_token(41)) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_45() {
    Token xsp;
    if (jj_3R_55()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_55()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_38() {
    if (jj_3R_48()) return true;
    return false;
  }

  private boolean jj_3R_42() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(5)) {
    jj_scanpos = xsp;
    if (jj_scan_token(6)) {
    jj_scanpos = xsp;
    if (jj_scan_token(7)) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_37() {
    if (jj_3R_47()) return true;
    return false;
  }

  private boolean jj_3R_54() {
    if (jj_3R_42()) return true;
    return false;
  }

  private boolean jj_3R_29() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_37()) {
    jj_scanpos = xsp;
    if (jj_3R_38()) return true;
    }
    return false;
  }

  private boolean jj_3R_44() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_53()) {
    jj_scanpos = xsp;
    if (jj_3R_54()) return true;
    }
    return false;
  }

  private boolean jj_3R_53() {
    if (jj_3R_41()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(2)) {
    jj_scanpos = xsp;
    if (jj_scan_token(3)) {
    jj_scanpos = xsp;
    if (jj_scan_token(4)) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_36() {
    if (jj_3R_45()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_46()) jj_scanpos = xsp;
    if (jj_3R_44()) return true;
    return false;
  }

  private boolean jj_3R_35() {
    if (jj_3R_43()) return true;
    if (jj_3R_44()) return true;
    return false;
  }

  private boolean jj_3R_34() {
    if (jj_3R_42()) return true;
    return false;
  }

  private boolean jj_3R_33() {
    if (jj_3R_41()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(1)) {
    jj_scanpos = xsp;
    if (jj_scan_token(2)) {
    jj_scanpos = xsp;
    if (jj_scan_token(3)) {
    jj_scanpos = xsp;
    if (jj_scan_token(4)) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_28()) return true;
    return false;
  }

  private boolean jj_3R_32() {
    if (jj_scan_token(PREFIXED_NAME)) return true;
    if (jj_scan_token(1)) return true;
    return false;
  }

  private boolean jj_3R_28() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) {
    jj_scanpos = xsp;
    if (jj_3R_36()) return true;
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_59() {
    if (jj_3R_43()) return true;
    return false;
  }

  private boolean jj_3_8() {
    if (jj_3R_31()) return true;
    return false;
  }

  private boolean jj_3R_56() {
    if (jj_scan_token(1)) return true;
    return false;
  }

  private boolean jj_3R_49() {
    if (jj_3R_57()) return true;
    if (jj_scan_token(2)) return true;
    return false;
  }

  private boolean jj_3R_40() {
    if (jj_3R_49()) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_3R_29()) return true;
    return false;
  }

  private boolean jj_3R_48() {
    if (jj_3R_41()) return true;
    if (jj_3R_56()) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_3R_29()) return true;
    return false;
  }

  private boolean jj_3_6() {
    if (jj_3R_29()) return true;
    return false;
  }

  private boolean jj_3R_62() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(26)) {
    jj_scanpos = xsp;
    if (jj_scan_token(27)) {
    jj_scanpos = xsp;
    if (jj_scan_token(13)) {
    jj_scanpos = xsp;
    if (jj_scan_token(31)) {
    jj_scanpos = xsp;
    if (jj_scan_token(32)) {
    jj_scanpos = xsp;
    if (jj_scan_token(10)) {
    jj_scanpos = xsp;
    if (jj_scan_token(17)) {
    jj_scanpos = xsp;
    if (jj_scan_token(18)) {
    jj_scanpos = xsp;
    if (jj_scan_token(34)) {
    jj_scanpos = xsp;
    if (jj_scan_token(33)) {
    jj_scanpos = xsp;
    if (jj_scan_token(19)) {
    jj_scanpos = xsp;
    if (jj_scan_token(5)) {
    jj_scanpos = xsp;
    if (jj_scan_token(7)) {
    jj_scanpos = xsp;
    if (jj_scan_token(14)) {
    jj_scanpos = xsp;
    if (jj_scan_token(15)) {
    jj_scanpos = xsp;
    if (jj_scan_token(35)) {
    jj_scanpos = xsp;
    if (jj_scan_token(36)) {
    jj_scanpos = xsp;
    if (jj_scan_token(16)) {
    jj_scanpos = xsp;
    if (jj_scan_token(6)) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_61() {
    if (jj_3R_62()) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_3R_28()) return true;
    return false;
  }

  private boolean jj_3R_30() {
    if (jj_scan_token(PREFIXED_NAME)) return true;
    if (jj_scan_token(2)) return true;
    return false;
  }

  private boolean jj_3R_60() {
    if (jj_3R_41()) return true;
    return false;
  }

  private boolean jj_3R_58() {
    if (jj_3R_57()) return true;
    return false;
  }

  private boolean jj_3R_57() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_60()) {
    jj_scanpos = xsp;
    if (jj_3R_61()) return true;
    }
    return false;
  }

  private boolean jj_3_5() {
    if (jj_3R_29()) return true;
    return false;
  }

  private boolean jj_3R_31() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_39()) {
    jj_scanpos = xsp;
    if (jj_3R_40()) return true;
    }
    return false;
  }

  private boolean jj_3R_39() {
    if (jj_3R_30()) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_3R_30()) return true;
    return false;
  }

  private boolean jj_3R_46() {
    if (jj_3R_43()) return true;
    return false;
  }

  private boolean jj_3R_52() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(57)) {
    jj_scanpos = xsp;
    if (jj_3R_58()) {
    jj_scanpos = xsp;
    if (jj_scan_token(2)) {
    jj_scanpos = xsp;
    if (jj_scan_token(58)) {
    jj_scanpos = xsp;
    if (jj_scan_token(8)) {
    jj_scanpos = xsp;
    if (jj_3R_59()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  /** Generated Token Manager. */
  public CompactSyntaxTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[71];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x9c0e0402,0x1e,0x2,0xe2,0x1c,0xe0,0xe0,0x0,0x0,0x0,0x8c0fe5e6,0x8c0fe5e6,0x402,0x16000,0x16000,0x8c0fe4e0,0x6000,0x8000,0x0,0x9c0e0400,0x100000,0x200000,0x400000,0x700000,0x700000,0x3800000,0x0,0x3800000,0x9e0fe4e0,0x0,0x100000,0x100000,0x9e0fe4e0,0x9c0fe4e0,0x2000000,0x40000000,0x40000000,0xe2,0xe0,0x20,0x1c,0x800,0x62,0x60,0x8000,0x40000000,0x40000000,0x800,0x0,0x0,0x0,0x8c0fe4e0,0x8c0fe4e0,0x8c0fe4e2,0x0,0x0,0x0,0x0,0x8c0fe4e0,0x2,0x8c0fe4e0,0x8c0fe4e0,0x0,0x8c0fe4e0,0x100,0x8c0fe4e0,0x100,0x100,0x100,0x100,0x8c0fe4e0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x6c0091f,0x0,0x0,0x2c00900,0x0,0xc00000,0x0,0x900,0x200,0x900,0x6c0001f,0x6c0001f,0x900,0x0,0x0,0xc0001f,0x0,0x4000000,0x8000000,0x6c0001f,0x0,0x0,0x0,0x0,0x0,0x0,0x8000000,0x0,0x3c0001f,0x8000000,0x0,0x0,0x3c0001f,0x2c0001f,0x1000000,0x0,0x0,0xc00900,0xc00000,0xc00000,0x0,0x0,0xc00900,0xc00000,0x0,0x0,0x0,0x0,0x4000000,0x2000018,0xc00000,0xc0001f,0xc0001f,0xc0091f,0x900,0x200,0x900,0x900,0x2c0001f,0x0,0x2c0001f,0x2c0001f,0x2c00000,0x6c0001f,0x0,0x6c0001f,0x0,0x0,0x0,0x0,0x1f,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[8];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public CompactSyntax(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CompactSyntax(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CompactSyntaxTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 71; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 71; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public CompactSyntax(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new CompactSyntaxTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 71; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 71; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public CompactSyntax(CompactSyntaxTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 71; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(CompactSyntaxTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 71; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[61];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 71; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 61; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 8; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
