package com.example.fengyin.plugin_framework;

import android.app.Application;
import android.content.Context;

import com.example.fengyin.plugin_framework.hook_ams_pms.*;
import com.example.fengyin.plugin_framework.hook_ams_pms.HookHelper;

/**
 * Created by fengyin on 16-4-11.
 */
public class AppApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        com.example.fengyin.plugin_framework.hook_ams_pms.HookHelper.hookActivityManager();
        HookHelper.hookPackageManager(base);
        super.attachBaseContext(base);
//        try {
//            HookHelper.attachContext();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
