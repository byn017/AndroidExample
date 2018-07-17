#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_yakit_mytestapp_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Sum Example";
    return env->NewStringUTF(hello.c_str());
}
