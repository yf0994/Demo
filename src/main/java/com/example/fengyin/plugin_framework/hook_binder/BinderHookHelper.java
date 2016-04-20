package com.example.fengyin.plugin_framework.hook_binder;

import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by fengyin on 16-4-12.
 */
public class BinderHookHelper {
    public static void hookClipboardService() throws Exception{
        final String CLIPBOARD_SERVICE = "clipboard";

        //This code means: ServiceManager.getService("clipboard");
        Class<?> serviceManager = Class.forName("android.os.ServiceManager");
        Method getService = serviceManager.getDeclaredMethod("getService", String.class);

        //Get Clipboard Binder Object.
        IBinder rawBinder = (IBinder)getService.invoke(null, CLIPBOARD_SERVICE);

        //Hook queryLocalInterface of this Proxy Binder Object.
        IBinder hookedBinder = (IBinder) Proxy.newProxyInstance(serviceManager.getClassLoader(),
                new Class<?>[]{IBinder.class}, new BinderProxyHandler(rawBinder));

        //Put hookedBinder to sCache of ServiceManager.
        Field cacheField = serviceManager.getDeclaredField("sCache");
        cacheField.setAccessible(true);
        Map<String, IBinder> cache = (Map)cacheField.get(null);
        cache.put(CLIPBOARD_SERVICE, hookedBinder);
    }
}
