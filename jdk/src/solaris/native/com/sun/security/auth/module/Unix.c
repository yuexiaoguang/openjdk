#ifdef __solaris__
#define _POSIX_C_SOURCE 199506L
#endif

#include <jni.h>
#include "com_sun_security_auth_module_UnixSystem.h"
#include <stdio.h>
#include <pwd.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

JNIEXPORT void JNICALL
Java_com_sun_security_auth_module_UnixSystem_getUnixInfo
                                                (JNIEnv *env, jobject obj) {

    int i;
    char pwd_buf[1024];
    struct passwd *pwd;
    struct passwd resbuf;
    jfieldID userNameID;
    jfieldID userID;
    jfieldID groupID;
    jfieldID supplementaryGroupID;

    jstring jstr;
    jlongArray jgroups;
    jlong *jgroupsAsArray;
    jsize numSuppGroups;
    gid_t *groups;
    jclass cls;

    numSuppGroups = getgroups(0, NULL);
    groups = (gid_t *)calloc(numSuppGroups, sizeof(gid_t));
    if (groups == NULL) {
        jclass cls = (*env)->FindClass(env,"java/lang/OutOfMemoryError");
        if(cls != 0)
            (*env)->ThrowNew(env, cls, NULL);
        return;
    }

    cls = (*env)->GetObjectClass(env, obj);

    memset(pwd_buf, 0, sizeof(pwd_buf));

    if (getpwuid_r(getuid(), &resbuf, pwd_buf, sizeof(pwd_buf), &pwd) == 0 &&
        pwd != NULL &&
        getgroups(numSuppGroups, groups) != -1) {

        userNameID = (*env)->GetFieldID(env, cls, "username", "Ljava/lang/String;");
        if (userNameID == 0)
            goto cleanUpAndReturn;

        userID = (*env)->GetFieldID(env, cls, "uid", "J");
        if (userID == 0)
            goto cleanUpAndReturn;

        groupID = (*env)->GetFieldID(env, cls, "gid", "J");
        if (groupID == 0)
            goto cleanUpAndReturn;

        supplementaryGroupID = (*env)->GetFieldID(env, cls, "groups", "[J");
        if (supplementaryGroupID == 0)
            goto cleanUpAndReturn;

        jstr = (*env)->NewStringUTF(env, pwd->pw_name);
        (*env)->SetObjectField(env, obj, userNameID, jstr);

        (*env)->SetLongField(env, obj, userID, pwd->pw_uid);

        (*env)->SetLongField(env, obj, groupID, pwd->pw_gid);

        jgroups = (*env)->NewLongArray(env, numSuppGroups);
        jgroupsAsArray = (*env)->GetLongArrayElements(env, jgroups, 0);
        for (i = 0; i < numSuppGroups; i++)
            jgroupsAsArray[i] = groups[i];
        (*env)->ReleaseLongArrayElements(env, jgroups, jgroupsAsArray, 0);
        (*env)->SetObjectField(env, obj, supplementaryGroupID, jgroups);
    }
cleanUpAndReturn:
    free(groups);
    return;
}
