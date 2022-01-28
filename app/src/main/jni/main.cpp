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
#include "Includes/obfuscate.h"
#include "Includes/Chams.h"
#include "Includes/Utils.h"
#include "Icon.h"
	 
#include <Substrate/SubstrateHook.h>
#include <Substrate/CydiaSubstrate.h>

#define libName OBFUSCATE("libil2cpp.so")

   struct My_Patches {   
    MemoryPatch GodMode, GodMode2;
} hexPatches; 
	
	bool chams, shading, wireframe, glow, outline, rainbow;
    
extern "C" {	
	
    JNIEXPORT jstring JNICALL
    Java_com_android_support_Loader_setTitleText(
        JNIEnv *env,
        jobject activityObject) {
    jstring str = env->NewStringUTF(OBFUSCATE("     Modded by Darkside"));
        return str;
    }

    JNIEXPORT jstring JNICALL
    Java_com_android_support_Loader_setHeadingText(
        JNIEnv *env,
        jobject activityObject) {
    jstring str = env->NewStringUTF(OBFUSCATE("Private Mod Menu | by Darkside"));
        return str;
    }

JNIEXPORT jobjectArray  JNICALL
Java_com_android_support_Loader_getFeatures(
    JNIEnv *env,
    jobject activityObject) {
    jobjectArray ret;
    const char *features[] = {          	   
            OBFUSCATE("Text_Chams Menu︎"), //0
            OBFUSCATE("ButtonOnOff_Default Chams"),//1
            OBFUSCATE("ButtonOnOff_Shading Chams"), //2
            OBFUSCATE("ButtonOnOff_Wireframe Chams"),//3
            OBFUSCATE("ButtonOnOff_Glow Chams"),//4
            OBFUSCATE("ButtonOnOff_Outline Chams"), //5
            OBFUSCATE("ButtonOnOff_Rainbow Chams"), //6         
            OBFUSCATE("SeekBar_Line Width_0_12"),//8
            OBFUSCATE("SeekBar_Color R_0_255"),//9
            OBFUSCATE("SeekBar_Color G_0_255"),//10
            OBFUSCATE("SeekBar_Color B_0_255"),//11
            OBFUSCATE("Hide_Icon invisible"),          
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
        chams = !chams;
        if (chams) {
        SetWallhack(true);
        } else {
        SetWallhack(false);
        }
        break;
        case 2:
        shading = !shading;
        if (shading) {
        SetWallhackS(true);
        } else {
        SetWallhackS(false);
        }
        break;
        case 3:
        wireframe = !wireframe;
        if (wireframe) {
        SetWallhackW(true);  
        } else {
        SetWallhackW(false);  
        }
        break;
        case 4:
        glow = !glow;
        if (glow) {
        SetWallhackG(true);
        } else {
        SetWallhackG(false);
        }
        break;
        case 5:
        outline = !outline;
        if (outline) {
        SetWallhackO(true); 
        } else {
        SetWallhackO(false);
        }
        break;
        case 6:
        rainbow = !rainbow;
        if (rainbow) {
        SetRainbow(true);
        } else {
        SetRainbow(false);
        }
        break;    
        case 8:
        SetW(value);
        break;      
        case 9:
        SetR(value);
        break;
        case 10:
        SetG(value);
        break;
        case 11:
        SetB(value);
        break; 
	}
}

// ---------- Hooking ---------- //

void *hack_thread(void *) {
    
    ProcMap il2cppMap;
    do {
        il2cppMap = KittyMemory::getLibraryMap(libName);
        sleep(1);
    } while (!il2cppMap.isValid() && mlovinit());
    setShader("_BumpMap");
    LogShaders();
    Wallhack();

	//Anti-lib rename :]
    do {
        sleep(1);
    } while (!isLibraryLoaded(OBFUSCATE("libDarkTeam.so")));
	
    return NULL;
}
    
JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *globalEnv;
    vm->GetEnv((void **) &globalEnv, JNI_VERSION_1_6);

    pthread_t ptid;
    pthread_create(&ptid, NULL, hack_thread, NULL);

    return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL
JNI_OnUnload(JavaVM *vm, void *reserved) {}
}
