package com.sun.xml.internal.xsom.impl.scd;

import java.util.*;
import java.io.*;
import com.sun.xml.internal.xsom.impl.UName;
import javax.xml.namespace.*;

public class SCDParser implements SCDParserConstants {
  private NamespaceContext nsc;
  public SCDParser(String text,NamespaceContext nsc) {
    this(new StringReader(text));
    this.nsc = nsc;
  }
  private String trim(String s) {
    return s.substring(1,s.length()-1);
  }
  private String resolvePrefix(String prefix) throws ParseException {
    try {
      String r=nsc.getNamespaceURI(prefix);
      // grrr!!
      if(prefix.equals(""))
        return r;
      if(!r.equals(""))
        return r;
    } catch( IllegalArgumentException e ) {
      ; // report an error
    }
    throw new ParseException("Unbound prefix: "+prefix);
  }

  // "[^:]+"
  final public UName QName() throws ParseException {
                  Token p,l=null;
    p = jj_consume_token(NCNAME);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 15:
      jj_consume_token(15);
      l = jj_consume_token(NCNAME);
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    if(l==null)
      {if (true) return new UName(resolvePrefix(""),p.image);}
    else
      {if (true) return new UName(resolvePrefix(p.image),l.image);}
    throw new Error("Missing return statement in function");
  }

  final public String Prefix() throws ParseException {
                    Token p;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NCNAME:
      p = jj_consume_token(NCNAME);
              {if (true) return resolvePrefix(p.image);}
      break;
    default:
      jj_la1[1] = jj_gen;
   {if (true) return resolvePrefix("");}
    }
    throw new Error("Missing return statement in function");
  }

  final public List RelativeSchemaComponentPath() throws ParseException {
  List steps = new ArrayList();
  Step s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 16:
    case 17:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 16:
        jj_consume_token(16);
          steps.add(new Step.Any(Axis.ROOT));
        break;
      case 17:
        jj_consume_token(17);
          steps.add(new Step.Any(Axis.DESCENDANTS));
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    s = Step();
            steps.add(s);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 16:
      case 17:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 16:
        jj_consume_token(16);
        break;
      case 17:
        jj_consume_token(17);
            steps.add(new Step.Any(Axis.DESCENDANTS));
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      s = Step();
              steps.add(s);
    }
    {if (true) return steps;}
    throw new Error("Missing return statement in function");
  }

  final public Step Step() throws ParseException {
  Step s; String p; Token n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 18:
    case 19:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 18:
        jj_consume_token(18);
        break;
      case 19:
        jj_consume_token(19);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      s = NameOrWildcard(Axis.ATTRIBUTE);
      break;
    case NCNAME:
    case 20:
    case 45:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 20:
        jj_consume_token(20);
        break;
      default:
        jj_la1[7] = jj_gen;
        ;
      }
      s = NameOrWildcard(Axis.ELEMENT);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
        Predicate(s);
        break;
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      break;
    case 21:
      jj_consume_token(21);
      s = NameOrWildcard(Axis.SUBSTITUTION_GROUP);
      break;
    case 22:
    case 23:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 22:
        jj_consume_token(22);
        break;
      case 23:
        jj_consume_token(23);
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      s = NameOrWildcardOrAnonymous(Axis.TYPE_DEFINITION);
      break;
    case 24:
      jj_consume_token(24);
      s = NameOrWildcard(Axis.BASETYPE);
      break;
    case 25:
      jj_consume_token(25);
      s = NameOrWildcard(Axis.PRIMITIVE_TYPE);
      break;
    case 26:
      jj_consume_token(26);
      s = NameOrWildcardOrAnonymous(Axis.ITEM_TYPE);
      break;
    case 27:
      jj_consume_token(27);
      s = NameOrWildcardOrAnonymous(Axis.MEMBER_TYPE);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
        Predicate(s);
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      break;
    case 28:
      jj_consume_token(28);
      s = NameOrWildcardOrAnonymous(Axis.SCOPE);
      break;
    case 29:
      jj_consume_token(29);
      s = NameOrWildcard(Axis.ATTRIBUTE_GROUP);
      break;
    case 30:
      jj_consume_token(30);
      s = NameOrWildcard(Axis.MODEL_GROUP_DECL);
      break;
    case 31:
      jj_consume_token(31);
      s = NameOrWildcard(Axis.IDENTITY_CONSTRAINT);
      break;
    case 32:
      jj_consume_token(32);
      s = NameOrWildcard(Axis.REFERENCED_KEY);
      break;
    case 33:
      jj_consume_token(33);
      s = NameOrWildcard(Axis.NOTATION);
      break;
    case 34:
      jj_consume_token(34);
                           s=new Step.Any(Axis.MODELGROUP_SEQUENCE);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
        Predicate(s);
        break;
      default:
        jj_la1[11] = jj_gen;
        ;
      }
      break;
    case 35:
      jj_consume_token(35);
                           s=new Step.Any(Axis.MODELGROUP_CHOICE);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
        Predicate(s);
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
      break;
    case 36:
      jj_consume_token(36);
                           s=new Step.Any(Axis.MODELGROUP_ALL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
        Predicate(s);
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
      break;
    case 37:
      jj_consume_token(37);
                           s=new Step.Any(Axis.MODELGROUP_ANY);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
        Predicate(s);
        break;
      default:
        jj_la1[14] = jj_gen;
        ;
      }
      break;
    case 38:
      jj_consume_token(38);
                           s=new Step.Any(Axis.WILDCARD);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
        Predicate(s);
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
      break;
    case 39:
      jj_consume_token(39);
                           s=new Step.Any(Axis.ATTRIBUTE_WILDCARD);
      break;
    case 40:
      jj_consume_token(40);
                           s=new Step.Any(Axis.FACET);
      break;
    case 41:
      jj_consume_token(41);
      n = jj_consume_token(FACETNAME);
                           s=new Step.Facet(Axis.FACET,n.image);
      break;
    case 42:
      jj_consume_token(42);
                           s=new Step.Any(Axis.DESCENDANTS);
      break;
    case 43:
      jj_consume_token(43);
      p = Prefix();
                           s=new Step.Schema(Axis.X_SCHEMA,p);
      break;
    case 44:
      jj_consume_token(44);
                           s=new Step.Any(Axis.X_SCHEMA);
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
   {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public Step NameOrWildcard(Axis a) throws ParseException {
                               UName un;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NCNAME:
      un = QName();
               {if (true) return new Step.Named(a,un);}
      break;
    case 45:
      jj_consume_token(45);
               {if (true) return new Step.Any(a);}
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Step NameOrWildcardOrAnonymous(Axis a) throws ParseException {
                                          UName un;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NCNAME:
      un = QName();
               {if (true) return new Step.Named(a,un);}
      break;
    case 45:
      jj_consume_token(45);
               {if (true) return new Step.Any(a);}
      break;
    case 46:
      jj_consume_token(46);
                 {if (true) return new Step.AnonymousType(a);}
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public int Predicate(Step s) throws ParseException {
  Token t;
    t = jj_consume_token(NUMBER);
               {if (true) return s.predicate=Integer.parseInt(trim(t.image));}
    throw new Error("Missing return statement in function");
  }

  public SCDParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_0();
      jj_la1_1();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x8000,0x1000,0x30000,0x30000,0x30000,0x30000,0xc0000,0x100000,0x2000,0xc00000,0x2000,0x2000,0x2000,0x2000,0x2000,0x2000,0xfffc1000,0x1000,0x1000,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3fff,0x2000,0x6000,};
   }

  public SCDParser(java.io.InputStream stream) {
     this(stream, null);
  }
  public SCDParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new SCDParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  public SCDParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SCDParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  public SCDParser(SCDParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  public void ReInit(SCDParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[47];
    for (int i = 0; i < 47; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
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
    for (int i = 0; i < 47; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

}
