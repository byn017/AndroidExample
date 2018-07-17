#include <jni.h>
#include <string>
#include <android/log.h>

#define MODULE_NAME "JNI-TEST"
#define LOGD(...) \
  __android_log_print(ANDROID_LOG_DEBUG, MODULE_NAME, __VA_ARGS__)


extern "C"
JNIEXPORT jstring

JNICALL
Java_com_yakir_example2_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello 111 from C++";
    LOGD("stringFromJNI: %s\n", hello.c_str());
    return env->NewStringUTF(hello.c_str());
}
