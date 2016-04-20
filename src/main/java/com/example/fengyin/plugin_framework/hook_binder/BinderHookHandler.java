package com.example.fengyin.plugin_framework.hook_binder;

import android.content.ClipData;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by fengyin on 16-4-12.
 */
public class BinderHookHandler implements InvocationHandler {
    private Object mBase;

    public BinderHookHandler(IBinder base, Class<?> stubClass){
        try {
            Method asInterfaceMethod = stubClass.getDeclaredMethod("asInterface", IBinder.class);
            mBase = asInterfaceMethod.invoke(null, base);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("getPrimaryClip".equals(method.getName())){
            Log.i("Demo", "hook getPrimaryClip");
            return ClipData.newPlainText(null, "you are hooked!");
        }

        if("hasPrimaryClip".equals(method.getName())){
            return true;
        }

        return method.invoke(mBase, args);
    }
}
