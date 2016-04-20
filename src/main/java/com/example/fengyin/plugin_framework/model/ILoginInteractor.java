package com.example.fengyin.plugin_framework.model;

import com.category.base.net.RequestCallback;

/**
 * Created by fengyin on 16-4-19.
 */
public interface ILoginInteractor<T> {
    public void login(String email, String password, RequestCallback<T> callback);
}
