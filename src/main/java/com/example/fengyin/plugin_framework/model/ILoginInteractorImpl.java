package com.example.fengyin.plugin_framework.model;

import android.content.Context;
import android.util.Log;

import com.category.base.net.IReponseListener;
import com.category.base.net.Params;
import com.category.base.net.RequestCallback;
import com.category.base.net.RequestManager;
import com.example.fengyin.plugin_framework.Result;
import com.example.fengyin.plugin_framework.Util;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by fengyin on 16-4-19.
 */
public class ILoginInteractorImpl implements ILoginInteractor<Result> {
    private Context mContext;
    public ILoginInteractorImpl(Context context){
        mContext = context;
    }

    @Override
    public void login(String email, String password, final RequestCallback<Result> callback) {
//        Request request = new Request(mContext);
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("account", email);
//        map.put("passwd", Util.getPswMD5Str("xthinkers" + password));
//        request.setMethod(com.android.volley.Request.Method.POST);
//        request.getReponseByPostMethod("http://tpos.yingzixia.com/p/org/orgLogin", new IReponseListener<JSONObject>() {
//            @Override
//            public void onSuccess(JSONObject obj) {
//                Log.i("Demo", "onSuccess:" + obj.toString());
//                callback.requestSuccess(obj);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                callback.requestError(msg);
//            }
//
//            @Override
//            public void beforeRequest() {
//                callback.beforeRequest();
//            }
//
//            @Override
//            public void afterRequest() {
//                callback.requestComplete();
//            }
//        });
//        });
        Params emailParams = new Params("account", email);
        Params passwordParams = new Params("passwd", Util.getPswMD5Str("xthinkers" + password));
        RequestManager.getInstance().getResponseByPostMethod("http://tpos.yingzixia.com/p/org/orgLogin", new IReponseListener<Result>() {
            @Override
            public void onSuccess(Result obj) {
                Log.i("Demo", "onSuccess:" + obj.toString());
                callback.requestSuccess(obj);
            }

            @Override
            public void onFail(String msg) {
                callback.requestError(msg);
            }

            @Override
            public void beforeRequest() {
                callback.beforeRequest();
            }

            @Override
            public void afterRequest() {
                callback.requestComplete();
            }
        }, Result.class, emailParams, passwordParams);
    }
}
