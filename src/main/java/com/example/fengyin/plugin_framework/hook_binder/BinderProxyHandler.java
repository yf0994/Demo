package com.example.fengyin.plugin_framework.hook_binder;

import android.os.IBinder;
import android.os.IInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by fengyin on 16-4-12.
 */
public class BinderProxyHandler implements InvocationHandler {
    private IBinder mBase;

    private Class<?> mStub;
    private Class<?> mIInterface;

    public BinderProxyHandler(IBinder iBinder){
        mBase = iBinder;
        try {
            mStub = Class.forName("android.content.IClipboard$Stub");
            mIInterface = Class.forName("android.content.IClipboard");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("queryLocalInterface".equals(method.getName())){
            return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
                    new Class[] {IBinder.class, IInterface.class, this.mIInterface}, new BinderHookHandler(mBase, mStub));
        }
        return null;
    }
}
