
#include <stdlib.h>
#include <jni.h>

#define CHECK_EXCEPTION_(value) if ((*env)->ExceptionOccurred(env)) { return value; }
#define CHECK_EXCEPTION if ((*env)->ExceptionOccurred(env)) { return;}
#define THROW_NEW_DEBUGGER_EXCEPTION_(str, value) { throw_new_debugger_exception(env, str); return value; }
#define THROW_NEW_DEBUGGER_EXCEPTION(str) { throw_new_debugger_exception(env, str); return;}

static void throw_new_debugger_exception(JNIEnv* env, const char* errMsg) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "sun/jvm/hotspot/debugger/DebuggerException"), errMsg);
}

/*
 * Class:     sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal
 * Method:    init0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_init0
  (JNIEnv *env, jclass cls) {
}

JNIEXPORT jint JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_getAddressSize
  (JNIEnv *env, jclass cls)
{
#ifdef _LP64
 return 8;
#else
 return 4;
#endif

}

/*
 * Class:     sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal
 * Method:    attach0
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_attach0__I
  (JNIEnv *env, jobject this_obj, jint jpid) {

  THROW_NEW_DEBUGGER_EXCEPTION("Can't attach to the process");
}

/*
 * Class:     sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal
 * Method:    attach0
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_attach0__Ljava_lang_String_2Ljava_lang_String_2
  (JNIEnv *env, jobject this_obj, jstring execName, jstring coreName) {
  THROW_NEW_DEBUGGER_EXCEPTION("Can't attach to the core file");
}

/*
 * Class:     sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal
 * Method:    detach0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_detach0
  (JNIEnv *env, jobject this_obj) {
}

/*
 * Class:     sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal
 * Method:    lookupByName0
 * Signature: (Ljava/lang/String;Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_lookupByName0
  (JNIEnv *env, jobject this_obj, jstring objectName, jstring symbolName) {
  return 0;
}

/*
 * Class:     sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal
 * Method:    lookupByAddress0
 * Signature: (J)Lsun/jvm/hotspot/debugger/cdbg/ClosestSymbol;
 */
JNIEXPORT jobject JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_lookupByAddress0
  (JNIEnv *env, jobject this_obj, jlong addr) {
  return 0;
}

/*
 * Class:     sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal
 * Method:    readBytesFromProcess0
 * Signature: (JJ)Lsun/jvm/hotspot/debugger/ReadResult;
 */
JNIEXPORT jbyteArray JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_readBytesFromProcess0
  (JNIEnv *env, jobject this_obj, jlong addr, jlong numBytes) {
  return 0;
}

JNIEXPORT jlongArray JNICALL Java_sun_jvm_hotspot_debugger_bsd_BsdDebuggerLocal_getThreadIntegerRegisterSet0
  (JNIEnv *env, jobject this_obj, jint lwp_id) {
  return 0;
}
