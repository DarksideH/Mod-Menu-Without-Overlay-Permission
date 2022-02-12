#include <list>
#include <vector>
#include <string.h>
#include <pthread.h>
#include <cstring>
#include <jni.h>
#include <unistd.h>
#include <fstream>
#include <iostream>
#include <dlfcn.h>
#include "KittyMemory/MemoryPatch.h"
#include "Includes/Utils.h"
#include "Icon.h"
	 
#include <Substrate/SubstrateHook.h>
#include <Substrate/CydiaSubstrate.h>

#define libName "libil2cpp.so"

   struct My_Patches {   
    MemoryPatch GodMode;
} hexPatches; 
    
extern "C" {	
	
    JNIEXPORT jstring JNICALL
    Java_com_android_support_Loader_setTitleText(
        JNIEnv *env,
        jobject activityObject) {
    jstring str = env->NewStringUTF("     Modded by Darkside");
        return str;
    }

    JNIEXPORT jstring JNICALL
    Java_com_android_support_Loader_setHeadingText(
        JNIEnv *env,
        jobject activityObject) {
    jstring str = env->NewStringUTF("No Permission Mod Menu | by Darkside");
        return str;
    }

JNIEXPORT jobjectArray  JNICALL
Java_com_android_support_Loader_GetFeatureList(
    JNIEnv *env,
    jobject activityObject) {
    jobjectArray ret;
    const char *features[] = {          	   
              "Text_The Text︎",//0
              "ButtonOnOff_The On/Off button",//1   
	          "Button_The Button",//2  
              "SeekBar_The SeekBar_0_12",//3     
			  "Text_Hide && icon",//4
              "Hide_Icon invisible",   
			  "Close_Close menu",  
            };

    int Total_Feature = (sizeof features /
                         sizeof features[0]); //Now you dont have to manually update the number everytime;

    ret = (jobjectArray) env->NewObjectArray(Total_Feature, env->FindClass("java/lang/String"),
                                             env->NewStringUTF(""));
    int i;
    for (i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));
    return (ret);
} 

JNIEXPORT void JNICALL
Java_com_android_support_Loader_Changes(
        JNIEnv *env,
        jobject activityObject,
        jint feature,
        jint value) {
        switch (feature) {
        case 1:
          break; 
	}
}

void *hack_thread(void *) {
    do {
        sleep(1);
    } while (!isLibraryLoaded(libName));
	
 return NULL;
}
    
__attribute__((constructor))
void lib_main() {

    pthread_t ptid;
    pthread_create(&ptid, NULL, hack_thread, NULL);
  }
}
