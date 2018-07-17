cmake_minimum_required(VERSION 3.4.1)
project(echo LANGUAGES C CXX)

add_library(jni
  SHARED
    test_main.cpp)

#include libraries needed for echo lib
target_link_libraries(jni
  PRIVATE
    android
    log)

target_compile_options(jni
  PRIVATE
    -Wall -Werror)