
#ifndef JDWP_SDE_H
#define JDWP_SDE_H

void
convertLineNumberTable(JNIEnv *env, jclass clazz,
                       jint *entryCountPtr,
                       jvmtiLineNumberEntry **tablePtr);

void
setGlobalStratumId(char *id);

/* Return 1 if p1 matches  any source name for clazz, else 0 */
int searchAllSourceNames(JNIEnv *env,
                         jclass clazz,
                         char * pattern);
#endif
