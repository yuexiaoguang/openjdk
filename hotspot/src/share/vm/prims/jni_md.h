/* Switch to the correct jni_md.h file without reliance on -I options. */
#ifdef TARGET_ARCH_x86
# include "jni_x86.h"
#endif
#ifdef TARGET_ARCH_sparc
# include "jni_sparc.h"
#endif
#ifdef TARGET_ARCH_zero
# include "jni_zero.h"
#endif
#ifdef TARGET_ARCH_arm
# include "jni_arm.h"
#endif
#ifdef TARGET_ARCH_ppc
# include "jni_ppc.h"
#endif


/*
  The local copies of JNI header files may be refreshed
  from a JDK distribution by means of these commands:

  cp ${JDK_DIST}/solaris/include/solaris/jni_md.h  ./jni_sparc.h
  cp ${JDK_DIST}/win32/include/win32/jni_md.h      ./jni_i486.h
  cp ${JDK_DIST}/win32/include/jni.h               ./jni.h

*/
