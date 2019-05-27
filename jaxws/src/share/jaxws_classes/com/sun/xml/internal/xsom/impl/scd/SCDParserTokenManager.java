package com.sun.xml.internal.xsom.impl.scd;

import java.util.*;
import java.io.*;
import com.sun.xml.internal.xsom.impl.UName;
import javax.xml.namespace.*;

public class SCDParserTokenManager implements SCDParserConstants
{
  public  java.io.PrintStream debugStream = System.out;
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x3c08000000L) != 0L)
         {
            jjmatchedKind = 12;
            return 103;
         }
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 12;
            return 55;
         }
         if ((active0 & 0x30000000000L) != 0L)
         {
            jjmatchedKind = 12;
            return 68;
         }
         if ((active0 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 12;
            return 81;
         }
         if ((active0 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 12;
            return 23;
         }
         if ((active0 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 12;
            return 34;
         }
         if ((active0 & 0x100000L) != 0L)
         {
            jjmatchedKind = 12;
            return 91;
         }
         if ((active0 & 0x18c1f4240000L) != 0L)
         {
            jjmatchedKind = 12;
            return 1;
         }
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 12;
            return 16;
         }
         return -1;
      case 1:
         if ((active0 & 0x1fffff740000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 1;
            return 1;
         }
         return -1;
      case 2:
         if ((active0 & 0x1fffff740000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 2;
            return 1;
         }
         return -1;
      case 3:
         if ((active0 & 0x4100000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x1fbeff740000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 3;
            return 1;
         }
         return -1;
      case 4:
         if ((active0 & 0x4100000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x400000L) != 0L)
         {
            if (jjmatchedPos < 3)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 3;
            }
            return -1;
         }
         if ((active0 & 0x1fbeff340000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 4;
            return 1;
         }
         return -1;
      case 5:
         if ((active0 & 0x4000000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x33c50000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0x400000L) != 0L)
         {
            if (jjmatchedPos < 3)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 3;
            }
            return -1;
         }
         if ((active0 & 0x1c82af340000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 5;
            return 1;
         }
         return -1;
      case 6:
         if ((active0 & 0x33c50000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0x1c82af340000L) != 0L)
         {
            if (jjmatchedPos != 6)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 6;
            }
            return 1;
         }
         return -1;
      case 7:
         if ((active0 & 0x100000L) != 0L)
         {
            if (jjmatchedPos < 6)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 6;
            }
            return -1;
         }
         if ((active0 & 0x13c00000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0x1c82af240000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 7;
            return 1;
         }
         return -1;
      case 8:
         if ((active0 & 0x480aa240000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 8;
            return 1;
         }
         if ((active0 & 0x180205000000L) != 0L)
         {
            if (jjmatchedPos < 7)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 7;
            }
            return -1;
         }
         if ((active0 & 0x100000L) != 0L)
         {
            if (jjmatchedPos < 6)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 6;
            }
            return -1;
         }
         if ((active0 & 0x1c00000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         return -1;
      case 9:
         if ((active0 & 0x80aa200000L) != 0L)
         {
            if (jjmatchedPos != 9)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 9;
            }
            return 1;
         }
         if ((active0 & 0x180205000000L) != 0L)
         {
            if (jjmatchedPos < 7)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 7;
            }
            return -1;
         }
         if ((active0 & 0x40000040000L) != 0L)
         {
            if (jjmatchedPos < 8)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 8;
            }
            return -1;
         }
         if ((active0 & 0x1c00000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         return -1;
      case 10:
         if ((active0 & 0x100000000000L) != 0L)
         {
            if (jjmatchedPos < 7)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 7;
            }
            return -1;
         }
         if ((active0 & 0x8000000L) != 0L)
         {
            if (jjmatchedPos < 9)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 9;
            }
            return -1;
         }
         if ((active0 & 0x40000040000L) != 0L)
         {
            if (jjmatchedPos < 8)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 8;
            }
            return -1;
         }
         if ((active0 & 0x80a2200000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 10;
            return 1;
         }
         if ((active0 & 0xc00000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         return -1;
      case 11:
         if ((active0 & 0x40000000000L) != 0L)
         {
            if (jjmatchedPos < 8)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 8;
            }
            return -1;
         }
         if ((active0 & 0x8000000L) != 0L)
         {
            if (jjmatchedPos < 9)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 9;
            }
            return -1;
         }
         if ((active0 & 0xc00000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0x80a2200000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 11;
            return 1;
         }
         return -1;
      case 12:
         if ((active0 & 0x8000000000L) != 0L)
         {
            if (jjmatchedPos < 11)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 11;
            }
            return -1;
         }
         if ((active0 & 0xc00000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0xa2200000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 12;
            return 1;
         }
         return -1;
      case 13:
         if ((active0 & 0x8000000000L) != 0L)
         {
            if (jjmatchedPos < 11)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 11;
            }
            return -1;
         }
         if ((active0 & 0x2000000L) != 0L)
         {
            if (jjmatchedPos < 12)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 12;
            }
            return -1;
         }
         if ((active0 & 0x400000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0xa0200000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 13;
            return 1;
         }
         return -1;
      case 14:
         if ((active0 & 0x8000000000L) != 0L)
         {
            if (jjmatchedPos < 11)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 11;
            }
            return -1;
         }
         if ((active0 & 0x20000000L) != 0L)
         {
            if (jjmatchedPos < 13)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 13;
            }
            return -1;
         }
         if ((active0 & 0x2000000L) != 0L)
         {
            if (jjmatchedPos < 12)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 12;
            }
            return -1;
         }
         if ((active0 & 0x400000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0x80200000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 14;
            return 1;
         }
         return -1;
      case 15:
         if ((active0 & 0x20000000L) != 0L)
         {
            if (jjmatchedPos < 13)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 13;
            }
            return -1;
         }
         if ((active0 & 0x80200000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 15;
            return 1;
         }
         return -1;
      case 16:
         if ((active0 & 0x80200000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 16;
            return 1;
         }
         return -1;
      case 17:
         if ((active0 & 0x80200000L) != 0L)
         {
            if (jjmatchedPos < 16)
            {
               jjmatchedKind = 12;
               jjmatchedPos = 16;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private final int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private final int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private final int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 42:
         return jjStopAtPos(0, 45);
      case 47:
         jjmatchedKind = 16;
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 48:
         return jjStopAtPos(0, 46);
      case 58:
         return jjStopAtPos(0, 15);
      case 64:
         return jjStopAtPos(0, 19);
      case 97:
         return jjMoveStringLiteralDfa1_0(0xc020040000L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x1000000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x30000000000L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x84000000L);
      case 107:
         return jjMoveStringLiteralDfa1_0(0x100000000L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x3c08000000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x10200000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 120:
         return jjMoveStringLiteralDfa1_0(0x180000000000L);
      case 126:
         return jjStopAtPos(0, 23);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private final int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa2_0(active0, 0x180000000000L);
      case 47:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(1, 17);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x30001000000L);
      case 99:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000L);
      case 100:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x108000000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0xc000000000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x43e00000000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x42000000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x24040000L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private final int jjMoveStringLiteralDfa2_0(long old0, long active0)
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
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x30000000000L);
      case 100:
         return jjMoveStringLiteralDfa3_0(active0, 0x3c00000000L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x84100000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000L);
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0x40008000000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x50000000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x180001000000L);
      case 116:
         return jjMoveStringLiteralDfa3_0(active0, 0x220040000L);
      case 121:
         return jjMoveStringLiteralDfa3_0(active0, 0xc100000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private final int jjMoveStringLiteralDfa3_0(long old0, long active0)
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
      case 58:
         return jjMoveStringLiteralDfa4_0(active0, 0x4100000000L);
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000000L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000L);
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x180000000000L);
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0x33c01400000L);
      case 109:
         return jjMoveStringLiteralDfa4_0(active0, 0x6100000L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000000L);
      case 112:
         return jjMoveStringLiteralDfa4_0(active0, 0x40010000000L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x20040000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private final int jjMoveStringLiteralDfa4_0(long old0, long active0)
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
      case 58:
         if ((active0 & 0x100000000L) != 0L)
            return jjStopAtPos(4, 32);
         return jjMoveStringLiteralDfa5_0(active0, 0x4000400000L);
      case 84:
         return jjMoveStringLiteralDfa5_0(active0, 0x5000000L);
      case 101:
         return jjMoveStringLiteralDfa5_0(active0, 0x18100000L);
      case 104:
         return jjMoveStringLiteralDfa5_0(active0, 0x180000000000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x22040000L);
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x3c00000000L);
      case 111:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000000L);
      case 112:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000L);
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x38280200000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private final int jjMoveStringLiteralDfa5_0(long old0, long active0)
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
      case 42:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStopAtPos(5, 38);
         break;
      case 58:
         if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(5, 22);
         return jjMoveStringLiteralDfa6_0(active0, 0x33c50000000L);
      case 98:
         return jjMoveStringLiteralDfa6_0(active0, 0x20040000L);
      case 101:
         return jjMoveStringLiteralDfa6_0(active0, 0x180000000000L);
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x280200000L);
      case 110:
         return jjMoveStringLiteralDfa6_0(active0, 0x40000100000L);
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000000L);
      case 116:
         return jjMoveStringLiteralDfa6_0(active0, 0x8002000000L);
      case 121:
         return jjMoveStringLiteralDfa6_0(active0, 0x5000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private final int jjMoveStringLiteralDfa6_0(long old0, long active0)
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
      case 58:
         if ((active0 & 0x10000000L) != 0L)
            return jjStopAtPos(6, 28);
         else if ((active0 & 0x40000000L) != 0L)
            return jjStopAtPos(6, 30);
         else if ((active0 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 41;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active0, 0x13c00000000L);
      case 84:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000000L);
      case 101:
         return jjMoveStringLiteralDfa7_0(active0, 0x40000000000L);
      case 105:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000000L);
      case 109:
         return jjMoveStringLiteralDfa7_0(active0, 0x180000000000L);
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x200000000L);
      case 112:
         return jjMoveStringLiteralDfa7_0(active0, 0x5000000L);
      case 114:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000000000L);
      case 116:
         return jjMoveStringLiteralDfa7_0(active0, 0x80300000L);
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x20040000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private final int jjMoveStringLiteralDfa7_0(long old0, long active0)
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
      case 42:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStopAtPos(7, 37);
         else if ((active0 & 0x10000000000L) != 0L)
            return jjStopAtPos(7, 40);
         break;
      case 58:
         return jjMoveStringLiteralDfa8_0(active0, 0x100000L);
      case 97:
         return jjMoveStringLiteralDfa8_0(active0, 0x181000000000L);
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x800000000L);
      case 101:
         return jjMoveStringLiteralDfa8_0(active0, 0x5000000L);
      case 105:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000000000L);
      case 110:
         return jjMoveStringLiteralDfa8_0(active0, 0x40200000000L);
      case 115:
         return jjMoveStringLiteralDfa8_0(active0, 0x400000000L);
      case 116:
         return jjMoveStringLiteralDfa8_0(active0, 0x20040000L);
      case 117:
         return jjMoveStringLiteralDfa8_0(active0, 0x200000L);
      case 118:
         return jjMoveStringLiteralDfa8_0(active0, 0x2000000L);
      case 121:
         return jjMoveStringLiteralDfa8_0(active0, 0x88000000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private final int jjMoveStringLiteralDfa8_0(long old0, long active0)
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
      case 58:
         if ((active0 & 0x100000L) != 0L)
            return jjStopAtPos(8, 20);
         return jjMoveStringLiteralDfa9_0(active0, 0x180205000000L);
      case 67:
         return jjMoveStringLiteralDfa9_0(active0, 0x80000000L);
      case 98:
         return jjMoveStringLiteralDfa9_0(active0, 0x8000000000L);
      case 101:
         return jjMoveStringLiteralDfa9_0(active0, 0x422040000L);
      case 104:
         return jjMoveStringLiteralDfa9_0(active0, 0x800000000L);
      case 108:
         return jjMoveStringLiteralDfa9_0(active0, 0x1000000000L);
      case 112:
         return jjMoveStringLiteralDfa9_0(active0, 0x8000000L);
      case 116:
         return jjMoveStringLiteralDfa9_0(active0, 0x40000200000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private final int jjMoveStringLiteralDfa9_0(long old0, long active0)
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
      case 58:
         if ((active0 & 0x1000000L) != 0L)
            return jjStopAtPos(9, 24);
         else if ((active0 & 0x4000000L) != 0L)
            return jjStopAtPos(9, 26);
         else if ((active0 & 0x200000000L) != 0L)
            return jjStopAtPos(9, 33);
         else if ((active0 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 43;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active0, 0x140000040000L);
      case 71:
         return jjMoveStringLiteralDfa10_0(active0, 0x20000000L);
      case 84:
         return jjMoveStringLiteralDfa10_0(active0, 0x2000000L);
      case 101:
         return jjMoveStringLiteralDfa10_0(active0, 0x8000000L);
      case 105:
         return jjMoveStringLiteralDfa10_0(active0, 0x200000L);
      case 108:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(9, 36);
         break;
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x880000000L);
      case 113:
         return jjMoveStringLiteralDfa10_0(active0, 0x400000000L);
      case 117:
         return jjMoveStringLiteralDfa10_0(active0, 0x8000000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private final int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStopAtPos(10, 44);
         break;
      case 58:
         if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(10, 18);
         return jjMoveStringLiteralDfa11_0(active0, 0x40008000000L);
      case 105:
         return jjMoveStringLiteralDfa11_0(active0, 0x800000000L);
      case 110:
         return jjMoveStringLiteralDfa11_0(active0, 0x80000000L);
      case 111:
         return jjMoveStringLiteralDfa11_0(active0, 0x200000L);
      case 114:
         return jjMoveStringLiteralDfa11_0(active0, 0x20000000L);
      case 116:
         return jjMoveStringLiteralDfa11_0(active0, 0x8000000000L);
      case 117:
         return jjMoveStringLiteralDfa11_0(active0, 0x400000000L);
      case 121:
         return jjMoveStringLiteralDfa11_0(active0, 0x2000000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private final int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStopAtPos(11, 42);
         break;
      case 58:
         if ((active0 & 0x8000000L) != 0L)
            return jjStopAtPos(11, 27);
         break;
      case 99:
         return jjMoveStringLiteralDfa12_0(active0, 0x800000000L);
      case 101:
         return jjMoveStringLiteralDfa12_0(active0, 0x8400000000L);
      case 110:
         return jjMoveStringLiteralDfa12_0(active0, 0x200000L);
      case 111:
         return jjMoveStringLiteralDfa12_0(active0, 0x20000000L);
      case 112:
         return jjMoveStringLiteralDfa12_0(active0, 0x2000000L);
      case 116:
         return jjMoveStringLiteralDfa12_0(active0, 0x80000000L);
      default :
         break;
   }
   return jjStartNfa_0(10, active0);
}
private final int jjMoveStringLiteralDfa12_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(10, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(11, active0);
      return 12;
   }
   switch(curChar)
   {
      case 58:
         return jjMoveStringLiteralDfa13_0(active0, 0x8000000000L);
      case 71:
         return jjMoveStringLiteralDfa13_0(active0, 0x200000L);
      case 101:
         if ((active0 & 0x800000000L) != 0L)
            return jjStopAtPos(12, 35);
         return jjMoveStringLiteralDfa13_0(active0, 0x2000000L);
      case 110:
         return jjMoveStringLiteralDfa13_0(active0, 0x400000000L);
      case 114:
         return jjMoveStringLiteralDfa13_0(active0, 0x80000000L);
      case 117:
         return jjMoveStringLiteralDfa13_0(active0, 0x20000000L);
      default :
         break;
   }
   return jjStartNfa_0(11, active0);
}
private final int jjMoveStringLiteralDfa13_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(11, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(12, active0);
      return 13;
   }
   switch(curChar)
   {
      case 58:
         return jjMoveStringLiteralDfa14_0(active0, 0x8002000000L);
      case 97:
         return jjMoveStringLiteralDfa14_0(active0, 0x80000000L);
      case 99:
         return jjMoveStringLiteralDfa14_0(active0, 0x400000000L);
      case 112:
         return jjMoveStringLiteralDfa14_0(active0, 0x20000000L);
      case 114:
         return jjMoveStringLiteralDfa14_0(active0, 0x200000L);
      default :
         break;
   }
   return jjStartNfa_0(12, active0);
}
private final int jjMoveStringLiteralDfa14_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(12, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(13, active0);
      return 14;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStopAtPos(14, 39);
         break;
      case 58:
         if ((active0 & 0x2000000L) != 0L)
            return jjStopAtPos(14, 25);
         return jjMoveStringLiteralDfa15_0(active0, 0x20000000L);
      case 101:
         if ((active0 & 0x400000000L) != 0L)
            return jjStopAtPos(14, 34);
         break;
      case 105:
         return jjMoveStringLiteralDfa15_0(active0, 0x80000000L);
      case 111:
         return jjMoveStringLiteralDfa15_0(active0, 0x200000L);
      default :
         break;
   }
   return jjStartNfa_0(13, active0);
}
private final int jjMoveStringLiteralDfa15_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(13, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(14, active0);
      return 15;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x20000000L) != 0L)
            return jjStopAtPos(15, 29);
         break;
      case 110:
         return jjMoveStringLiteralDfa16_0(active0, 0x80000000L);
      case 117:
         return jjMoveStringLiteralDfa16_0(active0, 0x200000L);
      default :
         break;
   }
   return jjStartNfa_0(14, active0);
}
private final int jjMoveStringLiteralDfa16_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(14, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(15, active0);
      return 16;
   }
   switch(curChar)
   {
      case 112:
         return jjMoveStringLiteralDfa17_0(active0, 0x200000L);
      case 116:
         return jjMoveStringLiteralDfa17_0(active0, 0x80000000L);
      default :
         break;
   }
   return jjStartNfa_0(15, active0);
}
private final int jjMoveStringLiteralDfa17_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(15, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(16, active0);
      return 17;
   }
   switch(curChar)
   {
      case 58:
         return jjMoveStringLiteralDfa18_0(active0, 0x80200000L);
      default :
         break;
   }
   return jjStartNfa_0(16, active0);
}
private final int jjMoveStringLiteralDfa18_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(16, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(17, active0);
      return 18;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x200000L) != 0L)
            return jjStopAtPos(18, 21);
         else if ((active0 & 0x80000000L) != 0L)
            return jjStopAtPos(18, 31);
         break;
      default :
         break;
   }
   return jjStartNfa_0(17, active0);
}
private final void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private final void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private final void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}
private final void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}
private final void jjCheckNAddStates(int start)
{
   jjCheckNAdd(jjnextStates[start]);
   jjCheckNAdd(jjnextStates[start + 1]);
}
static final long[] jjbitVec0 = {
   0x0L, 0xffffffffffffc000L, 0xfffff0007fffffffL, 0x7fffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec3 = {
   0x7ff3ffffffffffffL, 0x7ffffffffffffdfeL, 0xffffffffffffffffL, 0xfc31ffffffffe00fL
};
static final long[] jjbitVec4 = {
   0xffffffL, 0xffffffffffff0000L, 0xf80001ffffffffffL, 0x3L
};
static final long[] jjbitVec5 = {
   0x0L, 0x0L, 0xfffffffbffffd740L, 0xffffd547f7fffL
};
static final long[] jjbitVec6 = {
   0xffffffffffffdffeL, 0xffffffffdffeffffL, 0xffffffffffff0003L, 0x33fcfffffff199fL
};
static final long[] jjbitVec7 = {
   0xfffe000000000000L, 0xfffffffe027fffffL, 0x7fL, 0x707ffffff0000L
};
static final long[] jjbitVec8 = {
   0x7fffffe00000000L, 0xfffe0000000007feL, 0x7cffffffffffffffL, 0x60002f7fffL
};
static final long[] jjbitVec9 = {
   0x23ffffffffffffe0L, 0x3ff000000L, 0x3c5fdfffff99fe0L, 0x30003b0000000L
};
static final long[] jjbitVec10 = {
   0x36dfdfffff987e0L, 0x1c00005e000000L, 0x23edfdfffffbafe0L, 0x100000000L
};
static final long[] jjbitVec11 = {
   0x23cdfdfffff99fe0L, 0x3b0000000L, 0x3bfc718d63dc7e0L, 0x0L
};
static final long[] jjbitVec12 = {
   0x3effdfffffddfe0L, 0x300000000L, 0x3effdfffffddfe0L, 0x340000000L
};
static final long[] jjbitVec13 = {
   0x3fffdfffffddfe0L, 0x300000000L, 0x0L, 0x0L
};
static final long[] jjbitVec14 = {
   0xd7ffffffffffeL, 0x3fL, 0x200d6caefef02596L, 0x1fL
};
static final long[] jjbitVec15 = {
   0x0L, 0x3fffffffeffL, 0x0L, 0x0L
};
static final long[] jjbitVec16 = {
   0x0L, 0x0L, 0xffffffff00000000L, 0x7fffffffff003fL
};
static final long[] jjbitVec17 = {
   0x500000000007daedL, 0x2c62ab82315001L, 0xf580c90040000000L, 0x201080000000007L
};
static final long[] jjbitVec18 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffff0fffffffL, 0x3ffffffffffffffL
};
static final long[] jjbitVec19 = {
   0xffffffff3f3fffffL, 0x3fffffffaaff3f3fL, 0x5fdfffffffffffffL, 0x1fdc1fff0fcf1fdcL
};
static final long[] jjbitVec20 = {
   0x4c4000000000L, 0x0L, 0x7L, 0x0L
};
static final long[] jjbitVec21 = {
   0x3fe00000080L, 0xfffffffffffffffeL, 0xfffffffe001fffffL, 0x7ffffffffffffffL
};
static final long[] jjbitVec22 = {
   0x1fffffffffe0L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec23 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x3fffffffffL, 0x0L
};
static final long[] jjbitVec24 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffL, 0x0L
};
static final long[] jjbitVec25 = {
   0x0L, 0x0L, 0x80000000000000L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec26 = {
   0xffffffL, 0xffffffffffff0000L, 0xf80001ffffffffffL, 0x30003L
};
static final long[] jjbitVec27 = {
   0xffffffffffffffffL, 0x30000003fL, 0xfffffffbffffd7c0L, 0xffffd547f7fffL
};
static final long[] jjbitVec28 = {
   0xffffffffffffdffeL, 0xffffffffdffeffffL, 0xffffffffffff007bL, 0x33fcfffffff199fL
};
static final long[] jjbitVec29 = {
   0xfffe000000000000L, 0xfffffffe027fffffL, 0xbbfffffbfffe007fL, 0x707ffffff0016L
};
static final long[] jjbitVec30 = {
   0x7fffffe00000000L, 0xffff03ff0007ffffL, 0x7cffffffffffffffL, 0x3ff3dffffef7fffL
};
static final long[] jjbitVec31 = {
   0xf3ffffffffffffeeL, 0xffcfff1e3fffL, 0xd3c5fdfffff99feeL, 0x3ffcfb080399fL
};
static final long[] jjbitVec32 = {
   0xd36dfdfffff987e4L, 0x1fffc05e003987L, 0xf3edfdfffffbafeeL, 0xffc100003bbfL
};
static final long[] jjbitVec33 = {
   0xf3cdfdfffff99feeL, 0xffc3b0c0398fL, 0xc3bfc718d63dc7ecL, 0xff8000803dc7L
};
static final long[] jjbitVec34 = {
   0xc3effdfffffddfeeL, 0xffc300603ddfL, 0xc3effdfffffddfecL, 0xffc340603ddfL
};
static final long[] jjbitVec35 = {
   0xc3fffdfffffddfecL, 0xffc300803dcfL, 0x0L, 0x0L
};
static final long[] jjbitVec36 = {
   0x7ff7ffffffffffeL, 0x3ff7fffL, 0x3bff6caefef02596L, 0x3ff3f5fL
};
static final long[] jjbitVec37 = {
   0xc2a003ff03000000L, 0xfffe03fffffffeffL, 0x2fe3ffffebf0fdfL, 0x0L
};
static final long[] jjbitVec38 = {
   0x0L, 0x0L, 0x0L, 0x21fff0000L
};
static final long[] jjbitVec39 = {
   0x3efffe000000a0L, 0xfffffffffffffffeL, 0xfffffffe661fffffL, 0x77ffffffffffffffL
};
private final int jjMoveNfa_0(int startState, int curPos)
{
   int[] nextStates;
   int startsAt = 0;
   jjnewStateCnt = 148;
   int i = 1;
   jjstateSet[0] = startState;
   int j, kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 34:
               case 1:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 91:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 16:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 55:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 68:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 103:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 81:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 23:
                  if ((0x3ff600000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjAddStates(0, 1);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 34:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 33;
                  break;
               case 91:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 90;
                  break;
               case 16:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 55:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 54;
                  break;
               case 68:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 67;
                  break;
               case 103:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 146;
                  else if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 139;
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 132;
                  else if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 122;
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 112;
                  else if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 102;
                  break;
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  else if (curChar == 91)
                     jjstateSet[jjnewStateCnt++] = 3;
                  if (curChar == 109)
                     jjAddStates(2, 7);
                  else if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 91;
                  else if (curChar == 112)
                     jjstateSet[jjnewStateCnt++] = 81;
                  else if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 74;
                  else if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 68;
                  else if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 55;
                  else if (curChar == 119)
                     jjstateSet[jjnewStateCnt++] = 44;
                  else if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 34;
                  else if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 23;
                  else if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 16;
                  else if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 81:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 80;
                  break;
               case 23:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                     jjCheckNAdd(1);
                  }
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 1:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 2:
                  if (curChar == 91)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 4:
                  if (curChar == 93)
                     kind = 13;
                  break;
               case 5:
                  if (curChar == 100 && kind > 14)
                     kind = 14;
                  break;
               case 6:
               case 12:
                  if (curChar == 101)
                     jjCheckNAdd(5);
                  break;
               case 7:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 8:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 9:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 10:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 11:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 13:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 17:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 18:
                  if (curChar == 99 && kind > 14)
                     kind = 14;
                  break;
               case 19:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 20:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 21:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 109)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 24:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 25:
                  if (curChar == 121 && kind > 14)
                     kind = 14;
                  break;
               case 26:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 25;
                  break;
               case 27:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 28:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 29:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 30:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 31:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 32:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 33:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 32;
                  break;
               case 35:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 34;
                  break;
               case 36:
                  if (curChar == 101 && kind > 14)
                     kind = 14;
                  break;
               case 37:
                  if (curChar == 99)
                     jjCheckNAdd(36);
                  break;
               case 38:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 37;
                  break;
               case 39:
                  if (curChar == 112)
                     jjstateSet[jjnewStateCnt++] = 38;
                  break;
               case 40:
                  if (curChar == 83)
                     jjstateSet[jjnewStateCnt++] = 39;
                  break;
               case 41:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 40;
                  break;
               case 42:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 41;
                  break;
               case 43:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 42;
                  break;
               case 44:
                  if (curChar == 104)
                     jjstateSet[jjnewStateCnt++] = 43;
                  break;
               case 45:
                  if (curChar == 119)
                     jjstateSet[jjnewStateCnt++] = 44;
                  break;
               case 46:
                  if (curChar == 115 && kind > 14)
                     kind = 14;
                  break;
               case 47:
               case 57:
                  if (curChar == 116)
                     jjCheckNAdd(46);
                  break;
               case 48:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 47;
                  break;
               case 49:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 48;
                  break;
               case 50:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 49;
                  break;
               case 51:
                  if (curChar == 68)
                     jjstateSet[jjnewStateCnt++] = 50;
                  break;
               case 52:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 51;
                  break;
               case 53:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 52;
                  break;
               case 54:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 53;
                  break;
               case 56:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 55;
                  break;
               case 58:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 57;
                  break;
               case 59:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 58;
                  break;
               case 60:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 59;
                  break;
               case 61:
                  if (curChar == 68)
                     jjstateSet[jjnewStateCnt++] = 60;
                  break;
               case 62:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 61;
                  break;
               case 63:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 62;
                  break;
               case 64:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 63;
                  break;
               case 65:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 64;
                  break;
               case 66:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 65;
                  break;
               case 67:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 66;
                  break;
               case 69:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 68;
                  break;
               case 70:
                  if (curChar == 104 && kind > 14)
                     kind = 14;
                  break;
               case 71:
               case 134:
               case 141:
                  if (curChar == 116)
                     jjCheckNAdd(70);
                  break;
               case 72:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 71;
                  break;
               case 73:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 72;
                  break;
               case 74:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 73;
                  break;
               case 75:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 74;
                  break;
               case 76:
                  if (curChar == 110 && kind > 14)
                     kind = 14;
                  break;
               case 77:
                  if (curChar == 114)
                     jjCheckNAdd(76);
                  break;
               case 78:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 77;
                  break;
               case 79:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 78;
                  break;
               case 80:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 79;
                  break;
               case 82:
                  if (curChar == 112)
                     jjstateSet[jjnewStateCnt++] = 81;
                  break;
               case 83:
                  if (curChar == 111)
                     jjCheckNAdd(76);
                  break;
               case 84:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 83;
                  break;
               case 85:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 84;
                  break;
               case 86:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 85;
                  break;
               case 87:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 86;
                  break;
               case 88:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 87;
                  break;
               case 89:
                  if (curChar == 109)
                     jjstateSet[jjnewStateCnt++] = 88;
                  break;
               case 90:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 89;
                  break;
               case 92:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 91;
                  break;
               case 93:
                  if (curChar == 109)
                     jjAddStates(2, 7);
                  break;
               case 94:
               case 104:
               case 114:
               case 124:
                  if (curChar == 118)
                     jjCheckNAdd(36);
                  break;
               case 95:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 94;
                  break;
               case 96:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 95;
                  break;
               case 97:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 96;
                  break;
               case 98:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 97;
                  break;
               case 99:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 98;
                  break;
               case 100:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 99;
                  break;
               case 101:
                  if (curChar == 73)
                     jjstateSet[jjnewStateCnt++] = 100;
                  break;
               case 102:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 101;
                  break;
               case 105:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 104;
                  break;
               case 106:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 105;
                  break;
               case 107:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 106;
                  break;
               case 108:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 107;
                  break;
               case 109:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 108;
                  break;
               case 110:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 109;
                  break;
               case 111:
                  if (curChar == 73)
                     jjstateSet[jjnewStateCnt++] = 110;
                  break;
               case 112:
                  if (curChar == 120)
                     jjstateSet[jjnewStateCnt++] = 111;
                  break;
               case 113:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 112;
                  break;
               case 115:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 114;
                  break;
               case 116:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 115;
                  break;
               case 117:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 116;
                  break;
               case 118:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 117;
                  break;
               case 119:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 118;
                  break;
               case 120:
                  if (curChar == 120)
                     jjstateSet[jjnewStateCnt++] = 119;
                  break;
               case 121:
                  if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 120;
                  break;
               case 122:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 121;
                  break;
               case 123:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 122;
                  break;
               case 125:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 124;
                  break;
               case 126:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 125;
                  break;
               case 127:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 126;
                  break;
               case 128:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 127;
                  break;
               case 129:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 128;
                  break;
               case 130:
                  if (curChar == 120)
                     jjstateSet[jjnewStateCnt++] = 129;
                  break;
               case 131:
                  if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 130;
                  break;
               case 132:
                  if (curChar == 120)
                     jjstateSet[jjnewStateCnt++] = 131;
                  break;
               case 133:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 132;
                  break;
               case 135:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 134;
                  break;
               case 136:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 135;
                  break;
               case 137:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 136;
                  break;
               case 138:
                  if (curChar == 76)
                     jjstateSet[jjnewStateCnt++] = 137;
                  break;
               case 139:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 138;
                  break;
               case 140:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 139;
                  break;
               case 142:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 141;
                  break;
               case 143:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 142;
                  break;
               case 144:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 143;
                  break;
               case 145:
                  if (curChar == 76)
                     jjstateSet[jjnewStateCnt++] = 144;
                  break;
               case 146:
                  if (curChar == 120)
                     jjstateSet[jjnewStateCnt++] = 145;
                  break;
               case 147:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 146;
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
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 34:
               case 1:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 91:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 16:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 55:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 68:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 103:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 0:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 81:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
                  break;
               case 23:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(1);
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
      if ((i = jjnewStateCnt) == (startsAt = 148 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   3, 4, 103, 113, 123, 133, 140, 147,
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      case 1:
         return ((jjbitVec3[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec8[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec9[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec10[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec11[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec12[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec13[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec14[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec15[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec16[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec17[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec18[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec21[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec23[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec24[i2] & l2) != 0L);
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
         return ((jjbitVec25[i2] & l2) != 0L);
      case 1:
         return ((jjbitVec3[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec26[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec27[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec28[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec29[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec30[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec31[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec32[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec33[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec34[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec35[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec36[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec37[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec16[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec17[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec18[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec38[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec39[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec23[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec24[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null,
null, null, "\72", "\57", "\57\57", "\141\164\164\162\151\142\165\164\145\72\72",
"\100", "\145\154\145\155\145\156\164\72\72",
"\163\165\142\163\164\151\164\165\164\151\157\156\107\162\157\165\160\72\72", "\164\171\160\145\72\72", "\176", "\142\141\163\145\124\171\160\145\72\72",
"\160\162\151\155\151\164\151\166\145\124\171\160\145\72\72", "\151\164\145\155\124\171\160\145\72\72",
"\155\145\155\142\145\162\124\171\160\145\72\72", "\163\143\157\160\145\72\72",
"\141\164\164\162\151\142\165\164\145\107\162\157\165\160\72\72", "\147\162\157\165\160\72\72",
"\151\144\145\156\164\151\164\171\103\157\156\164\162\141\151\156\164\72\72", "\153\145\171\72\72", "\156\157\164\141\164\151\157\156\72\72",
"\155\157\144\145\154\72\72\163\145\161\165\145\156\143\145", "\155\157\144\145\154\72\72\143\150\157\151\143\145",
"\155\157\144\145\154\72\72\141\154\154", "\155\157\144\145\154\72\72\52", "\141\156\171\72\72\52",
"\141\156\171\101\164\164\162\151\142\165\164\145\72\72\52", "\146\141\143\145\164\72\72\52", "\146\141\143\145\164\72\72",
"\143\157\155\160\157\156\145\156\164\72\72\52", "\170\55\163\143\150\145\155\141\72\72",
"\170\55\163\143\150\145\155\141\72\72\52", "\52", "\60", };
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x7ffffffff001L,
};
static final long[] jjtoSkip = {
   0x3eL,
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[148];
private final int[] jjstateSet = new int[296];
protected char curChar;
public SCDParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}
public SCDParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private final void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 148; i-- > 0;)
      jjrounds[i] = 0x80000000;
}
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   Token t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   String im = jjstrLiteralImages[jjmatchedKind];
   t.image = (im == null) ? input_stream.GetImage() : im;
   t.beginLine = input_stream.getBeginLine();
   t.beginColumn = input_stream.getBeginColumn();
   t.endLine = input_stream.getEndLine();
   t.endColumn = input_stream.getEndColumn();
   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

public Token getNextToken()
{
  int kind;
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
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
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

}
