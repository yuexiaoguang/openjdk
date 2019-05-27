
#include "util.h"
#include "ClassObjectReferenceImpl.h"
#include "inStream.h"
#include "outStream.h"

static jboolean
reflectedType(PacketInputStream *in, PacketOutputStream *out)
{
    jbyte tag;
    jobject object;
    JNIEnv *env;

    env = getEnv();

    object = inStream_readObjectRef(env, in);
    if (inStream_error(in)) {
        return JNI_TRUE;
    }

    /*
     * In our implementation, the reference type id is the same as the
     * class object id, so we bounce it right back.
     *
     */

    tag = referenceTypeTag(object);
    (void)outStream_writeByte(out, tag);
    (void)outStream_writeObjectRef(env, out, object);

    return JNI_TRUE;
}

void *ClassObjectReference_Cmds[] = { (void *)1
    ,(void *)reflectedType
};
