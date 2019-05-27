
#ifndef CPU_X86_VM_BYTECODEINTERPRETER_X86_INLINE_HPP
#define CPU_X86_VM_BYTECODEINTERPRETER_X86_INLINE_HPP

// Inline interpreter functions for IA32

inline jfloat BytecodeInterpreter::VMfloatAdd(jfloat op1, jfloat op2) { return op1 + op2; }
inline jfloat BytecodeInterpreter::VMfloatSub(jfloat op1, jfloat op2) { return op1 - op2; }
inline jfloat BytecodeInterpreter::VMfloatMul(jfloat op1, jfloat op2) { return op1 * op2; }
inline jfloat BytecodeInterpreter::VMfloatDiv(jfloat op1, jfloat op2) { return op1 / op2; }
inline jfloat BytecodeInterpreter::VMfloatRem(jfloat op1, jfloat op2) { return fmod(op1, op2); }

inline jfloat BytecodeInterpreter::VMfloatNeg(jfloat op) { return -op; }

inline int32_t BytecodeInterpreter::VMfloatCompare(jfloat op1, jfloat op2, int32_t direction) {
  return ( op1 < op2 ? -1 :
               op1 > op2 ? 1 :
                   op1 == op2 ? 0 :
                       (direction == -1 || direction == 1) ? direction : 0);

}

inline void BytecodeInterpreter::VMmemCopy64(uint32_t to[2], const uint32_t from[2]) {
  // x86 can do unaligned copies but not 64bits at a time
  to[0] = from[0]; to[1] = from[1];
}

// The long operations depend on compiler support for "long long" on x86

inline jlong BytecodeInterpreter::VMlongAdd(jlong op1, jlong op2) {
  return op1 + op2;
}

inline jlong BytecodeInterpreter::VMlongAnd(jlong op1, jlong op2) {
  return op1 & op2;
}

inline jlong BytecodeInterpreter::VMlongDiv(jlong op1, jlong op2) {
  // QQQ what about check and throw...
  return op1 / op2;
}

inline jlong BytecodeInterpreter::VMlongMul(jlong op1, jlong op2) {
  return op1 * op2;
}

inline jlong BytecodeInterpreter::VMlongOr(jlong op1, jlong op2) {
  return op1 | op2;
}

inline jlong BytecodeInterpreter::VMlongSub(jlong op1, jlong op2) {
  return op1 - op2;
}

inline jlong BytecodeInterpreter::VMlongXor(jlong op1, jlong op2) {
  return op1 ^ op2;
}

inline jlong BytecodeInterpreter::VMlongRem(jlong op1, jlong op2) {
  return op1 % op2;
}

inline jlong BytecodeInterpreter::VMlongUshr(jlong op1, jint op2) {
  // CVM did this 0x3f mask, is the really needed??? QQQ
  return ((unsigned long long) op1) >> (op2 & 0x3F);
}

inline jlong BytecodeInterpreter::VMlongShr(jlong op1, jint op2) {
  return op1 >> (op2 & 0x3F);
}

inline jlong BytecodeInterpreter::VMlongShl(jlong op1, jint op2) {
  return op1 << (op2 & 0x3F);
}

inline jlong BytecodeInterpreter::VMlongNeg(jlong op) {
  return -op;
}

inline jlong BytecodeInterpreter::VMlongNot(jlong op) {
  return ~op;
}

inline int32_t BytecodeInterpreter::VMlongLtz(jlong op) {
  return (op <= 0);
}

inline int32_t BytecodeInterpreter::VMlongGez(jlong op) {
  return (op >= 0);
}

inline int32_t BytecodeInterpreter::VMlongEqz(jlong op) {
  return (op == 0);
}

inline int32_t BytecodeInterpreter::VMlongEq(jlong op1, jlong op2) {
  return (op1 == op2);
}

inline int32_t BytecodeInterpreter::VMlongNe(jlong op1, jlong op2) {
  return (op1 != op2);
}

inline int32_t BytecodeInterpreter::VMlongGe(jlong op1, jlong op2) {
  return (op1 >= op2);
}

inline int32_t BytecodeInterpreter::VMlongLe(jlong op1, jlong op2) {
  return (op1 <= op2);
}

inline int32_t BytecodeInterpreter::VMlongLt(jlong op1, jlong op2) {
  return (op1 < op2);
}

inline int32_t BytecodeInterpreter::VMlongGt(jlong op1, jlong op2) {
  return (op1 > op2);
}

inline int32_t BytecodeInterpreter::VMlongCompare(jlong op1, jlong op2) {
  return (VMlongLt(op1, op2) ? -1 : VMlongGt(op1, op2) ? 1 : 0);
}

// Long conversions

inline jdouble BytecodeInterpreter::VMlong2Double(jlong val) {
  return (jdouble) val;
}

inline jfloat BytecodeInterpreter::VMlong2Float(jlong val) {
  return (jfloat) val;
}

inline jint BytecodeInterpreter::VMlong2Int(jlong val) {
  return (jint) val;
}

// Double Arithmetic

inline jdouble BytecodeInterpreter::VMdoubleAdd(jdouble op1, jdouble op2) {
  return op1 + op2;
}

inline jdouble BytecodeInterpreter::VMdoubleDiv(jdouble op1, jdouble op2) {
  // Divide by zero... QQQ
  return op1 / op2;
}

inline jdouble BytecodeInterpreter::VMdoubleMul(jdouble op1, jdouble op2) {
  return op1 * op2;
}

inline jdouble BytecodeInterpreter::VMdoubleNeg(jdouble op) {
  return -op;
}

inline jdouble BytecodeInterpreter::VMdoubleRem(jdouble op1, jdouble op2) {
  return fmod(op1, op2);
}

inline jdouble BytecodeInterpreter::VMdoubleSub(jdouble op1, jdouble op2) {
  return op1 - op2;
}

inline int32_t BytecodeInterpreter::VMdoubleCompare(jdouble op1, jdouble op2, int32_t direction) {
  return ( op1 < op2 ? -1 :
               op1 > op2 ? 1 :
                   op1 == op2 ? 0 :
                       (direction == -1 || direction == 1) ? direction : 0);
}

// Double Conversions

inline jfloat BytecodeInterpreter::VMdouble2Float(jdouble val) {
  return (jfloat) val;
}

// Float Conversions

inline jdouble BytecodeInterpreter::VMfloat2Double(jfloat op) {
  return (jdouble) op;
}

// Integer Arithmetic

inline jint BytecodeInterpreter::VMintAdd(jint op1, jint op2) {
  return op1 + op2;
}

inline jint BytecodeInterpreter::VMintAnd(jint op1, jint op2) {
  return op1 & op2;
}

inline jint BytecodeInterpreter::VMintDiv(jint op1, jint op2) {
  /* it's possible we could catch this special case implicitly */
  if ((juint)op1 == 0x80000000 && op2 == -1) return op1;
  else return op1 / op2;
}

inline jint BytecodeInterpreter::VMintMul(jint op1, jint op2) {
  return op1 * op2;
}

inline jint BytecodeInterpreter::VMintNeg(jint op) {
  return -op;
}

inline jint BytecodeInterpreter::VMintOr(jint op1, jint op2) {
  return op1 | op2;
}

inline jint BytecodeInterpreter::VMintRem(jint op1, jint op2) {
  /* it's possible we could catch this special case implicitly */
  if ((juint)op1 == 0x80000000 && op2 == -1) return 0;
  else return op1 % op2;
}

inline jint BytecodeInterpreter::VMintShl(jint op1, jint op2) {
  return op1 << op2;
}

inline jint BytecodeInterpreter::VMintShr(jint op1, jint op2) {
  return op1 >> (op2 & 0x1f);
}

inline jint BytecodeInterpreter::VMintSub(jint op1, jint op2) {
  return op1 - op2;
}

inline jint BytecodeInterpreter::VMintUshr(jint op1, jint op2) {
  return ((juint) op1) >> (op2 & 0x1f);
}

inline jint BytecodeInterpreter::VMintXor(jint op1, jint op2) {
  return op1 ^ op2;
}

inline jdouble BytecodeInterpreter::VMint2Double(jint val) {
  return (jdouble) val;
}

inline jfloat BytecodeInterpreter::VMint2Float(jint val) {
  return (jfloat) val;
}

inline jlong BytecodeInterpreter::VMint2Long(jint val) {
  return (jlong) val;
}

inline jchar BytecodeInterpreter::VMint2Char(jint val) {
  return (jchar) val;
}

inline jshort BytecodeInterpreter::VMint2Short(jint val) {
  return (jshort) val;
}

inline jbyte BytecodeInterpreter::VMint2Byte(jint val) {
  return (jbyte) val;
}

#endif // CPU_X86_VM_BYTECODEINTERPRETER_X86_INLINE_HPP
