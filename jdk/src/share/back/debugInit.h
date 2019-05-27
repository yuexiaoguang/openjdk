
#ifndef JDWP_DEBUGINIT_H
#define JDWP_DEBUGINIT_H

void debugInit_waitInitComplete(void);
jboolean debugInit_isInitComplete(void);

/*
 * Access to debug options
 */
char *debugInit_launchOnInit(void);
jboolean debugInit_suspendOnInit(void);

void debugInit_reset(JNIEnv *env);
void debugInit_exit(jvmtiError, const char *);
void forceExit(int);

JNIEXPORT jint JNICALL Agent_OnLoad(JavaVM *, char *, void *);
JNIEXPORT void JNICALL Agent_OnUnload(JavaVM *);

#endif
