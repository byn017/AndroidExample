
#include "jni_interface.h"
#include "android_debug.h"
#include <jni.h>
#ifndef NELEM
# define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))
#endif


namespace android {

     jstring test(JNIEnv *env, jclass type) {

        jstring test  = env->NewStringUTF("JNI TEST");

        LOGD("====%s jni test", __FUNCTION__);
        return  test;

    }

    int jniRegisterNativeMethods(JNIEnv *env, const char *className,
                                 const JNINativeMethod *gMethods, int numMethods)
    {
        jclass klass;

        LOGD("Registering %s natives\n", className);
        klass = env->FindClass(className);
        if(klass < 0){
            LOGD("Native registration unable to find class %s\n", className);
            return -1;
        }

        if(env->RegisterNatives(klass, gMethods, numMethods) < 0){
            LOGD("RegisterNatives failed ofr %s\n", className);
            return -1;
        }

        return 0;
    }

static JNINativeMethod sMethods[] = {
        {"test", "()Ljava/lang/String;", (void *) test},
};


int register_com_yakir_example_jni(JNIEnv* env)
{
    LOGD("register_com_yakir_example_jni\n");
    return jniRegisterNativeMethods(env, "com/yakir/example/jni/JniActivity",
                                    sMethods, NELEM(sMethods));
}
} /* namespace android */

/*
 * JNI Initialization
 */
jint JNI_OnLoad(JavaVM *jvm, void *reserved)
{
    JNIEnv *e;
    int status;

    LOGD("Adapter Service : loading JNI\n");

    // Check JNI version
    if (jvm->GetEnv((void **)&e, JNI_VERSION_1_6)) {
        LOGD("JNI version mismatch error");
        return JNI_ERR;
    }

    if ((status = android::register_com_yakir_example_jni(e)) < 0) {
        LOGD("jni adapter service registration failure, status: %d", status);
        return JNI_ERR;
    }
    return JNI_VERSION_1_6;
}

