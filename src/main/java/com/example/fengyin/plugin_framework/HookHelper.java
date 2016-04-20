package com.example.fengyin.plugin_framework;

import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by fengyin on 16-4-11.
 */
public class HookHelper {

    public static void attachContext() throws Exception {
        //Get ActivityThread object by currentActivityThread.
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivity = currentActivityThreadMethod.invoke(null);

        //Get mInstrumentation field in ActivityThread.
        Field instrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        instrumentationField.setAccessible(true);
        Instrumentation instrumentation = (Instrumentation) instrumentationField.get(currentActivity);

        // Create proxy Object.
        Instrumentation proxyInstrumentation = new ProxyInstrumentation(instrumentation);

        // Using Proxy Object to replace origin object.
        instrumentationField.set(currentActivity, proxyInstrumentation);
    }
}
