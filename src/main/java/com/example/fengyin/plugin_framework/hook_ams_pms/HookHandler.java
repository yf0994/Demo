package com.example.fengyin.plugin_framework.hook_ams_pms;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by fengyin on 16-4-14.
 */
public class HookHandler implements InvocationHandler {

    private static final String TAG = "HookHandler";
    private Object mBase;
    public HookHandler(Object obj){
        mBase = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i(TAG, mBase.getClass().getName() + " method:" + method.getName());
        return method.invoke(mBase, args);
    }
}
