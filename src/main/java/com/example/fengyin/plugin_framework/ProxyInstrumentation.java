package com.example.fengyin.plugin_framework;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by fengyin on 16-4-11.
 */
public class ProxyInstrumentation extends Instrumentation {
    private Instrumentation mBase;
    public ProxyInstrumentation(Instrumentation instrumentation){
        mBase = instrumentation;
    }
    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) throws Exception{

        Log.i("Demo", "execStartActivity()");

        Method originMethod = Instrumentation.class.getDeclaredMethod("execStartActivity",
                Context.class, IBinder.class, IBinder.class, Activity.class,
                Intent.class, int.class, Bundle.class);
        originMethod.setAccessible(true);
        return (ActivityResult)originMethod.invoke(mBase, who, contextThread, token, target,
                intent, requestCode, options);
    }
}
