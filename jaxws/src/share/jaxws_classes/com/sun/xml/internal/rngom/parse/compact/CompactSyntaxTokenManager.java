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

/** Token Manager. */
public class CompactSyntaxTokenManager implements CompactSyntaxConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x1f8c0fe4e0L) != 0L)
         {
            jjmatchedKind = 54;
            return 43;
         }
         if ((active0 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 60;
            return -1;
         }
         return -1;
      case 1:
         if ((active0 & 0x1f8c0fe4e0L) != 0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 1;
            return 43;
         }
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 60;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 2:
         if ((active0 & 0x1f8c0fe4a0L) != 0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 2;
            return 43;
         }
         if ((active0 & 0x40L) != 0L)
            return 43;
         return -1;
      case 3:
         if ((active0 & 0x1f0c0be4a0L) != 0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 3;
            return 43;
         }
         if ((active0 & 0x80040000L) != 0L)
            return 43;
         return -1;
      case 4:
         if ((active0 & 0xe0c09e480L) != 0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 4;
            return 43;
         }
         if ((active0 & 0x1100020020L) != 0L)
            return 43;
         return -1;
      case 5:
         if ((active0 & 0x20c09e480L) != 0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 5;
            return 43;
         }
         if ((active0 & 0xc00000000L) != 0L)
            return 43;
         return -1;
      case 6:
         if ((active0 & 0x208092000L) != 0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 6;
            return 43;
         }
         if ((active0 & 0x400c480L) != 0L)
            return 43;
         return -1;
      case 7:
         if ((active0 & 0x8092000L) != 0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 7;
            return 43;
         }
         if ((active0 & 0x200000000L) != 0L)
            return 43;
         return -1;
      case 8:
         if ((active0 & 0x80000L) != 0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 8;
            return 43;
         }
         if ((active0 & 0x8012000L) != 0L)
            return 43;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 38:
         jjmatchedKind = 21;
         return jjMoveStringLiteralDfa1_0(0x8L);
      case 40:
         return jjStopAtPos(0, 28);
      case 41:
         return jjStopAtPos(0, 29);
      case 42:
         return jjStopAtPos(0, 25);
      case 43:
         return jjStopAtPos(0, 23);
      case 44:
         return jjStopAtPos(0, 22);
      case 45:
         return jjStopAtPos(0, 30);
      case 61:
         return jjStopAtPos(0, 2);
      case 62:
         return jjMoveStringLiteralDfa1_0(0x800000000000000L);
      case 63:
         return jjStopAtPos(0, 24);
      case 91:
         return jjStopAtPos(0, 1);
      case 93:
         return jjStopAtPos(0, 9);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x8000000L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x14040L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x204020000L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x8080L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x100000000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x82000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x800000020L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x1000040000L);
      case 123:
         return jjStopAtPos(0, 11);
      case 124:
         jjmatchedKind = 20;
         return jjMoveStringLiteralDfa1_0(0x10L);
      case 125:
         return jjStopAtPos(0, 12);
      case 126:
         return jjStopAtPos(0, 8);
      default :
         return jjMoveNfa_0(3, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 61:
         if ((active0 & 0x8L) != 0L)
            return jjStopAtPos(1, 3);
         else if ((active0 & 0x10L) != 0L)
            return jjStopAtPos(1, 4);
         break;
      case 62:
         if ((active0 & 0x800000000000000L) != 0L)
            return jjStopAtPos(1, 59);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x400012000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x44000L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x180000040L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000L);
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x8080L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000080000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x400L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x808000020L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x420L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x80L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000L);
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      case 104:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      case 107:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L);
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0xc00000000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000L);
      case 116:
         return jjMoveStringLiteralDfa3_0(active0, 0x208090000L);
      case 118:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(2, 6, 43);
         break;
      case 120:
         return jjMoveStringLiteralDfa3_0(active0, 0x100040000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x14000L);
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0x170000a000L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      case 109:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000400L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000020L);
      case 116:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(3, 18, 43);
         else if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(3, 31, 43);
         return jjMoveStringLiteralDfa4_0(active0, 0x20000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(4, 32, 43);
         break;
      case 101:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000000L);
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x80000L);
      case 109:
         return jjMoveStringLiteralDfa5_0(active0, 0x400L);
      case 110:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 36, 43);
         return jjMoveStringLiteralDfa5_0(active0, 0xc00000000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x200008000L);
      case 115:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      case 116:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(4, 5, 43);
         return jjMoveStringLiteralDfa5_0(active0, 0x10000L);
      case 117:
         return jjMoveStringLiteralDfa5_0(active0, 0x4080L);
      case 121:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(4, 17, 43);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x400L);
      case 98:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000000L);
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x80L);
      case 103:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(5, 35, 43);
         break;
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000L);
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x84000L);
      case 110:
         return jjMoveStringLiteralDfa6_0(active0, 0x204000000L);
      case 112:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 116:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(5, 34, 43);
         break;
      case 121:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x200002000L);
      case 101:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(6, 7, 43);
         break;
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x80000L);
      case 112:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000L);
      case 114:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(6, 10, 43);
         break;
      case 116:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(6, 14, 43);
         else if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(6, 15, 43);
         else if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(6, 26, 43);
         break;
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x2000L);
      case 101:
         return jjMoveStringLiteralDfa8_0(active0, 0x10000L);
      case 108:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(7, 33, 43);
         break;
      case 116:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000000L);
      case 119:
         return jjMoveStringLiteralDfa8_0(active0, 0x80000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(8, 13, 43);
         else if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(8, 27, 43);
         return jjMoveStringLiteralDfa9_0(active0, 0x80000L);
      case 115:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(8, 16, 43);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(9, 19, 43);
         break;
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec3 = {
   0x0L, 0xffffffffffffc000L, 0xfffff0007fffffffL, 0x7fffffL
};
static final long[] jjbitVec4 = {
   0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec5 = {
   0x7ff3ffffffffffffL, 0x7ffffffffffffdfeL, 0xffffffffffffffffL, 0xfc31ffffffffe00fL
};
static final long[] jjbitVec6 = {
   0xffffffL, 0xffffffffffff0000L, 0xf80001ffffffffffL, 0x3L
};
static final long[] jjbitVec7 = {
   0x0L, 0x0L, 0xfffffffbffffd740L, 0xffffd547f7fffL
};
static final long[] jjbitVec8 = {
   0xffffffffffffdffeL, 0xffffffffdffeffffL, 0xffffffffffff0003L, 0x33fcfffffff199fL
};
static final long[] jjbitVec9 = {
   0xfffe000000000000L, 0xfffffffe027fffffL, 0x7fL, 0x707ffffff0000L
};
static final long[] jjbitVec10 = {
   0x7fffffe00000000L, 0xfffe0000000007feL, 0x7cffffffffffffffL, 0x60002f7fffL
};
static final long[] jjbitVec11 = {
   0x23ffffffffffffe0L, 0x3ff000000L, 0x3c5fdfffff99fe0L, 0x30003b0000000L
};
static final long[] jjbitVec12 = {
   0x36dfdfffff987e0L, 0x1c00005e000000L, 0x23edfdfffffbafe0L, 0x100000000L
};
static final long[] jjbitVec13 = {
   0x23cdfdfffff99fe0L, 0x3b0000000L, 0x3bfc718d63dc7e0L, 0x0L
};
static final long[] jjbitVec14 = {
   0x3effdfffffddfe0L, 0x300000000L, 0x3effdfffffddfe0L, 0x340000000L
};
static final long[] jjbitVec15 = {
   0x3fffdfffffddfe0L, 0x300000000L, 0x0L, 0x0L
};
static final long[] jjbitVec16 = {
   0xd7ffffffffffeL, 0x3fL, 0x200d6caefef02596L, 0x1fL
};
static final long[] jjbitVec17 = {
   0x0L, 0x3fffffffeffL, 0x0L, 0x0L
};
static final long[] jjbitVec18 = {
   0x0L, 0x0L, 0xffffffff00000000L, 0x7fffffffff003fL
};
static final long[] jjbitVec19 = {
   0x500000000007daedL, 0x2c62ab82315001L, 0xf580c90040000000L, 0x201080000000007L
};
static final long[] jjbitVec20 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffff0fffffffL, 0x3ffffffffffffffL
};
static final long[] jjbitVec21 = {
   0xffffffff3f3fffffL, 0x3fffffffaaff3f3fL, 0x5fdfffffffffffffL, 0x1fdc1fff0fcf1fdcL
};
static final long[] jjbitVec22 = {
   0x4c4000000000L, 0x0L, 0x7L, 0x0L
};
static final long[] jjbitVec23 = {
   0x3fe00000080L, 0xfffffffffffffffeL, 0xfffffffe001fffffL, 0x7ffffffffffffffL
};
static final long[] jjbitVec24 = {
   0x1fffffffffe0L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec25 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x3fffffffffL, 0x0L
};
static final long[] jjbitVec26 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffL, 0x0L
};
static final long[] jjbitVec27 = {
   0x0L, 0x0L, 0x80000000000000L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec28 = {
   0xffffffL, 0xffffffffffff0000L, 0xf80001ffffffffffL, 0x30003L
};
static final long[] jjbitVec29 = {
   0xffffffffffffffffL, 0x30000003fL, 0xfffffffbffffd7c0L, 0xffffd547f7fffL
};
static final long[] jjbitVec30 = {
   0xffffffffffffdffeL, 0xffffffffdffeffffL, 0xffffffffffff007bL, 0x33fcfffffff199fL
};
static final long[] jjbitVec31 = {
   0xfffe000000000000L, 0xfffffffe027fffffL, 0xbbfffffbfffe007fL, 0x707ffffff0016L
};
static final long[] jjbitVec32 = {
   0x7fffffe00000000L, 0xffff03ff0007ffffL, 0x7cffffffffffffffL, 0x3ff3dffffef7fffL
};
static final long[] jjbitVec33 = {
   0xf3ffffffffffffeeL, 0xffcfff1e3fffL, 0xd3c5fdfffff99feeL, 0x3ffcfb080399fL
};
static final long[] jjbitVec34 = {
   0xd36dfdfffff987e4L, 0x1fffc05e003987L, 0xf3edfdfffffbafeeL, 0xffc100003bbfL
};
static final long[] jjbitVec35 = {
   0xf3cdfdfffff99feeL, 0xffc3b0c0398fL, 0xc3bfc718d63dc7ecL, 0xff8000803dc7L
};
static final long[] jjbitVec36 = {
   0xc3effdfffffddfeeL, 0xffc300603ddfL, 0xc3effdfffffddfecL, 0xffc340603ddfL
};
static final long[] jjbitVec37 = {
   0xc3fffdfffffddfecL, 0xffc300803dcfL, 0x0L, 0x0L
};
static final long[] jjbitVec38 = {
   0x7ff7ffffffffffeL, 0x3ff7fffL, 0x3bff6caefef02596L, 0x3ff3f5fL
};
static final long[] jjbitVec39 = {
   0xc2a003ff03000000L, 0xfffe03fffffffeffL, 0x2fe3ffffebf0fdfL, 0x0L
};
static final long[] jjbitVec40 = {
   0x0L, 0x0L, 0x0L, 0x21fff0000L
};
static final long[] jjbitVec41 = {
   0x3efffe000000a0L, 0xfffffffffffffffeL, 0xfffffffe661fffffL, 0x77ffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 43;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0xfffffffffffff9ffL & l) != 0L)
                  {
                     if (kind > 60)
                        kind = 60;
                  }
                  if ((0x100000601L & l) != 0L)
                  {
                     if (kind > 39)
                        kind = 39;
                     jjCheckNAdd(0);
                  }
                  else if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 31;
                  else if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 22;
                  else if (curChar == 35)
                  {
                     if (kind > 42)
                        kind = 42;
                     jjCheckNAdd(5);
                  }
                  if (curChar == 39)
                     jjCheckNAddTwoStates(13, 14);
                  else if (curChar == 34)
                     jjCheckNAddTwoStates(10, 11);
                  else if (curChar == 35)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 43:
                  if ((0x3ff600000000000L & l) != 0L)
                     jjCheckNAddTwoStates(39, 40);
                  else if (curChar == 58)
                     jjstateSet[jjnewStateCnt++] = 41;
                  if ((0x3ff600000000000L & l) != 0L)
                     jjCheckNAddTwoStates(36, 38);
                  else if (curChar == 58)
                     jjstateSet[jjnewStateCnt++] = 37;
                  if ((0x3ff600000000000L & l) != 0L)
                  {
                     if (kind > 54)
                        kind = 54;
                     jjCheckNAdd(35);
                  }
                  break;
               case 0:
                  if ((0x100000601L & l) == 0L)
                     break;
                  if (kind > 39)
                     kind = 39;
                  jjCheckNAdd(0);
                  break;
               case 1:
                  if (curChar != 35)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(2);
                  break;
               case 2:
                  if ((0xfffffffffffffbfeL & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(2);
                  break;
               case 4:
                  if (curChar != 35)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(5);
                  break;
               case 5:
                  if ((0xfffffffffffffbfeL & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(5);
                  break;
               case 8:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 55)
                     kind = 55;
                  jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 9:
                  if (curChar == 34)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 10:
                  if ((0xfffffffbfffffffeL & l) != 0L)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 11:
               case 20:
                  if (curChar == 34 && kind > 58)
                     kind = 58;
                  break;
               case 12:
                  if (curChar == 39)
                     jjCheckNAddTwoStates(13, 14);
                  break;
               case 13:
                  if ((0xffffff7ffffffffeL & l) != 0L)
                     jjCheckNAddTwoStates(13, 14);
                  break;
               case 14:
               case 29:
                  if (curChar == 39 && kind > 58)
                     kind = 58;
                  break;
               case 15:
                  if (curChar == 34)
                     jjCheckNAddStates(0, 2);
                  break;
               case 16:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 17:
               case 19:
                  if (curChar == 34)
                     jjCheckNAdd(16);
                  break;
               case 18:
                  if (curChar == 34)
                     jjAddStates(3, 4);
                  break;
               case 21:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 23:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 24:
                  if (curChar == 39)
                     jjCheckNAddStates(5, 7);
                  break;
               case 25:
                  if ((0xffffff7fffffffffL & l) != 0L)
                     jjCheckNAddStates(5, 7);
                  break;
               case 26:
               case 28:
                  if (curChar == 39)
                     jjCheckNAdd(25);
                  break;
               case 27:
                  if (curChar == 39)
                     jjAddStates(8, 9);
                  break;
               case 30:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 31:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 24;
                  break;
               case 32:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 33:
                  if ((0xfffffffffffff9ffL & l) != 0L && kind > 60)
                     kind = 60;
                  break;
               case 35:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 54)
                     kind = 54;
                  jjCheckNAdd(35);
                  break;
               case 36:
                  if ((0x3ff600000000000L & l) != 0L)
                     jjCheckNAddTwoStates(36, 38);
                  break;
               case 37:
                  if (curChar == 42 && kind > 56)
                     kind = 56;
                  break;
               case 38:
                  if (curChar == 58)
                     jjstateSet[jjnewStateCnt++] = 37;
                  break;
               case 39:
                  if ((0x3ff600000000000L & l) != 0L)
                     jjCheckNAddTwoStates(39, 40);
                  break;
               case 40:
                  if (curChar == 58)
                     jjstateSet[jjnewStateCnt++] = 41;
                  break;
               case 42:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 57)
                     kind = 57;
                  jjstateSet[jjnewStateCnt++] = 42;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if (kind > 60)
                     kind = 60;
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 54)
                        kind = 54;
                     jjCheckNAddStates(10, 14);
                  }
                  else if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 43:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(39, 40);
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(36, 38);
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 54)
                        kind = 54;
                     jjCheckNAdd(35);
                  }
                  break;
               case 2:
                  if (kind > 40)
                     kind = 40;
                  jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 5:
                  if (kind > 42)
                     kind = 42;
                  jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 6:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 7:
               case 8:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 55)
                     kind = 55;
                  jjCheckNAdd(8);
                  break;
               case 10:
                  jjAddStates(15, 16);
                  break;
               case 13:
                  jjAddStates(17, 18);
                  break;
               case 16:
                  jjAddStates(0, 2);
                  break;
               case 25:
                  jjAddStates(5, 7);
                  break;
               case 33:
                  if (kind > 60)
                     kind = 60;
                  break;
               case 34:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 54)
                     kind = 54;
                  jjCheckNAddStates(10, 14);
                  break;
               case 35:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 54)
                     kind = 54;
                  jjCheckNAdd(35);
                  break;
               case 36:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(36, 38);
                  break;
               case 39:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(39, 40);
                  break;
               case 41:
               case 42:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 57)
                     kind = 57;
                  jjCheckNAdd(42);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 60)
                        kind = 60;
                  }
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 54)
                        kind = 54;
                     jjCheckNAddStates(10, 14);
                  }
                  break;
               case 43:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 54)
                        kind = 54;
                     jjCheckNAdd(35);
                  }
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(36, 38);
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(39, 40);
                  break;
               case 2:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 5:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 55)
                     kind = 55;
                  jjCheckNAdd(8);
                  break;
               case 8:
                  if (!jjCanMove_2(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 55)
                     kind = 55;
                  jjCheckNAdd(8);
                  break;
               case 10:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(15, 16);
                  break;
               case 13:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(17, 18);
                  break;
               case 16:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(0, 2);
                  break;
               case 25:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(5, 7);
                  break;
               case 33:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 60)
                     kind = 60;
                  break;
               case 34:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 54)
                     kind = 54;
                  jjCheckNAddStates(10, 14);
                  break;
               case 35:
                  if (!jjCanMove_2(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 54)
                     kind = 54;
                  jjCheckNAdd(35);
                  break;
               case 36:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(36, 38);
                  break;
               case 39:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(39, 40);
                  break;
               case 41:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 57)
                     kind = 57;
                  jjCheckNAdd(42);
                  break;
               case 42:
                  if (!jjCanMove_2(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 57)
                     kind = 57;
                  jjCheckNAdd(42);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 43 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_1()
{
   return jjMoveNfa_1(1, 0);
}
private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 10;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((0xfffffffffffff9ffL & l) != 0L)
                  {
                     if (kind > 60)
                        kind = 60;
                  }
                  if ((0x100000601L & l) != 0L)
                  {
                     if (kind > 39)
                        kind = 39;
                     jjCheckNAdd(0);
                  }
                  if ((0x401L & l) != 0L)
                     jjCheckNAddStates(19, 22);
                  break;
               case 0:
                  if ((0x100000601L & l) == 0L)
                     break;
                  if (kind > 39)
                     kind = 39;
                  jjCheckNAdd(0);
                  break;
               case 2:
                  if ((0x401L & l) != 0L)
                     jjCheckNAddStates(19, 22);
                  break;
               case 3:
                  if ((0x100000200L & l) != 0L)
                     jjCheckNAddTwoStates(3, 6);
                  break;
               case 4:
                  if (curChar != 35)
                     break;
                  if (kind > 43)
                     kind = 43;
                  jjCheckNAdd(5);
                  break;
               case 5:
                  if ((0xfffffffffffffbfeL & l) == 0L)
                     break;
                  if (kind > 43)
                     kind = 43;
                  jjCheckNAdd(5);
                  break;
               case 6:
                  if (curChar == 35)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 7:
                  if ((0x100000200L & l) != 0L)
                     jjCheckNAddTwoStates(7, 8);
                  break;
               case 8:
                  if (curChar != 35)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(9);
                  break;
               case 9:
                  if ((0xfffffffffffffbfeL & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(9);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if (kind > 60)
                     kind = 60;
                  break;
               case 5:
                  if (kind > 43)
                     kind = 43;
                  jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 9:
                  if (kind > 44)
                     kind = 44;
                  jjstateSet[jjnewStateCnt++] = 9;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 60)
                     kind = 60;
                  break;
               case 5:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 43)
                     kind = 43;
                  jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 9:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjstateSet[jjnewStateCnt++] = 9;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 10 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_2()
{
   return jjMoveNfa_2(1, 0);
}
private int jjMoveNfa_2(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 7;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((0xfffffffffffff9ffL & l) != 0L)
                  {
                     if (kind > 60)
                        kind = 60;
                  }
                  if ((0x100000601L & l) != 0L)
                  {
                     if (kind > 39)
                        kind = 39;
                     jjCheckNAdd(0);
                  }
                  if ((0x401L & l) != 0L)
                     jjCheckNAddTwoStates(2, 5);
                  break;
               case 0:
                  if ((0x100000601L & l) == 0L)
                     break;
                  if (kind > 39)
                     kind = 39;
                  jjCheckNAdd(0);
                  break;
               case 2:
                  if ((0x100000200L & l) != 0L)
                     jjCheckNAddTwoStates(2, 5);
                  break;
               case 3:
                  if (curChar != 35)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(4);
                  break;
               case 4:
                  if ((0xfffffffffffffbfeL & l) == 0L)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(4);
                  break;
               case 5:
                  if (curChar == 35)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 6:
                  if ((0xfffffffffffff9ffL & l) != 0L && kind > 60)
                     kind = 60;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if (kind > 60)
                     kind = 60;
                  break;
               case 4:
                  if (kind > 41)
                     kind = 41;
                  jjstateSet[jjnewStateCnt++] = 4;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 60)
                     kind = 60;
                  break;
               case 4:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjstateSet[jjnewStateCnt++] = 4;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 7 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   16, 17, 18, 19, 21, 25, 26, 27, 28, 30, 35, 36, 38, 39, 40, 10,
   11, 13, 14, 3, 6, 7, 8,
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 1:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec8[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec9[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec10[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec11[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec12[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec13[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec14[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec15[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec16[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec17[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec18[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec21[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec23[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec24[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec25[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec26[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_2(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec27[i2] & l2) != 0L);
      case 1:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec28[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec29[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec30[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec31[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec32[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec33[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec34[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec35[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec36[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec37[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec38[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec39[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec18[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec21[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec40[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec41[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec24[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec25[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec26[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", "\133", "\75", "\46\75", "\174\75", "\163\164\141\162\164",
"\144\151\166", "\151\156\143\154\165\144\145", "\176", "\135",
"\147\162\141\155\155\141\162", "\173", "\175", "\156\141\155\145\163\160\141\143\145",
"\144\145\146\141\165\154\164", "\151\156\150\145\162\151\164", "\144\141\164\141\164\171\160\145\163",
"\145\155\160\164\171", "\164\145\170\164", "\156\157\164\101\154\154\157\167\145\144", "\174", "\46",
"\54", "\53", "\77", "\52", "\145\154\145\155\145\156\164",
"\141\164\164\162\151\142\165\164\145", "\50", "\51", "\55", "\154\151\163\164", "\155\151\170\145\144",
"\145\170\164\145\162\156\141\154", "\160\141\162\145\156\164", "\163\164\162\151\156\147",
"\164\157\153\145\156", null, null, null, null, null, null, null, null, null, null, null, null, null,
null, null, null, null, null, null, null, null, null, "\76\76", null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "AFTER_SINGLE_LINE_COMMENT",
   "AFTER_DOCUMENTATION",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 2, -1, 1, 2, -1, -1, -1, -1, -1, -1,
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
};
static final long[] jjtoToken = {
   0x1fc00b1fffffffffL,
};
static final long[] jjtoSkip = {
   0x148000000000L,
};
static final long[] jjtoSpecial = {
   0x140000000000L,
};
protected JavaCharStream input_stream;
private final int[] jjrounds = new int[43];
private final int[] jjstateSet = new int[86];
private final StringBuilder jjimage = new StringBuilder();
private StringBuilder image = jjimage;
private int jjimageLen;
private int lengthOfMatch;
protected char curChar;
/** Constructor. */
public CompactSyntaxTokenManager(JavaCharStream stream){
   if (JavaCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public CompactSyntaxTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 43; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 3 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken()
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   switch(curLexState)
   {
     case 0:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       break;
     case 2:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_2();
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
           matchedToken.specialToken = specialToken;
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
           if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
           {
              matchedToken = jjFillToken();
              if (specialToken == null)
                 specialToken = matchedToken;
              else
              {
                 matchedToken.specialToken = specialToken;
                 specialToken = (specialToken.next = matchedToken);
              }
              SkipLexicalActions(matchedToken);
           }
           else
              SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
